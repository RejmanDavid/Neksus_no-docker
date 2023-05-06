package com.example.neksus.dao;

import com.example.neksus.models.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModDAO {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Mod> modRowMapper = (rs, rowNum) -> {
        Mod mod = new Mod();
        mod.setModId(rs.getLong("MOD_ID"));
        mod.setModName(rs.getString("MOD_NAME"));
        mod.setDescription(rs.getString("DESCRIPTION"));
        mod.setAuthor(rs.getString("AUTHOR"));
        mod.setGameId(rs.getLong("GAME_ID"));
        mod.setDatePublished(rs.getDate("DATE_PUBLISHED"));
        mod.setThumbnailImageId(rs.getString("THUMBNAIL_IMAGE_ID"));
        mod.setTrackCount(rs.getLong("TRACK_COUNT"));
        return mod;
    };

    @Autowired
    public ModDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Mod> getAllMods() {
        String sql = "SELECT * FROM MOD";
        return jdbcTemplate.query(sql, modRowMapper);
    }

    public Mod getModById(long modId) {
        String sql = "SELECT * FROM MOD WHERE MOD_ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{modId}, modRowMapper);
    }

    public boolean insertMod(Mod mod) {
        String sql = "INSERT INTO MOD (MOD_NAME, DESCRIPTION, AUTHOR, GAME_ID, DATE_PUBLISHED, THUMBNAIL_IMAGE_ID, TRACK_COUNT) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, mod.getModName(), mod.getDescription(), mod.getAuthor(), mod.getGameId(), mod.getDatePublished(), mod.getThumbnailImageId(), mod.getTrackCount());
        return rowsAffected > 0;
    }

    public boolean updateMod(Mod mod) {
        String sql = "UPDATE MOD SET MOD_NAME = ?, DESCRIPTION = ?, AUTHOR = ?, GAME_ID = ?, DATE_PUBLISHED = ?, THUMBNAIL_IMAGE_ID = ?, TRACK_COUNT = ? WHERE MOD_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, mod.getModName(), mod.getDescription(), mod.getAuthor(), mod.getGameId(), mod.getDatePublished(), mod.getThumbnailImageId(), mod.getTrackCount(), mod.getModId());
        return rowsAffected > 0;
    }

    public boolean deleteMod(long modId) {
        String sql = "DELETE FROM MOD WHERE MOD_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, modId);
        return rowsAffected > 0;
    }
}