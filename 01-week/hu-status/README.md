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
| HU-00 | Document part 3 of the PDR | done | [Commit — docs: add part 3 of PDR](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/1d3fedb1b77b8124597671d0bcc83e6ef23d7551) |

## 2. My individual contribution

- I contributed to the project documentation for **OdontoSys**, focusing on the authentication and security component.
- I documented the **Auth Service** as a transversal component responsible for identity management and security.
- I defined the main responsibilities of the Auth Service, including user registration, login, JWT generation and validation, refresh tokens, role and permission management, MFA, session management, device management, and access control.
- I documented the initial role model of the system: Patient, Dentist, and Administrator.
- I documented the authorization flow in which authentication is centralized through Auth while each microservice applies authorization to its own resources.
- I documented the security mechanisms considered for the project, including JWT, refresh tokens, roles and permissions, MFA, session management, device control, rate limiting, auditing, HTTPS, and secure secret management.
- I documented the authentication micro frontend and its responsibilities for login, registration, password recovery, and MFA.
- I contributed this work as part of **Part 3 of the PDR**, reflected in the commit `docs: add part 3 of PDR`.

## 3. Blockers and risks

- Some project details are still being defined as the architecture and requirements continue to evolve.
- The final implementation details for authentication, authorization, MFA, sessions, and device management still need to be validated during development.
- The final repository organization and deployment configuration may require further definition.
- The exact implementation of roles and permissions must remain consistent across the Auth Service and the individual microservices.

## 4. Plan for next week

- Continue refining the PDR as the project requirements and architecture are validated.
- Continue defining the authentication and authorization flows for OdontoSys.
- Detail how JWT, roles, permissions, and security controls will be integrated with the microservices.
- Keep the documentation aligned with the implementation decisions made by the team.
- Continue working on the project according to the defined distributed architecture and microservice boundaries.

## 5. Compliance self-check

- Conventional Commits - `type(scope): summary`
- Per-environment HU branch + PR to that environment (`hu-xxx-dev -> develop`, ...)
- Testable acceptance criteria
- Tests added/updated (unit / integration)
- DDD / hexagonal boundaries respected (domain has no I/O)
- No secrets; config via environment variables

## 6. Evidence links

- [Commit — docs: add part 3 of PDR](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/1d3fedb1b77b8124597671d0bcc83e6ef23d7551)
