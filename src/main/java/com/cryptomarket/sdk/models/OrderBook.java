package com.cryptomarket.sdk.models;

import java.util.List;

public class OrderBook {
	private List<OrderbookLevel> ask;
	private List<OrderbookLevel> bid;
	private String batchingTime;
	private String symbol;
	private String timestamp;
	private String askAveragePrice;
	private String bidAveragePrice;
	private Integer sequence;

	public List<OrderbookLevel> getAsk() {
		return ask;
	}

	public void setAsk(List<OrderbookLevel> ask) {
		this.ask = ask;
	}

	public List<OrderbookLevel> getBid() {
		return bid;
	}

	public void setBid(List<OrderbookLevel> bid) {
		this.bid = bid;
	}

	public String getBatchingTime() {
		return batchingTime;
	}

	public void setBatchingTime(String batchingTime) {
		this.batchingTime = batchingTime;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAskAveragePrice() {
		return askAveragePrice;
	}

	public void setAskAveragePrice(String askAveragePrice) {
		this.askAveragePrice = askAveragePrice;
	}

	public String getBidAveragePrice() {
		return bidAveragePrice;
	}

	public void setBidAveragePrice(String bidAveragePrice) {
		this.bidAveragePrice = bidAveragePrice;
	}

	public Integer getSequence() {
		return sequence;
	}

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