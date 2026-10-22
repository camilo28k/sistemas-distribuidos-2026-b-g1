# SLIs, SLOs and error budgets: staff registration

## Scope and initial targets

HU-IAM-003 creates DENTIST and SECRETARY_ASSISTANT accounts through api-gateway
and auth-service. Auth owns User, the PostgreSQL iam schema and UserRegistered.
The bootstrap ADMINISTRATOR is outside POST /api/v1/auth/register.
These are proposed objectives for this journey, not achieved production results.

The [project NFRs](https://github.com/code-corhuila/dlc-docs/blob/71a89f6/04-requirements/non-functional.md)
propose 99.9% production availability. CP4 proposes a one-second registration UX
target; registration is not one of the NFR's explicitly listed 300-ms endpoints.
The publication objective below is an additional workshop proposal for the Auth
outbox, not an approved delivery guarantee or an email-success metric.

All objectives use a rolling **30-day** window, including maintenance. Evaluate
one environment at a time. The queries use proposed scrape labels
`environment="prod"` and `job="api-gateway"` or `job="auth-service"`.
These labels must be configured on scrape targets; CP3 does not emit them itself.
No production scraper, 30-day dataset or Prometheus query execution is claimed.

## Three objectives

| Objective | Valid events | Good events / SLI formula | Target and window |
|---|---|---|---|
| Registration availability | Completed registration controller calls observed by Gateway | Calls classified success or rejected / all completed calls | >=99.9% over rolling 30 days |
| Registration latency | All observations in the Gateway registration histogram | Calls completing in <=1 second / all observed calls | >=95% over rolling 30 days |
| UserRegistered publication reliability | Completed Auth publication attempts for UserRegistered | Attempts with broker confirmation / all completed publication attempts | >=99.9% over rolling 30 days |

Availability counts a legitimate 4xx rejection as a handled request, not a
technical outage. This does not prove that every 4xx is correct: domain acceptance
tests and rejection monitoring are still required. Gateway timeouts become 503
and count as errors even if Auth subsequently commits the User transaction.
Latency includes fast failures; meeting latency alone never establishes availability.
The implemented latency boundary excludes browser time and response delivery.

The publication SLI measures attempts, including retries, not unique events or
eventual delivery to a consumer. A broker confirmation does not prove consumption.
Its CP4 metric is proposed and must be implemented before evaluating this SLO.
The outbox pending gauge remains a companion diagnostic: unattempted events do
not enter the attempt denominator, so this SLO alone cannot detect a stuck publisher.

### Availability PromQL

```promql
sum(increase(dilucca_gateway_requests_total{environment="prod",job="api-gateway",route="/api/v1/auth/register",method="POST",outcome=~"success|rejected"}[30d]))
/
sum(increase(dilucca_gateway_requests_total{environment="prod",job="api-gateway",route="/api/v1/auth/register",method="POST"}[30d]))
```

### Latency PromQL

```promql
sum(increase(dilucca_gateway_request_duration_seconds_bucket{environment="prod",job="api-gateway",route="/api/v1/auth/register",method="POST",le="1.0"}[30d]))
/
sum(increase(dilucca_gateway_request_duration_seconds_count{environment="prod",job="api-gateway",route="/api/v1/auth/register",method="POST"}[30d]))
```

`le="1.0"` matches the actual CP3 exporter. This is a good-event fraction, not p95
divided by a threshold. Sum counter increases across replicas, not their ratios.

### Publication PromQL

```promql
sum(increase(dilucca_auth_publish_attempts_total{environment="prod",job="auth-service",event="UserRegistered",outcome="success"}[30d]))
/
sum(increase(dilucca_auth_publish_attempts_total{environment="prod",job="auth-service",event="UserRegistered"}[30d]))
```

Initialize bounded outcome counters to zero. Zero traffic is undefined/no data,
not 100% success. Missing scrapes are unknown, not evidence of zero failures.
Prometheus increase() extrapolates between samples and can return fractional
counts; for this sparse journey, reconcile results with the actual request log.
The Gateway counters exclude pre-controller failures, so they are a partial
availability SLI until edge admission/routing failures are measured too.

## User-supplied traffic estimate and budgets

The owner estimates 2-3 assistants, 2-3 dentists and one administrator: 5-7 staff
in total. Only the 4-6 assistants/dentists use ordinary staff registration.
Use **6 registration attempts in an initial 30-day onboarding window**, assuming
one successful attempt per new account, as an upper-count planning scenario.
This is not evidence of six new hires every month. Later monthly demand and
working hours were not supplied; do not infer a recurring arrival rate or roster.

With no rejection, failure or retry, six registrations also produce six
UserRegistered publication attempts. Actual retries change that denominator;
recompute the publication budget from actual attempts rather than account count.

For each SLO: allowed bad events = (1 - target) x valid events.
For comparison only: calendar-equivalent minutes = (1 - target) x 30 x 24 x 60.

| SLO | Allowed bad fraction | Budget for 6 observations | Calendar-equivalent minutes |
|---|---:|---:|---:|
| Availability, 99.9% | 0.001 | 0.006 failed registration calls | 43.2 |
| Latency, 95% within 1 s | 0.05 | 0.3 slow registration calls | 2,160 |
| Publication, 99.9% | 0.001 | 0.006 failed publication attempts | 43.2 |

The minutes column is a normalization under hypothetical uniform activity over
43,200 minutes, not measured downtime or an allowance to stop the clinic.
Latency's minutes are slow-response-equivalent minutes, not outage minutes.
Real registration traffic is sparse and clustered: a long idle outage may affect
no attempts, while a brief outage may affect the only registration of the month.

For six observations, all three budgets permit **zero whole bad events**. One
failure yields availability 5/6 = 83.333%, consuming 1/0.006 = 166.667 times the
availability budget (16,666.7%). One slow call consumes 1/0.3 = 3.333 times the
latency budget (333.3%). Do not round a fractional budget up to one allowed failure.
This makes the proposed objectives stringent and statistically coarse; validate
them with the team rather than inflating staff traffic to make the arithmetic easy.

## Proposed error-budget policy

Compute consumption per SLO as bad events / [(1 - target) x actual valid events].
Do not add budgets across objectives or replace missing data with success.

| Consumed | Registration reliability action |
|---|---|
| >=50% | IAM owner reviews trace_id evidence, Auth timing and iam pool pressure; prioritize corrective work. Pause non-critical feature deployments if this occurs in the first half of the window, consistent with NFR-002. |
| >=75% | Freeze non-critical changes affecting Gateway/Auth registration; require a reviewed recovery plan and passing regression tests before further rollout. |
| >=100% | Stop feature releases affecting the journey; allow reviewed security/recovery fixes only. Reconcile uncertain User creation and outbox state before retrying commands with the same idempotency key. |

At six valid calls the availability thresholds are 0.003, 0.0045 and 0.006 bad
calls: one actual failure crosses all levels, so apply the highest immediately.
The IAM owner coordinates recovery with the Gateway and infrastructure maintainers.
Resume normal releases only after corrective tests pass, the SLI is within target
and budget is available in the rolling window, or a documented team risk decision
explicitly changes the policy. A fix does not reset the rolling error budget.

Short-window burn-rate alerts must account for sparse traffic; exact detection
times cannot be inferred from staff count alone. Any controlled load used in a
later incident drill must be identified as synthetic, not a production forecast.
