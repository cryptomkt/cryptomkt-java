package com.cryptomarket.sdk.exceptions;

import com.cryptomarket.sdk.models.ErrorBody;

public class CryptomarketAPIException extends CryptomarketSDKException {
    private static final long serialVersionUID = 1L;
    
    public CryptomarketAPIException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public CryptomarketAPIException(String errorMessage) {
        super(errorMessage);
    }
    
    public CryptomarketAPIException(ErrorBody errorBody) {
        super(new StringBuffer(
            String.format("(code=%d) %s.", errorBody.getCode(), errorBody.getMessage()))
                .append((errorBody.getDescription() != null) ? " " + errorBody.getDescription() : "").toString());
    }

    public CryptomarketAPIException(ErrorBody errorBody, Throwable err) {
        super(new StringBuffer(
            String.format("(code=%d) %s.", errorBody.getCode(), errorBody.getMessage()))
                .append((errorBody.getDescription() != null) ? " " + errorBody.getDescription() : "").toString(),
            err);
    }
}
