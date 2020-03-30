package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CandleData {

    @JsonProperty("to_tx")
    private Integer tx;

    @JsonProperty("stock_id")
    private String stockId;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    @JsonProperty("data")
    private  CandleMarket market;

    public Integer getTx() {
        return tx;
    }

    public void setTx(Integer tx) {
        this.tx = tx;
    }

    public CandleMarket getMarket() {
        return market;
    }

    public void setMarket(CandleMarket market) {
        this.market = market;
    }
}
