package com.cryptomarket.sdk.models;

public class Order {
	private String id;
	private String clientOrderId;
	private String symbol;
	private String side;
	private String status;
	private String type;
	private String timeInForce;
	private String price;
	private String quantity;
	private String avgPrice;
	private String cumQuantity;
	private String createdAt;
	private String updatedAt;
	private boolean postOnly;
	private String stopPrice;
	private String expireTime;
	private String positionId;
	private String tradesReport;
	private String originalRequestClientOrderId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimeInForce() {
		return timeInForce;
	}

	public void setTimeInForce(String timeInForce) {
		this.timeInForce = timeInForce;
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

	public String getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getCumQuantity() {
		return cumQuantity;
	}

	public void setCumQuantity(String cumQuantity) {
		this.cumQuantity = cumQuantity;
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

	public boolean isPostOnly() {
		return postOnly;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public String getStopPrice() {
		return stopPrice;
	}

	public void setStopPrice(String stopPrice) {
		this.stopPrice = stopPrice;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getTradesReport() {
		return tradesReport;
	}

	public void setTradesReport(String tradesReport) {
		this.tradesReport = tradesReport;
	}

	public String getOriginalRequestClientOrderId() {
		return originalRequestClientOrderId;
	}

	public void setOriginalRequestClientOrderId(String originalRequestClientOrderId) {
		this.originalRequestClientOrderId = originalRequestClientOrderId;
	}

	@Override
	public String toString() {
		return "Order [avgPrice=" + avgPrice + ", clientOrderId=" + clientOrderId + ", createdAt=" + createdAt
				+ ", cumQuantity=" + cumQuantity + ", expireTime=" + expireTime + ", id=" + id
				+ ", originalRequestClientOrderId=" + originalRequestClientOrderId + ", positionId=" + positionId
				+ ", postOnly=" + postOnly + ", price=" + price + ", quantity=" + quantity + ", side=" + side
				+ ", status=" + status + ", stopPrice=" + stopPrice + ", symbol=" + symbol + ", timeInForce="
				+ timeInForce + ", tradesReport=" + tradesReport + ", type=" + type + ", updatedAt=" + updatedAt + "]";
	}

}