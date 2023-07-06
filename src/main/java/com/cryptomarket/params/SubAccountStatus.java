package com.cryptomarket.params;

/** Sub account status */
public enum SubAccountStatus {
  /** new */
  NEW("new"),
  /** active */
  ACTIVE("active"),
  /** disabled */
  DISABLED("disabled");

  private final String label;

  private SubAccountStatus(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }

}
