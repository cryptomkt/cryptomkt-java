package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import com.cryptomarket.params.NotificationType;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSWalletClientImpl;
import com.cryptomarket.sdk.websocket.CryptomarketWSMarketDataClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSMarketDataClientImpl;
import com.cryptomarket.sdk.websocket.CryptomarketWSSpotTradingClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSSpotTradingClientImpl;

import org.junit.Test;

public class TestWSClientLifeTime {
  BiConsumer<List<String>, CryptomarketSDKException> checkException = (result, exception) -> {
    if (exception != null) {
      System.out.println("Exception");
      fail();
    }
  };

  @Test
  public void testPublicClientLifetime() {
    try {
      CryptomarketWSMarketDataClient wsClient;
      wsClient = new CryptomarketWSMarketDataClientImpl() {
        @Override
        public void onClose(String reason) {
          System.out.println("closing");
        }

        @Override
        public void onConnect() {
          System.out.println("connected");
          this.subscribeToFullOrderBook(
              (data, notificationType) -> {
                if (notificationType == NotificationType.UPDATE) {
                  System.out.println("update");
                }
                if (notificationType.isSnapshot()) {
                  System.out.println("snapshot");
                }
                data.forEach((k, v) -> {
                  System.out.println("key:" + k.toString());
                  System.out.println("val:" + v.toString());
                });

              },
              Arrays.asList("EOSETH"),
              checkException);
          try {
            TimeUnit.SECONDS.sleep(3);
          } catch (InterruptedException e) {
            fail();
          }
          this.close();
        }

        @Override
        public void onFailure(Throwable t) {
          System.out.print("failed connection");
          t.printStackTrace();
        }
      };
      wsClient.connect();
      try {
        TimeUnit.SECONDS.sleep(8);
      } catch (

      InterruptedException e) {
        fail();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testTradingClientLifetime() {
    try {
      CryptomarketWSSpotTradingClient wsClient;
      wsClient = new CryptomarketWSSpotTradingClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret()) {
        @Override
        public void onClose(String reason) {
          System.out.println("closing");

        }

        @Override
        public void onConnect() {
          System.out.println("connected");
          try {
            TimeUnit.SECONDS.sleep(3);
          } catch (InterruptedException e) {
            fail();
          }
          this.close();
        }

        @Override
        public void onFailure(Throwable t) {
          t.printStackTrace();
        }
      };
      wsClient.connect();
      try {
        TimeUnit.SECONDS.sleep(8);
      } catch (

      InterruptedException e) {
        fail();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testAccountClientLifetime() {
    try {
      CryptomarketWSWalletClient wsClient;
      wsClient = new CryptomarketWSWalletClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret()) {
        @Override
        public void onClose(String reason) {
          System.out.println("closing");

        }

        @Override
        public void onConnect() {
          System.out.println("connected");
          this.getWalletBalances((balanceList, exception) -> {
            if (exception != null) {
              System.out.println(exception.toString());
              fail();
            }
          });
          try {
            TimeUnit.SECONDS.sleep(3);
          } catch (InterruptedException e) {
            fail();
          }
          this.close();
        }

        @Override
        public void onFailure(Throwable t) {
          t.printStackTrace();
        }
      };
      wsClient.connect();
      try {
        TimeUnit.SECONDS.sleep(8);
      } catch (InterruptedException e) {
        fail();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testFailedAuth() {
    try {
      CryptomarketWSWalletClient wsClient = new CryptomarketWSWalletClientImpl("uno", "dois");
      wsClient.connect();
      try {
        TimeUnit.SECONDS.sleep(8);
      } catch (InterruptedException e) {
        fail();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
