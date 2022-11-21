package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.OrderType;
import com.cryptomarket.sdk.Utils;
import com.squareup.moshi.FromJson;

public class OrderTypeAdapter {
  @FromJson
  OrderType fromJson(String str)  {
    return OrderType.valueOf(Utils.fromCamelCaseToCapSnakeCase(str));
  }
}
