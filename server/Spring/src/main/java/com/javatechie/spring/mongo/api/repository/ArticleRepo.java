package com.javatechie.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.javatechie.spring.mongo.api.model.Article;

import java.util.Optional;

public interface ArticleRepo extends MongoRepository<Article, Integer>{
    void deleteBookById(String id);

    Optional<Article> findBookById(String id);


}
