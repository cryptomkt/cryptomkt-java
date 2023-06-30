package com.cryptomarket.sdk.rest;

public enum HttpMethod {
  GET("GET"),
  PUT("PUT"),
  POST("POST"),
  DELETE("DELETE"),
  PATCH("PATCH");

  private final String label;

  private HttpMethod(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }
}
