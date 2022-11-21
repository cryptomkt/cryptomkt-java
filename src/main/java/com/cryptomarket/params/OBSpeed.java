package com.cryptomarket.params;

public enum OBSpeed {
  _100_MILISECONDS("100ms"),
  _500_MILISECONDS("500ms"),
  _1000_MILISECONDS("1000ms");

  private final String label;

  private OBSpeed(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }
}
