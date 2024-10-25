package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.cryptomarket.params.AccountType;
import com.cryptomarket.params.OrderBy;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.params.Sort;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Address;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.FeeRequest;
import com.cryptomarket.sdk.models.Transaction;
import com.cryptomarket.sdk.rest.CryptomarketRestClient;
import com.cryptomarket.sdk.rest.CryptomarketRestClientImpl;

public class TestRestClientWalletManagement {
  CryptomarketRestClient client = new CryptomarketRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());

  @Test
  public void testGetWalletBalances() throws CryptomarketSDKException {
    List<Balance> balances = client.getWalletBalances();
    if (balances.size() == 0)
      fail();
    balances.forEach(balance -> {
      if (balance.getCurrency() == null || balance.getCurrency().equals(""))
        fail();
    });
  }

  @Test
  public void testGetWalletBalanceOfCurrency() throws CryptomarketSDKException {
    Balance balance = client.getWalletBalanceByCurrency("ADA");
    Checker.checkBalance.accept(balance);
  }

  @Test
  public void testGetDepositCriptoAddresses() throws CryptomarketSDKException {
    List<Address> addresses = client.getDepositCryptoAddresses(null, null);
    addresses.forEach(Checker.checkAddress);
  }

  @Test
  public void testGetDepositCriptoAddressOfCurrency() throws CryptomarketSDKException {
    List<Address> addresses = client.getDepositCryptoAddresses("NEXO", null);
    addresses.forEach(Checker.checkAddress);
  }

  @Test
  public void testCreateDepositCryptoAddress() throws CryptomarketSDKException {
    Address addresses = client.createDepositCryptoAddress("BTC", null);
    Checker.checkAddress.accept(addresses);
  }

  @Test
  public void testLast10DepositCryptoAddresses() throws CryptomarketSDKException {
    List<Address> addresses = client.getLast10DepositCryptoAddresses("EOS", null);
    addresses.forEach(Checker.checkAddress);
  }

  @Test
  public void testLast10WithdrawalCryptoAddresses() throws CryptomarketSDKException {
    List<Address> addresses = client.getLast10WithdrawalCryptoAddresses("EOS", null);
    addresses.forEach(Checker.checkAddress);
  }

  @Test
  public void testWithdrawCrypto() throws CryptomarketSDKException {
    List<Address> adaAddresses = client.getDepositCryptoAddresses("ADA", null);
    String transaction_id = client.withdrawCrypto(new ParamsBuilder()
        .currency("ADA")
        .amount("0.1")
        .address(adaAddresses.get(0).getAddress()));
    if (transaction_id.equals("")) {
      fail();
    }
  }

  @Test
  public void testWithdrawCryptoCommit() throws CryptomarketSDKException {
    List<Address> adaAddresses = client.getDepositCryptoAddresses("ADA", null);
    String transactionId = client.withdrawCrypto(new ParamsBuilder()
        .currency("ADA")
        .amount("0.1")
        .address(adaAddresses.get(0).getAddress())
        .autoCommit(false));
    if (transactionId.equals("")) {
      fail();
    }
    boolean result = client.withdrawCryptoCommit(transactionId);
    if (!result) {
      fail();
    }
  }

  @Test
  public void testWithdrawCryptoRollback() throws CryptomarketSDKException {
    List<Address> adaAddresses = client.getDepositCryptoAddresses("ADA", null);
    String transactionId = client.withdrawCrypto(new ParamsBuilder()
        .currency("ADA")
        .amount("0.1")
        .address(adaAddresses.get(0).getAddress())
        .autoCommit(false));
    if (transactionId.equals("")) {
      fail();
    }
    boolean result = client.withdrawCryptoRollback(transactionId);
    if (!result) {
      fail();
    }
  }

  @Test
  public void testGetEstimateWithdrawFees() throws CryptomarketSDKException {
    var fees = client
        .getEstimateWithdrawalFees(List.of(new FeeRequest("EOS", "100", null), new FeeRequest("ETH", "100", null)));
    if (fees.size() != 2) {
      fail("invalid amount of fees");
    }
    fees.forEach(Checker.checkFee);
  }

  @Test
  public void testGetBulkEstimateWithdrawFees() throws CryptomarketSDKException {
    var fees = client
        .getBulkEstimateWithdrawalFees(List.of(new FeeRequest("EOS", "100", null), new FeeRequest("ETH", "100", null)));
    if (fees.size() != 2) {
      fail("invalid amount of fees");
    }
    fees.forEach(Checker.checkFee);
  }

  @Test
  public void testGetEstimateWithdrawFee() throws CryptomarketSDKException {
    String estimate = client.getEstimateWithdrawalFee("EOS", "100", null);
    if (estimate.equals("")) {
      fail();
    }
  }
  @Test
  public void testGetEstimateWithdrawFeesHash() throws CryptomarketSDKException {
    String estimate = client.getWithdrawalFeesHash();
    if (estimate.equals("")) {
      fail();
    }
  }

  // @Test
  // public void testGetEstimateDepositFees() throws CryptomarketSDKException {
  //   var fees = client
  //       .getBulkEstimateDepositFees(List.of(new FeeRequest("EOS", "100"), new FeeRequest("ETH", "100")));
  //   if (fees.size() != 2) {
  //     fail("invalid amount of fees");
  //   }
  //   fees.forEach(Checker.checkFee);
  // }

  // @Test
  // public void testGetEstimateDepositFee() throws CryptomarketSDKException {
  //   String estimate = client.getEstimateDepositFee("EOS", "100", null);
  //   if (estimate.equals("")) {
  //     fail();
  //   }
  // }

  @Test
  public void testCryptoAddressBelongsToCurrentAccount() throws CryptomarketSDKException {
    List<Address> addresses = client.getDepositCryptoAddresses("ADA", null);
    boolean isMine = client.checkCryptoAddressBelongsToCurrentAccount(addresses.get(0).getAddress());
    if (!isMine) {
      fail();
    }
  }

  @Test
  public void testTransferBetweenWalletAndExchage() throws CryptomarketSDKException {
    String transactionId = client.transferBetweenWalletAndExchange(new ParamsBuilder()
        .currency("ADA")
        .amount("0.1")
        .source(AccountType.WALLET)
        .destination(AccountType.SPOT));
    if (transactionId.equals("")) {
      fail();
    }
    transactionId = client.transferBetweenWalletAndExchange(new ParamsBuilder()
        .currency("ADA")
        .amount("0.1")
        .source(AccountType.SPOT)
        .destination(AccountType.WALLET));
    if (transactionId.equals("")) {
      fail();
    }

  }

  @Test
  public void testGetTransactionHistory() throws CryptomarketSDKException {
    List<Transaction> transactions = client.getTransactionHistory(new ParamsBuilder());
    transactions.forEach(Checker.checkTransaction);
  }

  @Test
  public void testGetTransactionHistoryWithParams() throws CryptomarketSDKException {
    List<Transaction> transactions = client.getTransactionHistory(new ParamsBuilder()
        .orderBy(OrderBy.CREATED_AT)
        .sort(Sort.DESC)
        .limit(1000)
        .offset(0)
        .currencies(List.of())
        .from("1614815872000"));
    assertTrue(transactions.size() > 0);
    transactions.forEach(Checker.checkTransaction);
  }

  @Test
  public void testGetTransaction() throws CryptomarketSDKException {
    List<Transaction> transactions = client.getTransactionHistory(new ParamsBuilder().limit(1));
    Transaction transaction = client.getTransaction(transactions.get(0).getNativeTransaction().getId());
    Checker.checkTransaction.accept(transaction);
  }

  @Test
  public void testOffchainAvailable() throws CryptomarketSDKException {
    List<Address> eosAddresses = client.getDepositCryptoAddresses("EOS", null);
    client.checkIfOffchainIsAvailable("EOS", eosAddresses.get(0).getAddress(), null);
  }

  @Test
  public void testGetAmountLock() {
  }
}
