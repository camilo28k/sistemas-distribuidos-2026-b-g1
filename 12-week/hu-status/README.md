<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       12-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 12

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca
- SPRINT_GOAL: Expose the IAM API use cases for role replacement, email verification, password reset, public signing keys and own-session revocation, validate active sessions for owner services.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-IAM-001 | Staff sign-in: login persistence in develop and public signing keys | doing | [dlc-iam-api #20](https://github.com/code-corhuila/dlc-iam-api/pull/20), [dlc-iam-api #26](https://github.com/code-corhuila/dlc-iam-api/pull/26) |
| HU-IAM-002 | Staff role management: role replacement policy | doing | [dlc-iam-api #18](https://github.com/code-corhuila/dlc-iam-api/pull/18) |
| HU-IAM-004 | Password recovery and email verification: single-use verification and reset | doing | [dlc-iam-api #19](https://github.com/code-corhuila/dlc-iam-api/pull/19), [dlc-iam-api #24](https://github.com/code-corhuila/dlc-iam-api/pull/24), [dlc-iam-api #25](https://github.com/code-corhuila/dlc-iam-api/pull/25), [dlc-iam-api #27](https://github.com/code-corhuila/dlc-iam-api/pull/27), [dlc-iam-api #29](https://github.com/code-corhuila/dlc-iam-api/pull/29) |
| HU-IAM-005 | Account-lock protection: three-failure lock and current-actor verification in develop | doing | [dlc-iam-api #22](https://github.com/code-corhuila/dlc-iam-api/pull/22), [dlc-iam-api #23](https://github.com/code-corhuila/dlc-iam-api/pull/23) |
| HU-IAM-006 | Own sessions: active-session validation, versioned sessions and revocation | doing | [dlc-iam-api #21](https://github.com/code-corhuila/dlc-iam-api/pull/21), [dlc-iam-api #28](https://github.com/code-corhuila/dlc-iam-api/pull/28), [dlc-iam-api #30](https://github.com/code-corhuila/dlc-iam-api/pull/30), [dlc-iam-db #24](https://github.com/code-corhuila/dlc-iam-db/pull/24) |

All PRs listed were merged into `develop`. The STRIDE threat model of the same week (`dlc-docs` PR #125 and issues #124, #126–#128) is reported in Week 13 together with the threat modeling workshop, so it is not repeated here. The IAM stories stay `doing` until the endpoints are integrated and accepted through Gateway and Front.

## 2. My individual contribution
- I implemented the staff role replacement policy and single-use staff email verification, including its persistence and endpoint (`dlc-iam-api` #18, #19, #24, #25).
- I brought the login persistence, the three-failure account lock and current-actor verification to `develop` (#20, #22, #23) and exposed the public signing keys (#26).
- I implemented single-use password reset with session revocation, with its persistence and endpoint (#27, #29).
- I implemented active IAM session validation for owner services, atomic own-session revocation with its event, and the own-session revocation endpoint (#21, #28, #30); in `dlc-iam-db` I versioned owned sessions for the read contract (#24).

## 3. Blockers and risks
- Staff registration, administrative lifecycle endpoints, refresh and logout were still pending.
- The new endpoints were verified by their tests only; live integration through the Gateway and `dlc-front` was not yet run.
- The login security work had to be brought to `develop` from stacked HU branches (#20, #22, #23), so `develop` lagged behind the reviewed branches until this week.

## 4. Plan for next week
- Implement own-session listing, refresh rotation, protected login, logout and CSRF proof.
- Implement staff registration, name updates, enabling/disabling, role assignment and administrative unlock endpoints.
- Deliver identity emails, the outbox to RabbitMQ and the integration with Gateway, Front, Patients and Clinical.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

PR titles follow Conventional Commits. Branches follow the team convention (`feat/*` → develop) instead of `hu-xxx-dev`. Tests accompany the API use cases in their PRs; `iam-core` has no framework or I/O dependencies.

## 6. Evidence links
All PRs below were authored by `camilo28k` in the `code-corhuila` organization and were retrieved through the GitHub API with their creation date (UTC-5) and final state.


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
