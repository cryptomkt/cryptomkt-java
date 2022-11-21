package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class Ticker {
	private String ask;
	private String bid;
	private String last;
	private String low;
	private String high;
	private String open;
	private String volume;
	@Json(name="volume_quote")
	private String volumeQuote;
	private String timestamp;
	
	public String getAsk() {
		return ask;
	}
	public void setAsk(String ask) {
		this.ask = ask;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getVolumeQuote() {
		return volumeQuote;
	}
	public void setVolumeQuote(String volumeQuote) {
		this.volumeQuote = volumeQuote;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Ticker [ask=" + ask + ", bid=" + bid + ", high=" + high + ", last=" + last + ", low=" + low + ", open="
				+ open + ", timestamp=" + timestamp + ", volume=" + volume + ", volumeQuote=" + volumeQuote + "]";
	}


}