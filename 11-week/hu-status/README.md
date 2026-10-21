<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       11-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 11

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca
- SPRINT_GOAL: Apply observability to staff registration with a trace map, structured log contract, executable Gateway instrumentation and a bounded metric catalog.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-IAM-003 | Staff account administration: observability for registration through Gateway and Auth | doing | [Journey](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/f8648863f29045467de5dbce89d0f0d9bdbadf0e), [instrumentation](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/23c73d8a39dcdee1808454eadb3ceb0a941c09a1), [tests](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/2ca68d6f87fe7bcd50831ae8b71733ca1af6fa65) |
| N/A - W11 technical workshop | Delivery 1 technical checkpoints CP0-CP4 | done | [Setup](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/709a46e4aff92d9e17a79c91e4761bf109cf20fa), [logs](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/80338aabe796d6de4a08f0ad2f3985a1ccd7ff98), [metrics](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/a71f3950b18833bb959c819aca845cab1f4d2cbd) |

The workshop task is separate from full HU acceptance. HU-IAM-003 remains doing:
the exercise does not establish working registration against the team's real IAM.

## 2. My individual contribution
- Mapped administrator-driven staff registration through dlc-iam-portal, api-gateway and auth-service, including PostgreSQL iam ownership and asynchronous UserRegistered publication through the Auth outbox.
- Defined required JSON log fields, domain fields, severity rules, privacy exclusions and seven synthetic lines describing a failed registration with one trace_id.
- Implemented a Java 21 / Spring Boot api-gateway exercise: traceparent parsing/creation, response context, structured logging and child-span propagation to an Auth HTTP stub.
- Added a completed-request counter by outcome, a latency histogram and an in-flight gauge exposed through /metrics; excluded scrapes from registration counts.
- Added automated checks for trace propagation over real local HTTP, parent/child IDs, malformed contexts, the log envelope, idempotency-key forwarding, rejection, timeout and metric behavior.
- Documented reproducible commands and actual console output. All eight tests passed; packaging succeeded, and the running JAR returned 201 through the Python Auth stub with correlated logs and matching metrics.
- Defined the four golden signals for Gateway and Auth, with proposed dependency diagnostics, PromQL, label cardinality estimates and latency buckets aligned with the code.

## 3. Blockers and risks
- The Auth endpoint used for tests is a synthetic stub. Live authentication, staff validation, PostgreSQL transactions, RabbitMQ publication and production authorization were not exercised.
- The sample Gateway binds to loopback and has no authentication enforcement. It is an instrumentation demonstration, not a deployable replacement for the team's Gateway.
- Gateway metrics currently cover registration controller calls, not pre-controller rejections or full browser-perceived availability. Auth/dependency metrics remain proposals; PromQL was not executed against a Prometheus server.
- An initial offline Maven run failed because Surefire dependencies were missing. The online run downloaded them and passed; a fresh machine needs dependency access.
- Delivery 1's stated deadline was 2026-10-20 at 20:30 America/Bogota. CP4 was published after that deadline and CP5 closes later. Earlier pushes were closer together than the guide's expected 15-20-minute rhythm; separate checkpoints and real timestamps are retained without rewriting history.
- The personal reflection document is linked, but its full contents, teacher Editor permission and Moodle submission have not been verified in this technical report.

## 4. Plan for next week
- Integrate tracing and structured logs into the real IAM API and Gateway, preserving authorization, safe error messages and sensitive-data exclusions.
- Validate registration against the IAM database and instrument the UserRegistered outbox and broker-confirm path.
- Add owner-side metrics and verify dashboards against representative traffic before setting production targets.
- Complete delivery 2 checkpoints CP6-CP10 separately: SLOs, alert rules, runbook, incident exercise and final status. They are not part of this delivery 1 report.

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
Each workshop commit adds no more than 150 lines. CP3 has two separate pushes;
its second increment uses three commits belonging only to CP3.

## 6. Evidence links
- CP0: [workshop setup](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/709a46e4aff92d9e17a79c91e4761bf109cf20fa), [reflection link](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/50531699fed86cb017da21bdb869fbdb1d43522e).
- CP1: [critical journey and trace propagation](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/f8648863f29045467de5dbce89d0f0d9bdbadf0e).
- CP2: [structured log contract](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/80338aabe796d6de4a08f0ad2f3985a1ccd7ff98).
- CP3 first push: [trace filter and logger](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/82901c79a52b956419d77af9d2216a89ed57122b).
- CP3 second push: [propagation and metrics](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/23c73d8a39dcdee1808454eadb3ceb0a941c09a1), [tests](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/2ca68d6f87fe7bcd50831ae8b71733ca1af6fa65), [run instructions and execution evidence](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/9adb370e822e4910af04a223ed628651233afab9).
- CP4: [golden signals and metric catalog](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/a71f3950b18833bb959c819aca845cab1f4d2cbd).
- [Individual fork](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1).
- [Workshop folder](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/tree/main/11-week/workshop).
- [Personal reflection document](https://docs.google.com/document/d/1BlAX8AtOa5-qsU91NF9cehHBdqza1EMEZRU_DtC6y_U/edit).
