package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CandleSide implements Serializable {

    @JsonAnySetter
    private Map<String, List<Candle>> candleSide;

    public Map<String, List<Candle>> getCandleSide() {
        return candleSide;
    }

    public void setCandleSide(Map<String, List<Candle>> candleSide) {
        this.candleSide = candleSide;
    }


}
