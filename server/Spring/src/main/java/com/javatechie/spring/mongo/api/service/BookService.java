package com.javatechie.spring.mongo.api.service;

import com.javatechie.spring.mongo.api.exception.UserNotFoundException;
import com.javatechie.spring.mongo.api.model.Article;
import com.javatechie.spring.mongo.api.repository.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookService {
    private final ArticleRepo articleRepo;

    @Autowired
    public BookService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public Article addBook(Article article) {
        return articleRepo.save(article);
    }

    public List<Article> findAllBooks() {
        return articleRepo.findAll();
    }

    public Article updateBook(Article article) {
        return articleRepo.save(article);
    }

    public Article findBookById(String id) {
        return articleRepo.findBookById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteBook(String id){
        articleRepo.deleteBookById(id);
    }
}
