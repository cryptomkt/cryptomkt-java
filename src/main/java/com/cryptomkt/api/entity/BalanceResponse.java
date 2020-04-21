package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BalanceResponse extends Response {

    @JsonProperty("data")
    private List<Balance> balances;

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    @Override
    public String toString() {
        return "BalanceResponse{" +
                super.toString() +
                ", balances=" + balances +
                '}';
    }
}
