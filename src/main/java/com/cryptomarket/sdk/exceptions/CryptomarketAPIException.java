package com.cryptomarket.sdk.exceptions;

import com.cryptomarket.sdk.models.ErrorBody;

/**
 * Exception originationg from the api
 */
public class CryptomarketAPIException extends CryptomarketSDKException {
  private static final long serialVersionUID = 1L;

  /**
   * A new exception
   *
   * @param errorMessage A message
   * @param err          the original Exception
   */
  public CryptomarketAPIException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

  /**
   * A new exception
   *
   * @param errorMessage A message
   */
  public CryptomarketAPIException(String errorMessage) {
    super(errorMessage);
  }

  /**
   * A new exception
   *
   * @param errorBody An error body with the recieved error from the api
   */
  public CryptomarketAPIException(ErrorBody errorBody) {
    super(new StringBuffer(
        String.format("(code=%d) %s.", errorBody.getCode(), errorBody.getMessage()))
        .append((errorBody.getDescription() != null) ? " " + errorBody.getDescription() : "").toString());
  }

  /**
   *
   * @param errorBody An error body with the recieved error from the api
   * @param err       The original error
   */
  public CryptomarketAPIException(ErrorBody errorBody, Throwable err) {
    super(new StringBuffer(
        String.format("(code=%d) %s.", errorBody.getCode(), errorBody.getMessage()))
        .append((errorBody.getDescription() != null) ? " " + errorBody.getDescription() : "").toString(),
        err);
  }
}
