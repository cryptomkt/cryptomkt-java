package com.cryptomarket.params;

/** Param to sort by */
public enum OrderBy {
  /** sort by creation date */
  CREATED_AT("created_at"),
  /** sort by id */
  ID("id"),
  /** sort by update date */
  UPDATE_AT("updated_at");

  private final String label;

  private OrderBy(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
