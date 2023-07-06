package com.cryptomarket.params;

/** Type of order */
public enum OrderType {
  /** Order type limit */
  LIMIT("limit"),
  /** Order type market */
  MARKET("market"),
  /** Order type stop limit */
  STOP_LIMIT("stopLimit"),
  /** Order type stop market */
  STOP_MARKET("stopMarket"),
  /** Order type take profit limit */
  TAKE_PROFIT_LIMIT("takeProfitLimit"),
  /** Order type take profit market */
  TAKE_PROFIT_MARKET("takeProfitMarket");

  private final String label;

  private OrderType(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
