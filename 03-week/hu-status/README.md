<!--
 Your weekly grade is read AUTOMATICALLY from this file:
   03-week/hu-status/README.md  (inside YOUR fork). English.
-->

# Weekly Status - Week 03

- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: OdontoSys
- SPRINT_GOAL: Define and consolidate the governance, scope, context, domain, and authentication data model documentation for the OdontoSys platform.

## 1. User stories worked this week

| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| DOC-01 | Add governance, context, and domain documentation for the OdontoSys platform | done | [Commit — docs: add governance, context, and domain documentation](https://github.com/code-corhuila/dlc-docs/commit/1bd330edee0d264517633926fbd9107a62ae0bc2) |
| DOC-02 | Correct and add scope and domain documentation for the OdontoSys platform | done | [Commit — docs: correct and add scope and domain documentation](https://github.com/code-corhuila/dlc-docs/commit/efd31cc2c8f30a767b8b2fb07e23f498b69fc1ff) |
| DOC-03 | Define the authentication service data model and supporting diagrams | done | [Commit — docs: add models, and images](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/6c13599a849a9f4b525715a4d9363fce165f6afd) |
| DOC-04 | Document Week 03 class sessions through summaries and diagrams | done | [Commit — docs: add Week 3 diagrams and class summaries](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/2c15a2f) |
| DOC-05 | Add the Week 03 Session 1 diagram | done | [Commit — docs: add Week 3 Session 1 diagram](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/9e63b5f) |

## 2. My individual contribution

During Week 03, I contributed to the documentation and data modeling of the **OdontoSys** platform.

My contributions included:

### Governance, context, scope, and domain

- Contributed to the governance documentation that establishes how project documentation should be created, organized, and maintained.
- Documented the project context and domain.
- Contributed to defining the scope and boundaries of the OdontoSys platform.
- Added and corrected domain documentation to improve consistency and alignment with the project's scope and context.
- Reviewed existing documentation and corrected inconsistencies between scope, context, and domain information.

### Authentication data model

- Designed and documented the data model for the **`auth-service`**.
- Defined **PostgreSQL as the source of truth** for authentication and authorization data.
- Defined Redis as the storage mechanism for ephemeral authentication state.
- Documented the main authentication tables:
  - `users`
  - `roles`
  - `user_roles`
  - `refresh_tokens`
- Documented the fields, data types, constraints, indexes, and relationships associated with the authentication model.
- Documented modeling decisions related to authentication, authorization, token management, and data integrity.
- Documented the migration strategy for the authentication data model.
- Added the relationship/entity diagram and supporting model documentation.

### Week 03 class documentation

- Documented the Week 03 class sessions through summaries and diagrams.
- Added the Session 1 and Session 2 diagrams.
- Added the corresponding class summaries as supporting documentation for the concepts studied during the week.

### Main evidence

- `1bd330ed` — `docs: add governance, context, and domain documentation`
- `efd31cc` — `docs: correct and add scope and domain documentation`
- `6c13599` — `docs: add models, and images`
- `2c15a2f` — `docs: add Week 3 diagrams and class summaries`
- `9e63b5f` — `docs: add Week 3 Session 1 diagram`

All of these contributions were documented using Conventional Commits.

## 3. Blockers and risks

- Some domain details may continue to evolve as the project requirements are refined.
- Future architectural decisions may require updates to the current context, scope, and domain documentation.
- Changes to the project scope may require corresponding updates to the documentation and service boundaries.
- The authentication data model may need adjustments as the final authentication and authorization implementation is developed.
- Governance rules must remain synchronized with the documentation produced throughout the project.
- The authentication model must remain consistent with the project's domain and architectural decisions.

## 4. Plan for next week

- Continue refining the domain and scope documentation as the project requirements become more precise.
- Validate the authentication data model against the implementation requirements.
- Cross-check the project scope with the defined domain and service responsibilities.
- Keep governance documentation aligned with the team's development and documentation practices.
- Continue translating documented domain and data models into requirements and architectural decisions.
- Keep the documentation and diagrams synchronized with the evolution of the project.

## 5. Compliance self-check

- [x] Conventional Commits used for my commits.
- [ ] Per-environment HU branch and PR to the corresponding environment — no PR evidence is included in this report.
- [ ] Testable acceptance criteria — this week's contributions were primarily documentation and data modeling.
- [ ] Tests added/updated — no software test implementation was part of the documented contributions.
- [x] DDD concepts were considered in the domain and bounded-context documentation.
- [x] No secrets were added to the documentation.
- [x] Authentication data model documentation separates persistent data in PostgreSQL from ephemeral authentication state in Redis.

## 6. Evidence links

- [Commit — docs: add governance, context, and domain documentation](https://github.com/code-corhuila/dlc-docs/commit/1bd330edee0d264517633926fbd9107a62ae0bc2)
- [Commit — docs: correct and add scope and domain documentation](https://github.com/code-corhuila/dlc-docs/commit/efd31cc2c8f30a767b8b2fb07e23f498b69fc1ff)
- [Commit — docs: add models, and images](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/6c13599a849a9f4b525715a4d9363fce165f6afd)
- [Commit — docs: add Week 3 diagrams and class summaries](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/2c15a2f)
- [Commit — docs: add Week 3 Session 1 diagram](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/9e63b5f)