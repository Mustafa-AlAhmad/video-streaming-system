package com.app.uploadvideoservice.repository;

import com.app.uploadvideoservice.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {
}
