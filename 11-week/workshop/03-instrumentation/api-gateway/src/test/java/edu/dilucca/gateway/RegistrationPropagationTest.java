package edu.dilucca.gateway;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RegistrationPropagationTest {
    private HttpServer auth;
    private java.util.concurrent.ExecutorService executor;
    private MockMvc gateway;
    private RegistrationMetrics metrics;
    private final AtomicReference<String> received = new AtomicReference<>();
    private final AtomicReference<String> idempotencyKey = new AtomicReference<>();
    private volatile int authStatus = 201;
    private volatile long delay;
    private static final String TRACE = "4bf92f3577b34da6a3ce929d0e0e4736";

    @BeforeEach void start() throws Exception {
        auth = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        executor = Executors.newCachedThreadPool();
        auth.setExecutor(executor);
        auth.createContext("/api/v1/auth/register", exchange -> {
            received.set(exchange.getRequestHeaders().getFirst("traceparent"));
            idempotencyKey.set(exchange.getRequestHeaders().getFirst("Idempotency-Key"));
            exchange.getRequestBody().readAllBytes();
            try {
                Thread.sleep(delay);
                byte[] body = "{}".getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(authStatus, body.length);
                exchange.getResponseBody().write(body);
            } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
            finally { exchange.close(); }
        });
        auth.start();
        metrics = new RegistrationMetrics();
        var log = new JsonLog();
        var controller = new RegistrationController("http://127.0.0.1:" + auth.getAddress().getPort(),
                500, log, metrics);
        gateway = MockMvcBuilders.standaloneSetup(controller).addFilters(new TraceFilter(log)).build();
    }

    @AfterEach void stop() {
        auth.stop(0);
        executor.shutdownNow();
    }

    @Test void preservesTraceAndKeyAcrossRealHttpCall() throws Exception {
        String incoming = "00-" + TRACE + "-00f067aa0ba902b7-00";
        var result = gateway.perform(post("/api/v1/auth/register").contentType("application/json")
                .content("{}").header("traceparent", incoming).header("Idempotency-Key", "synthetic-command-1"))
                .andExpect(status().isCreated()).andReturn();
        String returned = result.getResponse().getHeader("traceparent");
        assertEquals(TRACE, received.get().split("-")[1]);
        assertEquals(TRACE, returned.split("-")[1]);
        assertEquals("00", received.get().split("-")[3]);
        assertNotEquals(incoming.split("-")[2], returned.split("-")[2]);
        assertNotEquals(returned.split("-")[2], received.get().split("-")[2]);
        assertEquals("synthetic-command-1", idempotencyKey.get());
        gateway.perform(get("/metrics")).andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("outcome=\"success\"} 1")));
        assertTrue(metrics.scrape().contains("le=\"+Inf\"} 1"));
        assertTrue(metrics.scrape().contains("method=\"POST\"} 0"));
        System.out.println("PASS: incoming trace_id equals Auth HTTP trace_id; child span differs");
    }

    @Test void classifiesBusinessRejectionSeparately() throws Exception {
        authStatus = 409;
        gateway.perform(post("/api/v1/auth/register").contentType("application/json").content("{}"))
                .andExpect(status().isConflict());
        assertTrue(metrics.scrape().contains("outcome=\"rejected\"} 1"));
        assertTrue(metrics.scrape().contains("outcome=\"error\"} 0"));
    }

    @Test void timeoutBecomes503AndCountsOnce() throws Exception {
        delay = 900;
        gateway.perform(post("/api/v1/auth/register").contentType("application/json").content("{}"))
                .andExpect(status().isServiceUnavailable());
        assertTrue(metrics.scrape().contains("outcome=\"error\"} 1"));
        assertTrue(metrics.scrape().contains("le=\"+Inf\"} 1"));
        assertFalse(metrics.scrape().contains(TRACE));
    }

    @Test void metricsScrapesDoNotCountAsRegistrations() throws Exception {
        gateway.perform(get("/metrics")).andExpect(status().isOk());
        assertTrue(metrics.scrape().contains("le=\"+Inf\"} 0"));
    }

    @Test void histogramBucketsAreCumulativeAndUseSeconds() {
        var isolated = new RegistrationMetrics();
        isolated.start(); isolated.finish(201, 0.075);
        isolated.start(); isolated.finish(503, 2.5);
        String scrape = isolated.scrape();
        assertTrue(scrape.contains("le=\"0.05\"} 0"));
        assertTrue(scrape.contains("le=\"0.1\"} 1"));
        assertTrue(scrape.contains("le=\"3.0\"} 2"));
        assertTrue(scrape.contains("le=\"+Inf\"} 2"));
        assertTrue(scrape.contains("_sum{route=\"/api/v1/auth/register\",method=\"POST\"} 2.575000000"));
    }
}
