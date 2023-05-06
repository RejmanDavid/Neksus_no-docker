package com.example.neksus.services;

import com.example.neksus.dao.CommentDAO;
import com.example.neksus.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentDAO commentDAO;

    @Autowired
    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public List<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }

    public Comment getCommentById(Long commentId) {
        return commentDAO.getCommentById(commentId);
    }

    public boolean addComment(Comment comment) {
        commentDAO.insertComment(comment);
        return true;
    }

    public boolean updateComment(Comment comment) {
        commentDAO.updateComment(comment);
        return true;
    }

    public boolean deleteComment(Long commentId) {
        commentDAO.deleteComment(commentId);
        return true;
    }
}
