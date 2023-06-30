package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.Side;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class SideAdapter {
  @FromJson
  Side fromJson(String str) {
    return Side.valueOf(str.toUpperCase());
  }

  @ToJson
  String toJson(Side side) {
    return side.toString();
  }
}
