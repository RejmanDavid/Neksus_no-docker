package com.example.neksus.dao;

import com.example.neksus.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAO {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Comment> commentRowMapper = (rs, rowNum) -> {
        Comment comment = new Comment();
        comment.setCommentId(rs.getLong("COMMENT_ID"));
        comment.setUserId(rs.getString("USERS_ID"));
        comment.setModId(rs.getLong("MOD_ID"));
        comment.setCommentText(rs.getString("COMMENT_TEXT"));
        comment.setDateCommented(rs.getDate("DATE_COMMENTED"));
        comment.setParentComment(rs.getLong("PARENT_COMMENT"));
        return comment;
    };

    @Autowired
    public CommentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Comment> getAllComments() {
        String sql = "SELECT * FROM COMMENTS";
        return jdbcTemplate.query(sql, commentRowMapper);
    }

    public Comment getCommentById(Long commentId) {
        String sql = "SELECT * FROM COMMENTS WHERE COMMENT_ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{commentId}, commentRowMapper);
    }

    public int updateComment(@NonNull Comment comment) {
        String sql = "UPDATE COMMENTS SET USERS_ID = ?, MOD_ID = ?, COMMENT_TEXT = ?, DATE_COMMENTED = ?, PARENT_COMMENT = ? WHERE COMMENT_ID = ?";
        return jdbcTemplate.update(sql, comment.getUserId(), comment.getModId(), comment.getCommentText(), comment.getDateCommented(), comment.getParentComment(), comment.getCommentId());
    }

    public int insertComment(@NonNull Comment comment) {
        String sql = "INSERT INTO COMMENTS (COMMENT_ID, USERS_ID, MOD_ID, COMMENT_TEXT, DATE_COMMENTED, PARENT_COMMENT) VALUES (COMMENTS_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, comment.getUserId(), comment.getModId(), comment.getCommentText(), comment.getDateCommented(), comment.getParentComment());
    }

    public int deleteComment(Long commentId) {
        String sql = "DELETE FROM COMMENTS WHERE COMMENT_ID = ?";
        return jdbcTemplate.update(sql, commentId);
    }
}
