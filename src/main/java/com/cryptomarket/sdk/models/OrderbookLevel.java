package com.cryptomarket.sdk.models;

public class OrderbookLevel {
    private String price;
    private String amount;
    public String getPrice() {
      return price;
    }
    public void setPrice(String price) {
      this.price = price;
    }
    public String getAmount() {
      return amount;
    }
    public void setAmount(String amount) {
      this.amount = amount;
    }
    @Override
    public String toString() {
      return "OrderbookLevel [amount=" + amount + ", price=" + price + "]";
    }

}
