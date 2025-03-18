FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/auth-service-*.jar auth-service.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "auth-service.jar"]
