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
| HU-IAM-001 | Staff sign-in: IAM persistence, verified login use case, JWT and public JWKS | doing | [dlc-iam-db #8](https://github.com/code-corhuila/dlc-iam-db/pull/8), [dlc-iam-api #7](https://github.com/code-corhuila/dlc-iam-api/pull/7), [dlc-iam-api #8](https://github.com/code-corhuila/dlc-iam-api/pull/8), [dlc-iam-api #9](https://github.com/code-corhuila/dlc-iam-api/pull/9), [dlc-iam-api #10](https://github.com/code-corhuila/dlc-iam-api/pull/10), [dlc-iam-api #11](https://github.com/code-corhuila/dlc-iam-api/pull/11), [dlc-iam-api #12](https://github.com/code-corhuila/dlc-iam-api/pull/12), [dlc-infra-postgres #3](https://github.com/code-corhuila/dlc-infra-postgres/pull/3) |
| HU-IAM-002 | Staff role management: role assignment audit persistence | doing | [dlc-iam-db #9](https://github.com/code-corhuila/dlc-iam-db/pull/9), [dlc-iam-db #16](https://github.com/code-corhuila/dlc-iam-db/pull/16) |
| HU-IAM-003 | Staff account administration: lifecycle audit, revocation and display name | doing | [dlc-iam-db #11](https://github.com/code-corhuila/dlc-iam-db/pull/11), [dlc-iam-db #18](https://github.com/code-corhuila/dlc-iam-db/pull/18), [dlc-iam-db #21](https://github.com/code-corhuila/dlc-iam-db/pull/21) |
| HU-IAM-004 | Password recovery and email verification: challenge safety persistence | doing | [dlc-iam-db #10](https://github.com/code-corhuila/dlc-iam-db/pull/10), [dlc-iam-db #17](https://github.com/code-corhuila/dlc-iam-db/pull/17) |
| HU-IAM-005 | Account-lock protection: lock after three failures, IP attempt limits, audited unlock | doing | [dlc-iam-db #12](https://github.com/code-corhuila/dlc-iam-db/pull/12), [dlc-iam-db #19](https://github.com/code-corhuila/dlc-iam-db/pull/19), [dlc-iam-db #22](https://github.com/code-corhuila/dlc-iam-db/pull/22), [dlc-iam-db #23](https://github.com/code-corhuila/dlc-iam-db/pull/23), [dlc-iam-api #13](https://github.com/code-corhuila/dlc-iam-api/pull/13), [dlc-iam-api #14](https://github.com/code-corhuila/dlc-iam-api/pull/14), [dlc-iam-api #15](https://github.com/code-corhuila/dlc-iam-api/pull/15), [dlc-iam-api #16](https://github.com/code-corhuila/dlc-iam-api/pull/16), [dlc-docs #116](https://github.com/code-corhuila/dlc-docs/pull/116) |
| HU-IAM-006 | Own sessions: refresh lineage and session revocation persistence | doing | [dlc-iam-db #13](https://github.com/code-corhuila/dlc-iam-db/pull/13), [dlc-iam-db #20](https://github.com/code-corhuila/dlc-iam-db/pull/20) |

The workshop task is separate from full HU acceptance. HU-IAM-003 remains doing:
the exercise does not establish working registration against the team's real IAM.

The HU-IAM-001 to HU-IAM-006 rows after the workshop rows record the project work done the same week in the team repositories. Every PR listed was merged. The `dlc-iam-db` story PRs #8–#13 and #21 target `develop` and #14–#20 target `qa`; #22 and #23 were stacked on the #21 branch and reached `develop` later through `dlc-iam-db` #28 (week 13). The `dlc-iam-api` PRs #9–#16 were stacked on HU feature branches (`feat/hu-iam-001-*`, `feat/hu-iam-005-*`); they reached `develop` through #17 this week and #20, #22 and #23 in week 12. The stories stay `doing`: the API endpoints for roles, lifecycle, recovery and sessions were not yet implemented this week, and no end-to-end acceptance through Gateway and Front was run.

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

Project work in the Di Lucca team repositories during the same week (19-25 October):
- I designed and merged the IAM-owned PostgreSQL migrations for all six IAM stories in `dlc-iam-db`: staff persistence, role assignment audit, verification and recovery challenge guards, staff lifecycle audit and revocation, account lock and unlock audit, and refresh lineage with session revocation (PRs #8–#13).
- I promoted the reviewed database scaffold and each story's persistence to `qa` through separate promotion PRs (#14–#20), then added the owned staff display name for the Auth contract, minimal login IP attempt windows and the audit of automatic unlock expiry (#21–#23).
- I created the Spring Boot scaffold of `dlc-iam-api` and implemented the verified staff login use case, password and token security adapters, atomic persistence of the login session with the owner outbox, and the login and public JWKS endpoints, with the HU-IAM-001 verification recorded (PRs #7–#12).
- I implemented the account-lock security: lock after three failed passwords, direct-peer login attempt limits in PostgreSQL, current-actor verification for protected operations and authorized, audited staff unlock (#13–#16), and promoted the reviewed login security adapters to `develop` (#17).
- In `dlc-infra-postgres` I provisioned shared PostgreSQL connectivity for IAM and added a test that validates owner schema isolation (#3, #4).
- I documented the withdrawal of MFA from the project scope (`dlc-docs` #116) and opened the planning issues for the independent Clinical MongoDB replica set: provisioning, owner-scoped access and migration handoff, backup/restore and failure isolation, and secure authentication/transport/storage (`dlc-infra-mongo` #1–#4, `dlc-docs` #117).

## 3. Blockers and risks
- The Auth endpoint used for tests is a synthetic stub. Live authentication, staff validation, PostgreSQL transactions, RabbitMQ publication and production authorization were not exercised.
- The sample Gateway binds to loopback and has no authentication enforcement. It is an instrumentation demonstration, not a deployable replacement for the team's Gateway.
- Gateway metrics currently cover registration controller calls, not pre-controller rejections or full browser-perceived availability. Auth/dependency metrics remain proposals; PromQL was not executed against a Prometheus server.
- An initial offline Maven run failed because Surefire dependencies were missing. The online run downloaded them and passed; a fresh machine needs dependency access.
- The updated guide allows Moodle links until 2026-10-21 at 23:59 America/Bogota, while delivery 1's in-class work period ended on October 20 at 20:30. CP4 and CP5 were published after that class period. Earlier pushes were closer together than the expected rhythm; real history is retained without rewriting timestamps.
- CP9 is an analytical tabletop, not an executed 160-request load test or a production incident. Its minute-4 and minute-9 alert predictions assume the documented steady synthetic traffic and evaluation grid; actual scrape and evaluation timing can differ.
- Alert YAML parsing and arithmetic checks do not establish PromQL correctness at runtime. No promtool rule tests or live Prometheus evaluations were executed.
- The supplied reflection export contains entries through CP9 but did not include the required one-page personal implementation plan. The latest Google Docs content, teacher Editor permission and Moodle submission remain unverified. Technical closure does not establish completion of these personal submission requirements.
- The API covers login and account lock only; role, lifecycle, recovery and session endpoints are still pending, so the persistence for those stories is not yet exercised by the API.
- Live integration with the Gateway and `dlc-front` was not verified this week.
- MFA was removed from the scope; account protection relies on passwords, lockout and attempt limits.

## 4. Plan for next week
- Integrate tracing and structured logs into the real IAM API and Gateway, preserving authorization, safe error messages and sensitive-data exclusions.
- Validate registration against the IAM database and instrument the UserRegistered outbox and broker-confirm path.
- Add owner-side metrics and verify dashboards against representative traffic before setting production targets.
- Add an authorized reconciliation path and verify that retrying an uncertain registration outcome with the same idempotency key cannot create duplicate staff accounts.
- Execute the proposed partial-delay integration scenario against representative IAM dependencies and compare measured logs, metrics and outcomes with the analytical CP9 predictions.
- Add promtool rule tests for both alert windows, recovery and missing/low-volume traffic, then validate target feasibility with the team before enabling production alerts.
- Project: implement the remaining IAM API use cases (role replacement, email verification, password reset, session validation for owner services and own-session revocation) and bring the login persistence to `develop`.

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
For the project work, `dlc-iam-api` and `dlc-infra-postgres` PRs added automated tests and `iam-core` has no framework or I/O dependencies; this does not change the unchecked workshop items. Each workshop commit adds no more than 150 lines. CP3 has two separate pushes;
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
**dlc-docs**

- 2026-10-21: [PR #116](https://github.com/code-corhuila/dlc-docs/pull/116) — docs(iam): withdraw MFA from project scope (merged).

**dlc-iam-api**

- 2026-10-22: [PR #7](https://github.com/code-corhuila/dlc-iam-api/pull/7) — chore(iam-api): establish Spring Boot API scaffold (merged).
- 2026-10-22: [PR #8](https://github.com/code-corhuila/dlc-iam-api/pull/8) — feat(iam-api): define verified staff login use case (merged).
- 2026-10-22: [PR #9](https://github.com/code-corhuila/dlc-iam-api/pull/9) — feat(iam-api): add password and token security adapters (merged).
- 2026-10-22: [PR #10](https://github.com/code-corhuila/dlc-iam-api/pull/10) — feat(iam-api): persist login session and owner outbox atomically (merged).
- 2026-10-23: [PR #11](https://github.com/code-corhuila/dlc-iam-api/pull/11) — feat(iam-api): expose login and public JWKS endpoints (merged).
- 2026-10-23: [PR #12](https://github.com/code-corhuila/dlc-iam-api/pull/12) — docs(iam-api): record HU-IAM-001 login verification (merged).
- 2026-10-23: [PR #13](https://github.com/code-corhuila/dlc-iam-api/pull/13) — feat(iam-api): lock accounts after three failed passwords (merged).
- 2026-10-23: [PR #14](https://github.com/code-corhuila/dlc-iam-api/pull/14) — feat(iam-api): limit direct-peer login attempts in PostgreSQL (merged).
- 2026-10-23: [PR #15](https://github.com/code-corhuila/dlc-iam-api/pull/15) — feat(iam-api): verify current staff actor for protected operations (merged).
- 2026-10-23: [PR #16](https://github.com/code-corhuila/dlc-iam-api/pull/16) — feat(iam-api): authorize audited staff unlock (merged).
- 2026-10-23: [PR #17](https://github.com/code-corhuila/dlc-iam-api/pull/17) — feat(iam-api): promote reviewed login security adapters to develop (merged).

**dlc-iam-db**

- 2026-10-20: [PR #8](https://github.com/code-corhuila/dlc-iam-db/pull/8) — feat(iam-db): add IAM-owned persistence for HU-IAM-001 (merged).
- 2026-10-21: [PR #9](https://github.com/code-corhuila/dlc-iam-db/pull/9) — feat(iam-db): persist role assignment audit for HU-IAM-002 (merged).
- 2026-10-21: [PR #10](https://github.com/code-corhuila/dlc-iam-db/pull/10) — feat(iam-db): guard verification and recovery challenges (merged).
- 2026-10-21: [PR #11](https://github.com/code-corhuila/dlc-iam-db/pull/11) — feat(iam-db): persist staff lifecycle audit and revocation (merged).
- 2026-10-21: [PR #12](https://github.com/code-corhuila/dlc-iam-db/pull/12) — feat(iam-db): persist account lock and unlock audit (merged).
- 2026-10-21: [PR #13](https://github.com/code-corhuila/dlc-iam-db/pull/13) — feat(iam-db): protect refresh lineage and session revocation (merged).
- 2026-10-22: [PR #14](https://github.com/code-corhuila/dlc-iam-db/pull/14) — chore(iam-db): promote reviewed database scaffold to qa (merged).
- 2026-10-22: [PR #15](https://github.com/code-corhuila/dlc-iam-db/pull/15) — feat(iam-db): promote HU-IAM-001 persistence to qa (merged).
- 2026-10-22: [PR #16](https://github.com/code-corhuila/dlc-iam-db/pull/16) — feat(iam-db): promote HU-IAM-002 role audit to qa (merged).
- 2026-10-22: [PR #17](https://github.com/code-corhuila/dlc-iam-db/pull/17) — feat(iam-db): promote HU-IAM-004 challenge safety to qa (merged).
- 2026-10-22: [PR #18](https://github.com/code-corhuila/dlc-iam-db/pull/18) — feat(iam-db): promote HU-IAM-003 staff lifecycle to qa (merged).
- 2026-10-22: [PR #19](https://github.com/code-corhuila/dlc-iam-db/pull/19) — feat(iam-db): promote HU-IAM-005 account lock to qa (merged).
- 2026-10-22: [PR #20](https://github.com/code-corhuila/dlc-iam-db/pull/20) — feat(iam-db): promote HU-IAM-006 session lineage to qa (merged).
- 2026-10-22: [PR #21](https://github.com/code-corhuila/dlc-iam-db/pull/21) — fix(iam-db): add owned staff display name for Auth contract (merged).
- 2026-10-23: [PR #22](https://github.com/code-corhuila/dlc-iam-db/pull/22) — feat(iam-db): store minimal login IP attempt windows (merged).
- 2026-10-23: [PR #23](https://github.com/code-corhuila/dlc-iam-db/pull/23) — fix(iam-db): audit automatic account unlock expiry (merged).

**dlc-infra-postgres**

- 2026-10-19: [PR #3](https://github.com/code-corhuila/dlc-infra-postgres/pull/3) — feat(infra-postgres): provision shared PostgreSQL connectivity for IAM (merged).
- 2026-10-20: [PR #4](https://github.com/code-corhuila/dlc-infra-postgres/pull/4) — test(infra-postgres): validate owner schema isolation (merged).
