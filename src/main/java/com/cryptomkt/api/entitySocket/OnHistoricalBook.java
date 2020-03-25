package com.cryptomkt.api.entitySocket;

import java.util.List;
import java.util.Map;

public class OnHistoricalBook {

    private Map<String, List<OrderBookExecuted>> historicalBook;

    public Map<String, List<OrderBookExecuted>> getHistoricalBook() {
        return historicalBook;
    }

    public void setHistoricalBook(Map<String, List<OrderBookExecuted>> historicalBook) {
        this.historicalBook = historicalBook;
    }
}
