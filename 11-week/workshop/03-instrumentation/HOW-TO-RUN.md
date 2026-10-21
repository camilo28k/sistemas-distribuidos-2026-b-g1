# Run the api-gateway instrumentation module

Prerequisites: JDK 21, Maven 3.9.x and Python 3 for the optional manual stub.
From this directory, install dependencies, test and package:
```powershell
cd api-gateway
mvn test
mvn package
```
Maven downloads the dependencies declared in pom.xml. The automated tests start
an HTTP Auth stub on an ephemeral port and stop it afterwards. No database,
RabbitMQ, credentials or running team service is required.

## Manual execution

In terminal 1, from 03-instrumentation:
```powershell
python auth_stub.py
```
In terminal 2, from 03-instrumentation/api-gateway:
```powershell
java -jar target/api-gateway-0.1.0.jar
```
In terminal 3:
```powershell
$headers = @{
  traceparent = '00-4bf92f3577b34da6a3ce929d0e0e4736-00f067aa0ba902b7-01'
  'Idempotency-Key' = 'synthetic-registration-1'
}
Invoke-WebRequest -Uri http://127.0.0.1:8080/api/v1/auth/register -Method Post -ContentType application/json -Headers $headers -Body '{}'
(Invoke-WebRequest http://127.0.0.1:8080/metrics).Content
```
Use Ctrl+C in the two server terminals to stop them. For a rejection, restart
the stub with `python auth_stub.py --status 409`. For a timeout, restart with
`python auth_stub.py --delay 3`; the default gateway deadline is two seconds.
Override ports with `--port 18091` on the stub and
`--server.port=18080 --auth.base-url=http://127.0.0.1:18091` on the JAR.

## Scope and interpretation

This is a local api-gateway instrumentation slice using the team's Java/Spring
stack, not the team's production Gateway. It has no authentication enforcement
and must only receive synthetic inputs on loopback. It forwards to the synthetic
Auth stub, not a live IAM environment. The stub accepts `{}` deliberately and
does not implement registration validation, persistence or the full Staff schema.
No UserRegistered event or database write is produced by this exercise.

The filter accepts traceparent version 00, rejects malformed/zero IDs, creates a
server span, and returns traceparent. The HTTP client creates a distinct child
span and preserves the trace ID and flags. Tracestate, baggage, trace export and
asynchronous outbox instrumentation are outside this slice. Production adoption
requires the team's tracing SDK and authorization controls, not deploying this stub.

The counter measures completed registration controller calls, partitioned into
success (below 400), rejected (4xx) and error (5xx/transport failure). The histogram
measures that handler in seconds. In-flight is a gauge. Malformed requests rejected
before controller entry are outside these metrics; /metrics scrapes are excluded.
Metrics are process-local and reset on restart. Labels never contain IDs or bodies.
Application event lines use the CP2 JSON envelope; Maven/test infrastructure output
and the stub's transport diagnostic are not production application event logs.

## Actual execution evidence

Actual console output on 2026-10-20 at 20:06 America/Bogota:
```text
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
Total time: 23.910 s
```
An offline run initially failed because Surefire dependencies were missing.
Running Maven online downloaded them and all three tests passed.

Second increment, 2026-10-20 at 20:10 America/Bogota:
```text
PASS: incoming trace_id equals Auth HTTP trace_id; child span differs
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
Total time: 6.968 s
```
Coverage: incoming trace, missing/malformed/zero IDs, JSON envelope, actual
downstream HTTP propagation and idempotency key, 409 classification, timeout,
scrape exclusion, cumulative buckets and seconds. Test timeout is 500 ms and
the slow stub waits 900 ms; runtime defaults remain 2000 ms.

The packaged JAR was also started on port 18080 against the Python stub on 18091.
Actual response and selected scrape lines from this machine:
```text
HTTP 201
traceparent: 00-4bf92f3577b34da6a3ce929d0e0e4736-5f99d827a1cf42d0-01
{"status": "PENDING_VERIFICATION"}
dilucca_gateway_requests_total{route="/api/v1/auth/register",method="POST",outcome="success"} 1
dilucca_gateway_request_duration_seconds_count{route="/api/v1/auth/register",method="POST"} 1
dilucca_gateway_request_duration_seconds_sum{route="/api/v1/auth/register",method="POST"} 0.092324000
dilucca_gateway_in_flight_requests{route="/api/v1/auth/register",method="POST"} 0
PASS: packaged Spring Boot server -> Auth stub -> /metrics
PASS: two JSON gateway logs share trace_id and have distinct span_id values
```
The observed latency is one synthetic local request, not a performance claim.
