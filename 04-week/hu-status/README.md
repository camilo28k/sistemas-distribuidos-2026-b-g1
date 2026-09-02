<!--
 Your weekly grade is read AUTOMATICALLY from this file:
   04-week/hu-status/README.md  (inside YOUR fork). English.
-->

# Weekly Status - Week 04

- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: OdontoSys
- SPRINT_GOAL: Finalize the requirements and architecture documentation and contribute to the MVP implementation through authentication interface improvements and Docker-based deployment of the Di Lucca stack.

## 1. User stories worked this week

| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| DOC-01 | Add User Story templates to the project requirements documentation | done | [Commit — docs(requirements): add hu templates](https://github.com/code-corhuila/dlc-docs/commit/c2db297c9d105758dcc8ae938509f212db6e9e50) |
| DOC-02 | Define non-functional requirements and the requirements traceability matrix | done | [Commit — docs(requirements): add NFRs and traceability matrix](https://github.com/code-corhuila/dlc-docs/commit/a079407b76c35b6d5d14b7e3217e481982966bf0) |
| DOC-03 | Define the approved MVP User Stories | done | [Commit — docs(requirements): define approved MVP user stories](https://github.com/code-corhuila/dlc-docs/commit/846babc0060a29422d69bcbcad452103ff25053f) |
| DOC-04 | Document the Hexagonal Architecture and Architecture Decision Records | done | [Commit — docs(architecture): add hexagonal architecture and ADRs](https://github.com/code-corhuila/dlc-docs/commit/929f9534864047a19a276f63e6d9b81a5bdad241) |
| MVP-01 | Redesign the authentication login and password recovery views | done | [Commit — feat(auth): redesign login and recovery views](https://github.com/DanielPerez1822/di-lucca-mvp/commit/c127204126b603c0104b08c40546e01f2fccbd7f) |
| MVP-02 | Dockerize the Di Lucca MVP stack | done | [Commit — feat: dockerize Di Lucca stack](https://github.com/DanielPerez1822/di-lucca-mvp/commit/21a38a7090114a1cebe7a11ae8d81ebed9ceeca4) |
| DOC-05 | Document Week 04 class sessions through summaries and diagrams | done | [Commit — docs: add Week 4 diagrams and class summaries](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/97a639e) |

## 2. My individual contribution

During Week 04, I contributed to the requirements and architecture documentation and also participated directly in the implementation and deployment of the Di Lucca MVP.

### Requirements and architecture documentation

- Added the User Story templates used to standardize the definition and documentation of project requirements.
- Documented the **Non-Functional Requirements (NFRs)** and contributed to the requirements traceability matrix.
- Defined and documented the approved MVP User Stories for the first implementation stage.
- Documented the **Hexagonal Architecture** and the corresponding **Architecture Decision Records (ADRs)**.
- Contributed to maintaining consistency between the requirements, architectural decisions, and the planned MVP implementation.

### Authentication interface

- Redesigned the authentication **login** view.
- Redesigned the **password recovery** view.
- Added and updated the visual resources required by the authentication and welcome interfaces, including the authentication background, Di Lucca logos, and welcome screen assets.
- Updated the Angular routes to include the welcome view and maintain the authentication navigation flow.

### Docker and MVP deployment

- Contributed to making the MVP executable as a distributed application stack using Docker.
- Dockerized the main MVP components:
  - Angular frontend
  - Spring Boot backend
  - PostgreSQL database
- Created Docker configuration for the frontend and backend.
- Configured Docker Compose to orchestrate the application services.
- Configured environment-based settings for database credentials, JWT secrets, ports, CORS, and optional mail configuration.
- Avoided keeping application secrets directly in the source configuration and provided environment-based configuration through the Docker setup.
- Configured the backend health endpoint and service dependencies so that the application stack can coordinate service startup.
- Contributed to the integration of the authentication and deployment changes with the existing Di Lucca MVP structure.

### Week 04 class documentation

- Documented the Week 04 class sessions through summaries and diagrams.
- Added the Session 1 and Session 2 diagrams.
- Added the corresponding class summaries as supporting documentation for the concepts studied during the week.

### Main evidence

- `c2db297` — `docs(requirements): add hu templates`
- `a079407` — `docs(requirements): add NFRs and traceability matrix`
- `846babc` — `docs(requirements): define approved MVP user stories`
- `929f953` — `docs(architecture): add hexagonal architecture and ADRs`
- `c127204` — `feat(auth): redesign login and recovery views`
- `21a38a` — `feat: dockerize Di Lucca stack`
- `97a639e` — `docs: add Week 4 diagrams and class summaries`

All of these contributions were documented using Conventional Commits.

## 3. Blockers and risks

- Some architectural decisions may still require validation as the MVP implementation continues.
- The MVP represents an initial implementation and may require further integration between the documented architecture and implemented functionality.
- Authentication and authorization functionality may require additional validation against the approved User Stories and acceptance criteria.
- The Docker environment depends on the correct configuration of environment variables and local infrastructure.
- Requirements and architectural decisions may continue to evolve as implementation exposes new technical constraints.
- The requirements traceability matrix must remain synchronized with changes to the MVP implementation.

## 4. Plan for next week

- Continue implementing and validating the approved MVP User Stories.
- Continue integrating and validating the authentication functionality.
- Validate the Dockerized environment and communication between the frontend, backend, and database.
- Continue aligning the implementation with the documented Hexagonal Architecture and ADRs.
- Review and update the requirements traceability matrix as implementation progresses.
- Keep requirements, architecture, and implementation documentation synchronized.

## 5. Compliance self-check

- [x] Conventional Commits used for my commits.
- [ ] Per-environment HU branch and PR to the corresponding environment — no PR evidence is included in this report.
- [ ] Testable acceptance criteria — acceptance criteria are defined in the project requirements documentation, but this report does not provide separate implementation-level evidence for each criterion.
- [ ] Tests added/updated — no test-specific commit or test execution evidence is included in this report.
- [x] Hexagonal Architecture was documented and considered in the architectural contribution.
- [x] No secrets were committed; environment-based configuration was used for sensitive application settings.

## 6. Evidence links

### Requirements and architecture

- [Commit — docs(requirements): add hu templates](https://github.com/code-corhuila/dlc-docs/commit/c2db297c9d105758dcc8ae938509f212db6e9e50)
- [Commit — docs(requirements): add NFRs and traceability matrix](https://github.com/code-corhuila/dlc-docs/commit/a079407b76c35b6d5d14b7e3217e481982966bf0)
- [Commit — docs(requirements): define approved MVP user stories](https://github.com/code-corhuila/dlc-docs/commit/846babc0060a29422d69bcbcad452103ff25053f)
- [Commit — docs(architecture): add hexagonal architecture and ADRs](https://github.com/code-corhuila/dlc-docs/commit/929f9534864047a19a276f63e6d9b81a5bdad241)

### MVP implementation

- [Commit — feat(auth): redesign login and recovery views](https://github.com/DanielPerez1822/di-lucca-mvp/commit/c127204126b603c0104b08c40546e01f2fccbd7f)
- [Commit — feat: dockerize Di Lucca stack](https://github.com/DanielPerez1822/di-lucca-mvp/commit/21a38a7090114a1cebe7a11ae8d81ebed9ceeca4)

### Week 04 class documentation

- [Commit — docs: add Week 4 diagrams and class summaries](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/97a639e)