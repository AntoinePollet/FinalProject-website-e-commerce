package com.kyokyu.backend.payload.response;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.Set;

public class SessionResponse {

    @NotBlank
    private String id;

    private String username;

    private String email;

    private Set<?> roles;

    private String token;



    public SessionResponse() {
    }

    public SessionResponse(String id, String username, String email, Set<?> roles, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.token = token;
    }

    public SessionResponse(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<?> getRoles() {
        return roles.stream().findFirst();
    }

    public void setRoles(Set<?> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
