package com.cryptomarket.params;

/** Price speed */
public enum PriceSpeed {
  /** 1 second */
  _1_SECONDS("1s"),
  /** 3 seconds */
  _3_SECONDS("3s");

  private final String label;

  private PriceSpeed(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
