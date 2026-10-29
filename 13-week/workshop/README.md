# Week 13 - Threat Modeling and Security Backlog

<!-- CONFIG-START -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca Dental Care & Technology
- DOCS_REPO: https://github.com/code-corhuila/dlc-docs
- BOUNDARY: api-gateway -> auth-service (inbound HTTP requests)
- CLAIM_ISSUE: https://github.com/code-corhuila/dlc-docs/issues/124
- REFLECTION_DOC: https://docs.google.com/document/d/1xmY05LsNzr8W9yD9qynghd0fbPk-5RePA2aXGYyd3Pg/edit
- LIBRARY_BOOK: Enrique Daltabuit Godás, Leobardo Hernández Audelo, Guillermo Mallén Fullerton, José de Jesús Vázquez Gómez. La seguridad de la información. Mexico: Limusa / Grupo Noriega Editores, 2007. ISBN 978-968-18-6935-9. First edition. CORHUILA library call number: 005.8. Book identifier: 0006115. Consulted pages reported by the student: 350-351, 383-384, 436-437 and 502.
- LIBRARY_RECORD_TIME: 2026-10-28 18:58 America/Bogota (physical visit time confirmed by the student; institutional visit/loan record not independently inspected).
- STORIES: https://github.com/code-corhuila/dlc-docs/issues/126, https://github.com/code-corhuila/dlc-docs/issues/127, https://github.com/code-corhuila/dlc-docs/issues/128
<!-- CONFIG-END -->

## CP0 scope

The selected boundary is the inbound HTTP connection from `api-gateway` to
`auth-service`. Auth owns the `User` and `Session` aggregates and staff role
assignments. The analysis will distinguish caller identity, session eligibility
and authorization inside IAM from the Gateway's planned routing responsibilities.

Gateway integration is not implemented in the inspected repository revision.
This workshop models a documented target boundary and evaluates existing IAM
code where available; it does not claim that Gateway security controls already
run or that the distributed request path has been deployed.

## Inspected evidence

- Documentation baseline: [dlc-docs af9be7d](https://github.com/code-corhuila/dlc-docs/tree/af9be7d).
- [Authentication contract](https://github.com/code-corhuila/dlc-docs/blob/af9be7d/07-api/authentication.md): only internal staff authenticate; owner services enforce authorization.
- [Gateway responsibilities](https://github.com/code-corhuila/dlc-docs/blob/af9be7d/09-microservices/services/01-api-gateway/README.md): routing and trusted identity propagation are documented requirements.
- [ADR-016](https://github.com/code-corhuila/dlc-docs/blob/af9be7d/05-architecture/decisions/ADR-016-remove-mfa-from-project-scope.md): MFA is outside the approved project scope; password, session and authorization controls remain required.
- [Gateway dd234d4](https://github.com/code-corhuila/dlc-api-gateway/tree/dd234d42cca0f36ac8fcbb9225fee44ae317ca5d): `main`, `develop` and `qa` contain only README and CODEOWNERS. The README references an unrelated project; it is not Di Lucca implementation evidence.
- [IAM API develop 16a0d06](https://github.com/code-corhuila/dlc-iam-api/tree/16a0d06eae834622d7cbeae3ca6e8d83c591fb00): Java 21 / Spring Boot modules with login core and token adapters, not a complete HTTP integration.
- [IAM API feature revision c133d6a](https://github.com/code-corhuila/dlc-iam-api/tree/c133d6aa9877995dd1b988558b039c4eaf2e9dbc): login and staff-unlock controllers exist on a feature branch. Their presence is not evidence of promotion or deployment.

## Preparation status

- The boundary was claimed through issue #124 on October 28, 2026.
- The student confirms a physical CORHUILA library visit at 18:58 and the page
  references below, first edition and library call number 005.8.
  Online bibliographic metadata does not substitute for that physical visit.
- The supplied screenshots show the reflection document and teacher Editor
  access for jesus.gonzalez@corhuila.edu.co. The personal CP0 entry remains unverified.
- CONFIG follows the supplied English CP0 template. LIBRARY_RECORD_TIME has
  been recorded for CP2; STORIES will be added in CP3.
- This is a CP0 preparation increment. CP0 is not fully verified while these
  requirements remain pending. No CP1-CP9 deliverables are included.

## Physical book consultation notes

The student confirmed the following page/topic mappings from the physical book.
These notes identify sources for later checkpoints; they are not completed
STRIDE rows or security stories, and do not reproduce the book's text.

| Pages | Topic reported by the student | Intended project application |
|---|---|---|
| 350-351 | Identification and authentication | CP2: identity spoofing across the Gateway-to-Auth boundary |
| 383-384 | Authenticators and access control | CP3: acceptance criteria for IAM authorization |
| 436-437 | Logs and log analysis | CP2: repudiation and auditability of IAM operations |
| 502 | Denial-of-service attacks | CP2: availability risks at the Auth entry boundary |

Later analysis will mark book-supported threats or mitigations with [B] and
cite the relevant pages. At least one CP3 acceptance criterion will cite the
physical book. Specific controls and thresholds must be justified by Di Lucca
requirements or code; they must not be attributed to these pages without
checking the corresponding passage. No direct quotation is asserted here.

## Delivery boundaries

The individual fork holds the attack-surface map, proposed security CI and
weekly status. STRIDE belongs in a separate branch and open PR in `dlc-docs`;
the security stories belong in that repository and the team's Project.
The student's personal reflection remains in Google Docs.

## CP3 security backlog

Three P1 stories with risk score 6 are published in `dlc-docs`: [S-01 forged
identity](https://github.com/code-corhuila/dlc-docs/issues/126), [I-01 response
disclosure](https://github.com/code-corhuila/dlc-docs/issues/127), and [E-01
privilege escalation](https://github.com/code-corhuila/dlc-docs/issues/128).
Each issue has `security` and `P1` labels, verifiable acceptance criteria and
an automated test criterion. E-01 cites the physical book at pp. 383-384.
All three refer to the [STRIDE analysis PR](https://github.com/code-corhuila/dlc-docs/pull/125).
T-01 remains P1 in that analysis; Gateway transport implementation is pending.
Project 21 membership is pending verification because the available GitHub token
has repository access but lacks `read:project` and `project` scopes.

The scheduled CP0 interval was 18:30-18:40 America/Bogota on October 28.
This preparation occurred after that interval; actual GitHub timestamps remain
the evidence. No timestamp or published history is rewritten.
