package com.cryptomarket.sdk.exceptions;

/**
 * An Exception for argument errors while constructing params for requests
 */
public class CryptomarketArgumentException extends CryptomarketSDKException {
  /**
   * A new exception
   *
   * @param errorMessage A message
   */
  public CryptomarketArgumentException(String errorMessage) {
    super(errorMessage);
  }
}
