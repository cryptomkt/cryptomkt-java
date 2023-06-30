package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class Network {
	private String code;
	private String network;
	@Json(name = "is_ens_available")
	private Boolean ensAvailable;
	private String protocol;
	@Json(name = "default")
	private Boolean defaultNetwork;
	@Json(name = "payin_enabled")
	private Boolean payinEnabled;
	@Json(name = "payout_enabled")
	private Boolean payoutEnabled;
	@Json(name = "parecision_payout")
	private String precisionPayout;
	@Json(name = "payout_fee")
	private String payoutFee;
	@Json(name = "payout_is_payment_id")
	private Boolean payoutIsPaymentId;
	@Json(name = "payin_payment_id")
	private Boolean payinPaymentId;
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
	@Json(name = "crypto_payment_id_name")
	private String cryptoPaymentIdName;
	@Json(name = "crypto_explorer")
	private String cryptoExplorer;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public Boolean isEnsAvailable() {
		return ensAvailable;
	}

	public void setEnsAvailable(Boolean isEnsAvailable) {
		this.ensAvailable = isEnsAvailable;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Boolean isDefaultNetwork() {
		return defaultNetwork;
	}

	public void setDefaultNetwork(Boolean isDefaultNetwork) {
		this.defaultNetwork = isDefaultNetwork;
	}

	public Boolean isPayinEnabled() {
		return payinEnabled;
	}

	public void setPayinEnabled(Boolean payinEnabled) {
		this.payinEnabled = payinEnabled;
	}

	public Boolean isPayoutEnabled() {
		return payoutEnabled;
	}

	public void setPayoutEnabled(Boolean payoutEnabled) {
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

	public Boolean isPayoutIsPaymentId() {
		return payoutIsPaymentId;
	}

	public void setPayoutIsPaymentId(Boolean payoutIsPaymentId) {
		this.payoutIsPaymentId = payoutIsPaymentId;
	}

	public Boolean isPayinPaymentId() {
		return payinPaymentId;
	}

	public void setPayinPaymentId(Boolean payinPaymentId) {
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

	public String getCryptoPaymentIdName() {
		return cryptoPaymentIdName;
	}

	public void setCryptoPaymentIdName(String cryptoPaymentIdName) {
		this.cryptoPaymentIdName = cryptoPaymentIdName;
	}

	public String getCryptoExplorer() {
		return cryptoExplorer;
	}

	public void setCryptoExplorer(String cryptoExplorer) {
		this.cryptoExplorer = cryptoExplorer;
	}

	@Override
	public String toString() {
		return "Network [code=" + code + ", network=" + network + ", ensAvailable=" + ensAvailable + ", protocol="
				+ protocol + ", defaultNetwork=" + defaultNetwork + ", payinEnabled=" + payinEnabled + ", payoutEnabled="
				+ payoutEnabled + ", precisionPayout=" + precisionPayout + ", payoutFee=" + payoutFee + ", payoutIsPaymentId="
				+ payoutIsPaymentId + ", payinPaymentId=" + payinPaymentId + ", payinConfirmations=" + payinConfirmations
				+ ", addressRegex=" + addressRegex + ", paymentIdRegex=" + paymentIdRegex + ", lowProcessingTime="
				+ lowProcessingTime + ", highProcessingTime=" + highProcessingTime + ", avgProcessingTime=" + avgProcessingTime
				+ ", cryptoPaymentIdName=" + cryptoPaymentIdName + ", cryptoExplorer=" + cryptoExplorer + "]";
	}

}