package com.cryptomarket.params;

/** ticker speed */
public enum TickerSpeed {
  /** 1 seconds */
  _1_SECONDS("1s"),
  /** 3 seconds */
  _3_SECONDS("3s");

  private final String label;

  private TickerSpeed(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
