package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cryptomarket.params.Pagination;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Transaction;
import com.cryptomarket.sdk.websocket.CryptomarketWSAccountClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSAccountClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWSAccountClient {

    CryptomarketWSAccountClient wsClient;
    Boolean authenticated = false;

    @Before
    public void before() {
        try {
            wsClient = new CryptomarketWSAccountClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
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
    public void TestGetAccountBalance() {
        wsClient.getAccountBalance(new Callback<List<Balance>>() {
            @Override
            public void resolve(List<Balance> result) {
                result.forEach(balance -> Checker.checkBalance.accept(balance));
            }

            @Override
            public void reject(Throwable err) {
                err.printStackTrace();
            }
        });
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
    }

    @Test
    public void TestFindTransactions() {
        wsClient.findTransactions("EOS", null, null, new Callback<List<Transaction>>() {
            @Override
            public void resolve(List<Transaction> result) {
                result.forEach(transaction -> Checker.checkTransaction.accept(transaction));
            }

            @Override
            public void reject(Throwable err) {
                err.printStackTrace();
            }
        });
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
    }

    @Test
    public void TestFindTransactionsWithPagination() {
        wsClient.findTransactions("EOS", new Pagination.Builder().limit(2).build(), null, new Callback<List<Transaction>>() {
            @Override
            public void resolve(List<Transaction> result) {
                assertTrue("wrong number of transactions", result.size()==2);
                result.forEach(transaction -> Checker.checkTransaction.accept(transaction));
            }

            @Override
            public void reject(Throwable err) {
                err.printStackTrace();
            }
        });
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
    }


    @Test
    public void TestLoadTransactions() {
        wsClient.loadTransactions("EOS", null, null, new Callback<List<Transaction>>() {
            @Override
            public void resolve(List<Transaction> result) {
                result.forEach(transaction -> Checker.checkTransaction.accept(transaction));
            }

            @Override
            public void reject(Throwable err) {
                err.printStackTrace();
            }
        });
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
    }

    @Test
    public void TestLoadTransactionsWithPagination() {
        wsClient.loadTransactions("EOS", new Pagination.Builder().limit(2).build(), null, new Callback<List<Transaction>>() {
            @Override
            public void resolve(List<Transaction> result) {
                assertTrue("wrong number of transactions", result.size()==2);
                result.forEach(transaction -> Checker.checkTransaction.accept(transaction));
            }

            @Override
            public void reject(Throwable err) {
                err.printStackTrace();
            }
        });
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
    }
}
