package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.Side;
import com.squareup.moshi.FromJson;

public class SideAdapter {
  @FromJson
  Side fromJson(String str) {
    return Side.valueOf(str.toUpperCase());
  }
}
