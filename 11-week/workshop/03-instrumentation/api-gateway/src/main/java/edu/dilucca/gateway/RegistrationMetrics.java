package edu.dilucca.gateway;

import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMetrics {
    private static final double[] LIMITS = {0.05, 0.1, 0.25, 0.5, 1, 2, 3, 5};
    private static final String LABELS = "route=\"/api/v1/auth/register\",method=\"POST\"";
    private final long[] buckets = new long[LIMITS.length];
    private final long[] outcomes = new long[3];
    private long count;
    private int active;
    private double sum;

    public synchronized void start() { active++; }

    public synchronized void finish(int status, double seconds) {
        active--;
        count++;
        sum += seconds;
        outcomes[status >= 500 ? 2 : status >= 400 ? 1 : 0]++;
        for (int i = 0; i < LIMITS.length; i++) if (seconds <= LIMITS[i]) buckets[i]++;
    }

    public synchronized String scrape() {
        var text = new StringBuilder("# HELP dilucca_gateway_requests_total Completed registration requests\n")
                .append("# TYPE dilucca_gateway_requests_total counter\n");
        String[] names = {"success", "rejected", "error"};
        for (int i = 0; i < names.length; i++)
            text.append("dilucca_gateway_requests_total{").append(LABELS).append(",outcome=\"")
                    .append(names[i]).append("\"} ").append(outcomes[i]).append('\n');
        String metric = "dilucca_gateway_request_duration_seconds";
        text.append("# HELP ").append(metric).append(" Registration handler latency in seconds\n");
        text.append("# TYPE ").append(metric).append(" histogram\n");
        for (int i = 0; i < LIMITS.length; i++)
            text.append(metric).append("_bucket{").append(LABELS).append(",le=\"")
                    .append(LIMITS[i]).append("\"} ").append(buckets[i]).append('\n');
        text.append(metric).append("_bucket{").append(LABELS).append(",le=\"+Inf\"} ").append(count).append('\n');
        text.append(metric).append("_count{").append(LABELS).append("} ").append(count).append('\n');
        text.append(metric).append("_sum{").append(LABELS).append("} ")
                .append(String.format(Locale.ROOT, "%.9f", sum)).append('\n');
        text.append("# HELP dilucca_gateway_in_flight_requests Active registration handlers\n")
                .append("# TYPE dilucca_gateway_in_flight_requests gauge\n")
                .append("dilucca_gateway_in_flight_requests{").append(LABELS).append("} ").append(active).append('\n');
        return text.toString();
    }
}
