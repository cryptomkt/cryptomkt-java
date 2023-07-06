package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

/**
 * Symbol
 */
public class Symbol {
  private String type;
  @Json(name = "base_currency")
  private String baseCurrency;
  @Json(name = "quote_currency")
  private String quoteCurrency;
  private String status;
  @Json(name = "quantity_increment")
  private String quantityIncrement;
  @Json(name = "tick_size")
  private String tickSize;
  @Json(name = "take_rate")
  private String takeRate;
  @Json(name = "make_rate")
  private String makeRate;
  @Json(name = "fee_currency")
  private String feeCurrency;

  /**
   * Gets the symbol type
   *
   * @return
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the symbol type
   *
   * @param type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the symbol base currency
   *
   * @return
   */
  public String getBaseCurrency() {
    return baseCurrency;
  }

  /**
   * Sets the symbol base currency
   *
   * @param baseCurrency
   */
  public void setBaseCurrency(String baseCurrency) {
    this.baseCurrency = baseCurrency;
  }

  /**
   * Gets the symbol quote currency
   *
   * @return
   */
  public String getQuoteCurrency() {
    return quoteCurrency;
  }

  /**
   * Sets the symbol quote currency
   *
   * @param quoteCurrency
   */
  public void setQuoteCurrency(String quoteCurrency) {
    this.quoteCurrency = quoteCurrency;
  }

  /**
   * Gets the symbol status
   *
   * @return
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets the symbol status
   *
   * @param status
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets the symbol quantity increment
   *
   * @return
   */
  public String getQuantityIncrement() {
    return quantityIncrement;
  }

  /**
   * Sets the symbol quantity increment
   *
   * @param quantityIncrement
   */
  public void setQuantityIncrement(String quantityIncrement) {
    this.quantityIncrement = quantityIncrement;
  }

  /**
   * Gets the symbol tick size
   *
   * @return
   */
  public String getTickSize() {
    return tickSize;
  }

  /**
   * Sets the symbol tick size
   *
   * @param tickSize
   */
  public void setTickSize(String tickSize) {
    this.tickSize = tickSize;
  }

  /**
   * Gets the symbol take rate
   *
   * @return
   */
  public String getTakeRate() {
    return takeRate;
  }

  /**
   * Sets the symbol take rate
   *
   * @param takeRate
   */
  public void setTakeRate(String takeRate) {
    this.takeRate = takeRate;
  }

  /**
   * Gets the symbol make rate
   *
   * @return
   */
  public String getMakeRate() {
    return makeRate;
  }

  /**
   * Sets the symbol make rate
   *
   * @param makeRate
   */
  public void setMakeRate(String makeRate) {
    this.makeRate = makeRate;
  }

  /**
   * Gets the currency used for fee of the symbol
   *
   * @return
   */
  public String getFeeCurrency() {
    return feeCurrency;
  }

  /**
   * Sets the fee currency
   *
   * @param feeCurrency
   */
  public void setFeeCurrency(String feeCurrency) {
    this.feeCurrency = feeCurrency;
  }

  @Override
  public String toString() {
    return "Symbol [type=" + type + ", baseCurrency=" + baseCurrency + ", quoteCurrency=" + quoteCurrency + ", status="
        + status + ", quantityIncrement=" + quantityIncrement + ", tickSize=" + tickSize + ", takeRate=" + takeRate
        + ", makeRate=" + makeRate + ", feeCurrency=" + feeCurrency + "]";
  }
}
