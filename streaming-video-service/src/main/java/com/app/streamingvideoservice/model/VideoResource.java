package com.app.streamingvideoservice.model;

import lombok.Data;
import org.springframework.http.MediaType;

@Data
public class VideoResource {
    private final byte[] data;
    private final MediaType contentType;
}
