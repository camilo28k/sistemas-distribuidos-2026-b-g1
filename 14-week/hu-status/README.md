<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       14-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 14

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca
- SPRINT_GOAL: Complete the IAM API: own sessions, refresh, protected login and logout, staff administration, registration, identity emails, the RabbitMQ outbox and the Patients and Clinical permissions, and integrate it locally with Gateway, Front, Patients and Clinical.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-IAM-001 | Staff sign-in: refresh rotation, protected login, CSRF proof, cookie logout and trusted client IP | doing | [dlc-iam-api #34](https://github.com/code-corhuila/dlc-iam-api/pull/34), [dlc-iam-api #35](https://github.com/code-corhuila/dlc-iam-api/pull/35), [dlc-iam-api #36](https://github.com/code-corhuila/dlc-iam-api/pull/36), [dlc-iam-api #37](https://github.com/code-corhuila/dlc-iam-api/pull/37), [dlc-iam-api #38](https://github.com/code-corhuila/dlc-iam-api/pull/38), [dlc-iam-api #39](https://github.com/code-corhuila/dlc-iam-api/pull/39), [dlc-iam-api #40](https://github.com/code-corhuila/dlc-iam-api/pull/40), [dlc-iam-api #41](https://github.com/code-corhuila/dlc-iam-api/pull/41), [dlc-iam-api #42](https://github.com/code-corhuila/dlc-iam-api/pull/42), [dlc-iam-api #81](https://github.com/code-corhuila/dlc-iam-api/pull/81), [dlc-iam-api #82](https://github.com/code-corhuila/dlc-iam-api/pull/82), [dlc-iam-api #87](https://github.com/code-corhuila/dlc-iam-api/pull/87), [dlc-front #114](https://github.com/code-corhuila/dlc-front/pull/114), [dlc-api-gateway #18](https://github.com/code-corhuila/dlc-api-gateway/pull/18) |
| HU-IAM-002 | Staff role management: atomic role assignment and Patients/Clinical permissions | doing | [dlc-iam-api #47](https://github.com/code-corhuila/dlc-iam-api/pull/47), [dlc-iam-api #48](https://github.com/code-corhuila/dlc-iam-api/pull/48), [dlc-iam-api #83](https://github.com/code-corhuila/dlc-iam-api/pull/83), [dlc-iam-api #84](https://github.com/code-corhuila/dlc-iam-api/pull/84), [dlc-iam-api #85](https://github.com/code-corhuila/dlc-iam-api/pull/85), [dlc-iam-api #86](https://github.com/code-corhuila/dlc-iam-api/pull/86), [dlc-docs #160](https://github.com/code-corhuila/dlc-docs/pull/160), [dlc-docs #161](https://github.com/code-corhuila/dlc-docs/pull/161), [dlc-patient-api #33](https://github.com/code-corhuila/dlc-patient-api/pull/33), [dlc-clinical-api #122](https://github.com/code-corhuila/dlc-clinical-api/pull/122) |
| HU-IAM-003 | Staff account administration: registration, name updates, enabling and disabling | doing | [dlc-iam-api #43](https://github.com/code-corhuila/dlc-iam-api/pull/43), [dlc-iam-api #44](https://github.com/code-corhuila/dlc-iam-api/pull/44), [dlc-iam-api #50](https://github.com/code-corhuila/dlc-iam-api/pull/50), [dlc-iam-api #51](https://github.com/code-corhuila/dlc-iam-api/pull/51), [dlc-iam-api #52](https://github.com/code-corhuila/dlc-iam-api/pull/52), [dlc-iam-api #54](https://github.com/code-corhuila/dlc-iam-api/pull/54), [dlc-iam-api #55](https://github.com/code-corhuila/dlc-iam-api/pull/55), [dlc-iam-api #56](https://github.com/code-corhuila/dlc-iam-api/pull/56), [dlc-iam-api #57](https://github.com/code-corhuila/dlc-iam-api/pull/57), [dlc-iam-api #58](https://github.com/code-corhuila/dlc-iam-api/pull/58), [dlc-iam-api #59](https://github.com/code-corhuila/dlc-iam-api/pull/59), [dlc-iam-api #60](https://github.com/code-corhuila/dlc-iam-api/pull/60), [dlc-iam-api #62](https://github.com/code-corhuila/dlc-iam-api/pull/62), [dlc-iam-api #63](https://github.com/code-corhuila/dlc-iam-api/pull/63), [dlc-iam-api #64](https://github.com/code-corhuila/dlc-iam-api/pull/64), [dlc-iam-db #26](https://github.com/code-corhuila/dlc-iam-db/pull/26), [dlc-iam-db #27](https://github.com/code-corhuila/dlc-iam-db/pull/27) |
| HU-IAM-004 | Password recovery and email verification: neutral requests, limits and direct identity emails | doing | [dlc-iam-api #46](https://github.com/code-corhuila/dlc-iam-api/pull/46), [dlc-iam-api #61](https://github.com/code-corhuila/dlc-iam-api/pull/61), [dlc-iam-api #65](https://github.com/code-corhuila/dlc-iam-api/pull/65), [dlc-iam-api #66](https://github.com/code-corhuila/dlc-iam-api/pull/66), [dlc-iam-api #67](https://github.com/code-corhuila/dlc-iam-api/pull/67), [dlc-iam-api #68](https://github.com/code-corhuila/dlc-iam-api/pull/68), [dlc-iam-api #69](https://github.com/code-corhuila/dlc-iam-api/pull/69), [dlc-iam-api #70](https://github.com/code-corhuila/dlc-iam-api/pull/70), [dlc-iam-api #71](https://github.com/code-corhuila/dlc-iam-api/pull/71), [dlc-iam-api #72](https://github.com/code-corhuila/dlc-iam-api/pull/72), [dlc-iam-db #29](https://github.com/code-corhuila/dlc-iam-db/pull/29), [dlc-docs #156](https://github.com/code-corhuila/dlc-docs/pull/156) |
| HU-IAM-005 | Account-lock protection: audited administrative unlock | doing | [dlc-iam-api #45](https://github.com/code-corhuila/dlc-iam-api/pull/45), [dlc-iam-api #49](https://github.com/code-corhuila/dlc-iam-api/pull/49), [dlc-iam-api #53](https://github.com/code-corhuila/dlc-iam-api/pull/53), [dlc-iam-db #25](https://github.com/code-corhuila/dlc-iam-db/pull/25), [dlc-iam-db #28](https://github.com/code-corhuila/dlc-iam-db/pull/28) |
| HU-IAM-006 | Own sessions: owner-scoped session listing | doing | [dlc-iam-api #31](https://github.com/code-corhuila/dlc-iam-api/pull/31), [dlc-iam-api #32](https://github.com/code-corhuila/dlc-iam-api/pull/32), [dlc-iam-api #33](https://github.com/code-corhuila/dlc-iam-api/pull/33) |
| Integration | Outbox to RabbitMQ, runtime image and local integration with Gateway, Front, Patients and Clinical | done | [dlc-iam-api #73](https://github.com/code-corhuila/dlc-iam-api/pull/73), [dlc-iam-api #74](https://github.com/code-corhuila/dlc-iam-api/pull/74), [dlc-iam-api #75](https://github.com/code-corhuila/dlc-iam-api/pull/75), [dlc-iam-api #76](https://github.com/code-corhuila/dlc-iam-api/pull/76), [dlc-iam-api #77](https://github.com/code-corhuila/dlc-iam-api/pull/77), [dlc-iam-api #78](https://github.com/code-corhuila/dlc-iam-api/pull/78), [dlc-iam-api #79](https://github.com/code-corhuila/dlc-iam-api/pull/79), [dlc-iam-api #80](https://github.com/code-corhuila/dlc-iam-api/pull/80), [dlc-iam-db #30](https://github.com/code-corhuila/dlc-iam-db/pull/30), [dlc-iam-portal #67](https://github.com/code-corhuila/dlc-iam-portal/pull/67), [dlc-front #123](https://github.com/code-corhuila/dlc-front/pull/123), [dlc-patient-api #48](https://github.com/code-corhuila/dlc-patient-api/pull/48), [dlc-docs #166](https://github.com/code-corhuila/dlc-docs/pull/166) |

This report covers 2-8 November. All PRs listed were merged, except `dlc-clinical-api` #123, which was closed without merge after the administrator clinical read was approved by role in `dlc-docs` #161. The stories stay `doing` until they are promoted to `qa` and released.

## 2. My individual contribution
- I implemented owner-scoped own-session listing (`dlc-iam-api` #31–#33), atomic refresh rotation with reuse response, the atomic login IP attempt window, cookie-identified logout, protected staff login, the session-bound CSRF proof and the cookie-protected refresh and logout endpoints (#34–#42).
- I implemented staff administration: audited name updates, atomic role assignment, audited enabling and disabling with session revocation, administrator-scoped staff reads and the idempotent, audited administrative unlock (#43–#45, #47–#56, #58, #59), with the supporting migrations in `dlc-iam-db` (#25, #26).
- I implemented administrator staff registration with keyed replay proof and atomic persistence (#57, #60, #62–#64; `dlc-iam-db` #27) and integrated the missing login protection migrations into `develop` (`dlc-iam-db` #28).
- I defined direct identity email delivery (`dlc-docs` #156) and implemented neutral password recovery requests, replacement challenges, shared request limits, verification requests, the initial staff verification challenge and the direct TLS email adapter (#61, #65–#72; `dlc-iam-db` #29), and verified password reset atomicity in PostgreSQL (#46).
- I implemented the confirmed transactional outbox with bounded delivery to RabbitMQ and its PostgreSQL-to-RabbitMQ test (#73–#76; `dlc-iam-db` #30), packaged a non-root runtime image and a stage-scoped Compose runtime (#77, #78), fixed JDBC repository proxies (#79) and recorded the shared PostgreSQL develop integration (#80).
- I restricted the client IP to the trusted Gateway address (#81, #82, #87; `dlc-api-gateway` #18), defined and issued the Patients and Clinical JWT permissions and the administrator clinical read by role (`dlc-docs` #160, #161; #83–#86; `dlc-patient-api` #33; `dlc-clinical-api` #122), fixed the logout body in `dlc-front` (#114) and recorded the local IAM, Patients and Clinical integration (`dlc-iam-portal` #67, `dlc-front` #123, `dlc-patient-api` #48, `dlc-docs` #166).

## 3. Blockers and risks
- All this work is in `develop`; it still has to be promoted to `qa` with cherry-pick PRs under 400 lines.
- The local integration runs on a developer machine; shared environments for develop, qa and main are not yet defined.
- The identity email adapter needs a real SMTP provider configuration to deliver codes outside local tests.

## 4. Plan for next week
- Promote the IAM database, API and portal work to `qa`.
- Make the SMTP transport configurable and replace the code emails with branded messages.
- Run separate develop, qa and main stages with their own databases and prepare release 3.0.0 of the IAM repositories.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

PR titles follow Conventional Commits. Branches follow the team convention (`feat/*`, `fix/*` → develop) instead of `hu-xxx-dev`. Tests accompany the use cases, including PostgreSQL and RabbitMQ integration tests; `iam-core` has no framework or I/O dependencies.

## 6. Evidence links
All PRs below were authored by `camilo28k` in the `code-corhuila` organization and were retrieved through the GitHub API with their creation date (UTC-5) and final state.

**dlc-api-gateway**

- 2026-11-07: [PR #18](https://github.com/code-corhuila/dlc-api-gateway/pull/18) — fix(gateway): require trusted Front address at deployment (merged).

**dlc-clinical-api**

- 2026-11-06: [PR #122](https://github.com/code-corhuila/dlc-clinical-api/pull/122) — fix(clinical): enforce approved clinical permission names (merged).
- 2026-11-06: [PR #123](https://github.com/code-corhuila/dlc-clinical-api/pull/123) — fix(clinical): deny pending administrator grants (closed).

**dlc-docs**

- 2026-11-05: [PR #156](https://github.com/code-corhuila/dlc-docs/pull/156) — docs(iam): define direct identity email delivery (merged).
- 2026-11-06: [PR #160](https://github.com/code-corhuila/dlc-docs/pull/160) — docs(auth): define Patients and Clinical JWT permissions (merged).
- 2026-11-06: [PR #161](https://github.com/code-corhuila/dlc-docs/pull/161) — docs(iam): approve Administrator clinical read by role (merged).
- 2026-11-07: [PR #166](https://github.com/code-corhuila/dlc-docs/pull/166) — docs(devops): record local IAM Patients Clinical integration (merged).

**dlc-front**

- 2026-11-07: [PR #114](https://github.com/code-corhuila/dlc-front/pull/114) — fix(front): send required body on IAM logout (merged).
- 2026-11-07: [PR #123](https://github.com/code-corhuila/dlc-front/pull/123) — feat(front): select real Clinical portal for local integration (merged).

**dlc-iam-api**

- 2026-11-02: [PR #31](https://github.com/code-corhuila/dlc-iam-api/pull/31) — feat(iam-api): read owner-scoped session pages (merged).
- 2026-11-02: [PR #32](https://github.com/code-corhuila/dlc-iam-api/pull/32) — feat(iam-api): expose own-session listing endpoint (merged).
- 2026-11-02: [PR #33](https://github.com/code-corhuila/dlc-iam-api/pull/33) — feat(iam-api): deliver own-session listing endpoint to develop (merged).
- 2026-11-02: [PR #34](https://github.com/code-corhuila/dlc-iam-api/pull/34) — feat(iam-api): model atomic refresh rotation and reuse response (merged).
- 2026-11-02: [PR #35](https://github.com/code-corhuila/dlc-iam-api/pull/35) — feat(iam-api): rotate refresh credentials atomically in PostgreSQL (merged).
- 2026-11-02: [PR #36](https://github.com/code-corhuila/dlc-iam-api/pull/36) — feat(iam-api): prepare atomic login IP attempt window (merged).
- 2026-11-02: [PR #37](https://github.com/code-corhuila/dlc-iam-api/pull/37) — feat(iam-api): model cookie-identified logout revocation (merged).
- 2026-11-03: [PR #38](https://github.com/code-corhuila/dlc-iam-api/pull/38) — feat(iam-api): persist atomic logout revocation (merged).
- 2026-11-03: [PR #39](https://github.com/code-corhuila/dlc-iam-api/pull/39) — feat(iam-api): expose protected staff login (merged).
- 2026-11-04: [PR #40](https://github.com/code-corhuila/dlc-iam-api/pull/40) — feat(iam-api): issue session-bound CSRF proof (merged).
- 2026-11-04: [PR #41](https://github.com/code-corhuila/dlc-iam-api/pull/41) — feat(iam-api): expose cookie-protected logout (merged).
- 2026-11-04: [PR #42](https://github.com/code-corhuila/dlc-iam-api/pull/42) — feat(iam-api): expose cookie-protected refresh (merged).
- 2026-11-04: [PR #43](https://github.com/code-corhuila/dlc-iam-api/pull/43) — feat(iam-api): persist audited staff name updates (merged).
- 2026-11-04: [PR #44](https://github.com/code-corhuila/dlc-iam-api/pull/44) — feat(iam-api): expose audited staff name update (merged).
- 2026-11-04: [PR #45](https://github.com/code-corhuila/dlc-iam-api/pull/45) — feat(iam-api): define idempotent administrative unlock use case (merged).
- 2026-11-04: [PR #46](https://github.com/code-corhuila/dlc-iam-api/pull/46) — test(iam-api): verify password reset atomicity in PostgreSQL (merged).
- 2026-11-04: [PR #47](https://github.com/code-corhuila/dlc-iam-api/pull/47) — feat(iam-api): persist atomic role assignment changes (merged).
- 2026-11-04: [PR #48](https://github.com/code-corhuila/dlc-iam-api/pull/48) — feat(iam-api): expose staff role assignments (merged).
- 2026-11-04: [PR #49](https://github.com/code-corhuila/dlc-iam-api/pull/49) — feat(iam-api): persist audited administrative unlock (merged).
- 2026-11-04: [PR #50](https://github.com/code-corhuila/dlc-iam-api/pull/50) — feat(iam-api): define audited staff disabling use case (merged).
- 2026-11-04: [PR #51](https://github.com/code-corhuila/dlc-iam-api/pull/51) — feat(iam-api): define audited staff enabling use case (merged).
- 2026-11-04: [PR #52](https://github.com/code-corhuila/dlc-iam-api/pull/52) — feat(iam-api): persist staff disabling and session revocation (merged).
- 2026-11-04: [PR #53](https://github.com/code-corhuila/dlc-iam-api/pull/53) — feat(iam-api): expose audited administrator account unlock (merged).
- 2026-11-04: [PR #54](https://github.com/code-corhuila/dlc-iam-api/pull/54) — feat(iam-api): persist audited staff enabling (merged).
- 2026-11-04: [PR #55](https://github.com/code-corhuila/dlc-iam-api/pull/55) — feat(iam-api): expose audited staff disabling (merged).
- 2026-11-04: [PR #56](https://github.com/code-corhuila/dlc-iam-api/pull/56) — feat(iam-api): read administrator-scoped staff (merged).
- 2026-11-05: [PR #57](https://github.com/code-corhuila/dlc-iam-api/pull/57) — feat(iam-api): define staff registration use case (merged).
- 2026-11-05: [PR #58](https://github.com/code-corhuila/dlc-iam-api/pull/58) — test(iam-api): verify staff reads in PostgreSQL (merged).
- 2026-11-05: [PR #59](https://github.com/code-corhuila/dlc-iam-api/pull/59) — feat(iam-api): expose audited staff enabling (merged).
- 2026-11-05: [PR #60](https://github.com/code-corhuila/dlc-iam-api/pull/60) — feat(iam-api): add keyed registration replay proof (merged).
- 2026-11-05: [PR #61](https://github.com/code-corhuila/dlc-iam-api/pull/61) — feat(iam-api): define neutral password recovery requests (merged).
- 2026-11-05: [PR #62](https://github.com/code-corhuila/dlc-iam-api/pull/62) — feat(iam-api): persist staff registration atomically (merged).
- 2026-11-05: [PR #63](https://github.com/code-corhuila/dlc-iam-api/pull/63) — feat(iam-api): expose administrator staff registration (merged).
- 2026-11-05: [PR #64](https://github.com/code-corhuila/dlc-iam-api/pull/64) — test(iam-api): match PostgreSQL UUID ordering (merged).
- 2026-11-05: [PR #65](https://github.com/code-corhuila/dlc-iam-api/pull/65) — feat(iam-api): send recovery challenge after commit (merged).
- 2026-11-05: [PR #66](https://github.com/code-corhuila/dlc-iam-api/pull/66) — feat(iam-api): persist replacement recovery challenges (merged).
- 2026-11-05: [PR #67](https://github.com/code-corhuila/dlc-iam-api/pull/67) — feat(iam-api): add direct TLS identity email adapter (merged).
- 2026-11-05: [PR #68](https://github.com/code-corhuila/dlc-iam-api/pull/68) — feat(iam-api): enforce shared recovery request limits (merged).
- 2026-11-05: [PR #69](https://github.com/code-corhuila/dlc-iam-api/pull/69) — feat(iam-api): expose password recovery requests (merged).
- 2026-11-05: [PR #70](https://github.com/code-corhuila/dlc-iam-api/pull/70) — feat(iam-api): persist verification request challenges (merged).
- 2026-11-05: [PR #71](https://github.com/code-corhuila/dlc-iam-api/pull/71) — feat(iam-api): expose email verification requests (merged).
- 2026-11-05: [PR #72](https://github.com/code-corhuila/dlc-iam-api/pull/72) — feat(iam-api): issue initial staff verification challenge (merged).
- 2026-11-05: [PR #73](https://github.com/code-corhuila/dlc-iam-api/pull/73) — feat(iam-api): track confirmed outbox delivery (merged).
- 2026-11-05: [PR #74](https://github.com/code-corhuila/dlc-iam-api/pull/74) — feat(iam-api): publish confirmed IAM outbox events to RabbitMQ (merged).
- 2026-11-05: [PR #75](https://github.com/code-corhuila/dlc-iam-api/pull/75) — feat(iam-api): schedule bounded outbox delivery (merged).
- 2026-11-05: [PR #76](https://github.com/code-corhuila/dlc-iam-api/pull/76) — test(iam-api): verify PostgreSQL to RabbitMQ outbox delivery (merged).
- 2026-11-05: [PR #77](https://github.com/code-corhuila/dlc-iam-api/pull/77) — chore(iam-api): package non-root runtime image (merged).
- 2026-11-05: [PR #78](https://github.com/code-corhuila/dlc-iam-api/pull/78) — chore(iam-api): configure stage-scoped Compose runtime (merged).
- 2026-11-05: [PR #79](https://github.com/code-corhuila/dlc-iam-api/pull/79) — fix(iam-api): allow Spring proxies for JDBC repositories (merged).
- 2026-11-05: [PR #80](https://github.com/code-corhuila/dlc-iam-api/pull/80) — docs(iam-api): record shared PostgreSQL develop integration (merged).
- 2026-11-06: [PR #81](https://github.com/code-corhuila/dlc-iam-api/pull/81) — fix(iam-api): honor client IP only from trusted gateway (merged).
- 2026-11-06: [PR #82](https://github.com/code-corhuila/dlc-iam-api/pull/82) — fix(iam-api): disable automatic forwarded-header rewriting (merged).
- 2026-11-06: [PR #83](https://github.com/code-corhuila/dlc-iam-api/pull/83) — feat(iam-api): issue approved Patients JWT permissions (merged).
- 2026-11-06: [PR #84](https://github.com/code-corhuila/dlc-iam-api/pull/84) — fix(iam): validate current JWT permission claims (merged).
- 2026-11-07: [PR #85](https://github.com/code-corhuila/dlc-iam-api/pull/85) — feat(iam): grant administrator clinical read by role (merged).
- 2026-11-07: [PR #86](https://github.com/code-corhuila/dlc-iam-api/pull/86) — docs(iam): align permission catalog with ADR-019 (merged).
- 2026-11-07: [PR #87](https://github.com/code-corhuila/dlc-iam-api/pull/87) — fix(iam): require trusted Gateway address at deployment (merged).

**dlc-iam-db**

- 2026-11-04: [PR #25](https://github.com/code-corhuila/dlc-iam-db/pull/25) — feat(iam-db): retain audit reasons and unlock request keys (merged).
- 2026-11-04: [PR #26](https://github.com/code-corhuila/dlc-iam-db/pull/26) — feat(iam-db): audit staff lifecycle request reasons (merged).
- 2026-11-05: [PR #27](https://github.com/code-corhuila/dlc-iam-db/pull/27) — feat(iam-db): persist registration replay requests (merged).
- 2026-11-05: [PR #28](https://github.com/code-corhuila/dlc-iam-db/pull/28) — fix(iam-db): integrate missing login protection migrations (merged).
- 2026-11-05: [PR #29](https://github.com/code-corhuila/dlc-iam-db/pull/29) — feat(iam-db): store identity email request limits (merged).
- 2026-11-05: [PR #30](https://github.com/code-corhuila/dlc-iam-db/pull/30) — feat(iam-db): track owner outbox delivery state (merged).

**dlc-iam-portal**

- 2026-11-06: [PR #67](https://github.com/code-corhuila/dlc-iam-portal/pull/67) — test(iam-portal): verify requests through front and gateway (merged).

**dlc-patient-api**

- 2026-11-06: [PR #33](https://github.com/code-corhuila/dlc-patient-api/pull/33) — fix(patients): require approved IAM permission names (merged).
- 2026-11-07: [PR #48](https://github.com/code-corhuila/dlc-patient-api/pull/48) — docs(patients): explain private internal listener deployment (merged).
