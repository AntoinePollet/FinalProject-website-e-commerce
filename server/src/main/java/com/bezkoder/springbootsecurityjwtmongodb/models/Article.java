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
    private List pictures;
    private String category;
    private List color;
    private int price;
    private Double rating;
    private Boolean inStock = true;
    private Boolean favorites = false;

    public Article() {
    }

    public Article(String name, String description, List pictures, String category, List color, int price, Double rating, Boolean inStock, Boolean favorites) {
        this.name = name;
        this.description = description;
        this.pictures = pictures;
        this.category = category;
        this.color = color;
        this.price = price;
        this.rating = rating;
        this.inStock = inStock;
        this.favorites = favorites;
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
        return pictures;
    }

    public void setImages(List pictures) {
        this.pictures = pictures;
    }

    public String getCategorie() {
        return category;
    }

    public void setCategorie(String category) {
        this.category = category;
    }

    public List getCouleur() {
        return color;
    }

    public void setCouleur(List color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Double getNote() {
        return rating;
    }

    public void setNote(Double rating) {
        this.rating = rating;
    }

    public Boolean getStock() {
        return inStock;
    }

    public void setStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Boolean getFavoris() {
        return favorites;
    }

    public void setFavoris(Boolean favorites) {
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictures=" + pictures +
                ", category='" + category + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", inStock=" + inStock +
                ", favoris=" + favorites +
                '}';
    }
}

