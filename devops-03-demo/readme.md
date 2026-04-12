# DevOpsDemo
Sample project for DevOps Course @ ZHAW.

---

## JaCoco - Test Coverage Measurement
JaCoCo measures test coverage for Java code.
“How much of the code is tested?”

 - Implement: See build.gradle file
 - Builds reports after JUnit Tests

---

## SonarQube - Code Quality
SonarQube analyzes source code quality.
“Is the code good, safe, and maintainable?”

 - Install Run Image
 - Connect via Brwoser :9000
 - Run Test via PS:

### Backend
.\gradlew --% sonar -Dsonar.projectKey=devops-demo -Dsonar.projectName=devops-demo -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_959b8ab18bbbd67faca36a363427a6cef07bd556

### Frontend
npx --% sonar-scanner -Dsonar.host.url=http://localhost:9000 -Dsonar.projectKey=devops-demo-frontend -Dsonar.projectName=devops-demo-frontend -Dsonar.token=sqp_9c41238312391251d688b73e3cf40d9cacead0f3


---

## Jenkins - Automation / Orchestrator
Jenkins is a CI/CD automation server.
“When should things run, and in what order?”

---

## Selenium
Selenium automates real browsers.
“Does the system actually work for users?”

---

# CI/CD Pipline
1. Developer pushes code
2. Jenkins starts pipeline
3. Jenkins runs:
   - Unit tests
   - JaCoCo coverage
4. Jenkins sends results to SonarQube
5. SonarQube checks quality gate
6. Jenkins runs Selenium UI tests
7. If all pass → deploy
