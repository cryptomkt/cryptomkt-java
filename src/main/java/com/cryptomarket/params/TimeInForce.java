package com.cryptomarket.params;

/** Time in force */
public enum TimeInForce {
  /** good till canceled */
  GTC,
  /** inmediate or cancel */
  IOC,
  /** fill or kill */
  FOK,
  /** day */
  DAY {
    @Override
    public String toString() {
      return "Day";
    }
  },
  /** good till date */
  GTD,
}
