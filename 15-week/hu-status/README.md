<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       15-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 15

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca
- SPRINT_GOAL: Promote the validated IAM work to qa, deliver configurable SMTP with branded identity emails, run separate develop, qa and main stages with their own databases, and release version 3.0.0 of the IAM repositories to main.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-IAM-004 | Branded HTML verification and recovery emails over configurable SMTP; activation link on login | doing | [dlc-iam-api #116](https://github.com/code-corhuila/dlc-iam-api/pull/116), [dlc-iam-api #118](https://github.com/code-corhuila/dlc-iam-api/pull/118), [dlc-iam-portal #68](https://github.com/code-corhuila/dlc-iam-portal/pull/68), [dlc-iam-portal #69](https://github.com/code-corhuila/dlc-iam-portal/pull/69) |
| HU-IAM-001..006 | Promotion of IAM database and API work to qa | done | [dlc-iam-db #33](https://github.com/code-corhuila/dlc-iam-db/pull/33), [dlc-iam-db #34](https://github.com/code-corhuila/dlc-iam-db/pull/34), [dlc-iam-db #35](https://github.com/code-corhuila/dlc-iam-db/pull/35), [dlc-iam-api #117](https://github.com/code-corhuila/dlc-iam-api/pull/117), [dlc-infra-postgres #7](https://github.com/code-corhuila/dlc-infra-postgres/pull/7), [dlc-infra-postgres #8](https://github.com/code-corhuila/dlc-infra-postgres/pull/8) |
| Environments | Local develop, qa and main stages with separate databases | done | [dlc-infra #1](https://github.com/code-corhuila/dlc-infra/pull/1), [dlc-infra #17](https://github.com/code-corhuila/dlc-infra/pull/17), [dlc-infra #18](https://github.com/code-corhuila/dlc-infra/pull/18), [dlc-docs #174](https://github.com/code-corhuila/dlc-docs/pull/174) |
| Release 3.0.0 | IAM release to main with CHANGELOG, `v3.0.0` tag and GitHub release | done | [dlc-iam-api #119](https://github.com/code-corhuila/dlc-iam-api/pull/119), [dlc-iam-db #36](https://github.com/code-corhuila/dlc-iam-db/pull/36), [dlc-iam-portal #70](https://github.com/code-corhuila/dlc-iam-portal/pull/70) |

This report covers 9-15 November. `dlc-iam-api` #116, `dlc-iam-portal` #68 and `dlc-infra` #1 and #17 were merged into `develop`; the `chore(qa)`/promotion PRs into `qa`; `dlc-docs` #174 into `main`. The release PRs were approved and merged into `main`, and `v3.0.0` was tagged on each merge commit with its GitHub release. `dlc-iam-api` #117 was merged with no content change because the ADR-020 core was already present in `qa`.

## 2. My individual contribution
- I made the IAM SMTP transport configurable (STARTTLS, implicit SSL, timeouts) with secrets read from the git-ignored `.env`, logged only the provider failure type with the correlation id, and replaced the code emails with branded Spanish HTML + plain-text messages (`dlc-iam-api` #116), promoted to `qa` in #118.
- In the portal I moved the "Ya tengo un código de verificación" activation link to the login screen and clarified the code wording (`dlc-iam-portal` #68), promoted to `qa` with the Gateway smoke test in #69.
- I promoted the IAM database migrations (`dlc-iam-db` #33–#35) and the PostgreSQL scaffold, IAM connectivity and owner isolation (`dlc-infra-postgres` #7, #8) to `qa` with `cherry-pick -x` in PRs under 400 lines; after them `qa` matches `develop` in these repositories.
- In `dlc-infra` I added the GitHub baseline (#1) and the local develop, qa and main stages (#17), promoted to `qa` (#18): each stage is its own Compose project with its own PostgreSQL, MongoDB with TLS, network, ports and generated secrets; Liquibase contexts `dev`/`qa`/`prod` keep the synthetic seed out of `main`. develop and qa run the complete stack; main runs databases only. I documented it in `dlc-docs` #174.
- I verified the stages with 93 automated checks (healthy engines, migrations, seed per context, each API on its own stage database, admin login per stage with credentials and tokens rejected across stages, isolated data, networks, secrets and CA, cross-domain permissions denied, persistence after restart).
- I prepared release 3.0.0 of the IAM repositories: `release/3.0.0` branches cut from `main`, filled with `cherry-pick -x` from `qa`, with `CHANGELOG.md` and the full promotion trail (`dlc-iam-api` #119, `dlc-iam-db` #36, `dlc-iam-portal` #70). Local validation: 197 API tests and 186 portal tests passed, portal build succeeded, and each release tree equals `qa`. After the approved merge I tagged `v3.0.0` on `main` and published the GitHub release of each repository with the notes of its changelog.

## 3. Blockers and risks
- GitHub Actions does not run because of the organization billing lock; CI steps were reproduced locally.
- The stages run on a developer machine; shared hosting of develop, qa and main is pending.
- Other domains (Appointments, Billing) still have work in `develop` that is not in `qa`, which affects the full-system release.

## 4. Plan for next week
- Address the open security stories (#126–#128) and the IAM observability gaps (structured logs, metrics, traces).
- Support the team in the end-to-end demo of MVP 3.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

PR titles follow Conventional Commits. Promotion follows the team convention (`promotion/*` → qa, `release/*` → main) instead of `hu-xxx-*`. The email sender tests were updated and the API and portal suites were run locally. SMTP secrets moved to the git-ignored `.env`; `.env.example` and the stage env files contain no secrets. Acceptance criteria were not independently reviewed in this report.

## 6. Evidence links
All PRs below were authored by `camilo28k` in the `code-corhuila` organization and were retrieved through the GitHub API with their creation date (UTC-5) and final state.

**dlc-docs**

- 2026-11-11: [PR #174](https://github.com/code-corhuila/dlc-docs/pull/174) — docs(devops): record local develop, qa and main stage databases (merged).

**dlc-iam-api**

- 2026-11-10: [PR #116](https://github.com/code-corhuila/dlc-iam-api/pull/116) — fix(iam): configurable SMTP and branded HTML code emails (merged).
- 2026-11-10: [PR #117](https://github.com/code-corhuila/dlc-iam-api/pull/117) — chore(qa): promote ADR-020 service token core (1/2) (merged).
- 2026-11-10: [PR #118](https://github.com/code-corhuila/dlc-iam-api/pull/118) — chore(qa): promote configurable SMTP and branded code emails (merged).
- 2026-11-14: [PR #119](https://github.com/code-corhuila/dlc-iam-api/pull/119) — release(iam): promote QA-validated dlc-iam-api to 3.0.0 (merged).

**dlc-iam-db**

- 2026-11-10: [PR #33](https://github.com/code-corhuila/dlc-iam-db/pull/33) — chore(qa): promote staff display name, session version and audit reasons (merged).
- 2026-11-10: [PR #34](https://github.com/code-corhuila/dlc-iam-db/pull/34) — chore(qa): promote staff lifecycle audit requests and registration replay (merged).
- 2026-11-10: [PR #35](https://github.com/code-corhuila/dlc-iam-db/pull/35) — chore(qa): promote login IP windows, lock expiry audit, email limits and outbox state (merged).
- 2026-11-14: [PR #36](https://github.com/code-corhuila/dlc-iam-db/pull/36) — release(iam): promote QA-validated dlc-iam-db to 3.0.0 (merged).

**dlc-iam-portal**

- 2026-11-10: [PR #68](https://github.com/code-corhuila/dlc-iam-portal/pull/68) — fix(iam-portal): move account activation link to login and clarify code wording (merged).
- 2026-11-10: [PR #69](https://github.com/code-corhuila/dlc-iam-portal/pull/69) — chore(qa): promote gateway smoke and auth code links (merged).
- 2026-11-14: [PR #70](https://github.com/code-corhuila/dlc-iam-portal/pull/70) — release(iam): promote QA-validated dlc-iam-portal to 3.0.0 (merged).

**dlc-infra**

- 2026-11-10: [PR #1](https://github.com/code-corhuila/dlc-infra/pull/1) — chore(infra): add GitHub baseline for the infrastructure repository (merged).
- 2026-11-11: [PR #17](https://github.com/code-corhuila/dlc-infra/pull/17) — feat(infra): run develop, qa and main stages (merged).
- 2026-11-13: [PR #18](https://github.com/code-corhuila/dlc-infra/pull/18) — chore(release): promote local develop, qa and main stages to qa (merged).

**dlc-infra-postgres**

- 2026-11-10: [PR #7](https://github.com/code-corhuila/dlc-infra-postgres/pull/7) — chore(qa): promote PostgreSQL scaffold and IAM connectivity (merged).
- 2026-11-10: [PR #8](https://github.com/code-corhuila/dlc-infra-postgres/pull/8) — chore(qa): promote owner isolation, patients_app bootstrap and CI (merged).

Releases: [dlc-iam-api v3.0.0](https://github.com/code-corhuila/dlc-iam-api/releases/tag/v3.0.0), [dlc-iam-db v3.0.0](https://github.com/code-corhuila/dlc-iam-db/releases/tag/v3.0.0), [dlc-iam-portal v3.0.0](https://github.com/code-corhuila/dlc-iam-portal/releases/tag/v3.0.0).
