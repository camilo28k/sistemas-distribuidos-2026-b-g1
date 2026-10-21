# Structured log contract: staff registration

One UTF-8 JSON object per line on stdout. `api-gateway` and `auth-service`
use the same envelope for POST /api/v1/auth/register. Auth owns `User`,
`iam` and `UserRegistered`; no patient information belongs in this journey.
This is the workshop contract, not evidence of existing production logging.

## Fields

| Field | JSON type | Required | Example | Purpose |
|---|---|---|---|---|
| timestamp | string | Yes | 2026-10-20T23:20:00.000Z | ISO-8601 UTC event time |
| level | string enum | Yes | INFO | DEBUG, INFO, WARN or ERROR severity |
| service | string | Yes | api-gateway | Emitting component |
| version | string | Yes | 0.1.0 | Deployed artifact version |
| environment | string | Yes | workshop | Distinguish workshop, dev, qa and prod |
| trace_id | string | Yes | 4bf92f3577b34da6a3ce929d0e0e4736 | 32 nonzero lowercase hex digits; distributed search |
| span_id | string | Yes | 1111111111111111 | 16 nonzero lowercase hex digits; current operation |
| correlation_id | string | Yes | 4bf92f3577b34da6a3ce929d0e0e4736 | Workshop uses trace_id; never an email or token |
| event | string enum | Yes | staff.registration.completed | Stable machine-readable occurrence |
| message | string | Yes | Staff registration request completed | Fixed safe text, no request/exception interpolation |
| duration_ms | number | Yes | 2001.4 | Nonnegative elapsed time for the named operation |
| staff_role | string enum | Optional | DENTIST | Auth-validated requested role; DENTIST or SECRETARY_ASSISTANT; omit at Gateway |
| account_state | string enum | Optional | PENDING_VERIFICATION | Auth-owned account lifecycle result; omit when unknown |
| aggregate_type | string enum | Yes | User | IAM aggregate targeted by the command |
| operation | string enum | Yes | register_staff | Business operation, independent of route spelling |
| rejection_code | string enum | Optional | STAFF_ALREADY_EXISTS | Bounded internal reason; access-restricted logs only |
| domain_event | string enum | Optional | UserRegistered | Present only when the business fact was committed/published |
| http_status | integer | Optional | 503 | Result observed by this component; not an assumed downstream result |
| parent_span_id | string | Optional | 1111111111111111 | Reconstruct parent/child relationships |

All envelope fields must be injected by the logger, not copied from JSON input.
The optional domain fields come from validated application state. An absent field
means unknown/not applicable; it must not be filled with an invented account state.
Database/outbox logs use the same envelope and their own span IDs. A rejected
registration never emits UserRegistered.

## Never record

| Sensitive data in Di Lucca | Handling before the logger |
|---|---|
| Password, password hash, recovery/verification proof, MFA secret | Exclude fields entirely; use [REDACTED] only in separately sanitized diagnostics |
| JWT, refresh token, Authorization, Cookie, Set-Cookie, CSRF proof | Exclude headers and token-bearing response bodies; never partially mask a usable secret |
| Staff name, email, phone, IP and device details | Do not log request bodies or raw identity; omit these values from this contract |
| User/session IDs, idempotency keys | Omit here; if a later restricted audit requires linkage, use a keyed pseudonym with controlled rotation and retention |
| Patient identifiers, diagnosis, history, allergies, procedures | Exclude all clinical payloads; no clinical data is needed to register staff |
| Invoice/payment details | Exclude financial payloads and payment credentials |
| Exception text, raw URLs/query strings and arbitrary headers | Fixed error messages and normalized operation names; no attacker-controlled text |

Trace IDs support investigation but are not authentication credentials. Restrict
access and retention of logs; pseudonymization alone does not make data anonymous.

## Seven synthetic log lines: one failed request

Auth detects a duplicate and records its rejection at 80 ms. Its 409 response
is delayed in transport beyond the proposed 2-second gateway deadline. The
gateway therefore observes a timeout and returns 503. These are different
observations in the same request, not two completed registrations or a retry.
No User or outbox event is created. All values below are synthetic.

```jsonl
{"timestamp":"2026-10-20T23:20:00.000Z","level":"INFO","service":"api-gateway","version":"0.1.0","environment":"workshop","trace_id":"4bf92f3577b34da6a3ce929d0e0e4736","span_id":"1111111111111111","correlation_id":"4bf92f3577b34da6a3ce929d0e0e4736","event":"staff.registration.received","message":"Staff registration request received","duration_ms":0,"aggregate_type":"User","operation":"register_staff"}
{"timestamp":"2026-10-20T23:20:00.001Z","level":"DEBUG","service":"api-gateway","version":"0.1.0","environment":"workshop","trace_id":"4bf92f3577b34da6a3ce929d0e0e4736","span_id":"2222222222222222","parent_span_id":"1111111111111111","correlation_id":"4bf92f3577b34da6a3ce929d0e0e4736","event":"auth.registration.started","message":"Auth request started","duration_ms":0,"aggregate_type":"User","operation":"register_staff"}
{"timestamp":"2026-10-20T23:20:00.005Z","level":"INFO","service":"auth-service","version":"0.1.0","environment":"workshop","trace_id":"4bf92f3577b34da6a3ce929d0e0e4736","span_id":"3333333333333333","parent_span_id":"2222222222222222","correlation_id":"4bf92f3577b34da6a3ce929d0e0e4736","event":"staff.registration.received","message":"Staff registration request received","duration_ms":0,"aggregate_type":"User","operation":"register_staff"}
{"timestamp":"2026-10-20T23:20:00.020Z","level":"DEBUG","service":"auth-service","version":"0.1.0","environment":"workshop","trace_id":"4bf92f3577b34da6a3ce929d0e0e4736","span_id":"3333333333333333","correlation_id":"4bf92f3577b34da6a3ce929d0e0e4736","event":"staff.role.validated","message":"Requested staff role accepted","duration_ms":15,"aggregate_type":"User","operation":"register_staff","staff_role":"DENTIST"}
{"timestamp":"2026-10-20T23:20:00.080Z","level":"WARN","service":"auth-service","version":"0.1.0","environment":"workshop","trace_id":"4bf92f3577b34da6a3ce929d0e0e4736","span_id":"3333333333333333","correlation_id":"4bf92f3577b34da6a3ce929d0e0e4736","event":"staff.registration.rejected","message":"Staff registration rejected by domain rule","duration_ms":75,"aggregate_type":"User","operation":"register_staff","staff_role":"DENTIST","rejection_code":"STAFF_ALREADY_EXISTS","http_status":409}
{"timestamp":"2026-10-20T23:20:02.001Z","level":"ERROR","service":"api-gateway","version":"0.1.0","environment":"workshop","trace_id":"4bf92f3577b34da6a3ce929d0e0e4736","span_id":"2222222222222222","parent_span_id":"1111111111111111","correlation_id":"4bf92f3577b34da6a3ce929d0e0e4736","event":"auth.registration.timeout","message":"Auth response deadline exceeded","duration_ms":2000,"aggregate_type":"User","operation":"register_staff"}
{"timestamp":"2026-10-20T23:20:02.002Z","level":"ERROR","service":"api-gateway","version":"0.1.0","environment":"workshop","trace_id":"4bf92f3577b34da6a3ce929d0e0e4736","span_id":"1111111111111111","correlation_id":"4bf92f3577b34da6a3ce929d0e0e4736","event":"staff.registration.completed","message":"Staff registration request completed","duration_ms":2002,"aggregate_type":"User","operation":"register_staff","http_status":503}
```

## Severity

| Level | Rule | Di Lucca example |
|---|---|---|
| ERROR | Technical failure prevents the expected operation | Gateway times out waiting for Auth; no assumption about commit state |
| WARN | Expected rejection requiring investigation or user correction | Auth rejects duplicate staff with 409; not an availability outage by itself |
| INFO | Request lifecycle or committed business fact | Gateway completes a registration; Auth commits UserRegistered |
| DEBUG | Safe diagnostic details, normally disabled in production | Auth validates the bounded requested staff role |

Search JSON by exact trace_id first, then order by timestamp and inspect parent_span_id.
Clock synchronization is required; cross-host timestamps alone do not prove causality.
