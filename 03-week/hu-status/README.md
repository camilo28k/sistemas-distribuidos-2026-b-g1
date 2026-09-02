# Weekly Status - Week 03

* FULL_NAME: Harold Camilo Barrera Giraldo
* GITHUB_USER: camilo28k
* TEAM: OdontoSys
* SPRINT_GOAL: Define and consolidate the governance, scope, context, domain, and authentication data model documentation for the OdontoSys platform.

## 1. User stories worked this week

| HU ID  | Title                                                                        | Status (todo/doing/done) | Evidence (PR or commit URL)                                                                                                                                   |
| ------ | ---------------------------------------------------------------------------- | ------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| DOC-01 | Add governance, context, and domain documentation for the OdontoSys platform | done                     | [Commit — docs: add governance, context, and domain documentation](https://github.com/code-corhuila/dlc-docs/commit/1bd330edee0d264517633926fbd9107a62ae0bc2) |
| DOC-02 | Correct and add scope and domain documentation for the OdontoSys platform    | done                     | [Commit — docs: correct and add scope and domain documentation](https://github.com/code-corhuila/dlc-docs/commit/efd31cc2c8f30a767b8b2fb07e23f498b69fc1ff)    |
| DOC-03 | Define the authentication service data model and supporting diagrams         | done                     | [Commit — docs: add models, and images](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/6c13599a849a9f4b525715a4d9363fce165f6afd)         |

## 2. My individual contribution

* I contributed to the documentation of the **OdontoSys** platform.
* I worked on the governance documentation required to establish how project documentation should be created, organized, and maintained.
* I contributed to documenting the project context and domain.
* I worked on the project scope documentation to establish the boundaries of the OdontoSys platform.
* I added and corrected domain documentation to improve the consistency of the project's domain analysis.
* I reviewed existing documentation and made corrections where necessary to keep the scope, context, and domain information aligned.
* I designed and documented the **data model for the `auth-service`**.
* I defined **PostgreSQL as the source of truth** for users, roles, role assignments, and refresh tokens, while Redis is used for ephemeral authentication state.
* I documented the main authentication tables: `users`, `roles`, `user_roles`, and `refresh_tokens`, including their fields, types, constraints, and relationships.
* I defined indexes and modeling decisions to support authentication, authorization, token management, and data integrity.
* I documented the migration strategy and the relationship diagram for the authentication data model.
* I added the corresponding models and images to the project documentation repository.
* I documented these changes using Conventional Commits.

## 3. Blockers and risks

* Some domain details may continue to evolve as the project requirements are refined.
* Future architectural decisions may require updates to the current context and domain documentation.
* Changes to the project scope may require corresponding updates to the documentation.
* The authentication data model may need adjustments as the final authentication and authorization implementation is developed.
* Governance rules must remain synchronized with the documentation produced throughout the project.

## 4. Plan for next week

* Continue refining the domain documentation as the project requirements become more precise.
* Validate the authentication data model against the implementation requirements.
* Cross-check the project scope with the defined domain and service responsibilities.
* Keep the governance documentation aligned with the team's development and documentation practices.
* Continue translating the documented domain and data models into the project's requirements and architectural decisions.

## 5. Compliance self-check

* Conventional Commits - `type(scope): summary`
* Per-environment HU branch + PR to that environment (`hu-xxx-dev -> develop`, ...)
* Testable acceptance criteria
* Tests added/updated (unit / integration)
* DDD / hexagonal boundaries respected (domain has no I/O)
* No secrets; config via environment variables

## 6. Evidence links

* [Commit — docs: add governance, context, and domain documentation](https://github.com/code-corhuila/dlc-docs/commit/1bd330edee0d264517633926fbd9107a62ae0bc2)
* [Commit — docs: correct and add scope and domain documentation](https://github.com/code-corhuila/dlc-docs/commit/efd31cc2c8f30a767b8b2fb07e23f498b69fc1ff)
* [Commit — docs: add models, and images](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/6c13599a849a9f4b525715a4d9363fce165f6afd)
