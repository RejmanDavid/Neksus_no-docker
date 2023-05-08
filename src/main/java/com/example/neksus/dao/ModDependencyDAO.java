package com.example.neksus.dao;

import com.example.neksus.models.ModDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ModDependencyDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ModDependencyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean insertModDependency(ModDependency modDependency) {
        String sql = "INSERT INTO N_MOD_DEPENDENCY (CHILD_MOD_ID, PARENT_MOD_ID, MOD_DEPENDENCY_ID) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, modDependency.getChildModId(), modDependency.getParentModId(), modDependency.getModDependencyId()) > 0;
    }

    public boolean deleteModDependency(Long modDependencyId) {
        String sql = "DELETE FROM N_MOD_DEPENDENCY WHERE MOD_DEPENDENCY_ID = ?";
        return jdbcTemplate.update(sql, modDependencyId) > 0;
    }
}
