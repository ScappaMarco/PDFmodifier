package com.PDFmodifier.PDFmodifier.model;

import java.util.Date;

public class User {
    private Long id;
    private String username;
    private String email;
    private String encodedPassword;
    private Date accountCreateDate;

    public User(Long id, String username, String email, String encodedPassword, Date accountCreateDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.accountCreateDate = accountCreateDate;
    }

    public User(String username, String email, String encodedPassword, Date accountCreateDate) {
        this.username = username;
        this.email = email;
        this.encodedPassword = encodedPassword;
        this.accountCreateDate = accountCreateDate;
    }

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

    public Date getAccountCreateDate() {
        return this.accountCreateDate;
    }

    public void setAccountCreateDate(Date accountCreateDate) {
        this.accountCreateDate = accountCreateDate;
    }
}