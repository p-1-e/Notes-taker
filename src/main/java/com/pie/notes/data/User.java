package com.pie.notes.data;

import jakarta.persistence.*;


@Table(name = "app-user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;


    public User(String name, String password) {
        this.userName = name;
        this.password = password;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
