# Runbook: DiLuccaStaffRegistrationFastBurn

## Ownership and readiness

Journey: ADMINISTRATOR registers a DENTIST or SECRETARY_ASSISTANT through
api-gateway -> auth-service -> PostgreSQL iam; UserRegistered is published through
the Auth outbox. Primary owner: Harold Camilo Barrera Giraldo, transversal IAM.
This runbook also supports investigation of DiLuccaStaffRegistrationSlowBurn.

Status: workshop procedure with verified local instrumentation and proposed
production steps. The project targets Docker/AWS, but production resource names,
operator access, image revisions and rollback commands are not verified. Obtain
those from the environment maintainer before executing a production change.
No Kubernetes deployment, feature flag or circuit breaker is assumed to exist.

## Symptoms and immediate assessment

- Gateway returns 503 for POST /api/v1/auth/register, including Auth timeouts.
- Administrators report slow or uncertain staff registration. A 409 rejection
  alone is not this availability alert; inspect its domain reason separately.
- CP7 fast burn requires error ratios above 1.44% in both 1h and 5m windows.
- A timeout may precede a successful Auth commit. Treat the registration outcome
  as unknown until the owner checks authoritative state; never assume rollback.

Record incident start time in UTC, affected environment, alert name, first failed
trace_id, observed request count and recent Gateway/Auth changes. Notify the IAM
owner. Confirm that this is the expected environment, not a workshop stub.

## Confirm the alert and scope

Run both queries against the intended production scrape targets. Both results
must exceed 0.0144 to reproduce the fast-burn condition:

```promql
sum(rate(dilucca_gateway_requests_total{environment="prod",job="api-gateway",route="/api/v1/auth/register",method="POST",outcome="error"}[1h]))
/
sum(rate(dilucca_gateway_requests_total{environment="prod",job="api-gateway",route="/api/v1/auth/register",method="POST"}[1h]))
```

```promql
sum(rate(dilucca_gateway_requests_total{environment="prod",job="api-gateway",route="/api/v1/auth/register",method="POST",outcome="error"}[5m]))
/
sum(rate(dilucca_gateway_requests_total{environment="prod",job="api-gateway",route="/api/v1/auth/register",method="POST"}[5m]))
```

Check the sample volume as well:
```promql
sum(increase(dilucca_gateway_requests_total{environment="prod",job="api-gateway",route="/api/v1/auth/register",method="POST"}[5m]))
```
No data is unknown, not recovery. Check scrape health and counter resets before
interpreting ratios. With 4-6 onboarding accounts, a single failed call can
dominate the ratio; preserve the actual event count and CP6 budget calculation.

Export the relevant Gateway/Auth JSON event lines to an access-controlled file
named registration.jsonl. This PowerShell query uses CP2 fields; replace the
synthetic trace below with the failed request's trace_id:

```powershell
$incidentTrace = '4bf92f3577b34da6a3ce929d0e0e4736'
Get-Content -LiteralPath ./registration.jsonl |
  ForEach-Object { $_ | ConvertFrom-Json } |
  Where-Object { $_.trace_id -eq $incidentTrace } |
  Sort-Object timestamp |
  Select-Object timestamp,service,event,span_id,parent_span_id,duration_ms,http_status
```

Follow the Gateway server span into its Auth client span and the Auth server
span. Compare timeout, rejection and completion times; account for clock skew.
If Auth is slow, inspect its iam transaction and pool metrics when implemented.
If Auth committed, inspect the corresponding User/outbox state through approved
owner tools. Missing UserRegistered delivery is separate from HTTP availability.
Do not export passwords, tokens, staff email or clinical data into incident notes.

## First three mitigations, ordered by risk

| Order | Action and trigger | Execution boundary / exit condition |
|---|---|---|
| 1 - lowest risk | Ask the administrator to pause new registration submissions and repeated clicks while the IAM owner reconciles uncertain outcomes. Preserve the original command's idempotency key securely. | This is a manual coordination step, not a deployed flag. It limits duplicate-risk and additional exposure but does not repair availability or prove recovery. Keep existing staff sessions and other journeys operating if unaffected. |
| 2 - limited application change | If the timing correlates with a Gateway/Auth release or timeout/network configuration change, revert only the identified change to a verified previous working version/configuration. | Environment maintainer checks contract and database compatibility, captures the current revision and uses the repository's verified Docker/AWS procedure. Do not roll back the iam schema or invent an image tag. If no known-good revision or causal change exists, skip this action and escalate. |
| 3 - higher infrastructure risk | If evidence instead shows Auth resource or iam connection pressure, restore the affected connection/dependency or adjust Auth capacity within verified limits. | Infrastructure and IAM owners first check the shared PostgreSQL budget and other services. Increasing Auth replicas can worsen connection pressure. No database-wide restart, mass session termination or direct data edits as an improvised fix. |

After each action, record the change, owner, time and measured effect. Stop
escalating changes when recovery is demonstrated. Do not increase the two-second
workshop timeout merely to hide the symptom, bypass authorization or issue a new
idempotency key to retry a registration whose commit outcome is still unknown.

## Escalation

1. IAM maintainer Harold Camilo Barrera Giraldo leads trace review and User-state
   reconciliation; the administrator reports affected operations without secrets.
2. Involve the Gateway maintainer for routing/timeouts and the environment/PostgreSQL
   maintainer for dependency or pool evidence. Their names/contact channels must
   be confirmed from the current team roster; none are invented here.
3. Escalate immediately to the team technical lead for shared-database impact,
   a suspected security issue, no safe rollback, or an exhausted error budget.
   The lead coordinates other service owners and approves higher-risk changes.

## Verify recovery before resuming registration

- Reconcile every uncertain command through authorized Auth state: at most one
  User per intended registration and the appropriate committed outbox fact.
- In QA/local, repeat a controlled synthetic request and verify an expected 201,
  unchanged trace_id across the HTTP hop and latency <=1 second. In production,
  observe an authorized necessary registration; do not create fake staff accounts.
- Verify fresh telemetry and no new technical registration failures during at
  least five minutes with actual observations. With no traffic, report recovery
  as unverified rather than declaring the service healthy.
- Re-evaluate both CP7 windows. Fast burn clears when either required condition
  is false; the slow alert may remain until its 30-minute window improves.
  Historical budget remains consumed even after alerts clear.
- Resume submissions gradually with the administrator and review rejected/error
  outcomes. Record the recovery evidence and retain the CP6 release restrictions
  until its recovery criteria or documented risk-decision exception is satisfied.

## Local rehearsal supported by CP3

Use the commands in [HOW-TO-RUN](03-instrumentation/HOW-TO-RUN.md). Start the Python
Auth stub with `--delay 3` to exceed the Gateway's two-second deadline, submit one
synthetic request and inspect the resulting 503 and JSON trace. Stop only that
stub and restart without --delay; submit again and verify 201 and the metrics.
This models reverting a slow dependency configuration (mitigation 2), not a
production rollback. CP9 will specify the separate 25%-for-40-minutes scenario.
No live incident, deployment change or production recovery has been executed.
