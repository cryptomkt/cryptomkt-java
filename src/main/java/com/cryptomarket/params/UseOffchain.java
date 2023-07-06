package com.cryptomarket.params;

/** Use offchain */
public enum UseOffchain {
  /** never */
  NEVER("never"),
  /** optionally */
  OPTIONALLY("optionally"),
  /** reuquired */
  REQUIRED("required");

  private final String label;

  private UseOffchain(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }

}
