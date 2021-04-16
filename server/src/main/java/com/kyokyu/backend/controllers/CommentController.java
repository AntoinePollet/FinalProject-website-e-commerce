package com.kyokyu.backend.controllers;


import com.kyokyu.backend.models.Comment;
import com.kyokyu.backend.repository.CommentRepository;
import com.kyokyu.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments")
public class CommentController {


    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    private CommentRepository commentRepository;


    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComment () {
        List<Comment> comment = commentService.findAllComment();
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Comment> getCommentById (@PathVariable("id") String id) {
        Comment comment = commentService.findCommentById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<Comment> getCommentByUsername (@PathVariable("username") String username) {
        Comment comment = commentService.findCommentByUsername(username);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @GetMapping("/findByIdArticle/{idArticle}")
    public ResponseEntity<Comment> getCommentByIdArticle (@PathVariable("idArticle") String idArticle) {
        Comment comment = commentService.findCommentByIdArticle(idArticle);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/findByRating/{rating}")
    public ResponseEntity<Comment> getCommentByRating (@PathVariable("rating") Double rating) {
        Comment comment = commentService.findCommentByRating(rating);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Comment> addArticle(@RequestBody Comment comment) {
        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") String id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>("comment " + id + " deleted", HttpStatus.OK);
    }



}
