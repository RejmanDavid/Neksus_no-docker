package com.example.neksus.dao;

import com.example.neksus.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByEmail(String email) {
        String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
            User user = new User();
            user.setUsername(rs.getString("USERNAME"));
            user.setEmail(rs.getString("EMAIL"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setIsBanned(rs.getString("IS_BANNED").charAt(0));
            user.setIsAdmin(rs.getString("IS_ADMIN").charAt(0));
            user.setRegisterDate(rs.getDate("REGISTER_DATE"));
            user.setEmployeeId(rs.getString("EMPLOYEE_ID"));
            return user;
        });
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setUsername(rs.getString("USERNAME"));
            user.setEmail(rs.getString("EMAIL"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setIsBanned(rs.getString("IS_BANNED").charAt(0));
            user.setIsAdmin(rs.getString("IS_ADMIN").charAt(0));
            user.setRegisterDate(rs.getDate("REGISTER_DATE"));
            user.setEmployeeId(rs.getString("EMPLOYEE_ID"));
            return user;
        });
    }

    public User create(User user) {
        String sql = "INSERT INTO USERS (USERNAME, EMAIL, PASSWORD, IS_BANNED, IS_ADMIN, REGISTER_DATE, EMPLOYEE_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), String.valueOf(user.getIsBanned()), String.valueOf(user.getIsAdmin()), user.getRegisterDate(), user.getEmployeeId());
        return rowsAffected > 0 ? user : null;
    }

    public boolean update(User user) {
        String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ?, IS_BANNED = ?, IS_ADMIN = ?, REGISTER_DATE = ?, EMPLOYEE_ID = ? WHERE EMAIL = ?";
        int rowsAffected = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), String.valueOf(user.getIsBanned()), String.valueOf(user.getIsAdmin()), user.getRegisterDate(), user.getEmployeeId(), user.getEmail());
        return rowsAffected > 0;
    }

    public boolean delete(String email) {
        String sql = "DELETE FROM USERS WHERE EMAIL = ?";
        int rowsAffected = jdbcTemplate.update(sql, email);
        return rowsAffected > 0;
    }
}
