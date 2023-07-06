package com.cryptomarket.sdk.models;

/**
 * Body of an error response
 */
public class ErrorBody {
  private int code;
  private String message;
  private String description;

  /**
   * Gets the error code
   *
   * @return
   */
  public int getCode() {
    return code;
  }

  /**
   * Sets the error code
   *
   * @param code
   */
  public void setCode(int code) {
    this.code = code;
  }

  /**
   * Gets the error message
   *
   * @return
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the error message
   *
   * @param message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets the error description
   *
   * @return
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the error description
   *
   * @param description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "ErrorBody [code=" + code + ", description=" + description + ", message=" + message + "]";
  }
}
