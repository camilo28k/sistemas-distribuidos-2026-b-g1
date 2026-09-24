# EduTrack — Scrum Board (Sprint 1 / MVP 1) — v1.0

**Project:** Distributed Systems 2026-B — Team G1
**Tool:** GitHub Projects (Board view)
**Sprint:** MVP 1 (5 modules, 5 user stories)

---

## 1. Recommended project setup

1. Create the Project from the org/repo: `New project` → **Board** template.
2. `Status` field (columns) with these values, in this order:
   - 📋 **Backlog**
   - 📝 **To Do** (Sprint 1)
   - 🔨 **In Progress**
   - 👀 **In Review** (PR open)
   - ✅ **Done**
3. Add two custom fields (`+ New field`):
   - `Module` (single select): Identity, Academic Records, Attendance, Notifications, Communication, Shared
   - `Story` (single select): HU-001, HU-002, HU-003, HU-004, HU-005, N/A
4. Suggested labels in the repo (Settings → Labels):
   `hu-001` `hu-002` `hu-003` `hu-004` `hu-005` `module:identity` `module:academic` `module:attendance` `module:notifications` `module:communication` `type:feature` `type:test` `type:docs` `type:setup`

---

## 2. 📋 Backlog column (v2+, out of MVP 1 scope)

- Academic risk prediction with AI
- Shared school calendar
- Automated monthly PDF reports
- Any additional features not needed to demonstrate the distributed system

---

## 3. 📝 To Do column — Sprint 1 (MVP 1)

### 🔧 Shared / Setup (whole team)
- [ ] Create repo and branch structure `main` → `develop` → `feature/HU-XXX`
- [ ] Configure branch protection (`develop`, `main`) and PR rules
- [ ] Define the `GradeCreated` event contract (eventId, studentId, subjectId, grade, createdAt)
- [ ] Define the `StudentAbsent` event contract (eventId, studentId, date, reason)
- [ ] Document decisions (ADR) on the at-most-once trade-off for push notifications
- [ ] Prepare the E2E demo: "teacher publishes a grade → parent receives a notification"

### 👤 Member 1 — Identity & Accounts (`hu-003`)
- [ ] Entities: User, Parent, Student, School
- [ ] Endpoint: create parent / student / school
- [ ] Endpoint: link a student to a parent (support multiple children)
- [ ] Endpoint: GET children associated with a parent
- [ ] Duplicate-link validation for the parent-student relationship
- [ ] Basic tests + module README

### 👤 Member 2 — Academic Records (`hu-001`)
- [ ] Grade entity
- [ ] Endpoint: register a grade
- [ ] Endpoint: query grades by student
- [ ] Register and query assignments
- [ ] Emit `GradeCreated` event
- [ ] Basic tests + module README

### 👤 Member 3 — Attendance (`hu-005`)
- [ ] Attendance entity
- [ ] Endpoint: register attendance/absence
- [ ] Endpoint: query attendance by student
- [ ] Duplicate-event validation
- [ ] Preserve causal order (happens-before) of events
- [ ] Emit `StudentAbsent` event
- [ ] Basic tests + module README

### 👤 Member 4 — Notifications (`hu-001`, `hu-002`)
- [ ] Notification service (event consumer)
- [ ] Consume `GradeCreated` → generate notification
- [ ] Consume `StudentAbsent` → generate notification
- [ ] Duplicate control via idempotency key
- [ ] Basic retry mechanism on failure
- [ ] Basic tests + module README

### 👤 Member 5 — Communication (`hu-004`)
- [ ] Message entity
- [ ] Endpoint: send message parent → teacher
- [ ] Endpoint: query conversations
- [ ] Link message to a subject
- [ ] Show basic teacher availability
- [ ] Basic tests + module README

---

## 4. 🔨 In Progress / 👀 In Review / ✅ Done columns

Left empty in this first version — cards move here as each member opens their `feature/HU-XXX` branch, opens a PR into `develop`, and it gets approved and merged.

---

## 5. Optional — bulk-create issues with GitHub CLI

If you have `gh` installed and the repo already exists, this creates all the issues at once (adjust `OWNER/REPO`):

```bash
REPO="OWNER/REPO"

gh issue create -R "$REPO" -t "Setup: repo and branches (main/develop/feature)" -l "type:setup"
gh issue create -R "$REPO" -t "Define GradeCreated event contract" -l "type:docs"
gh issue create -R "$REPO" -t "Define StudentAbsent event contract" -l "type:docs"

gh issue create -R "$REPO" -t "[Identity] User/Parent/Student/School entities" -l "hu-003,module:identity"
gh issue create -R "$REPO" -t "[Identity] GET children by parent endpoint" -l "hu-003,module:identity"
gh issue create -R "$REPO" -t "[Identity] Validate duplicate links" -l "hu-003,module:identity"

gh issue create -R "$REPO" -t "[Academic] Register grade endpoint" -l "hu-001,module:academic"
gh issue create -R "$REPO" -t "[Academic] Query grades endpoint" -l "hu-001,module:academic"
gh issue create -R "$REPO" -t "[Academic] Emit GradeCreated event" -l "hu-001,module:academic"

gh issue create -R "$REPO" -t "[Attendance] Register attendance endpoint" -l "hu-005,module:attendance"
gh issue create -R "$REPO" -t "[Attendance] Validate duplicates and causal order" -l "hu-005,module:attendance"
gh issue create -R "$REPO" -t "[Attendance] Emit StudentAbsent event" -l "hu-005,module:attendance"

gh issue create -R "$REPO" -t "[Notifications] Consume GradeCreated" -l "hu-001,module:notifications"
gh issue create -R "$REPO" -t "[Notifications] Consume StudentAbsent with dedup" -l "hu-002,module:notifications"
gh issue create -R "$REPO" -t "[Notifications] Basic retry on failure" -l "hu-002,module:notifications"

gh issue create -R "$REPO" -t "[Communication] Send message endpoint" -l "hu-004,module:communication"
gh issue create -R "$REPO" -t "[Communication] Query conversations endpoint" -l "hu-004,module:communication"
```

Then, to add them to the Project (replace `NUMBER` with your project's number):

```bash
gh project item-add NUMBER --owner OWNER --url https://github.com/OWNER/REPO/issues/ISSUE_NUMBER
```

---

## 6. Note

Per what's on record, this first version of the board is due **today, Sunday, before midnight**. This structure already has the columns, per-module/per-member cards, and user stories mapped out — you just need to create them in GitHub Projects (manually or with the script) and assign each card to the right member.