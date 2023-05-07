package com.example.neksus.models;

public class Video {

    private Long videoId;
    private String videoPath;
    private Long modId;

    // Getters
    public Long getVideoId() {
        return videoId;
    }

    // Setters
    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Long getModId() {
        return modId;
    }

    public void setModId(Long modId) {
        this.modId = modId;
    }
}
