package com.cryptomarket.sdk.models;

public class Transaction {
	private String id;
	private String index;
	private String currency;
	private String amount;
	private String fee;
	private String address;
	private String paymentId;
	private String hash;
	private String status;
	private String type;
	private String subType;
	private String offchainId;
	private String confirmations;
	private String createdAt;
	private String updatedAt;
	private String errorCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

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

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
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

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getOffchainId() {
		return offchainId;
	}

	public void setOffchainId(String offchainId) {
		this.offchainId = offchainId;
	}

	public String getConfirmations() {
		return confirmations;
	}

	public void setConfirmations(String confirmations) {
		this.confirmations = confirmations;
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

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "Transaction [address=" + address + ", amount=" + amount + ", confirmations="
				+ confirmations + ", createdAt=" + createdAt + ", currency=" + currency + ", errorCode=" + errorCode
				+ ", fee=" + fee + ", hash=" + hash + ", id=" + id + ", index=" + index + ", offchainId=" + offchainId
				+ ", paymentId=" + paymentId + ", status=" + status + ", subType=" + subType + ", type=" + type 
				+ ", updatedAt=" + updatedAt + "]";
	}
}