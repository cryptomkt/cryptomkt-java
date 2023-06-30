package com.cryptomarket.params;

public enum TransactionStatus {
  CREATED("CREATED"),
  PENDING("PENDING"),
  FAILED("FAILED"),
  ROLLED_BACK("ROLLED_BACK"),
  SUCCESS("SUCCESS");

  private final String label;

  private TransactionStatus(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }

}
