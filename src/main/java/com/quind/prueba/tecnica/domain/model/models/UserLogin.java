package com.quind.prueba.tecnica.domain.model.models;

public class UserLogin {
    private String username;
    private String password;

    private String email;
    private Boolean locked;
    private Boolean disabled;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLogin(String username, String password, String email, Boolean locked, Boolean disabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.locked = locked;
        this.disabled = disabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
