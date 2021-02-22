FROM openjdk:8-jdk-alpine
ADD build/libs/test_task-0.0.1-SNAPSHOT.jar test_task-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/test_task-0.0.1-SNAPSHOT.jar"]