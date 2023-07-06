package com.cryptomarket.params;

/** Report type */
public enum ReportType {
  /** status */
  STATUS("status"),
  /** new */
  NEW("new"),
  /** suspended */
  SUSPENDED("suspended"),
  /** canceled */
  CANCELED("canceled"),
  /** rejected */
  REJECTED("rejected"),
  /** expired */
  EXPIRED("expired"),
  /** replaced */
  REPLACED("replaced"),
  /** trade */
  TRADE("trade");

  private final String label;

  private ReportType(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }

}
