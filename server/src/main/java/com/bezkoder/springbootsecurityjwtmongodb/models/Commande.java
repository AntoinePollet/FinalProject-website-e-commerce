package com.bezkoder.springbootsecurityjwtmongodb.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Document(collection = "commandes")
public class Commande {
    @Id
    private String id;
    //ref

    private List<String> idArticle;

    private Date date;
    // ref

    private String idUser;

    private Double total;


    public Commande() {
    }

    public Commande(List<String> idArticle, Date date, String idUser, Double total) {
        this.idArticle = idArticle;
        this.date = date;
        this.idUser = idUser;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(List<String> idArticle) {
        this.idArticle = idArticle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }






}