# Week 13 - Threat Modeling and Security Backlog

<!-- CONFIG-START -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca Dental Care & Technology
- DOCS_REPO: https://github.com/code-corhuila/dlc-docs
- BOUNDARY: api-gateway -> auth-service (inbound HTTP requests)
- CLAIM_ISSUE: https://github.com/code-corhuila/dlc-docs/issues/124
- REFLECTION_DOC: https://docs.google.com/document/d/1xmY05LsNzr8W9yD9qynghd0fbPk-5RePA2aXGYyd3Pg/edit
- LIBRARY_BOOK: Seguridad de la informacion; Enrique Daltabuit, Leobardo Hernandez, Guillermo Mallen, Jose de Jesus Vazquez. Edition, year, shelf mark, chapter and pages pending confirmation from the student's physical copy.
- LIBRARY_RECORD_TIME: Pending confirmation of the actual library visit or loan record.
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
- The student reports consulting a physical library book. Bibliographic details,
  the recorded visit time and the passages used still require confirmation.
- The reflection URL was supplied by the student. Document contents, CP0 entry
  and teacher Editor access have not been verified.
- CONFIG follows the supplied English CP0 template. LIBRARY_RECORD_TIME is
  completed in CP2 with the actual library record time; STORIES is added in CP3.
- This is a CP0 preparation increment. CP0 is not fully verified while these
  requirements remain pending. No CP1-CP9 deliverables are included.

## Delivery boundaries

The individual fork holds the attack-surface map, proposed security CI and
weekly status. STRIDE belongs in a separate branch and open PR in `dlc-docs`;
the security stories belong in that repository and the team's Project.
The student's personal reflection remains in Google Docs.

The scheduled CP0 interval was 18:30-18:40 America/Bogota on October 28.
This preparation occurred after that interval; actual GitHub timestamps remain
the evidence. No timestamp or published history is rewritten.
