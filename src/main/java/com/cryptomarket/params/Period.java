package com.cryptomarket.params;

/** Period */
public enum Period {
  /** one minute */
  _1_MINUTES("M1"),
  /** 3 minutes */
  _3_MINUTES("M3"),
  /** 5 minutes */
  _5_MINUTES("M5"),
  /** 15 minutes */
  _15_MINUTES("M15"),
  /** 30 minutes */
  _30_MINUTES("M30"),
  /** 1 hour */
  _1_HOURS("1H"),
  /** 4 hours */
  _4_HOURS("4H"),
  /** 1 day */
  _1_DAYS("1D"),
  /** 7 days */
  _7_DAYS("7D"),
  /** 1 month */
  _1_MONTHS("1M");

  private final String label;

  private Period(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
