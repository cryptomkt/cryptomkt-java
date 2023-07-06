package com.cryptomarket.params;

/** transaction status */
public enum TransactionStatus {
  /** created */
  CREATED("CREATED"),
  /** pending */
  PENDING("PENDING"),
  /** failed */
  FAILED("FAILED"),
  /** rolled back */
  ROLLED_BACK("ROLLED_BACK"),
  /** success */
  SUCCESS("SUCCESS");

  private final String label;

  private TransactionStatus(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }

}
