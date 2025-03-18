package com.app.streamingvideoservice.repository;

import com.app.streamingvideoservice.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {
}
