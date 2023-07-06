package com.cryptomarket.params;

/** Status of an order */
public enum OrderStatus {

  /** New order */
  NEW("new"),
  /** Suspended order */
  SUSPENDED("suspended"),
  /** Partially filled order */
  PARTIALLY_FILLED("partiallyFilled"),
  /** Fully filled order */
  FILLED("filled"),
  /** Canceled order */
  CANCELED("canceled"),
  /** Expired order */
  EXPIRED("expired");

  private final String label;

  private OrderStatus(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
