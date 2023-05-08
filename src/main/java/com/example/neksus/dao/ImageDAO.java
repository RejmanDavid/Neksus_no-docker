package com.example.neksus.dao;

import com.example.neksus.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ImageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Image> getAllImages() {
        String sql = "SELECT * FROM N_IMAGE";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Image image = new Image();
            image.setImageId(resultSet.getLong("IMAGE_ID"));
            image.setImagePath(resultSet.getString("IMAGE_PATH"));
            image.setModId(resultSet.getLong("MOD_ID"));
            return image;
        });
    }

    public Image getImageById(Long id) {
        String sql = "SELECT * FROM N_IMAGE WHERE IMAGE_ID = ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> {
            Image image = new Image();
            image.setImageId(resultSet.getLong("IMAGE_ID"));
            image.setImagePath(resultSet.getString("IMAGE_PATH"));
            image.setModId(resultSet.getLong("MOD_ID"));
            return image;
        }, id);
    }

    public List<Image> getImagesByModId(Long modId) {
        String sql = "SELECT * FROM N_IMAGE WHERE MOD_ID = ?";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Image image = new Image();
            image.setImageId(resultSet.getLong("IMAGE_ID"));
            image.setImagePath(resultSet.getString("IMAGE_PATH"));
            image.setModId(resultSet.getLong("MOD_ID"));
            return image;
        }, modId);
    }

    public boolean insertImage(Image image) {
        String sql = "INSERT INTO N_IMAGE (IMAGE_ID, IMAGE_PATH, MOD_ID) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, image.getImageId(), image.getImagePath(), image.getModId()) > 0;
    }

    public boolean deleteImage(Long id) {
        String sql = "DELETE FROM N_IMAGE WHERE IMAGE_ID = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
