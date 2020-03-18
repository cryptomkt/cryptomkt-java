package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Transaction implements Serializable{
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private int type;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("fee_percent")
    private String feePercent;

    @JsonProperty("fee_amount")
    private String feeAmount;

    @JsonProperty("balance")
    private String balance;

    @JsonProperty("date")
    private String date;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("address")
    private String address;

    @JsonProperty("memo")
    private String memo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() { return type; }

    public String getTypeString() {
        if (type == -1) {
            return "Withdrawal";
        } else {
            return "Deposit";
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFeePercent() {
        return feePercent;
    }

    public void setFeePercent(String feePercent) {
        this.feePercent = feePercent;
    }

    public String getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
