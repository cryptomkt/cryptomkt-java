package com.cryptomkt.api;

import com.cryptomkt.api.entity.Response;
import com.cryptomkt.api.exception.CryptoMarketException;

import java.io.IOException;
import java.util.Map;

public interface HTTPClient {
    <T extends Response> T get(String endpoint, Map<String, String> payload, Class<T> responseClass)
            throws IOException, CryptoMarketException;

    <T extends Response> T post(String endpoint, Map<String, String> payload, Class<T> responseClass)
            throws IOException, CryptoMarketException;
}
