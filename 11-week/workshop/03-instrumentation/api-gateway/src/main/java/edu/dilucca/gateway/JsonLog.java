package edu.dilucca.gateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.LinkedHashMap;
import org.springframework.stereotype.Component;
@Component
public class JsonLog {
    private final ObjectMapper mapper = new ObjectMapper();
    public String encode(TraceContext ctx, String level, String event, String message, double ms, int status) {
        var row = new LinkedHashMap<String, Object>();
        row.put("timestamp", Instant.now().toString()); row.put("level", level);
        row.put("service", "api-gateway"); row.put("version", "0.1.0"); row.put("environment", "workshop");
        row.put("trace_id", ctx.traceId()); row.put("span_id", ctx.spanId());
        row.put("correlation_id", ctx.traceId()); row.put("event", event); row.put("message", message);
        row.put("duration_ms", Math.max(0, ms)); row.put("aggregate_type", "User");
        row.put("operation", "register_staff"); row.put("http_status", status);
        if (ctx.parentId() != null) row.put("parent_span_id", ctx.parentId());
        try { return mapper.writeValueAsString(row); }
        catch (Exception ex) { throw new IllegalStateException("Log serialization failed"); }
    }
    public void write(TraceContext ctx, String level, String event, String message, double ms, int status) {
        System.out.println(encode(ctx, level, event, message, ms, status));
    }
}
