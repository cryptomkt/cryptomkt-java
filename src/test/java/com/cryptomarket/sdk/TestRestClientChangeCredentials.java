package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.rest.CryptomarketRestClient;
import com.cryptomarket.sdk.rest.CryptomarketRestClientImpl;

public class TestRestClientChangeCredentials {
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

    client.changeCredentials("null", "null");
    try {
      balances = client.getWalletBalances();
      fail("should fail");
    } catch (CryptomarketSDKException e) {
      // good
    }
    client.changeCredentials(KeyLoader.getApiKey(), KeyLoader.getApiSecret());

    balances = client.getWalletBalances();
    if (balances.size() == 0)
      fail();
    balances.forEach(balance -> {
      if (balance.getCurrency() == null || balance.getCurrency().equals(""))
        fail();
    });
  }
}
