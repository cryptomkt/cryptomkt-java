package com.cryptomarket.sdk.exceptions;

public class ParseException extends CryptomarketSDKException {

  public ParseException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

  public ParseException(String errorMessage) {
    super(errorMessage);
  }
}
