package com.sudhir.bankapplicationbackend.entity;

import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    String username;

    String password;

    String name;


    double primaryAccountBalance;

    double savingAccountBalance;

    public Long getId() {
        return id;
    }

    public User() {

    }
    public User(String username, String password, String name, double primaryAccountBalance, double savingAccountBalance) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.primaryAccountBalance = primaryAccountBalance;
        this.savingAccountBalance = savingAccountBalance;
    }

    public User(Long id, String username, String password, String name, double primaryAccountBalance, double savingAccountBalance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.primaryAccountBalance = primaryAccountBalance;
        this.savingAccountBalance = savingAccountBalance;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrimaryAccountBalance() {
        return primaryAccountBalance;
    }

    public void setPrimaryAccountBalance(double primaryAccountBalance) {
        this.primaryAccountBalance = primaryAccountBalance;
    }

    public double getSavingAccountBalance() {
        return savingAccountBalance;
    }

    public void setSavingAccountBalance(double savingAccountBalance) {
        this.savingAccountBalance = savingAccountBalance;
    }
}
