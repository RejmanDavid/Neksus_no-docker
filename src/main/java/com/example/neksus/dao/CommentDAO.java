package com.example.neksus.dao;

import com.example.neksus.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Comment> commentRowMapper = (rs, rowNum) -> {
        Comment comment = new Comment();
        comment.setId(rs.getLong("id"));
        comment.setUserId(rs.getLong("user_id"));
        comment.setModId(rs.getLong("mod_id"));
        comment.setContent(rs.getString("content"));
        comment.setDateCreated(rs.getDate("date_created"));
        return comment;
    };

    public List<Comment> getAllComments() {
        String sql = "SELECT * FROM COMMENTS";
        return jdbcTemplate.query(sql, commentRowMapper);
    }

    public Comment getCommentById(Long id) {
        String sql = "SELECT * FROM COMMENTS WHERE COMMENT_ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, commentRowMapper);
    }

    public void updateComment(Comment comment) {
        String sql = "UPDATE COMMENTS SET USERS_ID = ?, MOD_ID = ?, COMMENT_TEXT = ?, DATE_COMMENTED = ? WHERE COMMENT_ID = ?";
        jdbcTemplate.update(sql, comment.getUserId(), comment.getModId(), comment.getContent(), comment.getDateCreated(), comment.getId());
    }

    public void insertComment(Comment comment) {
        String sql = "INSERT INTO COMMENTS (USERS_ID, MOD_ID, COMMENT_TEXT, DATE_COMMENTED) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, comment.getUserId(), comment.getModId(), comment.getContent(), comment.getDateCreated());
    }

    public void deleteComment(Long id) {
        String sql = "DELETE FROM COMMENTS WHERE COMMENT_ID = ?";
        jdbcTemplate.update(sql, id);
    }
}
