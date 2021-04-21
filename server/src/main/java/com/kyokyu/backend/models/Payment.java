package com.kyokyu.backend.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;

    private String username;

    private String token;

    private int amount;

    private List<CommandeArticles> array;

    private Delivery delivery;


    public Payment() {
    }


    public Payment(String username, String token, int amount, List<CommandeArticles> array, Delivery delivery) {
        this.username = username;
        this.token = token;
        this.amount = amount;
        this.array = array;
        this.delivery = delivery;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<CommandeArticles> getArray() {
        return array;
    }

    public void setArray(List<CommandeArticles> array) {
        this.array = array;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}