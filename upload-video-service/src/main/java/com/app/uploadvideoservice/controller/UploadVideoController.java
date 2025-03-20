package com.app.uploadvideoservice.controller;

import com.app.uploadvideoservice.model.Video;
import com.app.uploadvideoservice.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadVideoController {

    @Value("${video.upload.dir:/videos}")
    private String uploadDir;

    private final VideoRepository videoRepository;

    public UploadVideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @PostMapping
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam String name) {
        try {
            File uploadPath = new File(uploadDir + "/uploads");
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            File destinationFile = new File(uploadPath, name + ".mp4");
            file.transferTo(destinationFile);

            Video video = new Video(name, "uploads");
            videoRepository.save(video);

            return ResponseEntity.ok("Video uploaded successfully to: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }
}
