package com.cryptomarket.sdk.rest;

public interface Authenticator {
    public String getCredential(String method, String body, String url);
}
