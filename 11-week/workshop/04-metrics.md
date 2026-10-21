# Golden signals and metric catalog: staff registration

## Scope and measurement boundary

The journey is HU-IAM-003: an ADMINISTRATOR submits a new staff account through
dlc-iam-portal, api-gateway and auth-service. Auth owns User, the PostgreSQL iam
schema and the UserRegistered outbox event published to RabbitMQ auth.events.
See [the journey](01-journey.md) and [execution evidence](03-instrumentation/HOW-TO-RUN.md).

Only the three gateway metric families below are implemented and exercised in
CP3. All Auth, client and dependency metrics are proposals, not live telemetry.
No Prometheus server or dashboard was deployed in this workshop; the PromQL is
a dashboard specification, not an executed query result.

Gateway measurements start when the registration controller is entered and end
when its call finishes. They exclude browser/network time, response delivery and
requests rejected before controller entry. /metrics is excluded. Production
availability measurement must additionally cover edge admission/routing failures.
Counters reset on process restart; rate() handles resets across valid scrapes.
Every query assumes the dashboard selects one environment and the intended job.

## api-gateway: implemented in CP3

| Signal | Metric | Type | Labels | Dashboard PromQL |
|---|---|---|---|---|
| Latency | dilucca_gateway_request_duration_seconds | histogram | route, method; generated le on buckets | `histogram_quantile(0.95, sum by (le) (rate(dilucca_gateway_request_duration_seconds_bucket[5m])))` |
| Traffic | dilucca_gateway_requests_total | counter | route, method, outcome | `sum(rate(dilucca_gateway_requests_total[5m]))` |
| Errors | dilucca_gateway_requests_total | counter, reused | route, method, outcome | `sum(rate(dilucca_gateway_requests_total{outcome="error"}[5m])) / sum(rate(dilucca_gateway_requests_total[5m]))` |
| Saturation | dilucca_gateway_in_flight_requests | gauge | route, method | `sum(dilucca_gateway_in_flight_requests)` |

Traffic is completed requests/second; multiply the error ratio by 100 for percent.
Outcome is success for status <400, rejected for 4xx, and error for 5xx or a
transport failure mapped to 503. A duplicate-staff 409 is rejected, not error.
Show rejected requests separately with `sum(rate(dilucca_gateway_requests_total{outcome="rejected"}[5m]))`.
Zero traffic makes the ratio undefined: display no traffic rather than 0% errors.
In-flight measures concurrent handler demand; it is a saturation proxy, not a
capacity percentage. A validated concurrency limit is needed before defining one.

## auth-service: proposed at the owner API boundary

| Signal | Metric | Type | Labels | Dashboard PromQL |
|---|---|---|---|---|
| Latency | dilucca_auth_request_duration_seconds | histogram | route, method; generated le | `histogram_quantile(0.95, sum by (le) (rate(dilucca_auth_request_duration_seconds_bucket[5m])))` |
| Traffic | dilucca_auth_requests_total | counter | route, method, outcome | `sum(rate(dilucca_auth_requests_total[5m]))` |
| Errors | dilucca_auth_requests_total | counter, reused | route, method, outcome | `sum(rate(dilucca_auth_requests_total{outcome="error"}[5m])) / sum(rate(dilucca_auth_requests_total[5m]))` |
| Saturation | dilucca_auth_db_connections | gauge | pool, state | `sum(dilucca_auth_db_connections{pool="iam",state="active"}) / sum(dilucca_auth_db_connections{pool="iam",state="max"})` |

The proposed Auth timer includes authorization, staff validation, hashing and the
User/outbox transaction, ending when the owner response is ready. Use the same
outcome categories as Gateway, but count each service's own observation. Auth
could finish with 409 while Gateway times out waiting for that response, as in CP2.
Never add both services' request counters to count distinct user registrations.
The connection gauge must report active and configured maximum connections from
the Auth-owned pool, not the entire shared PostgreSQL server. A zero/unknown max
means unavailable capacity data, not healthy saturation.

## Proposed dependency and client diagnostics

These are additional Auth/client instrumentation points, not invented services
or claims that RabbitMQ exposes these application-specific metric names.

| Boundary / signal | Metric | Type | Labels | Dashboard PromQL |
|---|---|---|---|---|
| iam transaction latency | dilucca_auth_db_operation_duration_seconds | histogram | operation; generated le | `histogram_quantile(0.95, sum by (le) (rate(dilucca_auth_db_operation_duration_seconds_bucket{operation="register_staff"}[5m])))` |
| iam transaction traffic | dilucca_auth_db_operations_total | counter | operation, outcome | `sum(rate(dilucca_auth_db_operations_total{operation="register_staff"}[5m]))` |
| iam transaction errors | dilucca_auth_db_operations_total | counter, reused | operation, outcome | `sum(rate(dilucca_auth_db_operations_total{operation="register_staff",outcome="error"}[5m]))` |
| iam pool saturation | dilucca_auth_db_connections | gauge, reused | pool, state | `sum(dilucca_auth_db_connections{pool="iam",state="active"}) / sum(dilucca_auth_db_connections{pool="iam",state="max"})` |
| RabbitMQ publish-confirm latency | dilucca_auth_publish_duration_seconds | histogram | event; generated le | `histogram_quantile(0.95, sum by (le) (rate(dilucca_auth_publish_duration_seconds_bucket{event="UserRegistered"}[5m])))` |
| RabbitMQ publication traffic | dilucca_auth_publish_attempts_total | counter | event, outcome | `sum(rate(dilucca_auth_publish_attempts_total{event="UserRegistered"}[5m]))` |
| RabbitMQ publication errors | dilucca_auth_publish_attempts_total | counter, reused | event, outcome | `sum(rate(dilucca_auth_publish_attempts_total{event="UserRegistered",outcome="error"}[5m]))` |
| Outbox backlog pressure | dilucca_auth_outbox_pending_events | gauge | event | `sum(dilucca_auth_outbox_pending_events{event="UserRegistered"})` |
| Browser-perceived latency | dilucca_iam_registration_duration_seconds | histogram | operation; generated le | `histogram_quantile(0.95, sum by (le) (rate(dilucca_iam_registration_duration_seconds_bucket[5m])))` |

DB counters observe each transaction attempt; a duplicate rejected by a uniqueness
constraint is rejected, while a connectivity failure is error. Publication attempts
include retries and count success only after broker confirmation, not user accounts
created. Outbox pending counts committed events not yet confirmed by the broker;
it measures queue pressure, not RabbitMQ memory saturation or a delivery guarantee.
Export the pending count once per shared outbox, not identically from every replica.
The browser timer would measure submit-to-visible-result through a controlled
telemetry collector. A browser is not a public Prometheus scrape endpoint.

## Label bounds and series budget

| Label | Allowed values in this journey | Cardinality | Replacement for unbounded data |
|---|---|---:|---|
| route | /api/v1/auth/register | 1 | Normalized route template, never raw URL/query |
| method | POST | 1 | Fixed operation method, not arbitrary input |
| outcome for HTTP/DB | success, rejected, error | 3 | Bounded category, never exception message or status text |
| outcome for publish | success, error | 2 | Broker-confirm result category |
| pool | iam | 1 | Logical pool name, never connection string |
| state | active, max | 2 | Defined pool measurements |
| operation | register_staff | 1 | Stable operation, never SQL text or user identifier |
| event | UserRegistered | 1 | Event type, never message/event instance ID |
| le | Eight finite boundaries plus +Inf | 9 | Histogram-generated bounds, never request latency as a label |

Each histogram has 11 series per label combination: 9 buckets + sum + count.
Gateway therefore exports 3 counter + 11 histogram + 1 gauge = **15 series per
target**. Auth HTTP plus its pool needs 3 + 11 + 2 = **16**. Additional DB metrics
need 11 + 3 = **14** (pool already counted); publisher/outbox needs 11 + 2 + 1 =
**14**; the optional browser histogram needs **11** per collector label set.

Deployment labels also multiply series. Proposed bounds are environment={dev,qa,prod}
(3), component-specific job (1 per target class), and at most 2 active instances
per class: Gateway's planning ceiling is 15 x 3 x 2 = **90 active series**.
These are planning assumptions, not measured fleet sizes; ephemeral instance churn
also increases stored historical series and must be reviewed with retention.
Never label metrics with user_id, patient_id, session_id, email, trace_id, span_id,
idempotency key, raw URL or exception text. Use bounded labels above; investigate
individual requests through the CP2 trace_id in access-controlled logs.

## Histogram buckets and user expectations

Use seconds: **0.05, 0.1, 0.25, 0.5, 1, 2, 3, 5, +Inf**, exactly matching CP3.
The proposal is that clinic administrators normally see a registration result
within 1 second; this is an initial UX target, not a measured SLO or an accepted
production commitment. Buckets below 1 second distinguish fast operations from
growing delay. The 2-second boundary matches the workshop Gateway timeout; 3 and
5 seconds expose slow Auth or asynchronous operations even when Gateway has failed.
+Inf retains every observation, including exceptional stalls above 5 seconds.

Use these initial bounds for all proposed timers to make comparison possible,
then tune DB/publish bounds after representative measurements. Cumulative buckets
must not be added together; quantile queries sum rates by le across instances.
Do not average instance p95 values. Registration traffic may be sparse: a 5-minute
p95 can be unstable or absent, so show count alongside it and use a longer window
when explaining historical behavior. A single local 92-ms stub request is not a
latency baseline for the real IAM API, password hashing or PostgreSQL transaction.

Naming follows [Prometheus conventions](https://prometheus.io/docs/practices/naming/):
snake_case, seconds for duration, _total for counters, and bounded label values.
