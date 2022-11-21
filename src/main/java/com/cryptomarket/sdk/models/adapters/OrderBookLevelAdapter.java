package com.cryptomarket.sdk.models.adapters;

import java.util.Arrays;
import java.util.List;

import com.cryptomarket.sdk.models.OrderbookLevel;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.ToJson;

public class OrderBookLevelAdapter {
  @ToJson
  List<String> toJson(OrderbookLevel orderbookLevel) {
    return Arrays.asList(orderbookLevel.getPrice(), orderbookLevel.getAmount());
  }

  @FromJson
  OrderbookLevel fromJson(List<String> level) {
    if (level.size() != 2)
      throw new JsonDataException("Invalid level format: " + level.toString());
    OrderbookLevel orderbookLevel = new OrderbookLevel();
    orderbookLevel.setPrice(level.get(0));
    orderbookLevel.setAmount(level.get(1));
    return orderbookLevel;
  }
}
