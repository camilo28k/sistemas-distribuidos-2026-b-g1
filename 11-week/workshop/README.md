# Week 11 Observability Workshop - Di Lucca

<!-- CONFIG-START -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca
- DOCS_REPO: https://github.com/code-corhuila/dlc-docs
- REFLECTION_DOC: Pending personal Google Docs URL before the CP0 push.
- JOURNEY:
<!-- CONFIG-END -->

## Project scope

Di Lucca Dental Care & Technology is an internal platform for clinic staff.
My assigned component is transversal `auth-service`, which owns the `User`
and `Session` aggregates and the PostgreSQL `iam` schema. The `api-gateway`
routes staff requests to Auth; patients do not have application accounts.

## Source baseline

Project documentation revision: `71a89f6`.

- [Product backlog](https://github.com/code-corhuila/dlc-docs/blob/71a89f6/03-product/product-backlog.md)
- [Architecture](https://github.com/code-corhuila/dlc-docs/blob/71a89f6/05-architecture/overview.md)
- [Auth contract](https://github.com/code-corhuila/dlc-docs/blob/71a89f6/07-api/contracts/openapi/auth-service.yaml)

The workshop module will demonstrate instrumentation against documented
contracts. Its tests do not establish production deployment or full IAM
acceptance. Personal reflections are maintained separately in Google Docs.
