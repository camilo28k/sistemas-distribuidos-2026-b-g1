# Unit 2 · Weekly · Corte 2

# Docker Compose and Orchestration Basics

## 1. Session overview

This session covers Docker Compose and the basics of orchestration for
distributed systems: shared networks, service dependencies, health
checks, environment-based configuration, persistent volumes, scaling,
and the role of an orchestrator.

## 2. Learning objectives

1.  Compose a multi-service system with a shared network.
2.  Order startup with health checks and `depends_on`.
3.  Externalize configuration per environment.
4.  Understand what an orchestrator adds and when it is needed.

## 3. From a container to a system

Docker Compose describes a complete system as services connected through
a shared network. Services can be built from images, configured through
environment variables, connected using service-name DNS, and connected
to persistent storage through volumes.

## 4. Annotated Compose example

``` yaml
services:
  orders-api:
    build: ./orders
    environment:
      DB_URL: postgres://db:5432/orders
      INVENTORY_URL: http://inventory-api:8080
    depends_on:
      db:
        condition: service_healthy
    ports:
      - "8080:8080"

  db:
    image: postgres:16
    environment:
      POSTGRES_DB: orders
    volumes:
      - dbdata:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres"]
      interval: 5s

volumes:
  dbdata: {}
```

Key ideas:

-   `services` defines the system components.
-   `environment` externalizes configuration.
-   `depends_on` defines service dependencies.
-   `condition: service_healthy` waits for readiness.
-   `healthcheck` checks service health.
-   `volumes` preserve persistent data.
-   Service names provide internal DNS.

## 5. Startup order and readiness

`depends_on` alone controls startup order; it does not guarantee that a
dependency is ready to accept connections.

Reliable startup requires:

1.  Health checks on dependencies.
2.  `condition: service_healthy` where appropriate.
3.  Retry or backoff logic in services during startup.

## 6. Configuration per environment

Use a base Compose configuration together with environment-specific
values or overrides.

Configuration should be externalized through environment variables.
Secrets must come from environment injection or a secret-management
mechanism and must never be committed to Git.

## 7. Persistent data

Database data should use named volumes rather than relying only on the
container filesystem.

Example:

``` yaml
volumes:
  dbdata: {}
```

## 8. When Compose is not enough

Docker Compose is suitable for local development and smaller deployments
on a single host.

An orchestrator such as Kubernetes becomes relevant when the system
requires multiple hosts, self-healing, rolling updates, autoscaling, or
cluster-level orchestration.

## 9. Practical scenario

If `docker compose up` fails because the API connects before PostgreSQL
finishes initializing, the solution is to add a database health check,
gate the API with `condition: service_healthy`, and implement
retry/backoff behavior.

## 10. Common mistakes

-   Relying on `depends_on` alone.
-   Confusing container startup with service readiness.
-   Using hard-coded IP addresses instead of service names.
-   Committing secrets.
-   Baking environment-specific configuration into images.
-   Storing database data only in the container filesystem.

## 11. Self-check

### Question 1

**In Compose, a service reaches another by:**\
**Answer:** Its service name on the shared network, such as
`http://inventory-api:8080`.

### Question 2

**`depends_on` without a condition guarantees:**\
**Answer:** Start order only, not dependency readiness.

### Question 3

**Database data should live in:**\
**Answer:** A named volume.

### Question 4

**Per-environment differences are handled by:**\
**Answer:** Compose overrides and environment values.

### Question 5

**You move from Compose to an orchestrator when you need:**\
**Answer:** Multiple hosts, self-healing, rolling updates, and
autoscaling.

### Question 6

**`docker compose up` fails in CI because the database is not ready.
Fix:**\
**Answer:** Add a database health check, `condition: service_healthy`,
and boot retry/backoff.

## 12. This week's activity

Bring the system up with a single `docker compose up`, using a shared
network, health checks, environment-based configuration, and persistent
volumes.

This activity prepares the orchestration planning work for Session 2.
