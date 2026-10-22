# Tabletop incident and blameless postmortem: slow staff registration

## Exercise boundary and assumptions

This is an analytical drill, not a report of a real outage, measured load test
or executed Prometheus alert. The affected CP1 hop is api-gateway -> auth-service
for POST /api/v1/auth/register. Auth owns User, PostgreSQL iam and UserRegistered.
For 40 minutes, 25% of Auth responses take 3 seconds; the remaining 75% complete
in 0.2 seconds. The Gateway retains its CP3 two-second timeout, so the slow calls
produce 503 and exceed the one-second latency target. No business rejections occur.

Use a controlled analytical stream of **4 completed Gateway calls/minute**, with
one error/minute during the incident and zero errors otherwise. This is synthetic
traffic, not the CP6 forecast of 4-6 initial staff accounts. Assume the same rate
throughout a complete 30-day baseline, including at least six healthy hours before
the incident. Fixture series use the CP7 prod/job labels in an isolated model;
no traffic is sent to production and no real staff accounts are created.

Time t=0 is the start of affected Gateway completions. Ratios below use ideal
constant-rate windows, evaluated at integer minutes exactly as CP7's 1m cadence.
Actual scrape phase, extrapolation and request timing may shift a live firing time.
There are no resets, missing samples, notification delays or extra `for` duration.

The assumed injected cause is a response-path delay after Auth commits: a test
configuration delays 25% of responses. User and outbox commit correctly, and
broker confirmation succeeds. This is a scenario assumption, not a diagnosed
production cause or evidence from the CP3 stub, which delays all requests when
started with --delay. The full 40-minute pattern is retained for the calculation.

## Trace of one slow request

All spans share trace_id 4bf92f3577b34da6a3ce929d0e0e4736. Times are relative to
the Gateway receiving this synthetic request. The trace illustrates the CP1 tree:

- Gateway SERVER span 1111111111111111: t=0..2005 ms, duration 2005 ms, response 503.
  - Auth HTTP CLIENT span 2222222222222222: t=1..2001 ms, duration 2000 ms, timeout.
    - Auth SERVER span 3333333333333333: t=4..3004 ms, duration 3000 ms; intended 201 arrives too late.
      - iam transaction span 4444444444444444: t=10..30 ms, duration 20 ms; User and outbox committed.
      - UserRegistered PRODUCER span 5555555555555555: t=35..45 ms, duration 10 ms; broker confirmation received.

The asynchronous producer uses saved Auth context, not a new trace. Auth work
can outlive the Gateway client span; a timeout does not cancel a committed
transaction. The delayed response does not imply that UserRegistered failed.

## Four illustrative CP2 log lines

These timestamps and values are synthetic, not captured execution evidence.
Only safe domain fields appear; no identity, credential or idempotency key is logged.

```jsonl
{"timestamp":"2026-10-21T15:00:00.030Z","level":"INFO","service":"auth-service","version":"0.1.0","environment":"workshop","trace_id":"4bf92f3577b34da6a3ce929d0e0e4736","span_id":"3333333333333333","parent_span_id":"2222222222222222","correlation_id":"4bf92f3577b34da6a3ce929d0e0e4736","event":"staff.registration.committed","message":"Staff registration transaction committed","duration_ms":26,"aggregate_type":"User","operation":"register_staff","account_state":"PENDING_VERIFICATION","domain_event":"UserRegistered"}
{"timestamp":"2026-10-21T15:00:02.001Z","level":"ERROR","service":"api-gateway","version":"0.1.0","environment":"workshop","trace_id":"4bf92f3577b34da6a3ce929d0e0e4736","span_id":"2222222222222222","parent_span_id":"1111111111111111","correlation_id":"4bf92f3577b34da6a3ce929d0e0e4736","event":"auth.registration.timeout","message":"Auth response deadline exceeded","duration_ms":2000,"aggregate_type":"User","operation":"register_staff","http_status":503}
{"timestamp":"2026-10-21T15:00:02.005Z","level":"ERROR","service":"api-gateway","version":"0.1.0","environment":"workshop","trace_id":"4bf92f3577b34da6a3ce929d0e0e4736","span_id":"1111111111111111","correlation_id":"4bf92f3577b34da6a3ce929d0e0e4736","event":"staff.registration.completed","message":"Staff registration request completed","duration_ms":2005,"aggregate_type":"User","operation":"register_staff","http_status":503}
{"timestamp":"2026-10-21T15:00:03.004Z","level":"INFO","service":"auth-service","version":"0.1.0","environment":"workshop","trace_id":"4bf92f3577b34da6a3ce929d0e0e4736","span_id":"3333333333333333","parent_span_id":"2222222222222222","correlation_id":"4bf92f3577b34da6a3ce929d0e0e4736","event":"staff.registration.completed","message":"Auth registration processing completed","duration_ms":3000,"aggregate_type":"User","operation":"register_staff","account_state":"PENDING_VERIFICATION","http_status":201}
```

## SLI values and budget impact

During the incident: 4 x 40 = **160 calls**, 40 technical failures and 120 timely
successes. Availability = 120/160 = **75%**; latency <=1 second = 120/160 = **75%**.
Publication success = 160/160 = **100%**, under the explicit no-retry, confirmed-
publication assumption. That last value is not inferred from the Gateway counter.

The complete synthetic 30-day denominator is 4 x 43,200 = **172,800 calls**.
Availability and latency over that window are 172,760/172,800 = **99.97685%**;
both monthly objectives still hold even though the incident-window SLIs are poor.
Publication remains 100% under this scenario. Do not compare the 40-minute 75%
directly with a claimed measured 30-day compliance result without stating windows.

| Budget | Synthetic 30-day allowance | Consumed by incident | Fraction consumed |
|---|---:|---:|---:|
| Availability, 99.9% | 172.8 failed calls | 40 | 23.14815% |
| Latency, 95% | 8,640 slow calls | 40 | 0.46296% |
| Publication, 99.9% | 172.8 failed attempts | 0 | 0% |

At four calls/minute, 40 bad calls equal **10 full-outage-equivalent minutes**
(25% x 40 minutes), not 40 minutes of complete outage. This consumes 10/43.2 =
23.14815% of the availability minute equivalent; the latency equivalent is
10/2,160 = 0.46296%. Actual affected users in this tabletop exercise: **zero**.
These synthetic budgets do not replace CP6's 0.006-failure initial-staff budget.

## Which alert fires first?

Before minute 40, error fraction in a window W minutes is 0.25 x min(t,W) / W.
Burn rate divides that fraction by 0.001. Both CP7 comparisons are strict `>`.

| Evaluation | Long-window error ratio | Short-window error ratio | Result |
|---|---:|---:|---|
| Fast, minute 3 | 0.25 x 3/60 = 1.25% | 0.25 x 3/5 = 15% | Long window below 1.44%; no alert |
| Fast, minute 4 | 0.25 x 4/60 = 1.66667% | 0.25 x 4/5 = 20% | Both exceed 1.44%; fast alert fires first |
| Slow, minute 8 | 0.25 x 8/360 = 0.55556% | 0.25 x 8/30 = 6.66667% | Long window below 0.6%; no alert |
| Slow, minute 9 | 0.25 x 9/360 = 0.625% | 0.25 x 9/30 = 7.5% | Both exceed 0.6%; slow alert also fires |

The continuous crossing times are t>3.456 and t>8.64 minutes, respectively;
the one-minute evaluation grid gives 4 and 9. At minute 4, 4 synthetic failures
have consumed 4/172.8 = 2.31481% of the availability budget, not exactly 2%.

## Blameless timeline and recovery

| Relative time | Model event / response |
|---|---|
| Before 0 | Healthy baseline; known-good response configuration identified for the tabletop rollback. |
| 0 | Begin the specified delayed-response pattern; some committed registrations appear failed to the caller. |
| 4 | Fast alert fires; IAM owner would correlate Gateway/Auth spans and reconcile uncertain User outcomes. |
| 9 | Slow alert also fires. Route both to the same team; CP7 proposes inhibition but does not configure it. |
| 40 | End the injected delay by reverting its test configuration: CP8 mitigation 2 stops additional failures. The exercise intentionally retains the full required 40 minutes rather than claiming an earlier fix. |
| 45 | Five healthy minutes with traffic; fast alert clears because its 5m ratio is zero, despite elevated 1h history. |
| 70 | Slow alert clears when its 30m window contains only healthy traffic. Historical budget consumption remains. |

In a real response, CP8 mitigation 1 would coordinate a pause in new submissions
and prevent blind retries; that would change traffic and this fixed-load model.
The model does not claim that this pause occurred while four calls/minute continued.
After mitigation 2, verify the CP8 trace/201/latency checks and reconcile committed
User/outbox state before resuming real registration. Never delete an account or
repeat a command with a new idempotency key merely because Gateway returned 503.

Assumed root cause: an injected response-delay configuration after the Auth
transaction, not slow PostgreSQL or a RabbitMQ failure. Contributing factors are
the shorter Gateway deadline, lack of cancellation of committed work, and an
uncertain outcome visible to the administrator. The response focuses on improving
the system and validation process, not attributing fault to a team member.

## Follow-up stories with verifiable acceptance criteria

1. **Registration outcome reconciliation (IAM/Gateway):** As an administrator,
   I need a safe way to resolve an uncertain registration. Acceptance: a delayed
   response followed by retry with the original key creates exactly one User and
   one logical UserRegistered fact; conflicting key reuse is rejected; no secret
   or raw key appears in logs. Verify against the real owner contract in QA.
2. **Controlled failure coverage (IAM):** As an IAM maintainer, I need tests of
   partial response delay. Acceptance: an isolated 160-request fixture delays
   exactly 40 responses to 3 seconds with a 2-second Gateway deadline, confirms
   120 successes/40 timeouts and checks trace linkage without real staff data.
3. **Alert-rule regression (infrastructure/IAM):** As an operator, I need validated
   alert timing. Acceptance: promtool checks CP7 rules and rule-test fixtures
   specify scrape/evaluation phase, reproduce modeled fast/slow detection near
   minutes 4/9, explain any sampling difference, and cover zero traffic and recovery.

Validation: arithmetic and ideal minute-grid threshold calculations were checked
locally; the four JSON logs were parsed and checked against CP2's required fields.
No live outage, 160-request load test, production rollback or promtool execution
is asserted by this tabletop document. The follow-up tests remain proposed work.
