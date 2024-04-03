package com.example.vothanhtrung_shop;

public class User {
    private String userName;
    private String email;
    private int roles; // Đổi kiểu dữ liệu của roles thành int để phản ánh vai trò của người dùng

    public User(String userName, String email, int roles) { // Thêm tham số roles vào constructor
        this.userName = userName;
        this.email = email;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public int getRoles() { // Thêm getter cho trường roles
        return roles;
    }
}
