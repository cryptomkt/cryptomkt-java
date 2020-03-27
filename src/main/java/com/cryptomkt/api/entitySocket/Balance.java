package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Balance implements Serializable {


    @JsonProperty("currency")
    private String currency;

    @JsonProperty("countable")
    private String countable;

    @JsonProperty("available")
    private String available;

    private Integer currency_kind;

    private String currency_name;

    private String currency_big_name;

    private String currency_prefix;

    private String currency_postfix;

    private Integer currency_decimals;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountable() {
        return countable;
    }

    public void setCountable(String countable) {
        this.countable = countable;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Integer getCurrency_kind() {
        return currency_kind;
    }

    public void setCurrency_kind(Integer currency_kind) {
        this.currency_kind = currency_kind;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getCurrency_big_name() {
        return currency_big_name;
    }

    public void setCurrency_big_name(String currency_big_name) {
        this.currency_big_name = currency_big_name;
    }

    public String getCurrency_prefix() {
        return currency_prefix;
    }

    public void setCurrency_prefix(String currency_prefix) {
        this.currency_prefix = currency_prefix;
    }

    public String getCurrency_postfix() {
        return currency_postfix;
    }

    public void setCurrency_postfix(String currency_postfix) {
        this.currency_postfix = currency_postfix;
    }

    public Integer getCurrency_decimals() {
        return currency_decimals;
    }

    public void setCurrency_decimals(Integer currency_decimals) {
        this.currency_decimals = currency_decimals;
    }

    public void setCurrencyData(Currency currency) {
        currency_kind = currency.getKind();
        currency_name = currency.getName();
        currency_big_name = currency.getBigName();
        currency_prefix = currency.getPrefix();
        currency_postfix = currency.getPostfix();
        currency_decimals = currency.getDecimals();
    }
}
