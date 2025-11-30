package com.PDFmodifier.PDFmodifier.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    private String username;
    private String email;

    //The user is constructed with a clear password field; it is going to be hashed when the details of the user are going to be saved in the DB
    private String password;
    private LocalDate accountCreateDate;

    public User(Long id, String firstName, String lastName, LocalDate birthDate, String username, String email, String password, LocalDate accountCreateDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountCreateDate = accountCreateDate;
    }

    public User(String firstName, String lastName, LocalDate birthDate, String username, String email, String password, LocalDate accountCreateDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountCreateDate = accountCreateDate;
    }

//    public User(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }

    public User() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public LocalDate getAccountCreateDate() {
        return this.accountCreateDate;
    }

    public void setAccountCreateDate(LocalDate accountCreateDate) {
        this.accountCreateDate = accountCreateDate;
    }
}