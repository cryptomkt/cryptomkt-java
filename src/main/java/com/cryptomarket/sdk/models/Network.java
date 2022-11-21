package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class Network {
	private String network;
	private String protocol;

	@Json(name = "default")
	private boolean default_network;
	@Json(name = "payin_enabled")
	private boolean payinEnabled;
	@Json(name = "payout_enabled")
	private boolean payoutEnabled;
	@Json(name = "parecision_payout")
	private String precisionPayout;
	@Json(name = "payout_fee")
	private String payoutFee;
	@Json(name = "payout_is_payment_id")
	private boolean payoutIsPaymentId;
	@Json(name = "payin_payment_id")
	private boolean payinPaymentId;
	@Json(name = "payin_confirmations")
	private int payinConfirmations;
	@Json(name = "address_regex")
	private String addressRegex;
	@Json(name = "payment_id_regex")
	private String paymentIdRegex;
	@Json(name = "low_processing_time")
	private String lowProcessingTime;
	@Json(name = "high_processing_time")
	private String highProcessingTime;
	@Json(name = "avg_processing_time")
	private String avgProcessingTime;
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public boolean isDefault_network() {
		return default_network;
	}
	public void setDefault_network(boolean default_network) {
		this.default_network = default_network;
	}
	public boolean isPayinEnabled() {
		return payinEnabled;
	}
	public void setPayinEnabled(boolean payinEnabled) {
		this.payinEnabled = payinEnabled;
	}
	public boolean isPayoutEnabled() {
		return payoutEnabled;
	}
	public void setPayoutEnabled(boolean payoutEnabled) {
		this.payoutEnabled = payoutEnabled;
	}
	public String getPrecisionPayout() {
		return precisionPayout;
	}
	public void setPrecisionPayout(String precisionPayout) {
		this.precisionPayout = precisionPayout;
	}
	public String getPayoutFee() {
		return payoutFee;
	}
	public void setPayoutFee(String payoutFee) {
		this.payoutFee = payoutFee;
	}
	public boolean isPayoutIsPaymentId() {
		return payoutIsPaymentId;
	}
	public void setPayoutIsPaymentId(boolean payoutIsPaymentId) {
		this.payoutIsPaymentId = payoutIsPaymentId;
	}
	public boolean isPayinPaymentId() {
		return payinPaymentId;
	}
	public void setPayinPaymentId(boolean payinPaymentId) {
		this.payinPaymentId = payinPaymentId;
	}
	public int getPayinConfirmations() {
		return payinConfirmations;
	}
	public void setPayinConfirmations(int payinConfirmations) {
		this.payinConfirmations = payinConfirmations;
	}
	public String getAddressRegex() {
		return addressRegex;
	}
	public void setAddressRegex(String addressRegex) {
		this.addressRegex = addressRegex;
	}
	public String getPaymentIdRegex() {
		return paymentIdRegex;
	}
	public void setPaymentIdRegex(String paymentIdRegex) {
		this.paymentIdRegex = paymentIdRegex;
	}
	public String getLowProcessingTime() {
		return lowProcessingTime;
	}
	public void setLowProcessingTime(String lowProcessingTime) {
		this.lowProcessingTime = lowProcessingTime;
	}
	public String getHighProcessingTime() {
		return highProcessingTime;
	}
	public void setHighProcessingTime(String highProcessingTime) {
		this.highProcessingTime = highProcessingTime;
	}
	public String getAvgProcessingTime() {
		return avgProcessingTime;
	}
	public void setAvgProcessingTime(String avgProcessingTime) {
		this.avgProcessingTime = avgProcessingTime;
	}
	@Override
	public String toString() {
		return "Network [addressRegex=" + addressRegex + ", avgProcessingTime=" + avgProcessingTime
				+ ", default_network=" + default_network + ", highProcessingTime=" + highProcessingTime
				+ ", lowProcessingTime=" + lowProcessingTime + ", network=" + network + ", payinConfirmations="
				+ payinConfirmations + ", payinEnabled=" + payinEnabled + ", payinPaymentId=" + payinPaymentId
				+ ", paymentIdRegex=" + paymentIdRegex + ", payoutEnabled=" + payoutEnabled + ", payoutFee=" + payoutFee
				+ ", payoutIsPaymentId=" + payoutIsPaymentId + ", precisionPayout=" + precisionPayout + ", protocol="
				+ protocol + "]";
	}
}