package com.cryptomarket.sdk.models;

import java.util.List;

import com.squareup.moshi.Json;

public class ApiKey {
  /** First 6 symbols of the public key */
  @Json(name = "api_key_first_six")
  private String firstSix;
  /** Name of the key */
  @Json(name = "key_name")
  private String name;
  /** "Orderbook, History, Trading balance" API key access right */
  @Json(name = "exchange_read")
  private Boolean exchangeRead;
  /** "Place/cancel orders" API key access right */
  @Json(name = "exchange_trade")
  private Boolean exchangeTrade;
  /** "Payment information" API key access right */
  @Json(name = "bank_read")
  private Boolean bankRead;
  /** "Withdraw cryptocurrencies" API key access right */
  @Json(name = "bank_withdraw")
  private Boolean bankWithdraw;
  /** A list of whitelisted IPs */
  @Json(name = "allowed_ips")
  private List<String> allowedIps;
  /** Flag indicating the API key is active */
  @Json(name = "is_active")
  private Boolean isActive;
  /** The date and time the key was created */
  @Json(name = "created_at")
  private String createdAt;
  /** The date and time the key was updated */
  @Json(name = "updated_at")
  private String updatedAt;

  public String getFirstSix() {
    return firstSix;
  }

  public void setFirstSix(String firstSix) {
    this.firstSix = firstSix;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getExchangeRead() {
    return exchangeRead;
  }

  public void setExchangeRead(Boolean exchangeRead) {
    this.exchangeRead = exchangeRead;
  }

  public Boolean getExchangeTrade() {
    return exchangeTrade;
  }

  public void setExchangeTrade(Boolean exchangeTrade) {
    this.exchangeTrade = exchangeTrade;
  }

  public Boolean getBankRead() {
    return bankRead;
  }

  public void setBankRead(Boolean bankRead) {
    this.bankRead = bankRead;
  }

  public Boolean getBankWithdraw() {
    return bankWithdraw;
  }

  public void setBankWithdraw(Boolean bankWithdraw) {
    this.bankWithdraw = bankWithdraw;
  }

  public List<String> getAllowedIps() {
    return allowedIps;
  }

  public void setAllowedIps(List<String> allowedIps) {
    this.allowedIps = allowedIps;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "ApiKey [firstSix=" + firstSix + ", name=" + name + ", exchangeRead=" + exchangeRead + ", exchangeTrade="
        + exchangeTrade + ", bankRead=" + bankRead + ", bankWithdraw=" + bankWithdraw + ", allowedIps=" + allowedIps
        + ", isActive=" + isActive + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
  }
}
