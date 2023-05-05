package com.example.neksus.services.impl;

import com.example.neksus.dao.CommentDAO;
import com.example.neksus.dao.impl.CommentDAOImpl;
import com.example.neksus.models.Comment;
import com.example.neksus.services.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentDAO commentDAO;

    public CommentServiceImpl() {
        commentDAO = new CommentDAOImpl();
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentDAO.getCommentById(id);
    }

    @Override
    public boolean addComment(Comment comment) {
        commentDAO.insertComment(comment);
        return true;
    }

    @Override
    public boolean updateComment(Comment comment) {
        commentDAO.updateComment(comment);
        return true;
    }

    @Override
    public boolean deleteComment(Long id) {
        commentDAO.deleteComment(id);
        return true;
    }
}
