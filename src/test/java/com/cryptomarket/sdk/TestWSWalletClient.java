package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cryptomarket.params.OrderBy;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Sort;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClientImpl;

public class TestWSWalletClient {

  CryptomarketWSWalletClient wsClient;
  Boolean authenticated = false;

  @Before
  public void before() throws IOException {
    wsClient = new CryptomarketWSWalletClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
    wsClient.connect();
    Helpers.sleep(3);
  }

  @After
  public void after() {
    wsClient.close();
  }

  @Test
  public void testGetWalletBalances() {
    wsClient.getWalletBalances((result, exception) -> {
      result.forEach(Checker.checkBalance);
    });
    Helpers.sleep(3);
  }

  @Test
  public void testGetWalletBalance() {
    wsClient.getWalletBalanceByCurrency(
        "EOS",
        (result, exception) -> {
          Checker.checkBalance.accept(result);
        });

    Helpers.sleep(3);
  }

  @Test
  public void testGetTransactions() {
    wsClient.getTransactions(
        (result, exception) -> result.forEach(Checker.checkTransaction),
        null);
    Helpers.sleep(3);
  }

  @Test
  public void testGetTransactionHistoryWithParams() throws CryptomarketSDKException {
    wsClient.getTransactions((transactions, exception) -> {
      assertTrue(transactions.size() > 0);
      transactions.forEach(Checker.checkTransaction);
    }, new ParamsBuilder()
        .orderBy(OrderBy.CREATED_AT)
        .sort(Sort.DESC)
        .limit(1000)
        .offset(0)
        .currencies(List.of())
        .from("1614815872000"));
  }

}
