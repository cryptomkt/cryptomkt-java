package com.cryptomarket.sdk;

public interface Authenticator {
    public String getCredential(String method, String body, String url);
}
