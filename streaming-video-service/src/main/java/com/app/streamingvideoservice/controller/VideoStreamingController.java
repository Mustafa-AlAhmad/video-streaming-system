package com.app.streamingvideoservice.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/stream")
public class VideoStreamingController {

    private static final String VIDEO_DIRECTORY = "/videos/uploads/uploads";  // ✅ FIXED PATH

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String filename) {
        File videoFile = new File(VIDEO_DIRECTORY, filename + ".mp4");

        if (!videoFile.exists()) {
            return ResponseEntity.notFound().build();  // 🔥 If video is missing, return 404
        }

        Resource videoResource = new FileSystemResource(videoFile);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("video/mp4"))
                .body(videoResource);
    }
}
