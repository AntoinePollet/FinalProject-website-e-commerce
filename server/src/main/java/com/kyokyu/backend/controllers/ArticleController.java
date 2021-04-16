package com.kyokyu.backend.controllers;

import com.kyokyu.backend.models.Article;
import com.kyokyu.backend.repository.ArticleRepo;
import com.kyokyu.backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    private ArticleRepo articleRepo;


    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public String allAccess() {
        return "OK ADMIN ^^";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticle () {
        List<Article> articles = articleService.findAllArticle();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Article> getArticleById (@PathVariable("id") String id) {
        Article article = articleService.findArticleById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        Article newArticle = articleService.addArticle(article);
        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        Article updateArticle = articleService.updateArticle(article);
        return new ResponseEntity<>(updateArticle, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateArticleById(@PathVariable("id") String id, @RequestBody Article article) {

        Article updateArticleById = articleService.updateArticleById(id, article);

        if(updateArticleById == null){
            return new ResponseEntity<>("article not found", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(updateArticleById, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") String id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>("article " + id + " deleted", HttpStatus.OK);
    }


}

