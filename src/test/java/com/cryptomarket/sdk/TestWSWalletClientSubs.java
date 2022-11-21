package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import com.cryptomarket.params.AccountType;
import com.cryptomarket.params.ParamsBuilder;
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
  public void testSubscribeToTransactions() {
    wsClient.subscribeToTransactions(
        (data, nType) -> {
          Checker.checkTransaction.accept(data);
        }, (result, exception) -> {
          if (exception != null) {
            fail();
          }
          if (!result) {
            fail();
          }
        });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
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
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
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
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
    wsClient.unsubscribeToTransactions((result, exception) -> {
      if (!result) {
        fail();
      }
    });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
  }

  @Test
  public void testSubscribeToWalletBalances() {
    wsClient.subscribeToWalletBalances((data, nType) -> {
      data.forEach(Checker.checkBalance);
    }, (result, exception) -> {
      if (!result) {
        fail();
      }
    });
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
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
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
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
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      fail();
    }
    wsClient.unsubscribeToWalletBalances((result, exception) -> {
      if (exception != null) {
        fail();
      }
      if (!result) {
        fail();
      }
    });

    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (

    InterruptedException e) {
      fail();
    }
  }
}
