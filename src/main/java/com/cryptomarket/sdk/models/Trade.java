package com.cryptomarket.sdk.models;

public class Trade {
	private long id;
	private String clientOrderId;
	private String orderId;
	private String symbol;
	private String quantity;
	private String price;
	private String side;
	private String fee;
	private String timestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Trade [clientOrderId=" + clientOrderId + ", fee=" + fee + ", id=" + id + ", orderId=" + orderId
				+ ", price=" + price + ", quantity=" + quantity + ", side=" + side + ", symbol=" + symbol
				+ ", timestamp=" + timestamp + "]";
	}

}