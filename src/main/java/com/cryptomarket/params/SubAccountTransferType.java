package com.cryptomarket.params;

/** Sub account transfer type */
public enum SubAccountTransferType {
  /** to sub account */
  TO_SUB_ACCOUNT("to_sub_account"),
  /** from sub account */
  FROM_SUB_ACCOUNT("from_sub_account");

  private final String label;

  private SubAccountTransferType(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }

}
