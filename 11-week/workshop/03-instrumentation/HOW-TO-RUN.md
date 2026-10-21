# Run the api-gateway instrumentation module

Prerequisites: JDK 21 and Maven 3.9.x. From this directory:
```powershell
cd api-gateway
mvn test
mvn spring-boot:run
```
Maven downloads the dependencies declared in pom.xml. This first increment
tests the registration trace filter and JSON logger; forwarding is added next.
The module uses the team's Java/Spring stack, not a verified Gateway deployment.

Actual console output on 2026-10-20 at 20:06 America/Bogota:
```text
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
Total time: 23.910 s
```
An offline run initially failed because Surefire dependencies were missing.
Running Maven online downloaded them and all three tests passed.
