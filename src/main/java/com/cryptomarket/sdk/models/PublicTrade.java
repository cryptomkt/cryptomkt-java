package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class PublicTrade {
	private long id;
	@Json(name = "qty")
	private String quantity;
	private String price;
	private String side;
	private String timestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
        return "Trade [id=" + id + ", price=" + price + ", quantity=" + quantity
                + ", side=" + side + ", timestamp=" + timestamp + "]";
	}
}