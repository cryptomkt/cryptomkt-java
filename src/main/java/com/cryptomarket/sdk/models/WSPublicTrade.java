package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class WSPublicTrade {
  @Json(name = "t")
  Long timestamp;
  @Json(name = "i")
  Long ID;
  @Json(name = "p")
  String price;
  @Json(name = "q")
  String quantity;
  @Json(name = "s")
  String side;
  @Override
  public String toString() {
    return "WSPublicTrade [ID=" + ID + ", price=" + price + ", quantity=" + quantity + ", side=" + side + ", timestamp="
        + timestamp + "]";
  }
}
