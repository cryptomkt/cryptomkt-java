package com.cryptomarket.params;

public enum SubAccountStatus {
  NEW("new"),
  ACTIVE("active"),
  DISABLED("disabled");

  private final String label;

  private SubAccountStatus(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }

}
