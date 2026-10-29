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

## 2. My individual contribution
- Claimed the inbound `api-gateway` to `auth-service` boundary, mapped its attack surface, and analyzed STRIDE risks to IAM `User` and `Session`.
- Opened STRIDE PR #125, published three P1 security stories in Project 21 Backlog, and proposed CI gates for `POST /api/v1/auth/staff/{id}/unlocks`.
- Ran the isolated negative IAM test: 2 tests, 0 failures, 0 errors. Reviewed a teammate's clinical-service STRIDE PR #133 with an inline comment on unverified Gateway header filtering.

## 3. Blockers and risks
- `dlc-api-gateway` main/develop still contain only README and CODEOWNERS; Gateway header stripping and routed authorization are unverified.
- PR #125 awaits team review. The security workflow and negative test are published as a fork proposal, not installed in `dlc-iam-api`; its dependency audit needs `NVD_API_KEY`.
- The three security stories are backlog items, not implemented fixes. A MockMvc test cannot establish behavior through Gateway or a live IAM database.

## 4. Plan for next week
- Seek team review of PR #125 and prioritize issues #126-#128 after the CP6-CP9 take-home work.
- Propose the negative IAM authorization test and security CI gates through an IAM PR after the team agrees on ownership and `NVD_API_KEY`.
- Add a Gateway-to-Auth routing test when Gateway implementation exists; record evidence before treating header stripping as an active control.

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
- Peer review comment: https://github.com/code-corhuila/dlc-docs/pull/133#discussion_r4418098322
- Reflection document: https://docs.google.com/document/d/1xmY05LsNzr8W9yD9qynghd0fbPk-5RePA2aXGYyd3Pg/edit
