# Service Communication — REST, gRPC and Messaging

**Unit 2 · Weekly · Corte 2**

This activity defines how Di Lucca services communicate using synchronous REST/gRPC or asynchronous messaging, and how delivery semantics and idempotency keep processing correct under failures and retries.

## 1. Communication choice

| Interaction | Mode | Contract | Reason |
|---|---|---|---|
| Staff to API Gateway | REST | OpenAPI `v1` | Immediate response and standard HTTP tooling. |
| Appointments to Clinical patient validation | REST | OpenAPI `v1` | Scheduling needs an immediate active/inactive decision. |
| Clinical to Billing and Appointments | Async event | `ProcedureCompleted.v1` schema | Decouples care completion and supports retries. |
| Internal high-throughput calls when justified | gRPC | Versioned `.proto` | Used when measured latency/throughput justifies the added complexity. |

## 2. REST — synchronous communication

REST/JSON over HTTP is the default synchronous option for public-facing APIs and service interactions that require an immediate answer.

It provides human-readable payloads, standard tooling and OpenAPI contracts. Its main trade-off is temporal coupling: if the target service is slow or unavailable, the caller is affected.

For Di Lucca, patient-status validation is an example where the caller needs an immediate result.

## 3. gRPC — typed internal communication

gRPC is suitable for internal service-to-service calls when high throughput or low latency is demonstrated as a requirement. It uses a `.proto` contract, typed clients/servers and compact binary communication over HTTP/2.

```proto
service Inventory {
  rpc CheckStock (StockRequest) returns (StockReply);
}

message StockRequest {
  string sku = 1;
}

message StockReply {
  int32 available = 1;
}
```

It is therefore an option for Di Lucca rather than a default for every interaction.

## 4. Messaging — asynchronous communication

A broker such as RabbitMQ decouples producers from consumers. The producer publishes an event and consumers process it independently.

Two useful patterns are:

- **Queues:** distribute work among consumers/workers.
- **Pub/sub:** allow one event to reach several independent subscribers.

For Di Lucca, `ProcedureCompleted.v1` is an asynchronous business event between Clinical and its consumers.

## 5. Delivery semantics and idempotency

Distributed networks can lose or duplicate messages. The main delivery semantics are:

- **At-most-once:** messages can be lost, but are not normally redelivered.
- **At-least-once:** messages can be delivered more than once.
- **Exactly-once delivery:** cannot be guaranteed end-to-end across a distributed network.

The practical approach is exactly-once **processing** using at-least-once delivery, an idempotency key and deduplication.

`ProcedureCompleted.v1` includes:

- `eventId`
- `schemaVersion`
- `aggregateId`
- `correlationId`
- `occurredAt`

Each consumer stores processed event identifiers. If the same `eventId` arrives again, the duplicate is ignored or the previous result is reused, preventing duplicate charges or repeated appointment transitions.

```text
receive event
     |
check eventId
  /       seen      new
 |          |
ignore   apply effect
            |
       store eventId
```

## 6. Choosing sync or async

| Question | Sync (REST/gRPC) | Async (events) |
|---|---|---|
| Need an answer immediately? | Yes | No |
| Caller must survive callee downtime? | No | Yes |
| Many consumers of one fact? | Less suitable | Yes |
| Public/browser-facing? | REST | — |
| Internal high-throughput? | gRPC | Events |

The choice is made per interaction according to its business and resilience requirements.

## 7. A path we will face in real life

A chain such as `Checkout -> Payments -> Fraud` can cascade under load when every call waits synchronously. Threads remain occupied, connection pools can exhaust, and the original operation can become unavailable.

The mitigation is to keep synchronous calls where an immediate response is required and move non-critical work to asynchronous events. Remaining synchronous calls should use timeouts and circuit breakers, while asynchronous operations can expose a pending state.

## 8. Common mistakes

- Long synchronous chains such as `A -> B -> C -> D`.
- Assuming at-least-once delivery means duplicates cannot occur.
- Using events where an immediate answer is required.
- No timeout/retry/circuit-breaker on synchronous calls.
- No idempotency strategy for event consumers.

## Self-check

### Question 1
**What is the main benefit of asynchronous messaging?**

**Answer:** Decoupling the caller from the callee, allowing temporary downtime tolerance and load buffering.

### Question 2
**gRPC is a strong choice for...**

**Answer:** Internal, high-throughput, contract-first service calls.

### Question 3
**At-least-once delivery requires consumers to be...**

**Answer:** Idempotent and safe to process duplicates.

### Question 4
**A pub/sub topic lets...**

**Answer:** One event fan out to multiple independent subscribers.

### Question 5
**Exactly-once delivery over a network is...**

**Answer:** Not guaranteed end-to-end; engineer exactly-once processing instead.

### Question 6
**A long synchronous chain under load tends to...**

**Answer:** Cascade because calls wait, pools can exhaust, and the system can freeze or degrade.

## This week

For each Di Lucca interaction, decide sync or async and justify it. Use REST or gRPC for synchronous calls, RabbitMQ queues/events for asynchronous communication, and make at least one consumer idempotent.
