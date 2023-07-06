package com.cryptomarket.params;

/** Order book speed */
public enum OBSpeed {
  /** Speed of 100 miliseconds */
  _100_MILISECONDS("100ms"),
  /** Speed of 500 miliseconds */
  _500_MILISECONDS("500ms"),
  /** Speed of 1000 miliseconds */
  _1000_MILISECONDS("1000ms");

  private final String label;

  private OBSpeed(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
