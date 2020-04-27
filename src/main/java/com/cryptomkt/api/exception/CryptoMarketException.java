package com.cryptomkt.api.exception;

public class CryptoMarketException extends Exception {
    private static final long serialVersionUID = 1;

    public CryptoMarketException(){
        super();
    }

    public CryptoMarketException(String message){
        super(message);
    }

}
