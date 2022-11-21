package com.cryptomarket.params;

public enum ReportType {
  STATUS("status"),
  NEW("new"),
  SUSPENDED("suspended"),
  CANCELED("canceled"),
  REJECTED("rejected"),
  EXPIRED("expired"),
  REPLACED("replaced"),
  TRADE("trade");

  private final String label;

  private ReportType(String label) {
    this.label = label;
  }

  public String toString() {
    return label;
  }

}
