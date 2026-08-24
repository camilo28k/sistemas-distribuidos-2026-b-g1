# Data Models per Service

> **What to fill in here:** The data schema for each microservice.
> Each service has its own section. Remember: **each service has its own database**.
> Schema changes are always done with versioned migrations, never by modifying tables in place.

> **DB engine note:** This document is technology-agnostic. The examples show standard SQL
> compatible with most relational engines. For document databases
> (MongoDB) or key-value stores (Redis), adapt the diagrams and schemas to the corresponding format.
> The engine choice is documented in each service section — the scaffold does not assume which one to use.

---

## Service: auth-service

**Bounded Context:** Identity and Access (IAM)  
**DB Engine:** PostgreSQL 15+ (source of truth) and Redis (ephemeral state)  
**Owner:** `auth-service`

**Justification:** PostgreSQL guarantees transactional consistency for users, roles,
role assignments, and refresh tokens. Redis holds temporary data only: revoked JWTs,
login attempt counters, and temporary locks. No other microservice accesses these tables directly.

### Table: users

| Field | Type | Nullable | Description | Constraints |
|---|---|---|---|---|
| id | UUID | No | User identifier | PK |
| email | VARCHAR(255) | No | Login email | UNIQUE, NOT NULL |
| password_hash | VARCHAR(255) | No | BCrypt/Argon2id password hash | NOT NULL; never expose it |
| email_verified | BOOLEAN | No | Email verification state | DEFAULT false |
| failed_attempts | INTEGER | No | Consecutive failed login attempts | DEFAULT 0, CHECK >= 0 |
| locked_until | TIMESTAMPTZ | Yes | Temporary account lock expiration | NULL = not locked |
| created_at | TIMESTAMPTZ | No | Creation timestamp | DEFAULT NOW() |
| updated_at | TIMESTAMPTZ | No | Last update timestamp | DEFAULT NOW() |
| deleted_at | TIMESTAMPTZ | Yes | Logical deletion timestamp | NULL = active |

### Table: roles

| Field | Type | Nullable | Description | Constraints |
|---|---|---|---|---|
| id | UUID | No | Role identifier | PK |
| name | VARCHAR(50) | No | Technical role name | UNIQUE; e.g. ADMIN, DENTIST , PACIENT |
| description | TEXT | Yes | Human-readable role description | — |
| created_at | TIMESTAMPTZ | No | Creation timestamp | DEFAULT NOW() |
| updated_at | TIMESTAMPTZ | No | Last update timestamp | DEFAULT NOW() |
| deleted_at | TIMESTAMPTZ | Yes | Logical deletion timestamp | NULL = active |


### Table: user_roles

| Field | Type | Nullable | Description | Constraints |
|---|---|---|---|---|
| user_id | UUID | No | User receiving the role | PK; FK users.id ON DELETE CASCADE |
| role_id | UUID | No | Assigned role | PK; FK roles.id ON DELETE RESTRICT |
| assigned_at | TIMESTAMPTZ | No | Assignment timestamp | DEFAULT NOW() |
| assigned_by | VARCHAR(255) | Yes | Identifier of the administrator who made the assignment | Audit value; no FK in the current MER |

**Primary key:** `(user_id, role_id)` prevents duplicate assignments.

### Table: refresh_tokens

| Field | Type | Nullable | Description | Constraints |
|---|---|---|---|---|
| id | UUID | No | Session/token identifier | PK |
| user_id | UUID | No | Token owner | FK users.id ON DELETE CASCADE |
| token_hash | VARCHAR(255) | No | Refresh token hash | UNIQUE; original token is never stored |
| expires_at | TIMESTAMPTZ | No | Token expiration | Must be later than created_at |
| revoked_at | TIMESTAMPTZ | Yes | Revocation timestamp | NULL = active |
| created_at | TIMESTAMPTZ | No | Creation timestamp | DEFAULT NOW() |
| user_agent | TEXT | Yes | Client/device description | — |

## Data Dictionary

### `users`

| Column | Type | Description | Example |
|---|---|---|---|
| `id` | UUID | Auto-generated unique identifier for the user | `550e8400-e29b-41d4-a716-446655440000` |
| `email` | VARCHAR(255) | Unique email used as login credential | `user@example.com` |
| `password_hash` | VARCHAR(255) | Cryptographic hash of the user's password | `$2b$12$...` |
| `email_verified` | BOOLEAN | Indicates whether the user's email has been verified | `true` |
| `failed_attempts` | INTEGER | Number of consecutive failed login attempts | `0` |
| `locked_until` | TIMESTAMPTZ | Date and time until which the account is locked | `2026-08-22T18:00:00Z` |
| `created_at` | TIMESTAMPTZ | Date and time when the user was created | `2026-08-22T10:30:00Z` |
| `updated_at` | TIMESTAMPTZ | Date and time of the last modification | `2026-08-22T10:45:00Z` |
| `deleted_at` | TIMESTAMPTZ | Logical deletion timestamp; NULL means active | `NULL` |

### `roles`

| Column | Type | Description | Example |
|---|---|---|---|
| `id` | UUID | Unique identifier of the role | `550e8400-e29b-41d4-a716-446655440000` |
| `name` | VARCHAR(50) | Technical name of the role | `ADMIN` |
| `description` | TEXT | Human-readable description of the role | `System administrator` |
| `created_at` | TIMESTAMPTZ | Date and time when the role was created | `2026-08-22T10:30:00Z` |
| `updated_at` | TIMESTAMPTZ | Last update timestamp | `2026-08-22T10:45:00Z` |
| `deleted_at` | TIMESTAMPTZ | Logical deletion timestamp | `NULL` |

### `user_roles`

| Column | Type | Description | Example |
|---|---|---|---|
| `user_id` | UUID | Identifier of the user receiving the role | `550e8400-e29b-41d4-a716-446655440000` |
| `role_id` | UUID | Identifier of the assigned role | `660e8400-e29b-41d4-a716-446655440000` |
| `assigned_at` | TIMESTAMPTZ | Date and time when the role was assigned | `2026-08-22T11:00:00Z` |
| `assigned_by` | VARCHAR(255) | Identifier of the administrator who assigned the role | `admin@example.com` |

### `refresh_tokens`

| Column | Type | Description | Example |
|---|---|---|---|
| `id` | UUID | Unique session/token identifier | `770e8400-e29b-41d4-a716-446655440000` |
| `user_id` | UUID | Identifier of the token owner | `550e8400-e29b-41d4-a716-446655440000` |
| `token_hash` | VARCHAR(255) | Cryptographic hash of the refresh token | `sha256:...` |
| `expires_at` | TIMESTAMPTZ | Date and time when the token expires | `2026-09-22T10:30:00Z` |
| `revoked_at` | TIMESTAMPTZ | Date and time when the token was revoked | `NULL` |
| `created_at` | TIMESTAMPTZ | Date and time when the token was created | `2026-08-22T10:30:00Z` |
| `user_agent` | TEXT | Client or device information associated with the session | `Mozilla/5.0` |
## SQL Schema

### Table: users

```sql
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    email_verified BOOLEAN NOT NULL DEFAULT false,
    failed_attempts INTEGER NOT NULL DEFAULT 0 CHECK (failed_attempts >= 0),
    locked_until TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);
CREATE TABLE roles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);
CREATE TABLE user_roles (
    user_id UUID NOT NULL
        REFERENCES users(id) ON DELETE CASCADE,

    role_id UUID NOT NULL
        REFERENCES roles(id) ON DELETE RESTRICT,

    assigned_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    assigned_by VARCHAR(255),

    PRIMARY KEY (user_id, role_id)
);
CREATE TABLE refresh_tokens (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    user_id UUID NOT NULL
        REFERENCES users(id) ON DELETE CASCADE,

    token_hash VARCHAR(255) NOT NULL UNIQUE,
    expires_at TIMESTAMPTZ NOT NULL,
    revoked_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    user_agent TEXT
);
```
### Indexes

| Name | Fields | Type | Justification |
|---|---|---|---|
| uq_users_email | users(email) | UNIQUE | Primary lookup during login |
| idx_users_active_email | users(email) WHERE deleted_at IS NULL | Partial | Filters active users |
| uq_roles_name | roles(name) | UNIQUE | Prevents repeated roles |
| idx_user_roles_role_id | user_roles(role_id) | B-tree | Finds users by role |
| uq_refresh_tokens_hash | refresh_tokens(token_hash) | UNIQUE | Secure token lookup/rotation |
| idx_refresh_tokens_user_active | refresh_tokens(user_id, expires_at) WHERE revoked_at IS NULL | Partial | Active session queries |

### Redis state

| Key pattern | Type | TTL | Purpose |
|---|---|---|---|
| `blacklist:{jti}` | String | Until JWT expiration | Revoked access tokens |
| `attempts:{email}` | Counter | 5 minutes | Login rate limiting |
| `locked:{email}` | String | Until unlock time | Temporary account lock |

### Modeling decisions

1. `auth-service` is the only source of truth for identity, roles, and sessions.
2. Passwords and refresh tokens are stored only as cryptographic hashes.
3. User removal is logical (`deleted_at`) for auditing and recovery.
4. The API Gateway is stateless and has no persistent data model; it can only read necessary ephemeral state from Redis.
5. `user_roles` uses a composite primary key `(user_id, role_id)` because the same role cannot be assigned more than once to the same user.

6. `user_roles` does not use a surrogate `id` because the relationship itself uniquely identifies the assignment.

## Migration Strategy

**Tool:** Prisma Migrate

**File naming convention:**

```text
migration_name/
└── migration.sql
```

**Migration rules:**

```text
✓ Migrations are always forward-only.
✓ One migration should represent one logical schema change.
✓ Migrations must be reviewed before being applied to shared or production environments.
✓ Seed data must be handled separately from structural schema migrations.
✗ Never modify a migration that has already been executed in any environment.
✗ Never manually modify production tables outside the migration process.
✗ Destructive schema changes must be performed using a controlled migration strategy.
```

### Compatible schema changes

**Add a nullable column:**

```sql
ALTER TABLE users
ADD COLUMN last_login_at TIMESTAMPTZ;
```

**Add a column with a valid default:**

```sql
ALTER TABLE users
ADD COLUMN email_verified BOOLEAN NOT NULL DEFAULT false;
```

**Create an index:**

```sql
CREATE INDEX idx_users_created_at
ON users (created_at);
```

### Incompatible changes

Destructive or breaking changes must follow a **two-phase migration strategy**.

#### Phase 1

1. Add the new field or structure.
2. Update the application to support both old and new structures.
3. Migrate existing data.
4. Deploy the updated application.

#### Phase 2

1. Stop using the old field.
2. Verify that no application code depends on it.
3. Remove the deprecated field in a later migration.

## DB Engine Selection

| Engine | Use When | Avoid When |
|---|---|---|
| **PostgreSQL** | ACID transactions, relational data, foreign keys, constraints, and consistent identity data | Highly unstructured document-oriented data |
| **Redis** | Temporary state, counters, rate limiting, revoked tokens, and short-lived locks | Source of truth or critical persistent business data |
| **MongoDB** | Flexible document-oriented data models | Strong relational consistency is required across many entities |
| **Elasticsearch** | Full-text search, analytics, and log indexing | Primary source of truth for transactional data |

### `auth-service` Engine Decision

`auth-service` uses **PostgreSQL as the source of truth** because identity, roles, role assignments, and refresh tokens require transactional consistency, referential integrity, uniqueness constraints, and reliable persistence.

**Redis is used only for ephemeral state**, including revoked JWT identifiers, login attempt counters, and temporary account locks. Redis is not considered the source of truth for user identity or authorization data.

## Relationship Diagram

The following entity-relationship diagram represents the persistent data model owned by `auth-service`.

[Auth Service ER Diagram](./image/mer.png)

**Diagram source:** [DrawDB](https://www.drawdb.app/editor?shareId=95fd5e399f6043c9b40139b3c011c9d3)

### Relationships

```text
users 1 ──────── N user_roles
roles 1 ──────── N user_roles
users 1 ──────── N refresh_tokens
```

## Correlations

- Domain entities and business rules mapped to these tables → `02-domain/entities-and-rules.md`
- IAM bounded context definition → `02-domain/domain-map.md`
- Domain events generated by authentication and authorization → `02-domain/domain-events.md`
- Distributed consistency, Saga, and Outbox patterns → `05-architecture/pattern-guide.md`
- API contracts that access authentication data → `07-api/contracts/openapi/`
- Detailed technical data model for `auth-service` → `09-microservices/services/02-auth-service/data-model.md`
---
