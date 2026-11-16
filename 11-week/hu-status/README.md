<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       11-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 11

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca
- SPRINT_GOAL: Apply observability to staff registration through tracing, structured logs, Gateway instrumentation, metrics, SLOs, burn-rate alerts, a runbook and an incident tabletop.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-IAM-003 | Staff account administration: observability for registration through Gateway and Auth | doing | [Journey](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/f8648863f29045467de5dbce89d0f0d9bdbadf0e), [instrumentation](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/23c73d8a39dcdee1808454eadb3ceb0a941c09a1), [tests](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/2ca68d6f87fe7bcd50831ae8b71733ca1af6fa65) |
| N/A - W11 technical workshop | Delivery 1 technical checkpoints CP0-CP4 | done | [Setup](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/709a46e4aff92d9e17a79c91e4761bf109cf20fa), [logs](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/80338aabe796d6de4a08f0ad2f3985a1ccd7ff98), [metrics](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/a71f3950b18833bb959c819aca845cab1f4d2cbd) |
| N/A - W11 delivery 2 | Technical artifacts CP6-CP9: SLOs, alerts, runbook and analytical incident exercise | done | [SLOs](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/0b16452a05825a7f7dc01e8d0ea179b5aec9ca80), [alerts](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/d26da4b35b131789d0a9481d509698f9e3c97496), [runbook](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/aed8cc6a3452879ab8a45515b98ac8f60f4d5e71), [incident](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/aff4ca9f13d38251f26d704965581403967c39c7) |
| HU-IAM-001 | Staff sign-in: login and recovery layout and artwork refinements | doing | [dlc-iam-portal #58](https://github.com/code-corhuila/dlc-iam-portal/pull/58), [dlc-iam-portal #59](https://github.com/code-corhuila/dlc-iam-portal/pull/59), [dlc-iam-portal #63](https://github.com/code-corhuila/dlc-iam-portal/pull/63), [dlc-front #90](https://github.com/code-corhuila/dlc-front/pull/90), [dlc-front #93](https://github.com/code-corhuila/dlc-front/pull/93) |
| HU-IAM-003 | Staff account administration: staff directory and registration modal aligned with the mockup | doing | [dlc-iam-portal #54](https://github.com/code-corhuila/dlc-iam-portal/pull/54), [dlc-iam-portal #56](https://github.com/code-corhuila/dlc-iam-portal/pull/56), [dlc-iam-portal #61](https://github.com/code-corhuila/dlc-iam-portal/pull/61), [dlc-iam-portal #62](https://github.com/code-corhuila/dlc-iam-portal/pull/62) |
| HU-IAM-006 | Own sessions: refreshed view and retry outside the alert | doing | [dlc-iam-portal #55](https://github.com/code-corhuila/dlc-iam-portal/pull/55), [dlc-iam-portal #57](https://github.com/code-corhuila/dlc-iam-portal/pull/57), [dlc-iam-portal #60](https://github.com/code-corhuila/dlc-iam-portal/pull/60) |
| Release 2.0.0 | IAM portal promoted from QA to main with its changelog; shell integration promoted to QA | done | [dlc-iam-portal #64](https://github.com/code-corhuila/dlc-iam-portal/pull/64), [dlc-iam-portal #65](https://github.com/code-corhuila/dlc-iam-portal/pull/65), [dlc-iam-portal #66](https://github.com/code-corhuila/dlc-iam-portal/pull/66), [dlc-front #87](https://github.com/code-corhuila/dlc-front/pull/87) |
| Infrastructure | Shared PostgreSQL infrastructure scaffold | done | [dlc-infra-postgres #2](https://github.com/code-corhuila/dlc-infra-postgres/pull/2) |

The workshop task is separate from full HU acceptance. HU-IAM-003 remains doing:
the exercise does not establish working registration against the team's real IAM.

The rows after the workshop rows record the project work done the same week (12-18 October) in the team repositories. Every PR listed was merged. The portal stories stay `doing`: the portal worked against the documented contracts, and the IAM API that serves them did not exist yet, so no live login, administration or session flow through Gateway and Auth was possible this week.

## 2. My individual contribution
- Mapped administrator-driven staff registration through dlc-iam-portal, api-gateway and auth-service, including PostgreSQL iam ownership and asynchronous UserRegistered publication through the Auth outbox.
- Defined required JSON log fields, domain fields, severity rules, privacy exclusions and seven synthetic lines describing a failed registration with one trace_id.
- Implemented a Java 21 / Spring Boot api-gateway exercise: traceparent parsing/creation, response context, structured logging and child-span propagation to an Auth HTTP stub.
- Added a completed-request counter by outcome, a latency histogram and an in-flight gauge exposed through /metrics; excluded scrapes from registration counts.
- Added automated checks for trace propagation over real local HTTP, parent/child IDs, malformed contexts, the log envelope, idempotency-key forwarding, rejection, timeout and metric behavior.
- Documented reproducible commands and actual console output. All eight tests passed; packaging succeeded, and the running JAR returned 201 through the Python Auth stub with correlated logs and matching metrics.
- Defined the four golden signals for Gateway and Auth, with proposed dependency diagnostics, PromQL, label cardinality estimates and latency buckets aligned with the code.
- Defined 30-day objectives for registration availability (99.9%), latency within one second (95%) and confirmed outbox publication attempts (99.9%). The initial planning scenario has six ordinary staff registrations; the administrator account is separate.
- Calculated request-based error budgets and explicitly separated them from uniform-traffic calendar equivalents. Documented the effect of one failure on a low-volume clinic and actions at 50%, 75% and 100% consumption.
- Added paired-window burn-rate rules: fast burn at 14.4 over 1h and 5m, slow burn at 6 over 6h and 30m. Validated YAML structure and budget arithmetic; runtime Prometheus validation remains pending.
- Wrote an IAM registration runbook with confirmation queries, trace/log correlation, three mitigations ordered by risk, ownership and recovery checks. Unknown registration outcomes require authorized reconciliation before retrying.
- Completed an analytical incident tabletop using the existing two-second Gateway timeout: a three-second downstream response affects 25% of traffic for 40 minutes. Included a trace, four illustrative logs, SLI calculations, alert timing, a blameless postmortem and follow-up stories.
- Updated this weekly status for CP10 with delivery 2 evidence and outstanding integration work.

Project work in the Di Lucca team repositories during the same week (12-18 October):
- I refined the IAM portal screens: the staff directory and the registration modal were aligned with the management mockup, the own-sessions view was refreshed with the retry kept outside the alert, and the login and recovery layouts and artwork were adjusted (`dlc-iam-portal` #54–#63); in `dlc-front` the public IAM host was stretched to the footer (#90, #93) and the shell portal integration was promoted to QA (#87).
- I prepared the portal release: QA release readiness (#64), the promotion of the QA-validated portal to `main` as 2.0.0 (#65) and its changelog (#66).
- I created the shared PostgreSQL infrastructure scaffold in `dlc-infra-postgres` (#2).

## 3. Blockers and risks
- The Auth endpoint used for tests is a synthetic stub. Live authentication, staff validation, PostgreSQL transactions, RabbitMQ publication and production authorization were not exercised.
- The sample Gateway binds to loopback and has no authentication enforcement. It is an instrumentation demonstration, not a deployable replacement for the team's Gateway.
- Gateway metrics currently cover registration controller calls, not pre-controller rejections or full browser-perceived availability. Auth/dependency metrics remain proposals; PromQL was not executed against a Prometheus server.
- An initial offline Maven run failed because Surefire dependencies were missing. The online run downloaded them and passed; a fresh machine needs dependency access.
- The updated guide allows Moodle links until 2026-10-21 at 23:59 America/Bogota, while delivery 1's in-class work period ended on October 20 at 20:30. CP4 and CP5 were published after that class period. Earlier pushes were closer together than the expected rhythm; real history is retained without rewriting timestamps.
- CP9 is an analytical tabletop, not an executed 160-request load test or a production incident. Its minute-4 and minute-9 alert predictions assume the documented steady synthetic traffic and evaluation grid; actual scrape and evaluation timing can differ.
- Alert YAML parsing and arithmetic checks do not establish PromQL correctness at runtime. No promtool rule tests or live Prometheus evaluations were executed.
- The supplied reflection export contains entries through CP9 but did not include the required one-page personal implementation plan. The latest Google Docs content, teacher Editor permission and Moodle submission remain unverified. Technical closure does not establish completion of these personal submission requirements.
- The portal release 2.0.0 does not establish live Auth/Gateway integration: the IAM API and its database migrations were still to be built.
- MFA screens still existed in the portal while MFA scope was under discussion.

## 4. Plan for next week
- Integrate tracing and structured logs into the real IAM API and Gateway, preserving authorization, safe error messages and sensitive-data exclusions.
- Validate registration against the IAM database and instrument the UserRegistered outbox and broker-confirm path.
- Add owner-side metrics and verify dashboards against representative traffic before setting production targets.
- Add an authorized reconciliation path and verify that retrying an uncertain registration outcome with the same idempotency key cannot create duplicate staff accounts.
- Execute the proposed partial-delay integration scenario against representative IAM dependencies and compare measured logs, metrics and outcomes with the analytical CP9 predictions.
- Add promtool rule tests for both alert windows, recovery and missing/low-volume traffic, then validate target feasibility with the team before enabling production alerts.
- Project: build the IAM database migrations and the IAM API (login, JWKS and account lock) so the portal can be integrated with a real backend.

These are technical follow-up proposals. The student's personal priorities, sprint
choices, blockers and learning belong in the separately authored Google Docs plan.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [ ] DDD / hexagonal boundaries respected (domain has no I/O)
- [ ] No secrets; config via environment variables

Checked criteria refer to the workshop: incoming/outgoing trace IDs match,
spans differ, response context is present, and metric outcomes are testable.
Workshop commits use the individual fork's main branch; environment promotion
and PR workflow were not exercised. Full domain-boundary and deployment-secret
compliance remain unverified. The exercise uses synthetic data and configurable
local endpoints, but that is not a security audit of the team application.
For the project work, the portal PRs follow Conventional Commits and the release went through QA; this does not change the unchecked workshop items. Each workshop commit adds no more than 150 lines. CP3 has two separate pushes;
its second increment uses three commits belonging only to CP3.

## 6. Evidence links
- CP0: [workshop setup](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/709a46e4aff92d9e17a79c91e4761bf109cf20fa), [reflection link](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/50531699fed86cb017da21bdb869fbdb1d43522e).
- CP1: [critical journey and trace propagation](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/f8648863f29045467de5dbce89d0f0d9bdbadf0e).
- CP2: [structured log contract](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/80338aabe796d6de4a08f0ad2f3985a1ccd7ff98).
- CP3 first push: [trace filter and logger](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/82901c79a52b956419d77af9d2216a89ed57122b).
- CP3 second push: [propagation and metrics](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/23c73d8a39dcdee1808454eadb3ceb0a941c09a1), [tests](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/2ca68d6f87fe7bcd50831ae8b71733ca1af6fa65), [run instructions and execution evidence](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/9adb370e822e4910af04a223ed628651233afab9).
- CP4: [golden signals and metric catalog](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/a71f3950b18833bb959c819aca845cab1f4d2cbd).
- [Individual fork](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1).
- CP5: [delivery 1 weekly status](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/961fdf76fecc88c5ea9bef750fbd5e4d533b48aa).
- CP6: [SLOs and error budgets](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/0b16452a05825a7f7dc01e8d0ea179b5aec9ca80).
- CP7: [burn-rate alert rules and policy](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/d26da4b35b131789d0a9481d509698f9e3c97496).
- CP8: [registration incident runbook](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/aed8cc6a3452879ab8a45515b98ac8f60f4d5e71).
- CP9: [analytical incident drill and postmortem](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/aff4ca9f13d38251f26d704965581403967c39c7).
- [Workshop folder](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/tree/main/11-week/workshop).
- [Personal reflection document](https://docs.google.com/document/d/1BlAX8AtOa5-qsU91NF9cehHBdqza1EMEZRU_DtC6y_U/edit).

Project PRs of the same week, authored by `camilo28k` in the `code-corhuila` organization and retrieved through the GitHub API with their creation date (UTC-5) and final state:

**dlc-front**

- 2026-10-12: [PR #87](https://github.com/code-corhuila/dlc-front/pull/87) — chore(iam): promote shell portal integration to QA (merged).
- 2026-10-13: [PR #90](https://github.com/code-corhuila/dlc-front/pull/90) — fix(shell): stretch public IAM host to footer (merged).
- 2026-10-13: [PR #93](https://github.com/code-corhuila/dlc-front/pull/93) — fix(shell): stretch public IAM host to footer (merged).

**dlc-iam-portal**

- 2026-10-12: [PR #54](https://github.com/code-corhuila/dlc-iam-portal/pull/54) — feat(iam): align staff directory with management mockup (merged).
- 2026-10-12: [PR #55](https://github.com/code-corhuila/dlc-iam-portal/pull/55) — feat(iam): refresh own sessions view (merged).
- 2026-10-12: [PR #56](https://github.com/code-corhuila/dlc-iam-portal/pull/56) — chore(iam): promote staff directory visual refresh to QA (merged).
- 2026-10-12: [PR #57](https://github.com/code-corhuila/dlc-iam-portal/pull/57) — chore(iam): promote own sessions visual refresh to QA (merged).
- 2026-10-12: [PR #58](https://github.com/code-corhuila/dlc-iam-portal/pull/58) — fix(iam): stretch auth artwork within public frame (merged).
- 2026-10-13: [PR #59](https://github.com/code-corhuila/dlc-iam-portal/pull/59) — fix(iam): refine login and recovery layouts (merged).
- 2026-10-13: [PR #60](https://github.com/code-corhuila/dlc-iam-portal/pull/60) — fix(iam): keep own sessions retry outside alert (merged).
- 2026-10-13: [PR #61](https://github.com/code-corhuila/dlc-iam-portal/pull/61) — fix(iam): align staff directory and registration modal (merged).
- 2026-10-13: [PR #62](https://github.com/code-corhuila/dlc-iam-portal/pull/62) — fix(iam): promote staff directory and registration modal to QA (merged).
- 2026-10-13: [PR #63](https://github.com/code-corhuila/dlc-iam-portal/pull/63) — fix(iam): stretch login artwork within public frame (merged).
- 2026-10-13: [PR #64](https://github.com/code-corhuila/dlc-iam-portal/pull/64) — Qa iam portal release readiness (merged).
- 2026-10-13: [PR #65](https://github.com/code-corhuila/dlc-iam-portal/pull/65) — release(iam): promote QA-validated portal to 2.0.0 (merged).
- 2026-10-13: [PR #66](https://github.com/code-corhuila/dlc-iam-portal/pull/66) — docs(iam): add v2.0.0 changelog (merged).

**dlc-infra-postgres**

- 2026-10-15: [PR #2](https://github.com/code-corhuila/dlc-infra-postgres/pull/2) — chore(infra-postgres): scaffold shared PostgreSQL infrastructure (merged).
