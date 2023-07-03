package com.cryptomarket.sdk;

import static org.junit.Assert.assertFalse;

import java.io.IOException;

import com.cryptomarket.params.AccountType;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.sdk.Helpers.FailChecker;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
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
  
  @Before
  public void before() throws IOException {
    wsClient = new CryptomarketWSWalletClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
    wsClient.connect();
    restClient = new CryptomarketRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());
    Helpers.sleep(3);
  }

  @After
  public void after() {
    wsClient.close();
  }

  @Test
  public void testSubscribeToTransactions() throws CryptomarketSDKException {
    FailChecker failChecker = new FailChecker();
    wsClient.subscribeToTransactions(
        Helpers.checker(failChecker, Checker.checkTransaction),
        Helpers.objectAndExceptionChecker(failChecker, Checker.checkBooleanTrue));
    Helpers.sleep(3);
    restClient.transferBetweenWalletAndExchange(new ParamsBuilder()
        .currency("EOS")
        .amount("0.01")
        .source(AccountType.WALLET)
        .destination(AccountType.SPOT));
    Helpers.sleep(3);
    restClient.transferBetweenWalletAndExchange(new ParamsBuilder()
        .currency("EOS")
        .amount("0.01")
        .destination(AccountType.WALLET)
        .source(AccountType.SPOT));
    Helpers.sleep(3);
    wsClient.unsubscribeToTransactions(
        Helpers.objectAndExceptionChecker(failChecker, Checker.checkBooleanTrue));
    Helpers.sleep(3);
    assertFalse(failChecker.failed());
  }

  @Test
  public void testSubscribeToWalletBalances() throws CryptomarketSDKException {
    FailChecker failChecker = new FailChecker();
    wsClient.subscribeToWalletBalances(
        Helpers.notificationListChecker(failChecker, Checker.checkBalance),
        Helpers.objectAndExceptionChecker(failChecker, Checker.checkBooleanTrue));
    Helpers.sleep(3);
    restClient.transferBetweenWalletAndExchange(new ParamsBuilder()
        .currency("EOS")
        .amount("0.01")
        .source(AccountType.WALLET)
        .destination(AccountType.SPOT));
    Helpers.sleep(3);
    restClient.transferBetweenWalletAndExchange(new ParamsBuilder()
        .currency("EOS")
        .amount("0.01")
        .destination(AccountType.WALLET)
        .source(AccountType.SPOT));
    Helpers.sleep(3);
    wsClient.unsubscribeToWalletBalances(
        Helpers.objectAndExceptionChecker(failChecker, Checker.checkBooleanTrue));
    Helpers.sleep(3);
    assertFalse(failChecker.failed());
  }
}
