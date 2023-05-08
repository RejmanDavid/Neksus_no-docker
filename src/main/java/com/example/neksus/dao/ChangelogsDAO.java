package com.example.neksus.dao;

import com.example.neksus.models.Changelogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChangelogsDAO {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Changelogs> changelogRowMapper = (resultSet, rowNum) -> {
        Changelogs changelog = new Changelogs();
        changelog.setChangelogId(resultSet.getLong("CHANGELOG_ID"));
        changelog.setDescription(resultSet.getString("DESCRIPTION"));
        changelog.setVersion(resultSet.getString("VERSION"));
        changelog.setModId(resultSet.getLong("MOD_ID"));
        return changelog;
    };

    @Autowired
    public ChangelogsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Changelogs> getAllChangelogs() {
        String sql = "SELECT * FROM N_CHANGELOG";
        return jdbcTemplate.query(sql, changelogRowMapper);
    }

    public Changelogs getChangelogById(Long id) {
        String sql = "SELECT * FROM N_CHANGELOG WHERE CHANGELOG_ID = ?";
        return jdbcTemplate.queryForObject(sql, changelogRowMapper, id);
    }

    public List<Changelogs> getChangelogsByModId(Long modId) {
        String sql = "SELECT * FROM N_CHANGELOG WHERE MOD_ID = ?";
        return jdbcTemplate.query(sql, changelogRowMapper, modId);
    }

    public boolean insertChangelog(Changelogs changelog) {
        String sql = "INSERT INTO N_CHANGELOG (CHANGELOG_ID, DESCRIPTION, VERSION, MOD_ID) VALUES (SEQ_N_CHANGELOG_ID.NEXTVAL, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, changelog.getDescription(), changelog.getVersion(), changelog.getModId());
        return rowsAffected > 0;
    }


    public boolean updateChangelog(Changelogs changelog) {
        String sql = "UPDATE N_CHANGELOG SET DESCRIPTION = ?, VERSION = ?, MOD_ID = ? WHERE CHANGELOG_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, changelog.getDescription(), changelog.getVersion(), changelog.getModId(), changelog.getChangelogId());
        return rowsAffected > 0;
    }

    public boolean deleteChangelog(Long id) {
        String sql = "DELETE FROM N_CHANGELOG WHERE CHANGELOG_ID = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}
