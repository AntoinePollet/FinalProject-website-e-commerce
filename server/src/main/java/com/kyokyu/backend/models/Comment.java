package com.kyokyu.backend.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@Data
@Document(collection = "commentaires")
public class Comment {

    @Id
    private String id;

    private Date date;

    private String idArticle;

    private String username;

    private String message;

    private Double rating = null;


    public Comment() {
    }

    public Comment(Date date, String idArticle, String username, String message, Double rating, Boolean firstRating) {
        this.date = date;
        this.idArticle = idArticle;
        this.username = username;
        this.message = message;
        this.rating = rating;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
