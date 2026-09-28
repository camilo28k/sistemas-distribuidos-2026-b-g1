<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.

     Your weekly grade is read AUTOMATICALLY from this file:

       08-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 08

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca
- SPRINT_GOAL: Align the Patients and transversal IAM documentation, complete the initial API contracts and architecture diagrams, and formalize appointment workflows and single-clinic operating rules.
<!-- CONFIG-END -->

## 1. User stories worked this week

| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| DOC-XXX-008 | Align Patients ownership and transversal IAM across governance, domain, requirements, architecture, and data documentation | done | [Commit](https://github.com/code-corhuila/dlc-docs/commit/881ee92a9ff8efb8c2eaf79eec901016a0e057bd) / [PR #12](https://github.com/code-corhuila/dlc-docs/pull/12) |
| DOC-XXX-009 | Clarify service classification, maintainer assignments, and ADR navigation | done | [Commit](https://github.com/code-corhuila/dlc-docs/commit/45d05b4ce338be5dd4f7b597de176836bb721ad6) / [PR #13](https://github.com/code-corhuila/dlc-docs/pull/13) |
| DOC-XXX-010 | Define API guidelines, authentication flows, service contracts, and endpoint traceability | done | [Commit](https://github.com/code-corhuila/dlc-docs/commit/6f30e70777a3bb2c410c6bf60cb0d0db8d74d73a) / [PR #14](https://github.com/code-corhuila/dlc-docs/pull/14) |
| DOC-XXX-011 | Document system boundaries and internal architecture with PlantUML diagrams | done | [Commit](https://github.com/code-corhuila/dlc-docs/commit/3c5e1227380a3decbc3df01317c1e552cdd7169e) / [PR #15](https://github.com/code-corhuila/dlc-docs/pull/15) |
| DOC-XXX-012 | Formalize appointment lifecycle, clinical assignments, and single-clinic operating rules | done | [Commit](https://github.com/code-corhuila/dlc-docs/commit/ef7f2d5927285640d28a6aeb6e6624fd8d3d4d4d) / [PR #27](https://github.com/code-corhuila/dlc-docs/pull/27) |

## 2. My individual contribution

- Aligned the project documentation with ADR-003: Patients owns administrative profiles, Clinical owns clinical records, and Auth/IAM is a transversal, independently deployable service for staff identity and access. Clarified patient deactivation, role permissions, service ownership, and traceability.
- Consolidated the reference for current service maintainers and repaired ADR links and classifications, distinguishing business services from transversal IAM and supporting infrastructure.
- Completed the initial API design: shared guidelines, authentication and authorization rules, an endpoint index, event contracts, OpenAPI contracts for Gateway and all five services, and private coordination contracts. Mapped operations to user stories and recorded implementation prerequisites.
- Added four PlantUML architecture views covering system context, the documentation-versus-runtime boundary, hexagonal service structure, and inward dependency direction. Updated the diagram index and contribution guidance.
- Added ADR-004 for the single-clinic operating rules. Updated the Appointments contract to describe staff-controlled appointment states, explicit completion after clinical readiness, ongoing clinical assignments, access-source revocation, and the separation of clinical facts from Billing-owned prices.
- Submitted and merged these documentation changes through PRs #12, #13, #14, #15, and #27. These contributions define and align the design; they do not claim that the related functional user stories are implemented.

## 3. Blockers and risks

- The OpenAPI contracts and coordination protocol still need service-owner review, implementation, and contract/provider tests. In particular, patient deactivation must remain safe during concurrent scheduling and clinical work.
- The MFA factor mechanism and some runtime security and deployment details remain to be selected and verified.
- The PlantUML source diagrams have not yet been rendered and visually checked.
- Appointment permissions, assignment revocation, care completion, and Billing reconciliation need implementation and negative authorization, concurrency, retry, and recovery tests before the functional stories can be marked done.

## 4. Plan for next week

- Review the API and event contracts with the service owners and resolve outstanding technical decisions.
- Render and verify the architecture diagrams, correcting any inconsistencies with the approved service boundaries.
- Turn the documented appointment, IAM, patient-deactivation, and care-closure rules into testable implementation tasks and contract tests.
- Keep requirements, ADRs, diagrams, API contracts, and service documentation synchronized as implementation proceeds.

## 5. Compliance self-check

- [ ] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

The completed work was documentation. The architecture artifacts respect DDD and hexagonal boundaries, but runtime boundaries and acceptance criteria still require code and test evidence. Some commits used `docs: summary` without a scope, and the PRs used documentation branches rather than per-environment HU branches.

## 6. Evidence links

- [Patients and IAM alignment commit](https://github.com/code-corhuila/dlc-docs/commit/881ee92a9ff8efb8c2eaf79eec901016a0e057bd) · [PR #12](https://github.com/code-corhuila/dlc-docs/pull/12) · [merge commit](https://github.com/code-corhuila/dlc-docs/commit/e3453a3cc5f0c25b34fa5e5b4777d5a43ff3ea5d)
- [Service classification and ADR navigation commit](https://github.com/code-corhuila/dlc-docs/commit/45d05b4ce338be5dd4f7b597de176836bb721ad6) · [PR #13](https://github.com/code-corhuila/dlc-docs/pull/13) · [merge commit](https://github.com/code-corhuila/dlc-docs/commit/344cd6e39ed5a4f2a9aea8f6a62c08c0e792edc3)
- [API guidelines and contracts commit](https://github.com/code-corhuila/dlc-docs/commit/6f30e70777a3bb2c410c6bf60cb0d0db8d74d73a) · [PR #14](https://github.com/code-corhuila/dlc-docs/pull/14) · [merge commit](https://github.com/code-corhuila/dlc-docs/commit/a768971adc072868358cc5850adf9552e47bb246)
- [Architecture diagrams commit](https://github.com/code-corhuila/dlc-docs/commit/3c5e1227380a3decbc3df01317c1e552cdd7169e) · [PR #15](https://github.com/code-corhuila/dlc-docs/pull/15) · [merge commit](https://github.com/code-corhuila/dlc-docs/commit/f523572f9fe0d389fa30d2f0a069c82a8023b587)
- [Appointment and operating-rules commit](https://github.com/code-corhuila/dlc-docs/commit/ef7f2d5927285640d28a6aeb6e6624fd8d3d4d4d) · [PR #27](https://github.com/code-corhuila/dlc-docs/pull/27) · [merge commit](https://github.com/code-corhuila/dlc-docs/commit/a8e28a04ccd91bb4da603cdad1b181ce19d191a1)