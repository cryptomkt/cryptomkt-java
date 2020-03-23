package com.cryptomkt.api.entitySocket;

import com.cryptomkt.api.entity.Balance;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OnBalance {
    private List<Currency> balance;

    public List<Currency> getBalance() {
        return balance;
    }

    public void setBalance(List<Currency> balance) {
        this.balance = balance;
    }
}
