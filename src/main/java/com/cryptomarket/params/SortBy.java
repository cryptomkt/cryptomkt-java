package com.cryptomarket.params;

/** Param to sort by */
public enum SortBy {
  /** sort by timestamp */
  TIMESTAMP("timestamp"),
  /** sort by id */
  ID("id"),
  /** sort by datetime */
  DATETIME("created_at");

  private final String label;

  private SortBy(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
