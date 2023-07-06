package com.cryptomarket.sdk.exceptions;

/**
 * An exception thrown when conversion to java classes fails
 */
public class ParseException extends CryptomarketSDKException {

  /**
   * A new exception
   *
   * @param errorMessage A message
   * @param err          the original exception
   */
  public ParseException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

  /**
   * A new exception
   *
   * @param errorMessage A message
   */
  public ParseException(String errorMessage) {
    super(errorMessage);
  }
}
