package com.cryptomarket.sdk.models.adapters;

import java.util.Arrays;
import java.util.List;

import com.cryptomarket.sdk.models.OrderbookLevel;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.ToJson;

/**
 * A moshi adapter for OrderbookLevel
 */
public class OrderBookLevelAdapter {
  /**
   * converts from orderbook level to json
   *
   * @param orderbookLevel
   * @return a list of string, first entry is the price, second is the amount
   */
  @ToJson
  List<String> toJson(OrderbookLevel orderbookLevel) {
    return Arrays.asList(orderbookLevel.getPrice(), orderbookLevel.getAmount());
  }

  /**
   * converts from a json list to a orderbook level. First entry of the list is
   * the price, second entry of the list is the amount
   *
   * @param level Json representation of the level
   * @return An orderbook level
   */
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
