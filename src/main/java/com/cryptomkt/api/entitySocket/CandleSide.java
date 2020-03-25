package com.cryptomkt.api.entitySocket;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CandleSide implements Serializable {

    private Map<String, List<Candle>> candleSide;

    public Map<String, List<Candle>> getCandleSide() {
        return candleSide;
    }

    public void setCandleSide(Map<String, List<Candle>> candleSide) {
        this.candleSide = candleSide;
    }


}
