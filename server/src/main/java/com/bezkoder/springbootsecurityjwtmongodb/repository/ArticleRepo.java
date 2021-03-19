package com.bezkoder.springbootsecurityjwtmongodb.repository;

import com.bezkoder.springbootsecurityjwtmongodb.models.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepo extends MongoRepository<Article, String> {
    void deleteArticleById(String id);

    Optional<Article> findArticleById(String id);


}
