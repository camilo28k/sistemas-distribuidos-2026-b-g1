package edu.dilucca.gateway;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
public class TraceFilter extends OncePerRequestFilter {
    private final JsonLog log;
    public TraceFilter(JsonLog log) { this.log = log; }
    @Override protected boolean shouldNotFilter(HttpServletRequest req) {
        return !req.getRequestURI().equals("/api/v1/auth/register") || !req.getMethod().equals("POST");
    }
    @Override protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        var ctx = TraceContext.receive(req.getHeader("traceparent"));
        req.setAttribute("traceContext", ctx); res.setHeader("traceparent", ctx.header());
        long started = System.nanoTime();
        try { chain.doFilter(req, res); }
        catch (ServletException | IOException | RuntimeException ex) { res.setStatus(500); throw ex; }
        finally {
            int status = res.getStatus();
            String level = status >= 500 ? "ERROR" : status >= 400 ? "WARN" : "INFO";
            log.write(ctx, level, "staff.registration.completed", "Staff registration request completed",
                    (System.nanoTime() - started) / 1e6, status);
        }
    }
}
