<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.

     Your weekly grade is read AUTOMATICALLY from this file:

       09-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 09

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca
- SPRINT_GOAL: Consolidate architectural decisions and service boundaries, advance the project backlog and repository issue breakdown, and prepare the IAM database, API, and portal repositories for feature development.
<!-- CONFIG-END -->

## 1. User stories worked this week

| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| N/A — supporting documentation task | Document the Patients service and refine service boundaries | done | [Commit](https://github.com/code-corhuila/dlc-docs/commit/4fd65b0c79daedb49a8c03f9dac007e38c84875c) / [PR #34](https://github.com/code-corhuila/dlc-docs/pull/34) |
| N/A — supporting documentation task | Consolidate architectural decisions and the service catalog | done | [Commit](https://github.com/code-corhuila/dlc-docs/commit/b0423d06864a5790e0263231dda24d7a5c3b91a4) / [PR #35](https://github.com/code-corhuila/dlc-docs/pull/35) |
| N/A — supporting documentation task | Align governance, appointment reminders, and architecture decisions | done | [Commit](https://github.com/code-corhuila/dlc-docs/commit/a4464e04ddfb46ddad189466eddbd2fe548b1071) / [PR #45](https://github.com/code-corhuila/dlc-docs/pull/45) |
| N/A — backlog planning task | Advance the user-story backlog and organize issues for each repository | doing | Supplied Di Lucca — Backlog screenshot; direct issue URLs remain to be added |
| N/A — IAM setup task | Prepare the IAM database repository and address review findings | done | [Scaffold commit](https://github.com/code-corhuila/dlc-iam-db/commit/68de9e5437ca677a75bb23bb1b06e0e9671e1d37) / [Review fixes](https://github.com/code-corhuila/dlc-iam-db/commit/8cc73c1987ae9493a90c7923577504ae9781be63) / [PR #1](https://github.com/code-corhuila/dlc-iam-db/pull/1) |
| N/A — IAM setup task | Prepare the Spring Boot IAM API skeleton | done | [Commit](https://github.com/code-corhuila/dlc-iam-api/commit/1e721b438c08f4a6734f5cbcdfb80b5d84466601) |
| N/A — IAM setup task | Prepare the Angular IAM portal skeleton | done | [Commit](https://github.com/code-corhuila/dlc-iam-portal/commit/ac0b69f0503b65e52d795173061c66af016fde49) |

These rows track documentation, planning, and repository preparation. No official HU IDs were supplied for these supporting tasks, so no new IDs are assigned here. A `done` setup task does not mean that the related functional user stories are complete. The backlog screenshot still shows the new functional stories in Backlog.

## 2. My individual contribution

- I contributed under my GitHub account, `camilo28k`. The commits and merge entries attributed to this account in the supplied history represent my individual work.
- I updated the microservices documentation to include the Patients service and refine the boundaries between services. This work was integrated through `dlc-docs` PR #34.
- I consolidated the architectural decisions and service catalog through `dlc-docs` PR #35, and subsequently aligned governance, appointment reminders, and architecture decisions through PR #45.
- I advanced the Di Lucca backlog by organizing its user stories and the corresponding issues for each repository. The supplied board screenshot includes the IAM stories for staff sign-in, role management, account administration, email verification and password recovery, MFA and account-lock protection, and session review and revocation, together with Patients and Appointments stories. This is progress in planning and task breakdown; those functional stories remain pending implementation.
- I prepared the repositories assigned to me: `dlc-iam-db`, `dlc-iam-api`, and `dlc-iam-portal`. I added the initial README and CODEOWNERS files to establish repository documentation and ownership.
- In `dlc-iam-db`, I created the database repository scaffold, addressed repository review findings, and merged the preparation work through PR #1.
- In `dlc-iam-api`, I added the Spring Boot API skeleton. In `dlc-iam-portal`, I added the Angular portal skeleton. These changes established the initial project structures for subsequent IAM development.

## 3. Blockers and risks

- The IAM repositories now have their initial scaffolds, but the functional stories still require implementation, integration, and acceptance testing before they can be marked done.
- Direct links to the backlog tracking issues and repository-specific issues still need to be included in this report to complete the planning evidence.
- The supplied history establishes the reported changes, but does not establish successful builds, automated test results, deployment readiness, or completion of the functional acceptance criteria.

## 4. Plan for next week

- Continue implementing the IAM user stories using the prepared database, Spring Boot API, and Angular portal repositories.
- Prioritize staff sign-in and its required database, API, and portal tasks, following the approved contracts and acceptance criteria.
- Keep each user story linked to its repository issues, implementation PRs, and validation evidence, and update the backlog as work progresses.
- Validate the repository builds and add the tests required for the implemented behavior.
- Keep governance, architecture decisions, service boundaries, and appointment-reminder documentation aligned with implementation.

## 5. Compliance self-check

- [ ] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [ ] DDD / hexagonal boundaries respected (domain has no I/O)
- [ ] No secrets; config via environment variables

The supplied commit titles follow Conventional Commit naming, although some documentation commits omit a scope. The supplied PRs use documentation or scaffold branches. This report does not include enough code-review, acceptance-criteria, test, or configuration evidence to confirm the remaining checks; unchecked items indicate unverified compliance, not a confirmed failure.

## 6. Evidence links

- Patients service and service boundaries: [commit](https://github.com/code-corhuila/dlc-docs/commit/4fd65b0c79daedb49a8c03f9dac007e38c84875c) · [PR #34](https://github.com/code-corhuila/dlc-docs/pull/34) · [merge commit](https://github.com/code-corhuila/dlc-docs/commit/f4a3851779adb28d4525d11c86c9da3a9884829d).
- Architectural decisions and service catalog: [commit](https://github.com/code-corhuila/dlc-docs/commit/b0423d06864a5790e0263231dda24d7a5c3b91a4) · [PR #35](https://github.com/code-corhuila/dlc-docs/pull/35) · [merge commit](https://github.com/code-corhuila/dlc-docs/commit/162c6dba40af464801cc7f4c2f3473bec79807af).
- Governance, reminders, and architecture alignment: [commit](https://github.com/code-corhuila/dlc-docs/commit/a4464e04ddfb46ddad189466eddbd2fe548b1071) · [PR #45](https://github.com/code-corhuila/dlc-docs/pull/45) · [merge commit](https://github.com/code-corhuila/dlc-docs/commit/2b3a61676d10edb80a5acfb29f9ca22cf04cd5ab).
- IAM database ownership and documentation: [README and CODEOWNERS commit](https://github.com/code-corhuila/dlc-iam-db/commit/a9d8d3f701e305e80cac5f18489a364770ff392a).
- IAM database preparation: [scaffold commit](https://github.com/code-corhuila/dlc-iam-db/commit/68de9e5437ca677a75bb23bb1b06e0e9671e1d37) · [review-fix commit](https://github.com/code-corhuila/dlc-iam-db/commit/8cc73c1987ae9493a90c7923577504ae9781be63) · [PR #1](https://github.com/code-corhuila/dlc-iam-db/pull/1) · [merge commit](https://github.com/code-corhuila/dlc-iam-db/commit/43bf35fd29a88a64a4017c01ce3883ae5c57e2e2).
- IAM API preparation: [README and CODEOWNERS commit](https://github.com/code-corhuila/dlc-iam-api/commit/1755f52e6a8f77aed44cc7f15302f871f372f924) · [Spring Boot skeleton commit](https://github.com/code-corhuila/dlc-iam-api/commit/1e721b438c08f4a6734f5cbcdfb80b5d84466601).
- IAM portal preparation: [README and CODEOWNERS commit](https://github.com/code-corhuila/dlc-iam-portal/commit/8eef13b311fdd8f42827554be90da8659cf10adc) · [Angular skeleton commit](https://github.com/code-corhuila/dlc-iam-portal/commit/ac0b69f0503b65e52d795173061c66af016fde49).
- Backlog planning: supplied Di Lucca — Backlog screenshot. Direct board and repository issue links were not supplied.

Evidence note: this report is based on the supplied commit history, backlog screenshot, and my reported planning work. The linked GitHub changes could not be independently retrieved during preparation of this report, so detailed diffs and CI results were not verified.
