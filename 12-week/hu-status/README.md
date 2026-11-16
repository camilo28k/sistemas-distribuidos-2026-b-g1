<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       12-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 12

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca
- SPRINT_GOAL: Build the IAM database migrations for the six IAM stories and the first IAM API slice: verified staff login, public JWKS and account-lock protection.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-IAM-001 | Staff sign-in: IAM persistence, verified login use case, JWT and public JWKS | doing | [dlc-iam-db #8](https://github.com/code-corhuila/dlc-iam-db/pull/8), [dlc-iam-api #7](https://github.com/code-corhuila/dlc-iam-api/pull/7), [dlc-iam-api #8](https://github.com/code-corhuila/dlc-iam-api/pull/8), [dlc-iam-api #9](https://github.com/code-corhuila/dlc-iam-api/pull/9), [dlc-iam-api #10](https://github.com/code-corhuila/dlc-iam-api/pull/10), [dlc-iam-api #11](https://github.com/code-corhuila/dlc-iam-api/pull/11), [dlc-iam-api #12](https://github.com/code-corhuila/dlc-iam-api/pull/12), [dlc-infra-postgres #3](https://github.com/code-corhuila/dlc-infra-postgres/pull/3) |
| HU-IAM-002 | Staff role management: role assignment audit persistence | doing | [dlc-iam-db #9](https://github.com/code-corhuila/dlc-iam-db/pull/9), [dlc-iam-db #16](https://github.com/code-corhuila/dlc-iam-db/pull/16) |
| HU-IAM-003 | Staff account administration: lifecycle audit, revocation and display name | doing | [dlc-iam-db #11](https://github.com/code-corhuila/dlc-iam-db/pull/11), [dlc-iam-db #18](https://github.com/code-corhuila/dlc-iam-db/pull/18), [dlc-iam-db #21](https://github.com/code-corhuila/dlc-iam-db/pull/21) |
| HU-IAM-004 | Password recovery and email verification: challenge safety persistence | doing | [dlc-iam-db #10](https://github.com/code-corhuila/dlc-iam-db/pull/10), [dlc-iam-db #17](https://github.com/code-corhuila/dlc-iam-db/pull/17) |
| HU-IAM-005 | Account-lock protection: lock after three failures, IP attempt limits, audited unlock | doing | [dlc-iam-db #12](https://github.com/code-corhuila/dlc-iam-db/pull/12), [dlc-iam-db #19](https://github.com/code-corhuila/dlc-iam-db/pull/19), [dlc-iam-db #22](https://github.com/code-corhuila/dlc-iam-db/pull/22), [dlc-iam-db #23](https://github.com/code-corhuila/dlc-iam-db/pull/23), [dlc-iam-api #13](https://github.com/code-corhuila/dlc-iam-api/pull/13), [dlc-iam-api #14](https://github.com/code-corhuila/dlc-iam-api/pull/14), [dlc-iam-api #15](https://github.com/code-corhuila/dlc-iam-api/pull/15), [dlc-iam-api #16](https://github.com/code-corhuila/dlc-iam-api/pull/16), [dlc-docs #116](https://github.com/code-corhuila/dlc-docs/pull/116) |
| HU-IAM-006 | Own sessions: refresh lineage and session revocation persistence | doing | [dlc-iam-db #13](https://github.com/code-corhuila/dlc-iam-db/pull/13), [dlc-iam-db #20](https://github.com/code-corhuila/dlc-iam-db/pull/20) |

The rows record the project work done this week (19-25 October) in the team repositories. Every PR listed was merged. The `dlc-iam-db` story PRs #8–#13 and #21 target `develop` and #14–#20 target `qa`; #22 and #23 were stacked on the #21 branch and reached `develop` later through `dlc-iam-db` #28 (week 14). The `dlc-iam-api` PRs #9–#16 were stacked on HU feature branches (`feat/hu-iam-001-*`, `feat/hu-iam-005-*`); they reached `develop` through #17 this week and #20, #22 and #23 in week 13. The stories stay `doing`: the API endpoints for roles, lifecycle, recovery and sessions were not yet implemented this week, and no end-to-end acceptance through Gateway and Front was run.

## 2. My individual contribution
- I designed and merged the IAM-owned PostgreSQL migrations for all six IAM stories in `dlc-iam-db`: staff persistence, role assignment audit, verification and recovery challenge guards, staff lifecycle audit and revocation, account lock and unlock audit, and refresh lineage with session revocation (PRs #8–#13).
- I promoted the reviewed database scaffold and each story's persistence to `qa` through separate promotion PRs (#14–#20), then added the owned staff display name for the Auth contract, minimal login IP attempt windows and the audit of automatic unlock expiry (#21–#23).
- I created the Spring Boot scaffold of `dlc-iam-api` and implemented the verified staff login use case, password and token security adapters, atomic persistence of the login session with the owner outbox, and the login and public JWKS endpoints, with the HU-IAM-001 verification recorded (PRs #7–#12).
- I implemented the account-lock security: lock after three failed passwords, direct-peer login attempt limits in PostgreSQL, current-actor verification for protected operations and authorized, audited staff unlock (#13–#16), and promoted the reviewed login security adapters to `develop` (#17).
- In `dlc-infra-postgres` I provisioned shared PostgreSQL connectivity for IAM and added a test that validates owner schema isolation (#3, #4).
- I documented the withdrawal of MFA from the project scope (`dlc-docs` #116) and opened the planning issues for the independent Clinical MongoDB replica set: provisioning, owner-scoped access and migration handoff, backup/restore and failure isolation, and secure authentication/transport/storage (`dlc-infra-mongo` #1–#4, `dlc-docs` #117).

## 3. Blockers and risks
- The API covers login and account lock only; role, lifecycle, recovery and session endpoints are still pending, so the persistence for those stories is not yet exercised by the API.
- Live integration with the Gateway and `dlc-front` was not verified this week.
- MFA was removed from the scope; account protection relies on passwords, lockout and attempt limits.

## 4. Plan for next week
- Implement the remaining IAM API use cases (role replacement, email verification, password reset, session validation for owner services and own-session revocation) and bring the login persistence to `develop`.
- Model the threats of the Gateway to Auth boundary (STRIDE).

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

PR titles follow Conventional Commits. Branches follow the team convention (`feat/*` → develop, `promotion/*` → qa) instead of `hu-xxx-dev`. `dlc-iam-api` and `dlc-infra-postgres` PRs added automated tests; `iam-core` has no framework or I/O dependencies.

## 6. Evidence links
All PRs below were authored by `camilo28k` in the `code-corhuila` organization and were retrieved through the GitHub API with their creation date (UTC-5) and final state.

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
