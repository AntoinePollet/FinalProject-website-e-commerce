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
@Document(collection = "shoppingCart")
public class ShoppingCart {

    @Id
    private String id;

    //ref
    private Date date;
    private List<CommandeArticles> articles;

    // ref
    private String username;
    private Double total = 0.0;


    public ShoppingCart() {
    }


    public ShoppingCart(List<CommandeArticles> articles, Date date, String username, Double total) {
        this.articles = articles;
        this.date = date;
        this.username = username;
        this.total = total;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CommandeArticles> getArticles() {
        return articles;
    }

    public void setArticles(List<CommandeArticles> articles) {
        this.articles = articles;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id='" + id + '\'' +
                ", articles =" + articles +
                ", date=" + date +
                ", username=" + username +
                ", total=" + total +
                '}';
    }
}
