package com.example.neksus.services;

import com.example.neksus.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    Comment getCommentById(Long id);

    boolean addComment(Comment comment);

    boolean updateComment(Comment comment);

    boolean deleteComment(Long id);
}
