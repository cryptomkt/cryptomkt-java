package com.cryptomkt.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Candle implements Serializable {
    private static final long serialVersionUID = 1;

    @JsonProperty("candle_id")
    private String id;

    @JsonProperty("open_price")
    private String openPrice;

    @JsonProperty("hight_price")
    private String HighPrice;

    @JsonProperty("close_price")
    private String ClosePrice;

    @JsonProperty("low_price")
    private String LowPrice;

    @JsonProperty("volume_sum")
    private String volumeSum;

    @JsonProperty("candle_date")
    private String candleDate;

    @JsonProperty("tick_count")
    private String tickCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getHighPrice() {
        return HighPrice;
    }

    public void setHighPrice(String highPrice) {
        HighPrice = highPrice;
    }

    public String getClosePrice() {
        return ClosePrice;
    }

    public void setClosePrice(String closePrice) {
        ClosePrice = closePrice;
    }

    public String getLowPrice() {
        return LowPrice;
    }

    public void setLowPrice(String lowPrice) {
        LowPrice = lowPrice;
    }

    public String getVolumeSum() {
        return volumeSum;
    }

    public void setVolumeSum(String volumeSum) {
        this.volumeSum = volumeSum;
    }

    public String getCandleDate() {
        return candleDate;
    }

    public void setCandleDate(String candleDate) {
        this.candleDate = candleDate;
    }

    @Override
    public String toString() {
        return "Candle{" +
                "id='" + id + '\'' +
                ", candleDate='" + candleDate + '\'' +
                ", openPrice='" + openPrice + '\'' +
                ", HighPrice='" + HighPrice + '\'' +
                ", ClosePrice='" + ClosePrice + '\'' +
                ", LowPrice='" + LowPrice + '\'' +
                ", volumeSum='" + volumeSum + '\'' +
                ", tickCount='" + tickCount + '\'' +
                '}';
    }
}
