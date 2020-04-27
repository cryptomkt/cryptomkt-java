package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Balance implements Serializable {
    private static final long serialVersionUID = 1;

    @JsonProperty("wallet")
    private String wallet;

    @JsonProperty("available")
    private String available;

    @JsonProperty("balance")
    private String balance;

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "wallet='" + wallet + '\'' +
                ", available='" + available + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
