package com.example.neksus.controllers;

import com.example.neksus.models.Video;
import com.example.neksus.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    @Autowired
    VideoService videoService;

    @PostMapping("/addVideo")
    public ResponseEntity<?> addVideo(@RequestBody Video video) {
        if (videoService.addVideo(video)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
