FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/auth-service-*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
