package com.cryptomarket.sdk.exceptions;

public class CryptomarketSDKException extends Exception {
    private static final long serialVersionUID = 1L;

    public CryptomarketSDKException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public CryptomarketSDKException(String errorMessage) {
        super(errorMessage);
    }   

}
