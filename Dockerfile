FROM openjdk:16 AS builder
COPY ./build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]