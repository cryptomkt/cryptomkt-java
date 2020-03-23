package com.cryptomkt.api.entitySocket;

import com.cryptomkt.api.entity.Market;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Candle implements Serializable {

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("closePrice")
    private Integer closePrice;

    @JsonProperty("lastBuyPrice")
    private Integer lastBuyPrice;

    @JsonProperty("lowPrice")
    private Integer lowPrice;

    @JsonProperty("openPrice")
    private Integer openPrice;

    @JsonProperty("volume")
    private Double volume;

    @JsonProperty("stockId")
    private String StockId;

    @JsonProperty("timeFrame")
    private Integer timeFrame;

    @JsonProperty("date")
    private String date;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("hightPrice")
    private Integer hightPrice;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Integer closePrice) {
        this.closePrice = closePrice;
    }

    public Integer getLastBuyPrice() {
        return lastBuyPrice;
    }

    public void setLastBuyPrice(Integer lastBuyPrice) {
        this.lastBuyPrice = lastBuyPrice;
    }

    public Integer getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Integer openPrice) {
        this.openPrice = openPrice;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getStockId() {
        return StockId;
    }

    public void setStockId(String stockId) {
        StockId = stockId;
    }

    public Integer getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(Integer timeFrame) {
        this.timeFrame = timeFrame;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHightPrice() {
        return hightPrice;
    }

    public void setHightPrice(Integer hightPrice) {
        this.hightPrice = hightPrice;
    }



}
