package com.kyokyu.backend.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Document(collection = "favorites")
public class Favorites {

    @Id
    private String id;


    private String username;

    private String idArticle;


    public Favorites() {
    }


    public Favorites(String username, String idArticle) {
        this.username = username;
        this.idArticle = idArticle;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }
}
