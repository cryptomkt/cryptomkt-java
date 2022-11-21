package com.cryptomarket.params;

public enum TickerSpeed {
  _1_SECONDS("1s"),
  _3_SECONDS("3s");

  private final String label;

  private TickerSpeed(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }
}
