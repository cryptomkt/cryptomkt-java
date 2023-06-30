package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClientImpl;
import com.cryptomarket.sdk.websocket.CryptomarketWSMarketDataClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSMarketDataClientImpl;
import com.cryptomarket.sdk.websocket.CryptomarketWSSpotTradingClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSSpotTradingClientImpl;

import org.junit.Test;

class Failed {
  public static Boolean failed = false;
}

public class TestWSClientLifeTime {
  BiConsumer<List<String>, CryptomarketSDKException> checkException = (result, exception) -> {
    if (exception != null) {
      fail();
    }
  };

  @Test
  public void testPublicClientLifetime() throws IOException {
    CryptomarketWSMarketDataClient wsClient;
    wsClient = new CryptomarketWSMarketDataClientImpl();
    wsClient.onConnect(() -> {
      System.out.println("connected");
      new Thread(() -> {
        wsClient.subscribeToFullOrderBook(
            (data, notificationType) -> System.out.println(notificationType),
            Arrays.asList("EOSETH"),
            checkException);
        Helpers.sleep(3);
        wsClient.close();
      }).run();
    });
    wsClient.onClose(reason -> System.out.println("closing"));
    wsClient.onFailure(t -> t.printStackTrace());
    wsClient.connect();
    Helpers.sleep(6);
  }

  @Test
  public void testTradingClientLifetime() throws IOException {
    CryptomarketWSSpotTradingClient wsClient;
    wsClient = new CryptomarketWSSpotTradingClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
    wsClient.onConnect(() -> {
      System.out.println("connected");
      Helpers.sleep(3);
      wsClient.close();
    });
    wsClient.onClose(reason -> System.out.println("closing"));
    wsClient.onFailure(t -> t.printStackTrace());
    wsClient.connect();
    Helpers.sleep(3);
  }

  @Test
  public void testAccountClientLifetime() throws IOException {
    CryptomarketWSWalletClient wsClient;
    wsClient = new CryptomarketWSWalletClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
    wsClient.onClose(reason -> {
      System.out.println("closing");
    });
    wsClient.onConnect(() -> {
      System.out.println("connected");
      wsClient.getWalletBalances((balanceList, exception) -> {
        System.out.println(balanceList);
        if (exception != null) {
          exception.printStackTrace();
          fail();
        }
      });
      Helpers.sleep(3);
      wsClient.close();
    });
    wsClient.onFailure(t -> {
      t.printStackTrace();
    });
    wsClient.connect();
    Helpers.sleep(6);
  }

  @Test
  public void testFailedAuth() throws IOException {
    Failed.failed = false;
    CryptomarketWSWalletClient wsClient = new CryptomarketWSWalletClientImpl("uno", "dois");
    wsClient.onFailure((t) -> {
      Failed.failed = true;
    });
    wsClient.connect();
    Helpers.sleep(3);
    assertTrue(Failed.failed);
  }
}
