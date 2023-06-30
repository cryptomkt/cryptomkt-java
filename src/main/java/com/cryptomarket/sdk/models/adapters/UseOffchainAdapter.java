package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.UseOffchain;
import com.squareup.moshi.ToJson;

public class UseOffchainAdapter {
  @ToJson
  String toJson(UseOffchain useOffchain) {
    return useOffchain.toString();
  }
}
