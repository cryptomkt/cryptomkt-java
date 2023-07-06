package com.cryptomarket.sdk.models;

/**
 * Error Response from api
 */
public class ErrorResponse {
  private int status;
  private ErrorBody error;
  private String timestamp;
  private String path;
  private String requestId;
  private String message;

  /**
   * Get the http response status
   *
   * @return
   */
  public int getStatus() {
    return status;
  }

  /**
   * Gets the error body
   *
   * @return
   */
  public ErrorBody getError() {
    return error;
  }

  /**
   * Gets response the timestamp
   *
   * @return
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Gets the response path
   *
   * @return
   */
  public String getPath() {
    return path;
  }

  /**
   * Gets the request id
   *
   * @return
   */
  public String getRequestId() {
    return requestId;
  }

  /**
   * Gets the response message
   *
   * @return
   */
  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "ErrorResponse [error=" + error + ", message=" + message + ", path=" + path + ", requestId=" + requestId
        + ", status=" + status + ", timestamp=" + timestamp + "]";
  }

}
