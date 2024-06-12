package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.rest.CryptomarketRestClient;
import com.cryptomarket.sdk.rest.CryptomarketRestClientImpl;

public class TestRestClientChangeWindow {
  CryptomarketRestClient client = new CryptomarketRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());

  @Test
  public void testChangeCredentials() throws CryptomarketSDKException {
    List<Balance> balances = client.getWalletBalances();
    if (balances.size() == 0)
      fail();
    balances.forEach(balance -> {
      if (balance.getCurrency() == null || balance.getCurrency().equals(""))
        fail();
    });

    client.changeWindow(100);
    try {
      balances = client.getWalletBalances();
      fail("should fail");
    } catch (CryptomarketSDKException e) {
      // good
    }
    client.changeWindow(10_000);

    balances = client.getWalletBalances();
    if (balances.size() == 0)
      fail();
    balances.forEach(balance -> {
      if (balance.getCurrency() == null || balance.getCurrency().equals(""))
        fail();
    });
  }
}
