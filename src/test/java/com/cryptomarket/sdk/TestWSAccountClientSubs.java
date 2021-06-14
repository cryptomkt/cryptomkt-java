package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cryptomarket.sdk.models.Transaction;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.websocket.CryptomarketWSAccountClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSAccountClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWSAccountClientSubs {
    CryptomarketWSAccountClient wsClient;
    CryptomarketRestClient restClient;
    Boolean authenticated = false;
    Callback<Boolean> resultCallback = new Callback<Boolean>() {
        @Override
        public void resolve(Boolean result) {
            ;
        }

        @Override
        public void reject(Throwable exception) {
            fail();
        }
    };

    @Before
    public void before() {
        try {
            wsClient = new CryptomarketWSAccountClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
            wsClient.connect();
            restClient = new CryptomarketRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
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
    public void testSubscribeToTransactions() {

        Callback<Transaction> callback = new Callback<Transaction>() {
			@Override
			public void resolve(Transaction result) {
                Checker.checkTransaction.accept(result);
			}
        };
        wsClient.subscribeToTransactions(callback, resultCallback);
        
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}
        try {
            restClient.transferMoneyFromAccountBalanceToTradingBalance("EOS", "0.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}
        try {
            restClient.transferMoneyFromTradingBalanceToAccountBalance("EOS", "0.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}
    }

    @Test
    public void testSubscribeToBalance() {

        Callback<List<Balance>> callback = new Callback<List<Balance>>() {
			@Override
			public void resolve(List<Balance> result) {
                for (Balance balance : result) {
                    Checker.checkBalance.accept(balance);
                }
			}
        };
        wsClient.subscribeToBalance(callback, resultCallback);

        Callback<Transaction> callback2 = new Callback<Transaction>() {
			@Override
			public void resolve(Transaction result) {
                Checker.checkTransaction.accept(result);
			}
        };
        wsClient.subscribeToTransactions(callback2, resultCallback);
        
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}
        try {
            restClient.transferMoneyFromAccountBalanceToTradingBalance("EOS", "0.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}
        try {
            restClient.transferMoneyFromTradingBalanceToAccountBalance("EOS", "0.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {fail();}
    }
}
