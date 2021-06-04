package com.cryptomarket.sdk.models;

public class Symbol {
	private String id;
	private String baseCurrency;
	private String quoteCurrency;
	private String quantityIncrement;
	private String tickSize;
	private String takeLiquidityRate;
	private String provideLiquidityRate;
	private String feeCurrency;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTakeLiquidityRate() {
		return takeLiquidityRate;
	}

	public void setTakeLiquidityRate(String takeLiquidityRate) {
		this.takeLiquidityRate = takeLiquidityRate;
	}

	public String getProvideLiquidityRate() {
		return provideLiquidityRate;
	}

	public void setProvideLiquidityRate(String provideLiquidityRate) {
		this.provideLiquidityRate = provideLiquidityRate;
	}

	public String getFeeCurrency() {
		return feeCurrency;
	}

	public void setFeeCurrency(String feeCurrency) {
		this.feeCurrency = feeCurrency;
	}

	@Override
	public String toString() {
		return "Symbol [baseCurrency=" + baseCurrency + ", feeCurrency=" + feeCurrency + ", id=" + id
				+ ", provideLiquidityRate=" + provideLiquidityRate + ", quantityIncrement=" + quantityIncrement
				+ ", quoteCurrency=" + quoteCurrency + ", takeLiquidityRate=" + takeLiquidityRate + ", tickSize="
				+ tickSize + "]";
	}	
}
