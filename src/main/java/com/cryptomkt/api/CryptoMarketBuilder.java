package com.cryptomkt.api;


import java.net.URL;

public class CryptoMarketBuilder {

    private String apiKey;
    private String apiSecret;
    private URL baseApiUrl;


    public CryptoMarket build(){
        return new CryptoMarketImpl(this);
    }

    public CryptoMarketBuilder withApiKey(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        return this;
    }

    public CryptoMarketBuilder withBaseApiUrl(URL baseApiUrl) {
        this.baseApiUrl = baseApiUrl;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public URL getBaseApiUrl() {
        return baseApiUrl;
    }
}
