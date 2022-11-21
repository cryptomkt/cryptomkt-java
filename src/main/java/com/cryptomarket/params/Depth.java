package com.cryptomarket.params;

public enum Depth {
  _5("D5"),
  _10("D10"),
  _20("D20");

  private final String label;

  private Depth(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }

}
