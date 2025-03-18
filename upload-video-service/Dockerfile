FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/upload-video-service-*.jar upload-video-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "upload-video-service.jar"]
