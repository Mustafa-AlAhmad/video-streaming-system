package com.app.streamingvideoservice.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/stream")
public class VideoStreamingController {

    private static final String VIDEO_DIRECTORY = "/app/videos/uploads";  // ✅ FIXED PATH

    @GetMapping("/{filename:.+}")  // 🔥 FIX: Supports filenames with dots
    public ResponseEntity<Resource> streamVideo(@PathVariable String filename) {
        System.out.println("🔥🔥🔥 REQUEST RECEIVED: " + filename + " 🔥🔥🔥");

        File videoFile = new File(VIDEO_DIRECTORY, filename);
        System.out.println("📂 Searching for file: " + videoFile.getAbsolutePath());

        if (!videoFile.exists()) {
            System.out.println("❌ FILE NOT FOUND: " + filename);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(null);
        }

        System.out.println("✅ FILE FOUND! STREAMING: " + filename);
        Resource videoResource = new FileSystemResource(videoFile);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(videoResource);
    }
}
