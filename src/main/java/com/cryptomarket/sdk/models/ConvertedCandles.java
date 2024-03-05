package com.cryptomarket.sdk.models;

import java.util.List;
import java.util.Map;

import com.squareup.moshi.Json;

/**
 * Candle
 */
public class ConvertedCandles {
  @Json(name = "target_currency")
  private String targetCurrency;
  private Map<String, List<Candle>> data;

  public String getTargetCurrency() {
    return targetCurrency;
  }

  public void setTargetCurrency(String targetCurrency) {
    this.targetCurrency = targetCurrency;
  }

  public Map<String, List<Candle>> getData() {
    return data;
  }

  public void setData(Map<String, List<Candle>> data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "ConvertedCandles [targetCurrency=" + targetCurrency + ", data=" + data + "]";
  }

}
