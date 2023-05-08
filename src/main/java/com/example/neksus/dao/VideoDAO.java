package com.example.neksus.dao;

import com.example.neksus.models.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoDAO {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Video> videoRowMapper = (resultSet, i) -> {
        Video video = new Video();
        video.setVideoId(resultSet.getLong("VIDEO_ID"));
        video.setVideoPath(resultSet.getString("VIDEO_PATH"));
        video.setModId(resultSet.getLong("MOD_ID"));
        return video;
    };

    @Autowired
    public VideoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Video> getAllVideos() {
        String sql = "SELECT * FROM N_VIDEO";
        return jdbcTemplate.query(sql, videoRowMapper);
    }

    public Video getVideoById(Long id) {
        String sql = "SELECT * FROM N_VIDEO WHERE VIDEO_ID = ?";
        return jdbcTemplate.queryForObject(sql, videoRowMapper, id);
    }

    public boolean insertVideo(Video video) {
        String sql = "INSERT INTO N_VIDEO (VIDEO_ID, VIDEO_PATH, MOD_ID) VALUES (SEQ_N_VIDEO_ID.NEXTVAL, ?, ?)";
        return jdbcTemplate.update(sql, video.getVideoPath(), video.getModId()) > 0;
    }

    public boolean updateVideo(Video video) {
        String sql = "UPDATE N_VIDEO SET VIDEO_PATH = ?, MOD_ID = ? WHERE VIDEO_ID = ?";
        return jdbcTemplate.update(sql, video.getVideoPath(), video.getModId(), video.getVideoId()) > 0;
    }

    public boolean deleteVideo(Long id) {
        String sql = "DELETE FROM N_VIDEO WHERE VIDEO_ID = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
