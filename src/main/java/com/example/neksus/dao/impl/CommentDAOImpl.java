package com.example.neksus.dao.impl;

import com.example.neksus.dao.CommentDAO;
import com.example.neksus.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private Connection connection;

    @Override
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMMENTS");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setUserId(resultSet.getLong("user_id"));
                comment.setModId(resultSet.getLong("mod_id"));
                comment.setContent(resultSet.getString("content"));
                comment.setDateCreated(resultSet.getDate("date_created"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public Comment getCommentById(Long id) {
        Comment comment = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMMENTS WHERE COMMENT_ID = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setUserId(resultSet.getLong("user_id"));
                comment.setModId(resultSet.getLong("mod_id"));
                comment.setContent(resultSet.getString("content"));
                comment.setDateCreated(resultSet.getDate("date_created"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    @Override
    public void updateComment(Comment comment) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE COMMENTS SET USERS_ID = ?, MOD_ID = ?, COMMENT_TEXT = ?, DATE_COMMENTED = ? WHERE COMMENT_ID = ?");
            statement.setLong(1, comment.getUserId());
            statement.setLong(2, comment.getModId());
            statement.setString(3, comment.getContent());
            statement.setDate(4, new java.sql.Date(comment.getDateCreated().getTime()));
            statement.setLong(5, comment.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertComment(Comment comment) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO COMMENTS (USERS_ID, MOD_ID, COMMENT_TEXT, DATE_COMMENTED) VALUES (?, ?, ?, ?)");
            statement.setLong(1, comment.getUserId());
            statement.setLong(2, comment.getModId());
            statement.setString(3, comment.getContent());
            statement.setDate(4, new java.sql.Date(comment.getDateCreated().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComment(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM COMMENTS WHERE COMMENT_ID = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
