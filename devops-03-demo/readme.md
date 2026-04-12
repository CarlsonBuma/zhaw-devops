# DevOpsDemo

Sample project for DevOps Course @ ZHAW.

## SonarQube
 - Install Run Image
 - Connect via Brwoser :9000
 - Run Test via PS:
    .\gradlew --% sonar -Dsonar.projectKey=devops-demo -Dsonar.projectName=devops-demo -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_959b8ab18bbbd67faca36a363427a6cef07bd556

### Frontend
npx --% sonar-scanner -Dsonar.host.url=http://localhost:9000 -Dsonar.projectKey=devops-demo-frontend -Dsonar.projectName=devops-demo-frontend -Dsonar.token=sqp_9c41238312391251d688b73e3cf40d9cacead0f3



