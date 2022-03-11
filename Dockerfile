FROM openjdk:16 AS builder
EXPOSE 8080
ENV TZ=Asia/Seoul
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]