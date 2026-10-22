# Burn-rate alert policy: Di Lucca staff registration

## Objective and scope

These rules use CP6's proposed 99.9% availability objective over 30 days for
POST /api/v1/auth/register through api-gateway and auth-service. Auth owns User,
PostgreSQL iam and the UserRegistered outbox. The alert does not claim that a
Gateway timeout rolled back a registration or that an event reached a consumer.
The numerator is outcome="error"; legitimate business rejections remain in the
denominator but not the numerator, matching the CP4/CP6 availability definition.

Burn rate = observed technical-error fraction / allowed bad fraction (0.001).
At rate 1, sustained uniform activity would consume the budget in 30 days.
The rules aggregate request counts across replicas before dividing; they never
average instance error percentages or count Auth and Gateway as separate users.

## Thresholds and budget interpretation

| Alert | Both windows must exceed | Error fraction threshold | Severity | Budget fraction represented by long window |
|---|---|---|---|---|
| DiLuccaStaffRegistrationFastBurn | Burn rate 14.4 over 1h AND 5m | 14.4 x 0.001 = 0.0144 (1.44%) | critical | 14.4 x 1 / 720 = 0.02 (2%) |
| DiLuccaStaffRegistrationSlowBurn | Burn rate 6 over 6h AND 30m | 6 x 0.001 = 0.006 (0.6%) | warning | 6 x 6 / 720 = 0.05 (5%) |

A 30-day window contains 720 hours or 43,200 minutes. At the threshold and under
constant traffic, fast burn represents 0.02 x 43.2 = **0.864 outage-equivalent
minutes**; slow burn represents 0.05 x 43.2 = **2.16 equivalent minutes**.
For CP6's six-attempt planning scenario (budget 0.006), those fractions correspond
to **0.00012** and **0.0003** failed-request equivalents, respectively.
These fractional values are not realizable individual failures or a promise to
warn before the budget is exhausted. One real failure already exceeds that budget.

The 2%/5% interpretation assumes window traffic matches the monthly average.
Actual budget consumption is failed requests / (0.001 x actual 30-day requests),
not a fixed 2% or 5% whenever an alert fires. A severe error burst can trigger
earlier and spend more; staff onboarding clusters invalidate uniform assumptions.

## Evaluation and sparse traffic

The group evaluates every minute. Each alert combines the long and short windows
using AND at the same evaluation. There is intentionally no additional `for`:
5m and 30m are confirmation lookback windows, not mandatory persistence delays.
Comparison is strict `>`; equality does not fire. Detection happens at the first
evaluation where both ratios exceed the threshold, subject to scrape timing.

For this single route/environment, sum() produces matching empty label sets on
both sides of AND; static alert labels identify the service and environment.
With zero requests the ratio is 0/0 (NaN), so the comparison is false. Missing
series yield no alert result, not a healthy state. Initialize outcome counters,
monitor scrape health separately and do not substitute zero for missing telemetry.

With only 4-6 onboarding accounts, one failure can dominate every populated
window. These workshop rules therefore demonstrate the required multi-window
method but need operational review before production paging. Do not invent a
high minimum-volume gate that suppresses all real registration failures. Examine
the event count and trace evidence when handling an alert; separately consider
safe synthetic probes for idle periods without creating real staff accounts.
Gateway metrics still miss pre-controller failures. The rules do not establish
full edge availability or cover a stopped publisher with zero publication attempts.

## Routing, runbook and activation

Send critical to the responsible IAM maintainer for immediate investigation;
route warning to the same team for prompt review. Alertmanager destinations and
credentials are not configured here. If both fire for the same journey, configure
critical-over-warning inhibition with matching team, service and environment
when Alertmanager is introduced; this YAML does not implement that inhibition.
The runbook_url is the reserved CP8 file path and will be supplied in the next
checkpoint. Neither rules nor notification routing have been deployed.
Before activation, attach the CP6 job/environment scrape labels, verify the real
counter semantics, check telemetry coverage and confirm the runbook is available.

## Cause-only alerts intentionally not created

1. **Auth CPU above 70% as a registration page:** password hashing can use CPU
   while registration still meets its objective. Keep CPU as diagnostic/capacity
   information; page on observed request failures, not this cause alone.
2. **PostgreSQL connection count above a fixed number as a registration page:**
   the database is shared and pool sizes matter. An absolute total cannot isolate
   iam or prove user impact. Inspect the Auth-owned pool ratio from CP4 alongside
   Gateway errors instead of paging solely on database-wide connection count.

## Validation and references

Local PyYAML parsing and checks of required labels/annotations, error selectors,
window pairs, expression delimiters and budget arithmetic passed. promtool was
unavailable; no PromQL execution or alert delivery is claimed.
Deployment must additionally run `promtool check rules 06-alerts.yml`
and exercise the rules against controlled counters, including no-traffic cases.
The incident drill will state its traffic and sampling assumptions explicitly.

Reference: [Google SRE multi-window burn-rate guidance](https://sre.google/workbook/alerting-on-slos/).
