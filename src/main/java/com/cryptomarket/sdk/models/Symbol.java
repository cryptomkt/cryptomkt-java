package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class Symbol {
	private String type;
	@Json(name="base_currency")
	private String baseCurrency;
	@Json(name="quote_currency")
	private String quoteCurrency;
	private String status;
	@Json(name="quantity_increment")
	private String quantityIncrement;
	@Json(name="tick_size")
	private String tickSize;
	@Json(name="take_rate")
	private String takeRate;
	@Json(name="make_rate")
	private String makeRate;
	@Json(name="fee_currency")
	private String FeeCurrency;
	@Json(name="margin_trading")
	private boolean marginTrading;
	@Json(name="max_initial_leverage")
	private String maxInitialLeverage;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getQuoteCurrency() {
		return quoteCurrency;
	}
	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getQuantityIncrement() {
		return quantityIncrement;
	}
	public void setQuantityIncrement(String quantityIncrement) {
		this.quantityIncrement = quantityIncrement;
	}
	public String getTickSize() {
		return tickSize;
	}
	public void setTickSize(String tickSize) {
		this.tickSize = tickSize;
	}
	public String getTakeRate() {
		return takeRate;
	}
	public void setTakeRate(String takeRate) {
		this.takeRate = takeRate;
	}
	public String getMakeRate() {
		return makeRate;
	}
	public void setMakeRate(String makeRate) {
		this.makeRate = makeRate;
	}
	public String getFeeCurrency() {
		return FeeCurrency;
	}
	public void setFeeCurrency(String feeCurrency) {
		FeeCurrency = feeCurrency;
	}
	public boolean getMarginTrading() {
		return marginTrading;
	}
	public void setMarginTrading(boolean marginTrading) {
		this.marginTrading = marginTrading;
	}
	public String getMaxInitialLeverage() {
		return maxInitialLeverage;
	}
	public void setMaxInitialLeverage(String maxInitialLeverage) {
		this.maxInitialLeverage = maxInitialLeverage;
	}
	
	@Override
	public String toString() {
		return "Symbol [FeeCurrency=" + FeeCurrency + ", baseCurrency=" + baseCurrency + ", makeRate=" + makeRate
				+ ", marginTrading=" + marginTrading + ", maxInitialLeverage=" + maxInitialLeverage
				+ ", quantityIncrement=" + quantityIncrement + ", quoteCurrency=" + quoteCurrency + ", status=" + status
				+ ", takeRate=" + takeRate + ", tickSize=" + tickSize + ", type=" + type + "]";
	}
}
