FROM eclipse-temurin:17-jre-focal
EXPOSE 8889
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]