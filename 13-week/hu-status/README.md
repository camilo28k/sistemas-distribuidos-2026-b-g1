<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       13-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 13

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca Dental Care & Technology
- SPRINT_GOAL: W13 individual focus - model the inbound api-gateway to auth-service boundary and turn IAM risks into testable security work.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| #126 (S-01) | Reject forged staff identity at Auth unlock | todo | https://github.com/code-corhuila/dlc-docs/issues/126 |
| #127 (I-01) | Keep IAM credentials and internal errors out of routed responses | todo | https://github.com/code-corhuila/dlc-docs/issues/127 |
| #128 (E-01) | Enforce current Administrator authority for staff administration | todo | https://github.com/code-corhuila/dlc-docs/issues/128 |
| HU-IAM-001 | Staff sign-in: login persistence in develop and public signing keys | doing | [dlc-iam-api #20](https://github.com/code-corhuila/dlc-iam-api/pull/20), [dlc-iam-api #26](https://github.com/code-corhuila/dlc-iam-api/pull/26) |
| HU-IAM-002 | Staff role management: role replacement policy | doing | [dlc-iam-api #18](https://github.com/code-corhuila/dlc-iam-api/pull/18) |
| HU-IAM-004 | Password recovery and email verification: single-use verification and reset | doing | [dlc-iam-api #19](https://github.com/code-corhuila/dlc-iam-api/pull/19), [dlc-iam-api #24](https://github.com/code-corhuila/dlc-iam-api/pull/24), [dlc-iam-api #25](https://github.com/code-corhuila/dlc-iam-api/pull/25), [dlc-iam-api #27](https://github.com/code-corhuila/dlc-iam-api/pull/27), [dlc-iam-api #29](https://github.com/code-corhuila/dlc-iam-api/pull/29) |
| HU-IAM-005 | Account-lock protection: three-failure lock and current-actor verification in develop | doing | [dlc-iam-api #22](https://github.com/code-corhuila/dlc-iam-api/pull/22), [dlc-iam-api #23](https://github.com/code-corhuila/dlc-iam-api/pull/23) |
| HU-IAM-006 | Own sessions: active-session validation, versioned sessions and revocation | doing | [dlc-iam-api #21](https://github.com/code-corhuila/dlc-iam-api/pull/21), [dlc-iam-api #28](https://github.com/code-corhuila/dlc-iam-api/pull/28), [dlc-iam-api #30](https://github.com/code-corhuila/dlc-iam-api/pull/30), [dlc-iam-db #24](https://github.com/code-corhuila/dlc-iam-db/pull/24) |

The HU-IAM rows record the project work done the same week (26 October - 1 November) in the team repositories. All those PRs were merged into `develop`. The IAM stories stay `doing` until the endpoints are integrated and accepted through Gateway and Front.

## 2. My individual contribution
- Claimed the inbound `api-gateway` to `auth-service` boundary, mapped its attack surface, and analyzed STRIDE risks to IAM `User` and `Session`.
- Opened STRIDE PR #125, published three P1 security stories in Project 21 Backlog, and proposed CI gates for `POST /api/v1/auth/staff/{id}/unlocks`.
- Addressed the peer comment on STRIDE PR #125, refined issues #126-#128, and reviewed a teammate's clinical-service STRIDE PR #133 with an inline comment on unverified Gateway header filtering.
- Re-ran the isolated negative IAM test on October 30: 2 tests, 0 failures, 0 errors, and Maven BUILD SUCCESS. Validated the proposed workflow as YAML and recorded both results in HOW-TO-RUN.md.

Project work in the Di Lucca team repositories during the same week:
- I implemented the staff role replacement policy and single-use staff email verification, including its persistence and endpoint (`dlc-iam-api` #18, #19, #24, #25).
- I brought the login persistence, the three-failure account lock and current-actor verification to `develop` (#20, #22, #23) and exposed the public signing keys (#26).
- I implemented single-use password reset with session revocation, with its persistence and endpoint (#27, #29).
- I implemented active IAM session validation for owner services, atomic own-session revocation with its event, and the own-session revocation endpoint (#21, #28, #30); in `dlc-iam-db` I versioned owned sessions for the read contract (#24).

## 3. Blockers and risks
- `dlc-api-gateway` main/develop still contain only README and CODEOWNERS; Gateway header stripping and routed authorization are unverified.
- STRIDE PR #125 requires a team-owned merge decision. The security workflow and negative test are published as a fork proposal, not installed in `dlc-iam-api`; its dependency audit needs `NVD_API_KEY`.
- The three security stories are backlog items, not implemented fixes. A MockMvc test cannot establish behavior through Gateway or a live IAM database.
- Staff registration, administrative lifecycle endpoints, refresh and logout were still pending.
- The new endpoints were verified by their tests only; live integration through the Gateway and `dlc-front` was not yet run.
- The login security work had to be brought to `develop` from stacked HU branches (#20, #22, #23), so `develop` lagged behind the reviewed branches until this week.

## 4. Plan for next week
- Seek final team review of PR #125 and prioritize issues #126-#128 in the next sprint.
- Propose the negative IAM authorization test and security CI gates through an IAM PR after the team agrees on ownership and `NVD_API_KEY`.
- Add a Gateway-to-Auth routing test when Gateway implementation exists; record evidence before treating header stripping as an active control.
- Project: implement own-session listing, refresh rotation, protected login, logout and CSRF proof.
- Project: implement staff registration, name updates, enabling/disabling, role assignment and administrative unlock endpoints.
- Project: deliver identity emails, the outbox to RabbitMQ and the integration with Gateway, Front, Patients and Clinical.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- Boundary claim: https://github.com/code-corhuila/dlc-docs/issues/124
- Workshop folder: https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/tree/main/13-week/workshop
- CP0 commits: https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/fd99460 ; https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/88d42ee ; https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/771004e
- CP1 commit: https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/74c75be
- CP2 commits: https://github.com/code-corhuila/dlc-docs/commit/46fa5a2b8deee4fdf704a4d6df1532aa1669ede9 ; https://github.com/code-corhuila/dlc-docs/commit/36a49381e7fe14a34dd2c40eebef9a17ed140a56
- STRIDE PR: https://github.com/code-corhuila/dlc-docs/pull/125
- CP3 commits: https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/2f7fdb92046cf4ac6247b1c077988d0b8322cc71 ; https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/dd75946880cfbea4336630e326d6ea0ead572caf
- CP3 stories: https://github.com/code-corhuila/dlc-docs/issues/126 ; https://github.com/code-corhuila/dlc-docs/issues/127 ; https://github.com/code-corhuila/dlc-docs/issues/128
- CP4 commit: https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/2e63115aa08159883208d105cf1418022c9dbc94
- CP6 STRIDE revision: https://github.com/code-corhuila/dlc-docs/commit/4295401b983e6260ec0421e1ee3de9ad7e8d04c0
- CP7 backlog refinement: https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/978467ca4dd02e939121d5573248f522359f2c7b
- CP8 test output and YAML validation: https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/8e982ca526be5256e7b6610248af229844e1d3d5
- Peer review comment: https://github.com/code-corhuila/dlc-docs/pull/133#discussion_r4418098322
- Reflection document: https://docs.google.com/document/d/1xmY05LsNzr8W9yD9qynghd0fbPk-5RePA2aXGYyd3Pg/edit

Project PRs of the same week, authored by `camilo28k` in the `code-corhuila` organization and retrieved through the GitHub API with their creation date (UTC-5) and final state:

**dlc-docs**

- 2026-10-28: [PR #125](https://github.com/code-corhuila/dlc-docs/pull/125) — Threat model: api-gateway -> auth-service (inbound) (merged).

**dlc-iam-api**

- 2026-10-26: [PR #18](https://github.com/code-corhuila/dlc-iam-api/pull/18) — feat(iam-api): enforce staff role replacement policy (merged).
- 2026-10-26: [PR #19](https://github.com/code-corhuila/dlc-iam-api/pull/19) — feat(iam-api): enforce single-use staff email verification (merged).
- 2026-10-26: [PR #20](https://github.com/code-corhuila/dlc-iam-api/pull/20) — feat(iam-api): bring login persistence to develop (merged).
- 2026-10-29: [PR #21](https://github.com/code-corhuila/dlc-iam-api/pull/21) — feat(iam-api): validate active IAM sessions for owner services (merged).
- 2026-10-29: [PR #22](https://github.com/code-corhuila/dlc-iam-api/pull/22) — feat(iam-api): lock accounts after three failed passwords (merged).
- 2026-10-31: [PR #23](https://github.com/code-corhuila/dlc-iam-api/pull/23) — feat(iam-api): verify current staff actor for protected operations (merged).
- 2026-10-31: [PR #24](https://github.com/code-corhuila/dlc-iam-api/pull/24) — feat(iam-api): persist single-use staff email verification (merged).
- 2026-10-31: [PR #25](https://github.com/code-corhuila/dlc-iam-api/pull/25) — feat(iam-api): expose staff email verification endpoint (merged).
- 2026-10-31: [PR #26](https://github.com/code-corhuila/dlc-iam-api/pull/26) — feat(iam-api): expose public signing keys (merged).
- 2026-10-31: [PR #27](https://github.com/code-corhuila/dlc-iam-api/pull/27) — feat(iam-api): persist single-use password reset and revocation (merged).
- 2026-11-01: [PR #28](https://github.com/code-corhuila/dlc-iam-api/pull/28) — feat(iam-api): persist own-session revocation and event atomically (merged).
- 2026-11-01: [PR #29](https://github.com/code-corhuila/dlc-iam-api/pull/29) — feat(iam-api): expose password reset endpoint (merged).
- 2026-11-01: [PR #30](https://github.com/code-corhuila/dlc-iam-api/pull/30) — feat(iam-api): expose own-session revocation endpoint (merged).

**dlc-iam-db**

- 2026-11-01: [PR #24](https://github.com/code-corhuila/dlc-iam-db/pull/24) — feat(iam-db): version owned sessions for read contract (merged).
