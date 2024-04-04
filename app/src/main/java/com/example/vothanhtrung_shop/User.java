package com.example.vothanhtrung_shop;

public class User {
    private String email;
    private String numphone;
    private String pass;
    private String photo;
    private String username;

    public User(String email, String pass, String photo, String username,String numphone) {
        this.email = email;
        this.numphone = numphone;
        this.pass = pass;
        this.photo = photo;
        this.username = username;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumphone() {
        return numphone;
    }

    public void setNumphone(String numphone) {
        this.numphone = numphone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}