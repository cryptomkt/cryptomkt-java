package com.cryptomarket.sdk.models.adapters;

import com.cryptomarket.params.Side;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/**
 * A moshi adapter for Side
 */
public class SideAdapter {
  /**
   * Converts from json to Side
   *
   * @param str Json representation of Side
   * @return A Side
   */
  @FromJson
  Side fromJson(String str) {
    return Side.valueOf(str.toUpperCase());
  }

  /**
   * Converts from Side to json
   *
   * @param side A Side
   * @return Json representation of Side
   */
  @ToJson
  String toJson(Side side) {
    return side.toString();
  }
}
