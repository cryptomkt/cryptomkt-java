package com.cryptomarket.sdk;

import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClientImpl;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

}
