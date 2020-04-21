package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SocAuthResponse extends Response {

    @JsonProperty("data")
    private SocAuth socAuth;

    public SocAuth getSocAuth() {
        return socAuth;
    }

    public void setSocAuth(SocAuth socAuth) { this.socAuth = socAuth; }

}
