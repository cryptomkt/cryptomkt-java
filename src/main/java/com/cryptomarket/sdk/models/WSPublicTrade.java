package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class WSPublicTrade {
  @Json(name = "t")
  private Long timestamp;
  @Json(name = "i")
  private Long id;
  @Json(name = "p")
  private String price;
  @Json(name = "q")
  private String quantity;
  @Json(name = "s")
  private String side;

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getSide() {
    return side;
  }

  public void setSide(String side) {
    this.side = side;
  }

  @Override
  public String toString() {
    return "WSPublicTrade [timestamp=" + timestamp + ", id=" + id + ", price=" + price + ", quantity=" + quantity
        + ", side=" + side + "]";
  }

}
