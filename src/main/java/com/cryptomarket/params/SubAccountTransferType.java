package com.cryptomarket.params;

public enum SubAccountTransferType {
  TO_SUB_ACCOUNT("to_sub_account"),
  FROM_SUB_ACCOUNT("from_sub_account");

  private final String label;

  private SubAccountTransferType(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }

}
