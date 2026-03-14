# Java
java -version

# Gradle
gradle -version
gradle build
gradle run

# NPM
node -v
npm -v

# Docker
docker -v
docker ps

## Jenkings Image
docker run -p 8084:8080 -p 50000:50000 --name jenkins-v -v jenkins_home:/var/jenkins_home mosazhaw/jenkins:2.528.2_jdk25.0.1

