package com.cryptomarket.sdk.models;

public class OrderbookLevel {
    private String price;
    private String size;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Entry [price=" + price + ", size=" + size + "]";
	}
}
