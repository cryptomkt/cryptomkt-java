package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.OrderStatus;
import com.cryptomarket.sdk.StrConverter;
import com.squareup.moshi.FromJson;

/**
 * A moshi adapter for OrderStatus
 */
public class OrderStatusAdapter {
  /**
   * converts from json to order status
   *
   * @param str Json representation to order status
   * @return An order status
   */
  @FromJson
  OrderStatus fromJson(String str) {
    return OrderStatus.valueOf(StrConverter.fromCamelCaseToCapSnakeCase(str));
  }
}
