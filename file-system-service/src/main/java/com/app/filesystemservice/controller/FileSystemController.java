package com.app.filesystem.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/files")
public class FileSystemController {

    private static final Logger logger = LoggerFactory.getLogger(FileSystemController.class);

    @Value("${video.upload.dir:videos}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam String name,
                                             @RequestParam String path) {
        try {
            String fileName = name + ".mp4";
            Path destinationDirectory = Path.of(uploadDir, path);
            Files.createDirectories(destinationDirectory); // Ensures the directory exists

            Path destinationFilePath = destinationDirectory.resolve(fileName);
            Files.copy(file.getInputStream(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);

            logger.info("File uploaded successfully: {}", destinationFilePath);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            logger.error("Error uploading file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<FileSystemResource> getVideo(@RequestParam String path, @RequestParam String name) {
        Path videoFilePath = Path.of(uploadDir, path, name + ".mp4");
        File videoFile = videoFilePath.toFile();

        if (!videoFile.exists()) {
            logger.error("Requested video not found: {}", videoFilePath);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        logger.info("Fetching video: {}", videoFilePath);
        return ResponseEntity.ok()
                .contentLength(videoFile.length())
                .body(new FileSystemResource(videoFile));
    }
}
