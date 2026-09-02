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

## 2. My individual contribution

- I contributed to the requirements documentation by adding the User Story templates used to standardize the definition of project requirements.
- I documented the Non-Functional Requirements (NFRs) and created the requirements traceability matrix to establish relationships between requirements and the project artifacts.
- I contributed to defining the approved MVP User Stories that establish the functionality committed for the first implementation stage.
- I documented the Hexagonal Architecture and Architecture Decision Records (ADRs), establishing the architectural decisions and the separation between domain logic and external infrastructure.
- I contributed directly to the MVP implementation through the redesign of the authentication experience.
- I redesigned the **login and password recovery views** of the authentication module.
- I added and updated the visual resources required by the authentication and welcome interfaces, including the authentication background, Di Lucca logos, and welcome screen resources. :contentReference[oaicite:2]{index=2}
- I updated the Angular routes to include the welcome view and maintain the authentication navigation flow. :contentReference[oaicite:3]{index=3}
- I contributed to making the MVP executable as a distributed stack using Docker.
- I dockerized the main MVP components, including the Angular frontend, Spring Boot backend, and PostgreSQL database. :contentReference[oaicite:4]{index=4}
- I created the Docker configuration for the backend and frontend and configured Docker Compose to orchestrate the application services. :contentReference[oaicite:5]{index=5}
- I configured environment variables for database credentials, JWT secrets, ports, CORS, and optional mail configuration instead of keeping those values directly in the application configuration. :contentReference[oaicite:6]{index=6}
- I configured the backend health endpoint and the Docker service dependencies so the frontend can wait for the backend service to become healthy. :contentReference[oaicite:7]{index=7}
- I contributed to the integration of the authentication and deployment work with the existing OdontoSys/Di Lucca MVP structure.

## 3. Blockers and risks

- Some architectural decisions may still require validation as the MVP implementation continues.
- The MVP currently represents an initial implementation and may require further integration between the documented architecture and the implemented services.
- Authentication and authorization functionality may require additional validation against the approved User Stories and acceptance criteria.
- The Docker environment depends on correct configuration of environment variables and local infrastructure.
- Some requirements and architecture decisions may continue to evolve as implementation exposes new technical constraints.

## 4. Plan for next week

- Continue implementing and validating the approved MVP User Stories.
- Continue integrating the authentication functionality with the rest of the MVP.
- Validate the Dockerized environment and the communication between the frontend, backend, and database.
- Continue aligning the implementation with the documented Hexagonal Architecture and ADRs.
- Review the requirements traceability matrix as implementation progresses.
- Keep the requirements, architecture, and implementation documentation synchronized.

## 5. Compliance self-check

- Conventional Commits - `type(scope): summary`
- Per-environment HU branch + PR to that environment (`hu-xxx-dev -> develop`, ...)
- Testable acceptance criteria
- Tests added/updated (unit / integration)
- DDD / hexagonal boundaries respected (domain has no I/O)
- No secrets; config via environment variables

## 6. Evidence links

### Requirements and architecture

- [Commit — docs(requirements): add hu templates](https://github.com/code-corhuila/dlc-docs/commit/c2db297c9d105758dcc8ae938509f212db6e9e50)
- [Commit — docs(requirements): add NFRs and traceability matrix](https://github.com/code-corhuila/dlc-docs/commit/a079407b76c35b6d5d14b7e3217e481982966bf0)
- [Commit — docs(requirements): define approved MVP user stories](https://github.com/code-corhuila/dlc-docs/commit/846babc0060a29422d69bcbcad452103ff25053f)
- [Commit — docs(architecture): add hexagonal architecture and ADRs](https://github.com/code-corhuila/dlc-docs/commit/929f9534864047a19a276f63e6d9b81a5bdad241)

### MVP implementation

- [Commit — feat(auth): redesign login and recovery views](https://github.com/DanielPerez1822/di-lucca-mvp/commit/c127204126b603c0104b08c40546e01f2fccbd7f)
- [Commit — feat: dockerize Di Lucca stack](https://github.com/DanielPerez1822/di-lucca-mvp/commit/21a38a7090114a1cebe7a11ae8d81ebed9ceeca4)
- [Di Lucca MVP repository](https://github.com/DanielPerez1822/di-lucca-mvp)