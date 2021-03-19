package com.bezkoder.springbootsecurityjwtmongodb.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@ToString
@Document(collection = "articles")
public class Article {
    @Id
    private String id;
    private String name;
    private String description;
    private List images;
    private String categorie;
    private String couleur;
    private int price;
    private Double note;
    private Boolean inStock = true;
    private Boolean favoris = false;

    public Article() {
    }

    public Article(String name, String description, List images, String categorie, String couleur, int price, Double note, Boolean inStock, Boolean favoris) {
        this.name = name;
        this.description = description;
        this.images = images;
        this.categorie = categorie;
        this.couleur = couleur;
        this.price = price;
        this.note = note;
        this.inStock = inStock;
        this.favoris = favoris;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List getImages() {
        return images;
    }

    public void setImages(List images) {
        this.images = images;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Boolean getStock() {
        return inStock;
    }

    public void setStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Boolean getFavoris() {
        return favoris;
    }

    public void setFavoris(Boolean favoris) {
        this.favoris = favoris;
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", images=" + images +
                ", categorie='" + categorie + '\'' +
                ", couleur='" + couleur + '\'' +
                ", price=" + price +
                ", note=" + note +
                ", inStock=" + inStock +
                ", favoris=" + favoris +
                '}';
    }
}

