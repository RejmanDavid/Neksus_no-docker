package com.example.neksus.dao;

import com.example.neksus.models.Comment;

import java.util.List;

public interface CommentDAO {
    List<Comment> getAllComments();

    Comment getCommentById(Long id);

    void insertComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(Long id);
}
