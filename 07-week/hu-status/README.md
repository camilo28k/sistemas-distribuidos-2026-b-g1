<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.

     Your weekly grade is read AUTOMATICALLY from this file:

       07-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 07

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->

- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca
- SPRINT_GOAL: Update the Di Lucca governance and architecture documentation according to the new microservice boundaries, separating Patients as a microservice and classifying IAM as a transversal component, while completing the Week 07 communication and contract testing activities.

<!-- CONFIG-END -->

## 1. User stories worked this week

| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| DOC-XXX-001 | Update governance and security rules according to the new microservice structure | done | [Commit — docs(governance): update microservices and security rules](https://github.com/code-corhuila/dlc-docs/commit/d1d9149312e3935da0e472ed5ee2f46b97f3739f) / [PR #4](https://github.com/code-corhuila/dlc-docs/pull/4) |
| DOC-XXX-002 | Update the architecture to separate Patients and classify IAM as a transversal component | done | [Commit — docs(architecture): separate patients and classify iam as transversal](https://github.com/code-corhuila/dlc-docs/commit/94fb11b924713e74f7b8bc1891cb6b6d7a0a8e98) / [PR #10](https://github.com/code-corhuila/dlc-docs/pull/10) |
| DOC-XXX-003 | Update the project documentation affected by the new architecture decision | done | Governance and architecture documentation updates across `00-governance` through `07-api` |
| DOC-XXX-004 | Create the Session 1 diagram on inter-service communication | done | [Commit — docs: add diagram session 1 inter-service communication](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/2afdfe0ad10e6b311ae0881fb3d7d510112d151a) |
| DOC-XXX-005 | Create the Session 2 diagram on versioned contracts and contract testing | done | [Commit — docs: add diagram session 2 versioned contracts and contract testing](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/0b3056f12b663e4f4bc6d33c392c5fde41d6d76e) |
| DOC-XXX-006 | Complete the Week 07 Session 1 communication activity | done | [Commit — docs: add week 7 session 1 communication activity](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/e0d9d5fc185b241fa6956c41832466f35242db32) |
| DOC-XXX-007 | Complete the Week 07 Session 2 contract testing activity | done | [Commit — docs: add week 7 session 2 contract testing activity](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/f33fdebe8cba54df44daaa80e30b3ab387e708ec) |

## 2. My individual contribution

During Week 07, I contributed to the evolution of the **Di Lucca** architecture and documentation after the team reviewed the role of IAM within the distributed system.

### Governance and architecture changes

- Updated the governance documentation to reflect the new microservice structure and security rules.
- Updated the architecture documentation after identifying that **IAM should not be represented as an independent microservice**.
- Helped establish **IAM as a transversal component** of the platform.
- Updated the architecture to represent **Patients as a separate microservice**.
- Applied the corresponding changes to the project documentation affected by this architectural decision.
- Updated the documentation across the project sections from `00-governance` through `07-api` so that the different artifacts remain consistent with the new architecture.
- Contributed these changes using Conventional Commits and the project's documentation workflow.

### Week 07 Session 1

- Completed the Session 1 activity focused on **inter-service communication**.
- Created the Session 1 diagram explaining the communication between services.
- Documented the concepts covered during the activity.

### Week 07 Session 2

- Completed the Session 2 activity focused on **versioned contracts and contract testing**.
- Created the Session 2 diagram focused on versioned contracts and contract testing.
- Documented the concepts addressed during the activity.

### Main architectural decision

The main architectural adjustment during this week was the clarification of the role of IAM within the system:

- **IAM:** transversal component responsible for cross-cutting identity and access concerns.
- **Patients:** represented as an independent microservice with its own service responsibility.
- The affected governance, architecture, and related documentation were updated to maintain consistency with this decision.

## 3. Blockers and risks

- Changing the classification of IAM affected multiple documentation sections and required consistency across the project documentation.
- Separating Patients as a microservice requires the corresponding service boundaries and responsibilities to remain consistent across the architecture and API documentation.
- Changes to service boundaries may require further updates to communication and contract documentation.
- Inter-service communication contracts must remain synchronized as the service architecture evolves.
- Versioned contracts require compatibility rules to be maintained as services evolve independently.

## 4. Plan for next week

- Continue validating the new Patients microservice boundary against the project requirements and domain.
- Continue refining the transversal IAM responsibilities across the architecture.
- Review the API and communication documentation according to the updated service boundaries.
- Continue defining and validating versioned service contracts.
- Keep governance, architecture, API, and microservice documentation synchronized.
- Incorporate feedback from the team into the updated architecture.

## 5. Compliance self-check

- [x] Conventional Commits - `type(scope): summary`
- [x] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links

### Governance

- [Commit — docs(governance): update microservices and security rules](https://github.com/code-corhuila/dlc-docs/commit/d1d9149312e3935da0e472ed5ee2f46b97f3739f)
- [Pull Request #4 — docs(governance)](https://github.com/code-corhuila/dlc-docs/pull/4)
- [Merge commit — Pull Request #4](https://github.com/code-corhuila/dlc-docs/commit/e46d0d342142f23bfbf617791d4a73aad5e9a7c3)

### Architecture

- [Commit — docs(architecture): separate patients and classify iam as transversal](https://github.com/code-corhuila/dlc-docs/commit/94fb11b924713e74f7b8bc1891cb6b6d7a0a8e98)
- [Pull Request #10 — docs(architecture)](https://github.com/code-corhuila/dlc-docs/pull/10)
- [Merge commit — Pull Request #10](https://github.com/code-corhuila/dlc-docs/commit/f8f6321896afb3a56fbef99099efa7e363c26cfb)

### Session 1

- [Commit — docs: add diagram session 1 inter-service communication](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/2afdfe0ad10e6b311ae0881fb3d7d510112d151a)
- [Commit — docs: add week 7 session 1 communication activity](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/e0d9d5fc185b241fa6956c41832466f35242db32)

### Session 2

- [Commit — docs: add diagram session 2 versioned contracts and contract testing](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/0b3056f12b663e4f4bc6d33c392c5fde41d6d76e)
- [Commit — docs: add week 7 session 2 contract testing activity](https://github.com/camilo28k/sistemas-distribuidos-2026-b-g1/commit/f33fdebe8cba54df44daaa80e30b3ab387e708ec)