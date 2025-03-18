FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/file-system-service-*.jar file-system-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "file-system-service.jar"]
