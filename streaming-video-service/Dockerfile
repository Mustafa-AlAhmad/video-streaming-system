FROM openjdk:17-jdk-slim
WORKDIR /app
COPY ./target/streaming-video-service-*.jar streaming-video-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "streaming-video-service.jar"]
