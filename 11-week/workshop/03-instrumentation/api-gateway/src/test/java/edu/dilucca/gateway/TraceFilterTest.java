package edu.dilucca.gateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;
import static org.junit.jupiter.api.Assertions.*;
class TraceFilterTest {
    static final String TRACE = "4bf92f3577b34da6a3ce929d0e0e4736";
    @Test void acceptsParentAndReturnsNewServerSpan() throws Exception {
        var request = new MockHttpServletRequest("POST", "/api/v1/auth/register");
        request.addHeader("traceparent", "00-" + TRACE + "-00f067aa0ba902b7-00");
        var response = new MockHttpServletResponse();
        new TraceFilter(new JsonLog()).doFilter(request, response, (req, res) -> {});
        var ctx = (TraceContext) request.getAttribute("traceContext");
        assertEquals(TRACE, ctx.traceId()); assertEquals("00", ctx.flags());
        assertNotEquals("00f067aa0ba902b7", ctx.spanId());
        assertEquals(ctx.header(), response.getHeader("traceparent"));
    }
    @Test void replacesMissingMalformedAndZeroContexts() {
        for (String input : new String[]{null, "invalid", "00-" + "0".repeat(32) + "-00f067aa0ba902b7-01",
                "00-" + TRACE + "-" + "0".repeat(16) + "-01"}) {
            var ctx = TraceContext.receive(input);
            assertTrue(ctx.traceId().matches("[0-9a-f]{32}"));
            assertNotEquals("0".repeat(32), ctx.traceId()); assertNull(ctx.parentId());
        }
    }
    @Test void loggerInjectsRequiredContextAndDomain() throws Exception {
        var ctx = TraceContext.receive(null);
        String json = new JsonLog().encode(ctx, "INFO", "staff.registration.completed", "Completed", 2, 201);
        var row = new ObjectMapper().readTree(json);
        for (String field : "timestamp level service version environment trace_id span_id correlation_id event message duration_ms aggregate_type operation".split(" "))
            assertTrue(row.has(field), field);
        assertEquals(ctx.traceId(), row.get("trace_id").asText());
        assertEquals("User", row.get("aggregate_type").asText());
    }
}
