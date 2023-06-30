package com.cryptomarket.sdk;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import com.cryptomarket.params.AccountType;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.sdk.Helpers.FailChecker;
import com.cryptomarket.sdk.rest.CryptomarketRestClient;
import com.cryptomarket.sdk.rest.CryptomarketRestClientImpl;
import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestWSWalletClientSubs {
  CryptomarketWSWalletClient wsClient;
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
      wsClient = new CryptomarketWSWalletClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
      wsClient.connect();
      restClient = new CryptomarketRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
      Helpers.sleep(3);
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
    FailChecker failChecker = new FailChecker();
    wsClient.subscribeToTransactions(
        Helpers.checker(failChecker, Checker.checkTransaction),
        (result, exception) -> {
          if (exception != null) {
            fail();
          }
          if (!result) {
            fail();
          }
        });
    Helpers.sleep(3);
    try {
      restClient.transferBetweenWalletAndExchange(new ParamsBuilder()
          .currency("EOS")
          .amount("0.01")
          .source(AccountType.WALLET)
          .destination(AccountType.SPOT));
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
    Helpers.sleep(3);
    try {
      restClient.transferBetweenWalletAndExchange(new ParamsBuilder()
          .currency("EOS")
          .amount("0.01")
          .destination(AccountType.WALLET)
          .source(AccountType.SPOT));
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
    Helpers.sleep(3);
    wsClient.unsubscribeToTransactions((result, exception) -> {
      if (!result) {
        fail();
      }
    });
    Helpers.sleep(3);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testSubscribeToWalletBalances() {
    FailChecker failChecker = new FailChecker();
    wsClient.subscribeToWalletBalances(
        Helpers.listChecker(failChecker, Checker.checkBalance),
        (result, exception) -> {
          if (!result) {
            fail();
          }
        });
    Helpers.sleep(3);
    try {
      restClient.transferBetweenWalletAndExchange(new ParamsBuilder()
          .currency("EOS")
          .amount("0.01")
          .source(AccountType.WALLET)
          .destination(AccountType.SPOT));
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
    Helpers.sleep(3);
    try {
      restClient.transferBetweenWalletAndExchange(new ParamsBuilder()
          .currency("EOS")
          .amount("0.01")
          .destination(AccountType.WALLET)
          .source(AccountType.SPOT));
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
    Helpers.sleep(3);
    wsClient.unsubscribeToWalletBalances((result, exception) -> {
      if (exception != null) {
        fail();
      }
      if (!result) {
        fail();
      }
    });
    Helpers.sleep(3);
    assertFalse(failChecker.failed());
  }
}
