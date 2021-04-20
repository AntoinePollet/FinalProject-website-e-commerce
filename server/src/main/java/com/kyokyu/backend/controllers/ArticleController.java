package com.kyokyu.backend.controllers;

import com.kyokyu.backend.models.Article;
import com.kyokyu.backend.repository.ArticleRepo;
import com.kyokyu.backend.service.ArticleService;
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
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    private ArticleRepo articleRepo;


    Logger logger= LoggerFactory.getLogger(ArticleController.class);



    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public String allAccess() {
        return "OK ADMIN ^^";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticle () {
        List<Article> articles = articleService.findAllArticle();
        logger.info("articles found : {}", articles);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Article> getArticleById (@PathVariable("id") String id) {
        Article article = articleService.findArticleById(id);
        logger.info("article found : {}",article);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        Article newArticle = articleService.addArticle(article);
        logger.info("articles added : {}", newArticle);
        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        Article updateArticle = articleService.updateArticle(article);
        logger.info("articles updated : {}", updateArticle);
        return new ResponseEntity<>(updateArticle, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateArticleById(@PathVariable("id") String id, @RequestBody Article article) {

        Article updateArticleById = articleService.updateArticleById(id, article);

        if(updateArticleById == null){
            logger.info("article not found : {}", updateArticleById);
            return new ResponseEntity<>("article not found", HttpStatus.NOT_FOUND);

        }
        logger.info("article updated : {}", updateArticleById);
        return new ResponseEntity<>(updateArticleById, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") String id) {
        articleService.deleteArticle(id);
        logger.info("article deletedById : {}", id);
        return new ResponseEntity<>("article " + id + " deleted", HttpStatus.OK);
    }


}

