package com.cryptomarket.sdk.exceptions;

/**
 * The base exception of com.cryptomarket.sdk
 */
public class CryptomarketSDKException extends Exception {
  private static final long serialVersionUID = 1L;

  /**
   * A new exception
   *
   * @param errorMessage A message
   * @param err          The original exception
   */
  public CryptomarketSDKException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

  /**
   * A new exception
   *
   * @param errorMessage A message
   */
  public CryptomarketSDKException(String errorMessage) {
    super(errorMessage);
  }

}
