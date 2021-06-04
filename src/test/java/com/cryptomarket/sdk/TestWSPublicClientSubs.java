package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cryptomarket.params.Period;
import com.cryptomarket.sdk.models.Candle;
import com.cryptomarket.sdk.models.OrderBook;
import com.cryptomarket.sdk.models.OrderbookLevel;
import com.cryptomarket.sdk.models.PublicTrade;
import com.cryptomarket.sdk.models.Ticker;
import com.cryptomarket.sdk.websocket.CryptomarketWSPublicClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSPublicClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWSPublicClientSubs {
    CryptomarketWSPublicClient wsClient;
    Boolean authenticated = false;
    Callback<Boolean> resultCallback =  new Callback<Boolean>() {
        @Override
        public void resolve(Boolean result) {;}

        @Override
        public void reject(Throwable exception) {fail();}
    };

    @Before
    public void before() {
        try {
            wsClient = new CryptomarketWSPublicClientImpl();
            wsClient.connect();
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    @After
    public void after() {
        wsClient.close();
    }

    @Test
    public void TestTimeFlow() {
        TimeFlow.reset();
        Boolean goodFLow;
        goodFLow = TimeFlow.checkNextTimestamp("2021-01-27T15:47:54.418Z");
        if (!goodFLow) {
            fail();
        }
        goodFLow = TimeFlow.checkNextTimestamp("2021-01-27T15:47:55.118Z");
        if (!goodFLow) {
            fail();
        }
        goodFLow = TimeFlow.checkNextTimestamp("2021-01-27T15:47:54.418Z");
        if (goodFLow) {
            fail();
        }
    }

    @Test
    public void testTickerSubscription() {
        TimeFlow.reset();
        String symbol = "ETHBTC";
        wsClient.subscribeToTicker(symbol, 
            new Callback<Ticker>() {

                @Override
                public void resolve(Ticker result) {
                    if (!TimeFlow.checkNextTimestamp(result.getTimestamp())) fail();
                }
            },
            resultCallback
        );
        try {TimeUnit.MINUTES.sleep(3);} catch (InterruptedException e) {fail();}

        wsClient.unsubscribeToTicker(symbol, resultCallback);
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}
    }

    @Test
    public void testOrderbookSubscription() {

        String symbol = "ETHBTC";

        Callback<OrderBook> callback = new Callback<OrderBook>() {
			@Override
			public void resolve(OrderBook result) {
                SequenceFlow.checkNextSequence(result.getSequence());
                BigDecimal size;
                BigDecimal zero;
                BigDecimal actualPrice;
                BigDecimal lastPrice = null;
                for (OrderbookLevel entry: result.getAsk()) {
                    size = new BigDecimal(entry.getSize());
                    zero = new BigDecimal("0.00");
                    if (size.compareTo(zero) == 0) fail();
                    actualPrice = new BigDecimal(entry.getPrice());
                    if (lastPrice != null) {
                        if (lastPrice.compareTo(actualPrice) == 1) fail();
                    }
                    lastPrice = actualPrice;
                    
                }
                lastPrice = null;
                for (OrderbookLevel entry: result.getBid()) {
                    size = new BigDecimal(entry.getSize());
                    zero = new BigDecimal("0.00");
                    if (size.compareTo(zero) == 0) fail();
                    actualPrice = new BigDecimal(entry.getPrice());
                    if (lastPrice != null) {
                        if (lastPrice.compareTo(actualPrice) == -1) fail();
                    }
                    lastPrice = actualPrice;
                }
			}
        };

        wsClient.subscribeToOrderbook(symbol, callback, resultCallback);

        try {TimeUnit.MINUTES.sleep(7);} catch (InterruptedException e) {fail();}

        wsClient.unsubscribeToOrderbook(symbol, resultCallback);

        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}
    }

    @Test
    public void testTradesSubscription() {
        String symbol = "ETHBTC";

        Callback<List<PublicTrade>> callback = new Callback<List<PublicTrade>>() {
			@Override
			public void resolve(List<PublicTrade> result) {
                result.forEach(Checker.checkPublicTrade);
			}
        };

        wsClient.subscribeToTrades(symbol, null, callback, resultCallback);

        try {TimeUnit.MINUTES.sleep(5);} catch (InterruptedException e) {fail();}
        
        wsClient.unsubscribeToTrades(symbol, resultCallback);

        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}
    }


    @Test
    public void testSubscribeToCandles() {
        String symbol = "ETHBTC";

        Callback<List<Candle>> callback = new Callback<List<Candle>>() {
			@Override
			public void resolve(List<Candle> result) {
                assertTrue(result.size() != 0);
                result.forEach(Checker.checkCandle);
			}
        };

        wsClient.subscribeToCandles(symbol, Period._1_MINUTES, null, callback, resultCallback);

        try {TimeUnit.MINUTES.sleep(3);} catch (InterruptedException e) {fail();}
        
        wsClient.unsubscribeToTrades(symbol, resultCallback);

        try {TimeUnit.SECONDS.sleep(10);} catch (InterruptedException e) {fail();}
    }
}
