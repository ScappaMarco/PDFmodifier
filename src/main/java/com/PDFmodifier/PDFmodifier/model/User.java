package com.PDFmodifier.PDFmodifier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String username;
    private String email;
    private String encodedPassword;
    private LocalDateTime accountCreateDate;

    public User(Long id, String username, String email, String encodedPassword, LocalDateTime accountCreateDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.accountCreateDate = accountCreateDate;
    }

    public User(String username, String email, String encodedPassword, LocalDateTime accountCreateDate) {
        this.username = username;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.accountCreateDate = accountCreateDate;
    }

    public User() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncodedPassword() {
        return this.encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public LocalDateTime getAccountCreateDate() {
        return this.accountCreateDate;
    }

    public void setAccountCreateDate(LocalDateTime accountCreateDate) {
        this.accountCreateDate = accountCreateDate;
    }
}