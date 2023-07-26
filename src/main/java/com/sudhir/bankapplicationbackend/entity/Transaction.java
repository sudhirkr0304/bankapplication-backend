package com.sudhir.bankapplicationbackend.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String username;

    String transactionType; // deposit,withdraw,transfer,request

    String accountType;

    double amount;

    public Transaction() {

    }

    public Transaction(String username, String transactionType, String accountType, double amount) {
        this.username = username;
        this.transactionType = transactionType;
        this.accountType = accountType;
        this.amount = amount;
    }

    public Transaction(Long id, String username, String transactionType, String accountType, double amount) {
        this.id = id;
        this.username = username;
        this.transactionType = transactionType;
        this.accountType = accountType;
        this.amount = amount;
    }

    public Long getId() {
        return id;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
