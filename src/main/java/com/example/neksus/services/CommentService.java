package com.example.neksus.services;

import com.example.neksus.dao.CommentDAO;
import com.example.neksus.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
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

    public int addComment(@NonNull Comment comment) {
        if (!isValidComment(comment)) {
            throw new IllegalArgumentException("Invalid comment object");
        }
        return commentDAO.insertComment(comment);
    }

    public int updateComment(@NonNull Comment comment) {
        if (!isValidComment(comment)) {
            throw new IllegalArgumentException("Invalid comment object");
        }
        return commentDAO.updateComment(comment);
    }

    public int deleteComment(Long commentId) {
        return commentDAO.deleteComment(commentId);
    }

    private boolean isValidComment(Comment comment) {
        return comment.getCommentText() != null && !comment.getCommentText().trim().isEmpty();
    }
}
