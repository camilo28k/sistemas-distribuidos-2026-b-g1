# Unit 2 · Planning · Corte 2

# Planning --- Environments, Configuration Strategy and Orchestration

## 1. Session overview

This planning session defines how the product runs across `develop`,
`qa`, and `prod`, how configuration and secrets are managed, how
branches map to environments, and how orchestration work is sliced for
MVP 2.

## 2. Learning objectives

1.  Define environments and what differs between them.
2.  Design a configuration and secrets strategy following the 12-factor
    approach.
3.  Map the branch model to each environment.
4.  Slice orchestration work into MVP 2 stories.

## 3. Environments

The same application image should run in every environment while only
the configuration changes.

### Develop

Fast integration and disposable feedback.

### QA

Production-like integration and testing.

### Prod

Real users and production data.

Promotion means running the tested artifact with the target
environment's configuration, not rebuilding the application specifically
for production.

## 4. Configuration and secrets

The 12-factor principle is:

> Configuration lives in the environment, not in the code.

Therefore:

-   Do not hard-code environment names.
-   Do not scatter production-specific conditionals through the code.
-   Supply configuration through environment values.
-   Supply secrets through a secret store or environment injection.
-   Never commit secrets to Git.
-   A new environment should require new configuration values, not
    application-code changes.

A `.env.example` should document required variables without containing
real secrets.

## 5. Branch model and environments

The planning activity maps branches to environments:

``` text
hu-xxx-dev  -> develop
hu-xxx-qa   -> qa
hu-xxx-main -> main
```

Changes should progress through the corresponding environment flow
rather than bypassing the validation process.

## 6. MVP 2 orchestration stories

### Must --- Start all services with one Compose command

Acceptance criteria:

-   Health checks work.
-   Named volumes are used.
-   Service-name DNS works.

### Must --- Externalize configuration

Acceptance criteria:

-   Required variables are validated.
-   No secrets are committed.

### Must --- Promote the same artifact to QA

Acceptance criteria:

-   Image digest remains unchanged.
-   QA configuration is injected.

### Must --- Map branch to environment

Acceptance criteria:

-   Child pull requests target the correct parent branch.

### Should --- Add environment smoke tests

Acceptance criteria:

-   `/health` works in QA.
-   One business path runs successfully in QA.

### Could --- Add deployment metrics

Acceptance criteria:

-   Lead time and rollback time are recorded.

## 7. Configuration drift scenario

If the application works in `develop` but fails in `qa`, possible causes
include:

-   A hard-coded `localhost:5432`.
-   Different variable names such as `DATABASE_URL` and `DB_URL`.
-   Missing required configuration.

Prevent configuration drift by using consistent variable names,
validating required variables at startup, and maintaining a
`.env.example`.

## 8. Common mistakes

-   Rebuilding a different image for each environment.
-   Hard-coding environment differences.
-   Using inconsistent environment variable names.
-   Committing secrets.
-   Copying secrets into Docker images.
-   Not maintaining a `.env.example`.
-   Not validating required configuration at startup.

## 9. Self-check

### Question 1

**Across environments you should:**\
**Answer:** Run the same built image and change only configuration.

### Question 2

**The 12-factor rule says:**\
**Answer:** Configuration lives in the environment, not in the code.

### Question 3

**A change reaches production:**\
**Answer:** After passing through QA through the defined environment
flow.

### Question 4

**To prevent configuration drift:**\
**Answer:** Use consistent variable names, validate required variables
at startup, and maintain `.env.example`.

### Question 5

**Secrets belong:**\
**Answer:** In a secret store or through environment injection, never in
Git.

### Question 6

**"Works in develop, breaks in QA" can be caused by:**\
**Answer:** Configuration drift.

## 10. Environment matrix

  ----------------------------------------------------------------------------
  Environment       Purpose           Data                   Required gate
  ----------------- ----------------- ---------------------- -----------------
  `develop`         Fast integration  Synthetic              Green CI, unit
                    and disposable                           tests, and build
                    feedback                                 

  `qa`              Production-like   Synthetic/anonymized   Contract tests,
                    contract and                             integration
                    integration                              tests, and smoke
                    testing                                  test

  `prod`            Real users        Production data        Approved release
                                                             and rollback plan
  ----------------------------------------------------------------------------

## 11. Orchestration requirements

1.  Compose starts the required services with health checks.
2.  Services depend on readiness rather than only container startup.
3.  Persistent data uses named volumes.
4.  Disposable containers do not own persistent state.
5.  Startup fails clearly when required environment variables are
    missing.
6.  Service-to-service URLs use service-name DNS, never fixed IP
    addresses or `localhost`.
7.  Logs include useful context such as correlation ID, service name,
    and environment without exposing secrets.

## 12. Acceptance criteria

The orchestration plan should satisfy:

-   `docker compose config` resolves without exposing secrets.
-   Health checks prevent traffic before dependencies are ready.
-   The same image digest can be used across `develop`, `qa`, and
    `prod`.
-   Missing configuration causes startup to fail with a clear variable
    name.
-   QA deployment is blocked when contract or smoke tests fail.

## 13. Expected outcome

The planning activity should leave the team with a clear strategy for
environment configuration, secret management, branch promotion, and MVP
2 orchestration work.
