package com.kyokyu.backend.controllers;


import com.kyokyu.backend.models.Comment;
import com.kyokyu.backend.repository.CommentRepository;
import com.kyokyu.backend.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {


    private final CommentService commentService;

    Logger logger= LoggerFactory.getLogger(ArticleController.class);


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    private CommentRepository commentRepository;


    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComment () {
        List<Comment> comment = commentService.findAllComment();
        logger.info("getAllComment : {}", comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<List<Comment>> getCommentById (@PathVariable("id") String id) {
        List<Comment> comment = commentService.findCommentById(id);
        logger.info("getCommentById : {}", comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<List<Comment>> getCommentByUsername (@PathVariable("username") String username) {
        List<Comment> comment = commentService.findCommentByUsername(username);
        logger.info("getCommentByUsername : {}", comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @GetMapping("/findByIdArticle/{idArticle}")
    public ResponseEntity<List<Comment>> getCommentByIdArticle (@PathVariable("idArticle") String idArticle) {
        List<Comment> comment = commentService.findCommentByIdArticle(idArticle);
        logger.info("getCommentByIdArticle : {}", comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/findByRating/{rating}")
    public ResponseEntity<List<Comment>> getCommentByRating (@PathVariable("rating") Double rating) {
        List<Comment> comment = commentService.findCommentByRating(rating);
        logger.info("getCommentByRating : {}", comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }



    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment newComment = commentService.addComment(comment);
        logger.info("addComment : {}", comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    //Think about updating the comment ??

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteComment(@PathVariable("id") String id) {
        commentService.deleteComment(id);
        logger.info("deleteComment : {}", id);
        return new ResponseEntity<>("comment " + id + " deleted", HttpStatus.OK);
    }



}
