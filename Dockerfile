FROM openjdk:16 AS builder
EXPOSE 8080
ENV TZ=Asia/Seoul
COPY ./alchemist-backend-main/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]