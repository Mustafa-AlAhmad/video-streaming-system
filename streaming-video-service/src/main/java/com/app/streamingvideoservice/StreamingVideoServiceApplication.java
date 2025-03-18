package com.app.streamingvideoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class StreamingVideoServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(StreamingVideoServiceApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Video Streaming Service...");
        SpringApplication.run(StreamingVideoServiceApplication.class, args);
        logger.info("Video Streaming Service started successfully!");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
