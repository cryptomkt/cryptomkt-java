package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.OrderType;
import com.cryptomarket.sdk.StrConverter;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/**
 * A moshi adapter for OrderType
 */
public class OrderTypeAdapter {
  /**
   * Converts from json to OrderType
   *
   * @param str Json representation of an order type
   * @return An OrderType
   */
  @FromJson
  OrderType fromJson(String str) {
    return OrderType.valueOf(StrConverter.fromCamelCaseToCapSnakeCase(str));
  }

  /**
   * Converts from OrderType to json
   *
   * @param type An OrderType
   * @return The json representation
   */
  @ToJson
  String toJson(OrderType type) {
    return type.toString();
  }
}
