package com.app.filesystemservice;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
public class FileSystemService {

    private static final String VIDEO_DIR = "/videos/";

    @GetMapping("/get")
    public ResponseEntity<FileSystemResource> getVideo(@RequestParam String path, @RequestParam String name) {
        File videoFile = new File(VIDEO_DIR + path + "/" + name + ".mp4");
        if (videoFile.exists()) {
            return ResponseEntity.ok()
                    .contentLength(videoFile.length())
                    .body(new FileSystemResource(videoFile));
        }
        return ResponseEntity.notFound().build();
    }
}
