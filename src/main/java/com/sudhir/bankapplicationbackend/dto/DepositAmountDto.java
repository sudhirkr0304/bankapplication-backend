package com.sudhir.bankapplicationbackend.dto;

public class DepositAmountDto {
    String username;
    String accountType;

    Double amount;

    public DepositAmountDto(String username, String accountType, Double amount) {
        this.username = username;
        this.accountType = accountType;
        this.amount = amount;
    }

    public DepositAmountDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
