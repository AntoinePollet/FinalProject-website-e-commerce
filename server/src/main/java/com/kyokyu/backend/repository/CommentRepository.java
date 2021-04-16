package com.kyokyu.backend.repository;

import com.kyokyu.backend.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Optional<Comment> findCommentById(String id);

    Optional<Comment> findCommentByUsername(String username);

    Optional<Comment> findCommentByIdArticle(String idArticle);

    Optional<Comment> findCommentByRating(Double rating);

    void deleteCommentById(String id);



}


