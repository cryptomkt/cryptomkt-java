package com.cryptomarket.params;

public enum TransactionStatus {
  CREATED("CREATED"),
  PENDING("PENDING"),
  FAILED("FAILED"),
  SUCCESS("SUCCESS"),
  ROLLED_BACK("ROLLED_BACK");

  private final String label;

  private TransactionStatus(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }

}
