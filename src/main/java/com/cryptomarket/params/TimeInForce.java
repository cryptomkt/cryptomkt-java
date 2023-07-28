package com.cryptomarket.params;

import com.squareup.moshi.Json;

/** Time in force */
public enum TimeInForce {
  /** good till canceled */
  GTC,
  /** inmediate or cancel */
  IOC,
  /** fill or kill */
  FOK,
  /** day */
  @Json(name="Day")
  DAY,
  /** good till date */
  GTD,
}
