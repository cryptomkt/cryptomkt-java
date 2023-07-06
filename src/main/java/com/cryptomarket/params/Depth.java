package com.cryptomarket.params;

/** Orderbook depth */
public enum Depth {
  /** 5 prices of depth */
  _5("D5"),
  /** 10 prices of depth */
  _10("D10"),
  /** 20 prices of depth */
  _20("D20");

  private final String label;

  private Depth(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }

}
