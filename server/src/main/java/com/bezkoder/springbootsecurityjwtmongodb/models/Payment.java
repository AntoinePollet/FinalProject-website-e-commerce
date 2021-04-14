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
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;

    private String username;

    private String token;

    private int amount;

    private List<String> array;



    public Payment() {
    }


    public Payment(String username, String token, int amount, List<String> array) {
        this.username = username;
        this.token = token;
        this.amount = amount;
        this.array = array;
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

    public List<String> getArray() {
        return array;
    }

    public void setArray(List<String> array) {
        this.array = array;
    }
}