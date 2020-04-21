package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccount {

    @JsonProperty("id")
    private String id;

    @JsonProperty("bank")
    private String bank;

    @JsonProperty("description")
    private String description;

    @JsonProperty("country")
    private String country;

    @JsonProperty("number")
    private String number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id='" + id + '\'' +
                ", bank='" + bank + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
