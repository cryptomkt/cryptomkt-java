package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Personal commission rate by symbol
 */
public class Commission {
  private String symbol;
  @Json(name = "take_rate")
  private String takeRate;
  @Json(name = "make_rate")
  private String makeRate;

  /**
   * Gets the symbol of the commission
   *
   * @return
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Sets symbol
   *
   * @param symbol
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  /**
   * Gets take rate
   *
   * @return
   */
  public String getTakeRate() {
    return takeRate;
  }

  /**
   * Sets the take rate
   *
   * @param takeRate
   */
  public void setTakeRate(String takeRate) {
    this.takeRate = takeRate;
  }

  /**
   * Gets the make rate
   *
   * @return
   */
  public String getMakeRate() {
    return makeRate;
  }

  /**
   * Sets the make rate
   *
   * @param makeRate
   */
  public void setMakeRate(String makeRate) {
    this.makeRate = makeRate;
  }

  @Override
  public String toString() {
    return "Commission [makeRate=" + makeRate + ", symbol=" + symbol + ", takeRate=" + takeRate + "]";
  }

}
