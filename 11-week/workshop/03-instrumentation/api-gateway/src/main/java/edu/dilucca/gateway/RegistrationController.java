package edu.dilucca.gateway;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    private final URI endpoint;
    private final Duration timeout;
    private final JsonLog log;
    private final RegistrationMetrics metrics;
    private final HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2)).build();

    public RegistrationController(@Value("${auth.base-url:http://127.0.0.1:8091}") String baseUrl,
            @Value("${auth.timeout-ms:2000}") long timeoutMs, JsonLog log, RegistrationMetrics metrics) {
        this.endpoint = URI.create(baseUrl + "/api/v1/auth/register");
        this.timeout = Duration.ofMillis(timeoutMs);
        this.log = log;
        this.metrics = metrics;
    }

    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<String> register(@RequestBody String body, HttpServletRequest request) {
        var ctx = ((TraceContext) request.getAttribute("traceContext")).child();
        var outgoing = HttpRequest.newBuilder(endpoint).timeout(timeout)
                .header("Content-Type", "application/json").header("traceparent", ctx.header());
        String key = request.getHeader("Idempotency-Key");
        if (key != null) outgoing.header("Idempotency-Key", key);
        long started = System.nanoTime();
        int status = 503;
        metrics.start();
        try {
            var response = client.send(outgoing.POST(HttpRequest.BodyPublishers.ofString(body)).build(),
                    HttpResponse.BodyHandlers.ofString());
            status = response.statusCode();
            log.write(ctx, status >= 500 ? "ERROR" : status >= 400 ? "WARN" : "INFO",
                    "auth.registration.completed", "Auth request completed", elapsed(started) * 1000, status);
            return ResponseEntity.status(status).header("Content-Type", "application/json").body(response.body());
        } catch (HttpTimeoutException ex) {
            log.write(ctx, "ERROR", "auth.registration.timeout", "Auth response deadline exceeded",
                    elapsed(started) * 1000, 503);
        } catch (IOException ex) {
            log.write(ctx, "ERROR", "auth.registration.unavailable", "Auth transport unavailable",
                    elapsed(started) * 1000, 503);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            log.write(ctx, "ERROR", "auth.registration.interrupted", "Auth request interrupted",
                    elapsed(started) * 1000, 503);
        } finally {
            metrics.finish(status, elapsed(started));
        }
        return ResponseEntity.status(503).body("{\"error\":\"AUTH_UNAVAILABLE\"}");
    }

    @GetMapping(value = "/metrics", produces = "text/plain;version=0.0.4;charset=utf-8")
    public String metrics() { return metrics.scrape(); }

    private static double elapsed(long started) { return (System.nanoTime() - started) / 1e9; }
}
