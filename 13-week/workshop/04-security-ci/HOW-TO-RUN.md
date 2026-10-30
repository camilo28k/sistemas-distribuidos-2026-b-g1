# CP4 - IAM security gate proposal

After team review, add `security.yml` and the Java test to `dlc-iam-api`.
GitHub does not execute workflows from this workshop directory. The proposal
scans Git history for secrets, audits Maven dependencies, and tests negative
authorization on pull requests to `develop`.

## Story and test boundary

The test covers [CP3 S-01, "Reject forged staff identity at Auth unlock"](https://github.com/code-corhuila/dlc-docs/issues/126),
based on [STRIDE PR #125](https://github.com/code-corhuila/dlc-docs/pull/125).
It targets `POST /api/v1/auth/staff/{id}/unlocks` in
[`dlc-iam-api` revision c133d6a](https://github.com/code-corhuila/dlc-iam-api/tree/c133d6aa9877995dd1b988558b039c4eaf2e9dbc).
The test uses the real controller and IAM use cases with mocked token, role and
persistence ports. It checks 401 without a bearer, 403 for a DENTIST bearer
with forged Administrator headers, and no store unlock. It does not prove
database audit entries or Gateway header stripping.

## Run and evidence

Copy `ForgedIdentityUnlockTest.java` to
`iam-app/src/test/java/co/edu/corhuila/dlc/iam/app/` in an isolated copy of
that IAM revision. Run from its repository root:

`mvn -B -ntp -pl iam-app -am -Dtest=ForgedIdentityUnlockTest -Dsurefire.failIfNoSpecifiedTests=false test`

On 2026-10-28 at 23:48 Colombia time, this test passed locally in an isolated
copy: 2 run, 0 failures, 0 errors, reactor BUILD SUCCESS. The sandbox could not
load Mockito's agent dynamically; the successful run added
`-DargLine=-javaagent:<local mockito-core-5.17.0.jar path>` to the command.
This is a MockMvc result, not a Gateway integration or GitHub Actions run.

The audit requires an `NVD_API_KEY` repository secret and uses a proposed CVSS
failure threshold of 7, both subject to team review. Gitleaks uses its CLI
container. No secret value belongs here. The workflow has not run on GitHub;
its execution URL must come from a later reviewed IAM PR.

References: [Gitleaks container](https://github.com/gitleaks/gitleaks/pkgs/container/gitleaks),
[Dependency-Check Maven goal](https://jeremylong.github.io/DependencyCheck/dependency-check-maven/check-mojo.html).

## CP8 local execution (2026-10-30)

The isolated IAM checkout was still at `c133d6aa9877995dd1b988558b039c4eaf2e9dbc`;
the remote feature branch resolved to the same revision. The copied CP4 test was
run on Windows with Java 21 and Maven 3.9.16. Because dynamic Mockito agent
attachment is unavailable here, the command supplied its local JAR explicitly:

```powershell
$agent = 'C:\Users\hcbar\.m2\repository\org\mockito\mockito-core\5.17.0\mockito-core-5.17.0.jar'
mvn -B -ntp -pl iam-app -am '-Dtest=ForgedIdentityUnlockTest' '-Dsurefire.failIfNoSpecifiedTests=false' "-DargLine=-javaagent:$agent" test
```

Console excerpt from the successful run, 17:36:40–17:37:10 Colombia time:

```text
[INFO] Running co.edu.corhuila.dlc.iam.app.ForgedIdentityUnlockTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 5.184 s -- in co.edu.corhuila.dlc.iam.app.ForgedIdentityUnlockTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] Reactor Summary for dlc-iam-api 0.1.0-SNAPSHOT:
[INFO] BUILD SUCCESS
[INFO] Total time:  26.785 s
```

`security.yml` parsed successfully with PyYAML `BaseLoader`. The local check
confirmed the `develop` pull-request branch, five security job steps, and
`permissions.contents: read`:

```text
YAML parse: PASS
pull_request branch: develop
security steps: 5
permissions.contents: read
```

This validates YAML structure and the selected fields; it does not execute
the proposed workflow or prove that its external tools and credentials work.
