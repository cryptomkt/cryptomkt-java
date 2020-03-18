package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountResponse extends Response {

    @JsonProperty("data")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
