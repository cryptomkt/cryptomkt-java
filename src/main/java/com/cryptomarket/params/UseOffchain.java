package com.cryptomarket.params;

public enum UseOffchain {
  NEVER("never"),
  OPTIONALLY("optionally"),
  REQUIRED("required");

  private final String label;

  private UseOffchain(String label) {
      this.label = label;
  }

  public String toString() {
      return label;
  }

}
