package com.example.neksus.dao;

import com.example.neksus.models.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilesDAO {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Files> filesRowMapper = (resultSet, rowNum) -> {
        Files file = new Files();
        file.setFileId(resultSet.getLong("FILE_ID"));
        file.setModId(resultSet.getLong("MOD_ID"));
        file.setDescription(resultSet.getString("DESCRIPTION"));
        file.setFilePath(resultSet.getString("FILE_PATH"));
        file.setReleaseDate(resultSet.getDate("RELEASE_DATE"));
        file.setVersion(resultSet.getString("VERSION"));
        return file;
    };

    @Autowired
    public FilesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Files> getAllFiles() {
        String sql = "SELECT * FROM N_FILE";
        return jdbcTemplate.query(sql, filesRowMapper);
    }

    public Files getFileById(Long fileId) {
        String sql = "SELECT * FROM N_FILE WHERE FILE_ID = ?";
        return jdbcTemplate.queryForObject(sql, filesRowMapper, fileId);
    }


    public List<Files> getFilesByModId(Long modId) {
        String sql = "SELECT * FROM N_FILE WHERE MOD_ID = ?";
        return jdbcTemplate.query(sql, filesRowMapper, modId);
    }

    public boolean insertFile(Files file) {
        String sql = "INSERT INTO N_FILE (MOD_ID, DESCRIPTION, FILE_PATH, VERSION) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, file.getModId(), file.getDescription(), file.getFilePath(), file.getVersion());
        return rowsAffected > 0;
    }

    public boolean updateFile(Files file) {
        String sql = "UPDATE N_FILE SET MOD_ID = ?, DESCRIPTION = ?, FILE_PATH = ?, RELEASE_DATE = ?, VERSION = ? WHERE FILE_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, file.getModId(), file.getDescription(), file.getFilePath(), file.getReleaseDate(), file.getVersion(), file.getFileId());
        return rowsAffected > 0;
    }

    public boolean deleteFile(Long fileId) {
        String sql = "DELETE FROM N_FILE WHERE FILE_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, fileId);
        return rowsAffected > 0;
    }
}
