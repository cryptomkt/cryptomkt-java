package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWSWalletClient {

  CryptomarketWSWalletClient wsClient;
  Boolean authenticated = false;

  @Before
  public void before() {
    try {
      wsClient = new CryptomarketWSWalletClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
      wsClient.connect();
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        fail();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
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
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testGetWalletBalance() {
    wsClient.getWalletBalanceOfCurrency(
        "EOS",
        (result, exception) -> {
          Checker.checkBalance.accept(result);
        });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testGetTransactions() {
    wsClient.getTransactions(
        (result, exception) -> {
          result.forEach(Checker.checkTransaction);
        },
        null);
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
  }

}
