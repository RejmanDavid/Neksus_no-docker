package com.example.neksus.controllers;

import com.example.neksus.models.Comment;
import com.example.neksus.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{modId}")
    public List<Comment> getCommentsByModId(@PathVariable Long modId) {
        return commentService.getCommentsByModId(modId);
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return comment;
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
