package com.cryptomarket.sdk.models;

import com.squareup.moshi.Json;

public class Candle {
	private String timestamp;
	private String open;
	private String close;
	private String min;
	private String max;
	private String volume;
  @Json(name="volume_quote")
	private String volumeQuote;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
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

	@Override
	public String toString() {
		return "Candle [close=" + close + ", max=" + max + ", min=" + min + ", open=" + open + ", timestamp="
				+ timestamp + ", volume=" + volume + ", volumeQuote=" + volumeQuote + "]";
	}

}