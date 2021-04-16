package com.kyokyu.backend.models;

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

    public List getPictures() {
        return pictures;
    }

    public void setPictures(List pictures) {
        this.pictures = pictures;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List getColor() {
        return color;
    }

    public void setColor(List color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Boolean getFavorites() {
        return favorites;
    }

    public void setFavorites(Boolean favorites) {
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

