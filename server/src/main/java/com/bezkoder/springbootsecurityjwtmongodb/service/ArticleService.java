package com.bezkoder.springbootsecurityjwtmongodb.service;

import com.bezkoder.springbootsecurityjwtmongodb.models.Article;
import com.bezkoder.springbootsecurityjwtmongodb.repository.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleService {
    private final ArticleRepo articleRepo;

    @Autowired
    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public Article addArticle(Article article) {
        return articleRepo.save(article);
    }

    public List<Article> findAllArticle() {
        return articleRepo.findAll();
    }

    public Article updateArticle(Article article) {
        return articleRepo.save(article);
    }

    public Article updateArticleById(String id, Article article) {
        Optional<Article> articleData = articleRepo.findById(id);

        if (articleData.isPresent()) {
            Article _article = articleData.get();
            _article.setName(article.getName());
            _article.setDescription(article.getDescription());
            _article.setImages(article.getImages());
            _article.setCategorie(article.getCategorie());
            _article.setCouleur(article.getCouleur());
            _article.setPrice(article.getPrice());
            _article.setNote(article.getNote());
            _article.setStock(article.getStock());
            _article.setFavoris(article.getFavoris());

            return articleRepo.save(_article);
        }

        return null;
    }


    public Article findArticleById(String id) {
        return articleRepo.findById(id)
                .orElseThrow(() -> new Error("User by id " + id + " was not found"));
    }

    public void deleteArticle(String id){
        articleRepo.deleteArticleById(id);
    }
}