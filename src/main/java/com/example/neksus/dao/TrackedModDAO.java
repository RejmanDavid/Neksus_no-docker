package com.example.neksus.dao;

import com.example.neksus.models.TrackedMod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrackedModDAO {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<TrackedMod> trackedModRowMapper = (resultSet, i) -> {
        TrackedMod trackedMod = new TrackedMod();
        trackedMod.setId(resultSet.getLong("id"));
        trackedMod.setUserId(resultSet.getString("user_id"));
        trackedMod.setModId(resultSet.getLong("mod_id"));
        return trackedMod;
    };

    @Autowired
    public TrackedModDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean insertTrackedMod(TrackedMod trackedMod) {
        String sql = "INSERT INTO N_TRACKED_MOD (TRACKED_MOD_ID, user_id, mod_id) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, trackedMod.getId(), trackedMod.getUserId(), trackedMod.getModId());
        return rowsAffected > 0;
    }

    public boolean updateTrackedMod(TrackedMod trackedMod) {
        String sql = "UPDATE N_TRACKED_MOD SET user_id = ?, mod_id = ? WHERE TRACKED_MOD_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, trackedMod.getUserId(), trackedMod.getModId(), trackedMod.getId());
        return rowsAffected > 0;
    }

    public boolean deleteTrackedMod(Long id) {
        String sql = "DELETE FROM N_TRACKED_MOD WHERE TRACKED_MOD_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

//    @Override
//    public List<TrackedMod> getAllTrackedModsByUserId(Long userId) {
//        String sql = "SELECT * FROM tracked_mod WHERE user_id = ?";
//        List<TrackedMod> trackedMods = jdbcTemplate.query(sql, trackedModRowMapper, userId);
//        return trackedMods;
//    }

    public List<TrackedMod> getAllTrackedMods() {
        String sql = "SELECT * FROM N_TRACKED_MOD";
        return jdbcTemplate.query(sql, trackedModRowMapper);
    }

    public TrackedMod getTrackedModById(Long id) {
        String sql = "SELECT * FROM N_TRACKED_MOD WHERE TRACKED_MOD_ID = ?";
        return jdbcTemplate.queryForObject(sql, trackedModRowMapper, id);
    }
}
