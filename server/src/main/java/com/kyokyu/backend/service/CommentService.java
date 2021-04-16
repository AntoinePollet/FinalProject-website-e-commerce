package com.kyokyu.backend.service;


import com.kyokyu.backend.models.Comment;
import com.kyokyu.backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }

    public Comment findCommentById(String id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new Error("User by id " + id + " was not found"));
    }

    public Comment findCommentByUsername(String username) {
        return commentRepository.findCommentByUsername(username)
                .orElseThrow(() -> new Error("User by username " + username + " was not found"));
    }

    public Comment findCommentByIdArticle(String idArticle) {
        return commentRepository.findCommentByIdArticle(idArticle)
                .orElseThrow(() -> new Error("User by username " + idArticle + " was not found"));
    }

    public Comment findCommentByRating(Double rating) {
        return commentRepository.findCommentByRating(rating)
                .orElseThrow(() -> new Error("User by username " + rating + " was not found"));
    }


    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(String id){
        commentRepository.deleteCommentById(id);
    }


}
