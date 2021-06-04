package com.cryptomarket.sdk.models;

public class Address {
	private String address;
	private String paymentId;
	private String publicKey;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public String toString() {
		return "Address [address=" + address + ", paymentId=" + paymentId + ", publicKey=" + publicKey + "]";
	}
}