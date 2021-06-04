package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cryptomarket.params.Pagination;
import com.cryptomarket.sdk.models.Currency;
import com.cryptomarket.sdk.models.PublicTrade;
import com.cryptomarket.sdk.models.Symbol;
import com.cryptomarket.sdk.websocket.CryptomarketWSPublicClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSPublicClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWSPublicClient {
    CryptomarketWSPublicClient wsClient;

    
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
    public void testGetCurrency() {
        wsClient.getCurrency("EOS", new Callback<Currency>() {
            @Override
            public void resolve(Currency result) {Checker.checkCurrency.accept(result);}

            @Override
            public void reject(Throwable exception) {fail();}
        });
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            fail();
        }
    }

    @Test
    public void testGetCurrencies() {
        wsClient.getCurrencies(new Callback<List<Currency>>() {
            @Override
            public void resolve(List<Currency> result) {
                if (result.isEmpty()) fail();
                result.forEach(Checker.checkCurrency);
            }

            @Override
            public void reject(Throwable exception) {fail();}
        });
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            fail();
        }
    }
    
    
    @Test
    public void testGetSymbol() {
        wsClient.getSymbol("ETHBTC", new Callback<Symbol>() {
            @Override
            public void resolve(Symbol result) {Checker.checkSymbol.accept(result);}

            @Override
            public void reject(Throwable exception) {exception.printStackTrace();fail();}
        });
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            fail();
        }
    }

    @Test
    public void testGetSymbols() {
        wsClient.getSymbols(new Callback<List<Symbol>>() {
            @Override
            public void resolve(List<Symbol> result) {
                if (result.isEmpty()) fail();
                result.forEach(Checker.checkSymbol);
            }

            @Override
            public void reject(Throwable exception) {fail();}
        });
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            fail();
        }
    }

    @Test
    public void testGetTrades() {
        wsClient.getTrades("EOSETH", null, null, null, null, null, null, new Callback<List<PublicTrade>>() {
            @Override
            public void resolve(List<PublicTrade> result) {
                if (result.isEmpty()) fail();
                result.forEach(Checker.checkPublicTrade);
            }

            @Override
            public void reject(Throwable exception) {fail();}
        });
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            fail();
        }
    }

    @Test
    public void testGet2Trades() {
        wsClient.getTrades("EOSETH", null, null, null, null, 2, null, new Callback<List<PublicTrade>>() {
            @Override
            public void resolve(List<PublicTrade> result) {
                if (result.size() != 2) fail();
                result.forEach(Checker.checkPublicTrade);
            }

            @Override
            public void reject(Throwable exception) {exception.printStackTrace();fail();}
        });
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            fail();
        }
    }

    @Test
    public void testGet2TradesWithPagination() {
        wsClient.getTrades("EOSETH", new Pagination.Builder().limit(2).build(), new Callback<List<PublicTrade>>() {
            @Override
            public void resolve(List<PublicTrade> result) {
                result.forEach(Checker.checkPublicTrade);
                assertTrue(result.size() <= 2);
            }

            @Override
            public void reject(Throwable exception) {exception.printStackTrace();fail();}
        });
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            fail();
        }
    }
}
