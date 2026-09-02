<!--
 Your weekly grade is read AUTOMATICALLY from this file:
   01-week/hu-status/README.md  (inside YOUR fork). English.
-->

# Weekly Status - Week 01

- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: OdontoSys
- SPRINT_GOAL: Contribute to the initial project documentation and define the authentication and security foundation for OdontoSys.

## 1. User stories worked this week

| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-00 | Document Part 3 of the PDR, with a focus on authentication and security | done | [Commit — docs: add part 3 of PDR](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/1d3fedb1b77b8124597671d0bcc83e6ef23d7551) |
| HU-00 | Document the authentication and security foundation | done | [Commit — docs: add part 3 of PDR: authentication and security](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/462fdb9) |
| HU-00 | Update authentication documentation and diagrams to English | done | [Commit — docs: update English documentation and diagrams](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/59d2029) |

## 2. My individual contribution

During Week 01, I contributed to the initial project documentation for **OdontoSys**, with a specific focus on the authentication and security foundation.

My main contributions were:

- Documented **Part 3 of the Project Definition Report (PDR)** and its technical foundations.
- Documented the **Auth Service** as a transversal component responsible for identity management and security.
- Defined the main responsibilities of the Auth Service, including:
  - User registration
  - Login
  - JWT generation and validation
  - Refresh tokens
  - Role and permission management
  - Multi-Factor Authentication (MFA)
  - Session management
  - Device management
  - Access control
- Documented the initial role model of the system:
  - Patient
  - Dentist
  - Administrator
- Documented the authorization approach in which authentication is centralized through the Auth Service while each microservice applies authorization to its own resources.
- Documented security mechanisms considered for the platform, including JWT, refresh tokens, roles and permissions, MFA, session management, device control, rate limiting, auditing, HTTPS, and secure secret management.
- Documented the authentication micro frontend and its responsibilities for login, registration, password recovery, and MFA.
- Updated the authentication and security documentation and diagrams to English to maintain consistency with the project documentation standards.

### Main evidence

- `1d3fedb` — `docs: add part 3 of PDR`
- `462fdb9` — `docs: add part 3 of PDR: authentication and security`
- `59d2029` — `docs: update English documentation and diagrams`

## 3. Blockers and risks

- Some project requirements and architectural decisions were still being defined during the initial documentation stage.
- The final implementation details for authentication, authorization, MFA, sessions, and device management still needed to be validated during development.
- The final repository organization and deployment configuration were still subject to change.
- The role and permission model needed to remain consistent between the Auth Service and the individual microservices.

## 4. Plan for next week

- Continue refining the PDR as the project requirements and architecture evolve.
- Continue defining authentication and authorization flows.
- Detail how JWT, roles, permissions, and security controls will integrate with the microservices.
- Keep the documentation aligned with the implementation decisions made by the team.
- Continue contributing to the distributed architecture and microservice documentation.

## 5. Compliance self-check

- [x] Conventional Commits used for my commits.
- [ ] Per-environment HU branch and PR to the corresponding environment — no PR evidence is included in this report.
- [ ] Testable acceptance criteria — not applicable to the documentation work reported here.
- [ ] Tests added/updated — no test implementation was part of this contribution.
- [ ] DDD / hexagonal boundaries respected — this week's contribution focused on project documentation rather than implementation.
- [x] No secrets were added to the documentation.

## 6. Evidence links

- [Commit — docs: add part 3 of PDR](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/1d3fedb1b77b8124597671d0bcc83e6ef23d7551)
- [Commit — docs: add part 3 of PDR: authentication and security](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/462fdb9)
- [Commit — docs: update English documentation and diagrams](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/59d2029)