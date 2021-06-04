package com.cryptomarket.sdk.websocket;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MethodMap {
    public static final Map<String, String> map;
    static {
        Map<String, String> map1 = new HashMap<String,String>();
        map1.put("subscribeReports", "reports");
        map1.put("subscribeReports","reports");
        map1.put("unsubscribeReports","reports");
        map1.put("activeOrders","reports");
        map1.put("reports","reports");
        map1.put("report","reports");
        map1.put("subscribeTicker","tickers");
        map1.put("unsubscribeTicker","tickers");
        map1.put("ticker","tickers");
        map1.put("subscribeOrderbook","orderbooks");
        map1.put("unsubscribeOrderbook","orderbooks");
        map1.put("snapshotOrderbook","orderbooks");
        map1.put("updateOrderbook","orderbooks");
        map1.put("subscribeTrades","trades");
        map1.put("unsubscribeTrades","trades");
        map1.put("snapshotTrades","trades");
        map1.put("updateTrades","trades");
        map1.put("subscribeCandles","candles");
        map1.put("unsubscribeCandles","candles");
        map1.put("snapshotCandles","candles");
        map1.put("updateCandles","candles");
        map = Collections.unmodifiableMap(map1);
    }

    public static String getMethodKey(String key) {
        if (!map.containsKey(key)) return null;
        return map.get(key);
    }

    public static boolean isOrderbookFeed(String method) {
        if (map.get(method).equals("orderbooks")) return true;
        return false;
    }

    public static boolean isCandlesFeed(String method) {
        if (map.get(method).equals("candles")) return true;
        return false;
    }

    public static boolean isTradesFeed(String method) {
        if (map.get(method).equals("trades")) return true;
        return false;
    }

    
}
