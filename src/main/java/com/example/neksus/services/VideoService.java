package com.example.neksus.services;

import com.example.neksus.dao.VideoDAO;
import com.example.neksus.models.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VideoService {

    private static final Pattern YOUTUBE_URL_PATTERN = Pattern.compile("^(https?://)?(www\\.)?(youtube\\.com|youtu\\.?be)/.+$");
    private final VideoDAO videoDAO;

    @Autowired
    public VideoService(VideoDAO videoDAO) {
        this.videoDAO = videoDAO;
    }

    public List<Video> getAllVideos() {
        return videoDAO.getAllVideos();
    }

    public Video getVideoById(Long id) {
        return videoDAO.getVideoById(id);
    }

    public boolean addVideo(Video video) {
        if (isVideoValid(video)) {
            return videoDAO.insertVideo(video);
        } else {
            throw new IllegalArgumentException("Invalid video data.");
        }
    }

    public boolean updateVideo(Video video) {
        if (isVideoValid(video)) {
            return videoDAO.updateVideo(video);
        } else {
            throw new IllegalArgumentException("Invalid video data.");
        }
    }

    public boolean deleteVideo(Long id) {
        return videoDAO.deleteVideo(id);
    }

    private boolean isVideoValid(Video video) {
        if (video.getVideoPath() == null || !isValidYouTubeUrl(video.getVideoPath())) {
            return false;
        }

        if (video.getModId() == null || video.getModId() <= 0) {
            return false;
        }
        return true;
    }

    private boolean isValidYouTubeUrl(String url) {
        Matcher matcher = YOUTUBE_URL_PATTERN.matcher(url);
        return matcher.matches();
    }

}