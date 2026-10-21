package edu.dilucca.gateway;
import java.security.SecureRandom;
import java.util.HexFormat;
public record TraceContext(String traceId, String spanId, String parentId, String flags) {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static String id(int size) {
        byte[] bytes = new byte[size];
        do { RANDOM.nextBytes(bytes); } while (java.util.Arrays.equals(bytes, new byte[size]));
        return HexFormat.of().formatHex(bytes);
    }
    public static TraceContext receive(String header) {
        if (header != null && header.matches("00-[0-9a-f]{32}-[0-9a-f]{16}-[0-9a-f]{2}")) {
            String[] parts = header.split("-");
            if (!parts[1].equals("0".repeat(32)) && !parts[2].equals("0".repeat(16)))
                return new TraceContext(parts[1], id(8), parts[2], parts[3]);
        }
        return new TraceContext(id(16), id(8), null, "01");
    }
    public TraceContext child() { return new TraceContext(traceId, id(8), spanId, flags); }
    public String header() { return "00-" + traceId + "-" + spanId + "-" + flags; }
}
