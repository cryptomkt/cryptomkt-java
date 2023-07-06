package com.cryptomarket.sdk.rest;

/**
 * Htthp method
 */
public enum HttpMethod {
  /** Get */
  GET("GET"),
  /** Put */
  PUT("PUT"),
  /** Post */
  POST("POST"),
  /** Delete */
  DELETE("DELETE"),
  /** Patch */
  PATCH("PATCH");

  private final String label;

  private HttpMethod(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
