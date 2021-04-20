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

    public List<Comment> findCommentById(String id) {
        return commentRepository.findCommentById(id);
    }

    public List<Comment> findCommentByUsername(String username) {
        return commentRepository.findCommentByUsername(username);
    }

    public List<Comment> findCommentByIdArticle(String idArticle) {
        return commentRepository.findCommentByIdArticle(idArticle);
    }

    public List<Comment> findCommentByRating(Double rating) {
        return commentRepository.findCommentByRating(rating);
    }


    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(String id){
        commentRepository.deleteCommentById(id);
    }


}
