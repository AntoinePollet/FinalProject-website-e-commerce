package com.kyokyu.backend.repository;

import com.kyokyu.backend.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findCommentById(String id);

    List<Comment> findCommentByUsername(String username);

    List<Comment> findCommentByIdArticle(String idArticle);

    List<Comment> findCommentByRating(Double rating);

    void deleteCommentById(String id);



}


