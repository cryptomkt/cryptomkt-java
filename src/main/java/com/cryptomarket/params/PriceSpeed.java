package com.cryptomarket.params;

public enum PriceSpeed {
  _1_SECONDS("1s"),
  _3_SECONDS("3s");

  private final String label;

  private PriceSpeed(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }
}
