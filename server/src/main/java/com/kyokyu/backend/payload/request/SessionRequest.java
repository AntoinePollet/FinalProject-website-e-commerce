package com.kyokyu.backend.payload.request;


import javax.validation.constraints.NotBlank;

public class SessionRequest {

    @NotBlank
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
