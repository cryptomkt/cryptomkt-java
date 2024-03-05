package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

/**
 * Candle
 */
public class ConvertedCandlesBySymbol {
  @Json(name = "target_currency")
  private String targetCurrency;
  private List<Candle> data;

  public String getTargetCurrency() {
    return targetCurrency;
  }

  public void setTargetCurrency(String targetCurrency) {
    this.targetCurrency = targetCurrency;
  }

  public List<Candle> getData() {
    return data;
  }

  public void setData(List<Candle> data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "ConvertedCandles [targetCurrency=" + targetCurrency + ", data=" + data + "]";
  }

}
