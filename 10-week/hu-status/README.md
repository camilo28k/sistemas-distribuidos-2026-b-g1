<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.

     Your weekly grade is read AUTOMATICALLY from this file:

       10-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 10

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Harold Camilo Barrera Giraldo
- GITHUB_USER: camilo28k
- TEAM: Di Lucca
- SPRINT_GOAL: Advance the six IAM user stories through portal implementation, automated tests, and shell integration contracts, while documenting the phased MFA delivery and the remaining live acceptance work.
<!-- CONFIG-END -->

## 1. User stories worked this week

| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-IAM-001 | Staff sign-in: portal foundation, shell integration, and interim password login | doing | [PR #9](https://github.com/code-corhuila/dlc-iam-portal/pull/9), [PR #14](https://github.com/code-corhuila/dlc-iam-portal/pull/14), [PR #49](https://github.com/code-corhuila/dlc-iam-portal/pull/49), [PR #58](https://github.com/code-corhuila/dlc-iam-portal/pull/58) |
| HU-IAM-002 | Staff role management through the shell HTTP capability | doing | [PR #33](https://github.com/code-corhuila/dlc-iam-portal/pull/33) |
| HU-IAM-003 | Staff account administration and directory presentation | doing | [PR #18](https://github.com/code-corhuila/dlc-iam-portal/pull/18), [PR #20](https://github.com/code-corhuila/dlc-iam-portal/pull/20), [PR #24](https://github.com/code-corhuila/dlc-iam-portal/pull/24), [PR #26](https://github.com/code-corhuila/dlc-iam-portal/pull/26), [PR #28](https://github.com/code-corhuila/dlc-iam-portal/pull/28), [PR #30](https://github.com/code-corhuila/dlc-iam-portal/pull/30), [PR #34](https://github.com/code-corhuila/dlc-iam-portal/pull/34), [PR #54](https://github.com/code-corhuila/dlc-iam-portal/pull/54) |
| HU-IAM-004 | Password recovery, password reset, and staff email verification | doing | [PR #16](https://github.com/code-corhuila/dlc-iam-portal/pull/16), [PR #36](https://github.com/code-corhuila/dlc-iam-portal/pull/36), [PR #38](https://github.com/code-corhuila/dlc-iam-portal/pull/38) |
| HU-IAM-005 | Account-lock protection: administrative unlock and phased MFA delivery | doing | [PR #48](https://github.com/code-corhuila/dlc-iam-portal/pull/48), [documentation PR #103](https://github.com/code-corhuila/dlc-docs/pull/103) |
| HU-IAM-006 | Review and revoke own sessions through the portal entry | doing | [PR #40](https://github.com/code-corhuila/dlc-iam-portal/pull/40), [PR #41](https://github.com/code-corhuila/dlc-iam-portal/pull/41), [PR #44](https://github.com/code-corhuila/dlc-iam-portal/pull/44), [PR #55](https://github.com/code-corhuila/dlc-iam-portal/pull/55) |

The portal PRs are merged into `develop`, but the complete user stories remain `doing`: live Auth/Gateway/shell integration and acceptance are still pending. MFA is deferred to a later delivery. Documentation evidence has the verification limitations described in section 6.

## 2. My individual contribution

- I implemented the IAM sign-in foundation, challenge expiry/retry handling, and shell module integration. I subsequently adapted the portal to the interim password-only login contract: authentication is confirmed through the shell session capability, and the standalone preview fails closed when that capability is unavailable.
- I implemented staff listing, detail, creation, name editing, disabling, enabling, and role assignment through the shell HTTP capability. I handled validation, stale data, conflicts, expired sessions, duplicate submissions, and terminal failure states.
- I implemented password recovery requests, password reset, and email verification, including distinct failure and uncertain-outcome states. Recovery and verification tokens are entered in forms rather than placed in URLs.
- I added the administrative unlock flow for locked staff, sending the reason, record version, and idempotency key to Auth. Authorization, auditing, and account eligibility remain Auth responsibilities.
- I implemented own-session listing and revocation, connected the sessions screen to the portal entry, and packaged the browser module for shell deployment. I improved the staff directory and session-card presentation and corrected authentication artwork sizing.
- I added or updated automated tests. The final artwork PR (#58) reports 181 passing tests across 20 files, along with successful skeleton and build checks. These are recorded PR results, not a new test run performed for this report.
- I updated IAM event/session documentation, authentication decisions, contributor guidance, frontend composition boundaries, the own-sessions route, and the phased MFA delivery. The documentation history supplied for this report attributes this work to `camilo28k`.

## 3. Blockers and risks

- The portal changes do not establish live acceptance. Auth, Gateway, and `dlc-front` must be integrated and verified together, including Docker deployment, login, email flows, account actions, and revocation across real devices.
- MFA remains pending; the interim password-only login must not be reported as completed MFA protection.
- The directory role filter and role counts apply only to the loaded page. Global role counts and server-side role filtering are not provided by the current API contract.
- Documentation PR URLs could not be independently retrieved during report preparation. Their availability needs to be restored or confirmed for grading.

## 4. Plan for next week

- Coordinate and validate the matching Auth and shell implementations for the interim login and own-sessions route.
- Deploy the packaged IAM portal and verify its behavior inside the running shell with live Gateway responses.
- Execute acceptance scenarios for role management, account administration, recovery, verification, unlock, and session revocation; record results against each HU.
- Continue the deferred MFA work according to the agreed delivery phases.
- Record environment promotion evidence and update HU status only after the corresponding acceptance criteria are met.

## 5. Compliance self-check

- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [ ] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [ ] DDD / hexagonal boundaries respected (domain has no I/O)
- [ ] No secrets; config via environment variables

Feature and fix commit titles follow Conventional Commit naming; documentation commits sometimes omit the optional scope. Portal PRs were verified as merged into `develop`, but the full environment promotion workflow was not verified. Automated test additions and results are documented in the PRs. The portal uses shell-owned HTTP/session capabilities; this report does not establish a complete domain-boundary or secrets/configuration audit. Formal HU acceptance criteria were not independently reviewed. Unchecked items indicate incomplete verification, not a confirmed violation.

## 6. Evidence links

Portal pull requests independently verified through the GitHub API: each of the following 24 PRs was authored by `camilo28k` and merged into `develop`.

- [Portal PR #9](https://github.com/code-corhuila/dlc-iam-portal/pull/9) — feat(iam): add sign-in foundation and challenge state.
- [Portal PR #11](https://github.com/code-corhuila/dlc-iam-portal/pull/11) — feat(iam): style staff sign-in preview.
- [Portal PR #13](https://github.com/code-corhuila/dlc-iam-portal/pull/13) — feat(iam): handle MFA challenge expiry and retry.
- [Portal PR #14](https://github.com/code-corhuila/dlc-iam-portal/pull/14) — feat(iam): add shell portal module integration.
- [Portal PR #16](https://github.com/code-corhuila/dlc-iam-portal/pull/16) — feat(iam): add password recovery request screen.
- [Portal PR #18](https://github.com/code-corhuila/dlc-iam-portal/pull/18) — feat(iam): list staff through shell HTTP.
- [Portal PR #20](https://github.com/code-corhuila/dlc-iam-portal/pull/20) — feat(iam): read staff detail through shell HTTP.
- [Portal PR #24](https://github.com/code-corhuila/dlc-iam-portal/pull/24) — feat(iam): create staff through shell HTTP.
- [Portal PR #26](https://github.com/code-corhuila/dlc-iam-portal/pull/26) — feat(iam): edit staff names through shell HTTP.
- [Portal PR #28](https://github.com/code-corhuila/dlc-iam-portal/pull/28) — feat(iam): disable staff through shell HTTP.
- [Portal PR #30](https://github.com/code-corhuila/dlc-iam-portal/pull/30) — feat(iam): enable staff through shell HTTP.
- [Portal PR #33](https://github.com/code-corhuila/dlc-iam-portal/pull/33) — feat(iam): assign staff roles through shell HTTP.
- [Portal PR #34](https://github.com/code-corhuila/dlc-iam-portal/pull/34) — fix(iam): block retries after terminal staff disable errors.
- [Portal PR #36](https://github.com/code-corhuila/dlc-iam-portal/pull/36) — feat(iam): add password reset flow.
- [Portal PR #38](https://github.com/code-corhuila/dlc-iam-portal/pull/38) — feat(iam): add staff email verification flow.
- [Portal PR #40](https://github.com/code-corhuila/dlc-iam-portal/pull/40) — feat(iam): list own sessions through shell HTTP.
- [Portal PR #41](https://github.com/code-corhuila/dlc-iam-portal/pull/41) — feat(iam): revoke own sessions through shell HTTP.
- [Portal PR #44](https://github.com/code-corhuila/dlc-iam-portal/pull/44) — feat(iam): route own sessions through portal entry.
- [Portal PR #46](https://github.com/code-corhuila/dlc-iam-portal/pull/46) — feat(iam): package portal module for shell deployment.
- [Portal PR #48](https://github.com/code-corhuila/dlc-iam-portal/pull/48) — feat(iam): unlock locked staff through shell HTTP.
- [Portal PR #49](https://github.com/code-corhuila/dlc-iam-portal/pull/49) — feat(iam): complete password login through shell session.
- [Portal PR #54](https://github.com/code-corhuila/dlc-iam-portal/pull/54) — feat(iam): align staff directory with management mockup.
- [Portal PR #55](https://github.com/code-corhuila/dlc-iam-portal/pull/55) — feat(iam): refresh own sessions view.
- [Portal PR #58](https://github.com/code-corhuila/dlc-iam-portal/pull/58) — fix(iam): stretch auth artwork within public frame.

Portal implementation commits from the supplied history, corroborated in local Git under Harold Camilo Barrera Giraldo and the same author email used by the `camilo28k` merge entries. These commits provide detail for the PRs above; they are not separate claims of completed user stories. Individual commit pages were not separately fetched.

- 2026-10-12: [fix(iam): stretch auth artwork within public frame](https://github.com/code-corhuila/dlc-iam-portal/commit/6410bab76d7c095a22e2628c8e49b6184fb3bc6b).
- 2026-10-12: [feat(iam): refresh own sessions view](https://github.com/code-corhuila/dlc-iam-portal/commit/986be4b07a76fc94144fd9811f20653fcdec0dc1).
- 2026-10-12: [feat(iam): align staff directory with management mockup](https://github.com/code-corhuila/dlc-iam-portal/commit/7726c0f4f221147c208aa79d8b38b8a80ec03cfb).
- 2026-10-11: [fix(iam): distinguish staff unlock validation errors](https://github.com/code-corhuila/dlc-iam-portal/commit/5800013901cbc0ef5654ec35cd0bee98d1087364).
- 2026-10-11: [feat(iam): complete password login through shell session](https://github.com/code-corhuila/dlc-iam-portal/commit/72219f47e7e472c1d4711a2572433e6fc22eb7c6).
- 2026-10-11: [feat(iam): unlock locked staff through shell HTTP](https://github.com/code-corhuila/dlc-iam-portal/commit/37208fd1483cef2adf36fcfb773106cf09ffdcf4).
- 2026-10-11: [feat(iam): package portal module for shell deployment](https://github.com/code-corhuila/dlc-iam-portal/commit/026b4b579960f21718acae663f6ffc2c12d0cbe5).
- 2026-10-11: [feat(iam): route own sessions through portal entry](https://github.com/code-corhuila/dlc-iam-portal/commit/fe3d7bd7c677460d7729d05324d686ceeffa7fd2).
- 2026-10-11: [feat(iam): revoke own sessions through shell HTTP](https://github.com/code-corhuila/dlc-iam-portal/commit/a1ff3f48053f950a7a21151427ea060f83c26380).
- 2026-10-11: [feat(iam): list own sessions through shell HTTP](https://github.com/code-corhuila/dlc-iam-portal/commit/6b07ff8b71802df0904bc5f015f53859f171c314).
- 2026-10-11: [fix(iam): allow retry after email verification rate limit](https://github.com/code-corhuila/dlc-iam-portal/commit/ae33b56ed80070ce9572c3cf4b52fde2fca22331).
- 2026-10-11: [fix(iam): clarify email verification outcomes](https://github.com/code-corhuila/dlc-iam-portal/commit/2da370a248a97e5d789aedc39e6d7c53916566df).
- 2026-10-11: [fix(iam): clarify uncertain password reset outcomes](https://github.com/code-corhuila/dlc-iam-portal/commit/141e1aee3a09ca6c13de2ac47a8f771d8720d131).
- 2026-10-11: [feat(iam): add staff email verification flow](https://github.com/code-corhuila/dlc-iam-portal/commit/1fe36fe2da909c18927288dbc8d00d751c16141f).
- 2026-10-10: [feat(iam): add password reset flow](https://github.com/code-corhuila/dlc-iam-portal/commit/832926e54d57872456af53d976bbd0da00808b7b).
- 2026-10-10: [fix(iam): reject unchanged staff role assignments](https://github.com/code-corhuila/dlc-iam-portal/commit/e90fc684b9255c777657eb35911227a9a463db2d).
- 2026-10-10: [refactor(iam): share assignable staff roles](https://github.com/code-corhuila/dlc-iam-portal/commit/bc887cff09ba93c8c3fdd816583af472748b6fdf).
- 2026-10-10: [fix(iam): keep terminal disable states blocked on rebind](https://github.com/code-corhuila/dlc-iam-portal/commit/25a62f7351145cc5c65c5ca9761a994e7b4b510e).
- 2026-10-10: [fix(iam): preserve conflict editing and verify retry](https://github.com/code-corhuila/dlc-iam-portal/commit/e1a0e677e0f6ac2079bcf784a9fba17c2d742b7d).
- 2026-10-10: [fix(iam): block retries after terminal staff disable errors](https://github.com/code-corhuila/dlc-iam-portal/commit/4568dcc7e71a7e6d16a44401d88c3e2d62061738).
- 2026-10-10: [feat(iam): assign staff roles through shell HTTP](https://github.com/code-corhuila/dlc-iam-portal/commit/2b11096a4940a87ff20413b8c785ce87bd45419e).
- 2026-10-10: [feat(iam): enable staff through shell HTTP](https://github.com/code-corhuila/dlc-iam-portal/commit/73137baec1425d2a991adbbc2b6a21efcbb2a8ac).
- 2026-10-10: [fix(iam): refresh staff disable key after conflict](https://github.com/code-corhuila/dlc-iam-portal/commit/be9d8e74957d5f8c91848af13a3794c4b086fa0e).
- 2026-10-10: [feat(iam): disable staff through shell HTTP](https://github.com/code-corhuila/dlc-iam-portal/commit/1d5e9aee20b06033caacc092bf35ba65c666efd8).
- 2026-10-10: [feat(iam): edit staff names through shell HTTP](https://github.com/code-corhuila/dlc-iam-portal/commit/78767ef8b7c87bdf4f99d853dbe01b707bf92582).
- 2026-10-10: [fix(iam): require explicit staff role selection](https://github.com/code-corhuila/dlc-iam-portal/commit/f15a792ae32bf72b0803844e28a03b35213e4d3e).
- 2026-10-10: [feat(iam): create staff through shell HTTP](https://github.com/code-corhuila/dlc-iam-portal/commit/740b0b227bc76df708dd970809b1e2f61afb0d61).
- 2026-10-10: [feat(iam): read staff detail through shell HTTP](https://github.com/code-corhuila/dlc-iam-portal/commit/7af0a135c9130a26343e563d5c5d7183605512fb).
- 2026-10-10: [fix(iam): handle session expiry and stale staff pages](https://github.com/code-corhuila/dlc-iam-portal/commit/ab51963ba33a67d6f789280f31c44b1e07a6ba98).
- 2026-10-10: [feat(iam): list staff through shell HTTP](https://github.com/code-corhuila/dlc-iam-portal/commit/d3cc5a5a1868c72a1b3f5b2735e9d84438ec1da4).
- 2026-10-09: [feat(iam): add password recovery request screen](https://github.com/code-corhuila/dlc-iam-portal/commit/dd1efdd1dc61d8151f0f6681eedeaf7855161ffd).
- 2026-10-09: [feat(iam): add shell portal module integration](https://github.com/code-corhuila/dlc-iam-portal/commit/b0842002c64f48a38424b425e9cb19cfb3afcad3).
- 2026-10-08: [feat(iam): handle MFA challenge expiry and retry](https://github.com/code-corhuila/dlc-iam-portal/commit/c91bed9eadb9933eb2a6b0e3b7c9c77c4d4ca3a0).
- 2026-10-08: [docs(iam): update sign-in preview status](https://github.com/code-corhuila/dlc-iam-portal/commit/bf85a48a6a3c430748fce84e111cf94c968b9e8f).
- 2026-10-07: [feat(iam): style staff sign-in preview](https://github.com/code-corhuila/dlc-iam-portal/commit/585a112670042fe379ea748021b7d0e017752b78).
- 2026-10-07: [fix(iam): block fake login in production build](https://github.com/code-corhuila/dlc-iam-portal/commit/2412d4896a6cfa277206cfa301e83f77b1f0ee46).
- 2026-10-07: [refactor(iam): use fake login adapter for standalone portal](https://github.com/code-corhuila/dlc-iam-portal/commit/9c1a00d33b95b176e59ad7088768cd762b5e8ea7).
- 2026-10-07: [style: add final newlines to portal files](https://github.com/code-corhuila/dlc-iam-portal/commit/db389c1dacb3ac9394d19c40578e5f527308b644).
- 2026-10-07: [fix(iam): prevent duplicate login requests and run tests in CI](https://github.com/code-corhuila/dlc-iam-portal/commit/1c00c9437d4bbf78d9cdf050a7a8312f49fc2e18).
- 2026-10-06: [feat(iam): add sign-in foundation and challenge state](https://github.com/code-corhuila/dlc-iam-portal/commit/6ee62c96f8a9b5fc6d55c53b13f2f97848814a70).
- 2026-10-05: [fix(iam-portal): validate skeleton structure in CI](https://github.com/code-corhuila/dlc-iam-portal/commit/e2dee8835aa3dfa51e1f29d76b7f3608c90d8d2a).

Additional portal setup evidence supplied for October 5; PR #8 was not independently fetched in this review:

- Angular portal skeleton integration: [PR #8](https://github.com/code-corhuila/dlc-iam-portal/pull/8) · [merge commit](https://github.com/code-corhuila/dlc-iam-portal/commit/86955e307d1af84c3291acae76615811bcced4f6).

Documentation PRs attributed to `camilo28k` in the supplied history. Their online endpoints were unavailable during preparation; local Git additionally corroborates PRs #100–#102 and the implementation commits listed below.

- [Documentation PR #58](https://github.com/code-corhuila/dlc-docs/pull/58) — .
- [Documentation PR #59](https://github.com/code-corhuila/dlc-docs/pull/59) — .
- [Documentation PR #60](https://github.com/code-corhuila/dlc-docs/pull/60) — .
- [Documentation PR #70](https://github.com/code-corhuila/dlc-docs/pull/70) — .
- [Documentation PR #71](https://github.com/code-corhuila/dlc-docs/pull/71) — .
- [Documentation PR #72](https://github.com/code-corhuila/dlc-docs/pull/72) — .
- [Documentation PR #100](https://github.com/code-corhuila/dlc-docs/pull/100) — .
- [Documentation PR #101](https://github.com/code-corhuila/dlc-docs/pull/101) — .
- [Documentation PR #102](https://github.com/code-corhuila/dlc-docs/pull/102) — .
- [Documentation PR #103](https://github.com/code-corhuila/dlc-docs/pull/103) — .

- 2026-10-11: [docs(iam): accept own sessions route](https://github.com/code-corhuila/dlc-docs/commit/9325686490eb0fb10c39756974162ee1d49433be).
- 2026-10-11: [docs(iam): define own sessions portal route](https://github.com/code-corhuila/dlc-docs/commit/bdbe17bad8f05b52ee4c90b024cb029682fbdc86).
- 2026-10-09: [docs: specify front composition and portal integration contract](https://github.com/code-corhuila/dlc-docs/commit/9c875afdef4f5d436770093441fd7b168b46f36a).
- 2026-10-08: [docs: document dlc-front architecture and portal boundaries](https://github.com/code-corhuila/dlc-docs/commit/bca0f37be6167da6ec346bcf333bed357d794a31).
- 2026-10-05: [docs: update auth readiness and contributor guidance](https://github.com/code-corhuila/dlc-docs/commit/1380d28a254b9b2c3c4457ad3b46be0ab861c82a).
- 2026-10-05: [docs(microservices): align session model and authentication decisions](https://github.com/code-corhuila/dlc-docs/commit/b595a53ba9d762015c3431f9acc501322c528528).
- 2026-10-05: [docs: align gateway operations and auth service overview](https://github.com/code-corhuila/dlc-docs/commit/200f56093fb5a35635fcf3d6100ecd2827a4e54a).
- 2026-10-05: [docs: align IAM lifecycle events and gateway contracts](https://github.com/code-corhuila/dlc-docs/commit/1781ab73642b5bf9627a1e6df8da9358f16776d2).

Documentation merge commit URLs supplied alongside the PRs:

- [Merge for documentation PR #58](https://github.com/code-corhuila/dlc-docs/commit/).
- [Merge for documentation PR #59](https://github.com/code-corhuila/dlc-docs/commit/).
- [Merge for documentation PR #60](https://github.com/code-corhuila/dlc-docs/commit/).
- [Merge for documentation PR #70](https://github.com/code-corhuila/dlc-docs/commit/).
- [Merge for documentation PR #71](https://github.com/code-corhuila/dlc-docs/commit/).
- [Merge for documentation PR #72](https://github.com/code-corhuila/dlc-docs/commit/).

Earlier database evidence retained for traceability only, not claimed again as Week 10 work. These links were supplied with the history and were already covered by Week 09:

- Database scaffold: [PR #1](https://github.com/code-corhuila/dlc-iam-db/pull/1) · [merge commit](https://github.com/code-corhuila/dlc-iam-db/commit/43bf35fd29a88a64a4017c01ce3883ae5c57e2e2).
- Repository review fixes: [commit](https://github.com/code-corhuila/dlc-iam-db/commit/8cc73c1987ae9493a90c7923577504ae9781be63).
- Database repository scaffold: [commit](https://github.com/code-corhuila/dlc-iam-db/commit/68de9e5437ca677a75bb23bb1b06e0e9671e1d37).
- README and CODEOWNERS: [commit](https://github.com/code-corhuila/dlc-iam-db/commit/a9d8d3f701e305e80cac5f18489a364770ff392a).


