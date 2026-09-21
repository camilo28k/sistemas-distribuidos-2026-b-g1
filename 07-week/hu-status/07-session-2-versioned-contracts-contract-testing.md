# Planning — Versioned Contracts and Contract Testing

**Unit 2 · Planning · Corte 2**

This activity formalizes the contracts that allow independent Di Lucca services to evolve safely. It defines machine-readable contracts, versioning and compatibility rules, and consumer-driven contract testing with Pact.

## 1. The contract is the source of truth

Each service interaction should have a machine-readable contract:

- `openapi.yaml` for REST.
- `.proto` for gRPC.
- JSON Schema or Avro for events.

Contracts are versioned in the repository and define the boundary between services. They can be used to validate requests and responses, generate clients, and verify compatibility.

## 2. Every endpoint or event declares its contract

A complete contract defines:

1. Method and path, or event name.
2. Request/event structure.
3. Response structure.
4. Error structure.
5. Version.
6. Correlation information.

Di Lucca conventions include ISO-8601 UTC timestamps, UUID identifiers, `?page=&limit=` pagination, and this error envelope:

```json
{
  "error": {
    "code": "ERROR_CODE",
    "message": "Human-readable message",
    "details": {},
    "trace_id": "uuid"
  }
}
```

## 3. Versioning and backward compatibility

Services deploy independently, so consumers cannot always migrate simultaneously.

### Compatible change

Adding an optional field is backward-compatible when existing consumers continue to work.

### Breaking change

Removing, renaming or retyping an existing field is breaking. Such a change requires a new version:

```text
/api/v1/...
/api/v2/...
```

or:

```text
ProcedureCompleted.v1
ProcedureCompleted.v2
```

The old version is deprecated and remains available while consumers migrate. Deprecation should be announced and can use a `Sunset` header for HTTP APIs.

## 4. Consumer-driven contract testing with Pact

Unit tests verify services individually. Pact verifies that the producer continues to satisfy what a consumer expects.

```text
Consumer test
      |
      v
Pact contract
      |
      v
Producer verification in CI
      |
      v
QA promotion
```

If the producer introduces an incompatible response change, the producer build fails before promotion.

## 5. Di Lucca contract examples

### Patient status

```http
GET /api/v1/patients/{patientId}/status
```

Expected responses:

- `200`: active/inactive status.
- `404`: `PATIENT_NOT_FOUND`.
- `409`: `PATIENT_INACTIVE`.

This interaction uses OpenAPI `v1`.

### Procedure completion

Clinical publishes:

```text
ProcedureCompleted.v1
```

The event contains clinical identifiers, procedure codes, quantities and justified extras. Clinical does not publish prices; Billing resolves monetary values from its own catalog.

For tracing and idempotency, the event includes:

```text
eventId
schemaVersion
aggregateId
correlationId
occurredAt
```

## 6. Contract testing backlog

| Priority | Work item | Acceptance gate | Status |
|---|---|---|---|
| Must | Add OpenAPI contract for patient status | Request/response/error schemas validate | doing |
| Must | Add Pact consumer for Appointments -> Clinical | Pact file published from consumer CI | todo |
| Must | Verify Clinical producer against Pact | Producer build fails on incompatible response | todo |
| Must | Version `ProcedureCompleted.v1` | Event schema and consumers agree | doing |
| Should | Add compatibility check for optional fields | Safe changes pass; breaking changes require v2 | todo |
| Should | Add duplicate-event acceptance test | Same `eventId` produces one effect | todo |

These are planning items; status should change as implementation evidence becomes available.

## 7. A path we will face in real life

Suppose a producer changes:

```text
available
```

to:

```text
qty
```

Orders can receive `null` when it still expects `available`, even if both services pass their individual unit tests.

A versioned contract and consumer pact make the expectation explicit:

```text
Consumer expectation
        |
        v
Pact contains "available"
        |
        v
Producer verification
        |
        v
Incompatible change -> CI fails
```

The rename must therefore be handled as a compatible evolution or a new version according to its impact.

## 8. Common mistakes

- No machine-readable contract.
- Breaking changes shipped under the same version.
- Relying only on unit tests for cross-service compatibility.
- Deprecating without announcing the migration.
- Changing event fields without considering consumers.
- Contract tests that are not executed in CI.

## Self-check

### Question 1
**The source of truth for an API should be...**

**Answer:** A versioned, machine-readable contract such as OpenAPI, proto or schema.

### Question 2
**Which change is backward-compatible?**

**Answer:** Adding an optional field while preserving the existing contract.

### Question 3
**A breaking change requires...**

**Answer:** A new version and deprecation of the old one while consumers migrate.

### Question 4
**Consumer-driven contract testing with Pact makes...**

**Answer:** Producer verification fail when the producer breaks a consumer expectation.

### Question 5
**A standard error envelope should include...**

**Answer:** `code`, `message`, `details`, and `trace_id`.

### Question 6
**A silent field rename is prevented by...**

**Answer:** Contracts, consumer pacts and CI verification.

## This week

Publish the Di Lucca contracts in machine-readable form, define versioning and compatibility rules, and add at least one consumer-driven contract test to CI. Slice the MVP 2 integration work into testable acceptance criteria.
