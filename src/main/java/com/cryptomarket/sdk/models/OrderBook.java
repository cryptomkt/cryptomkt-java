package com.cryptomarket.sdk.models;

import java.util.List;

/**
 * Order book
 */
public class OrderBook {
  private List<OrderbookLevel> ask;
  private List<OrderbookLevel> bid;
  private String batchingTime;
  private String symbol;
  private String timestamp;
  private String askAveragePrice;
  private String bidAveragePrice;
  private Integer sequence;

  /**
   * Gets the list of asks prices and amounts, as orderbook levels
   *
   * @return A list of OrderbookLevels
   */
  public List<OrderbookLevel> getAsk() {
    return ask;
  }

  /**
   * Sets the ask orderbook levels
   *
   * @param ask
   */
  public void setAsk(List<OrderbookLevel> ask) {
    this.ask = ask;
  }

  /**
   * Gets the list of bids prices and amounts, as orderbook levels
   *
   * @return A list of OrderbookLevels
   */
  public List<OrderbookLevel> getBid() {
    return bid;
  }

  /**
   * Sets the bids orderbook levels
   *
   * @param bid
   */
  public void setBid(List<OrderbookLevel> bid) {
    this.bid = bid;
  }

  /**
   * Gets the batching time of the orderbook
   *
   * @return
   */
  public String getBatchingTime() {
    return batchingTime;
  }

  /**
   * Sets the batching time of the orderbook
   *
   * @param batchingTime
   */
  public void setBatchingTime(String batchingTime) {
    this.batchingTime = batchingTime;
  }

  /**
   * Gets the symbol of the orderbook
   *
   * @return
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Sets the symbol of the orderbook
   *
   * @param symbol
   */
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  /**
   * Gets the orderbook timestamp
   *
   * @return
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the orderbook timestamp
   *
   * @param timestamp
   */
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets the ask average price
   *
   * @return
   */
  public String getAskAveragePrice() {
    return askAveragePrice;
  }

  /**
   * Sets the ask average price
   *
   * @param askAveragePrice
   */
  public void setAskAveragePrice(String askAveragePrice) {
    this.askAveragePrice = askAveragePrice;
  }

  /**
   * Gets the bid average price
   *
   * @return
   */
  public String getBidAveragePrice() {
    return bidAveragePrice;
  }

  /**
   * Sets the bid average price
   *
   * @param bidAveragePrice
   */
  public void setBidAveragePrice(String bidAveragePrice) {
    this.bidAveragePrice = bidAveragePrice;
  }

  /**
   * Gets the orderbook sequence value
   *
   * @return
   */
  public Integer getSequence() {
    return sequence;
  }

  /**
   * Gets the orderbook sequence value
   *
   * @param sequence
   */
  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

  @Override
  public String toString() {
    return "OrderBook [ask=" + ask + ", askAveragePrice=" + askAveragePrice + ", batchingTime=" + batchingTime
        + ", bid=" + bid + ", bidAveragePrice=" + bidAveragePrice + ", sequence=" + sequence + ", symbol="
        + symbol + ", timestamp=" + timestamp + "]";
  }

}
