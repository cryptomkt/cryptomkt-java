package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.List;

import com.cryptomarket.params.AccountType;
import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Address;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Transaction;

import org.junit.Test;

public class TestRestClientWalletManagement {
  CryptomarketRestClient client = new CryptomarketRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());

  @Test
  public void testGetWalletBalances() {
    try {
      List<Balance> balances = client.getWalletBalances();
      if (balances.size() == 0)
        fail();
      balances.forEach(balance -> {
        if (balance.getCurrency() == null || balance.getCurrency().equals(""))
          fail();

      });
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetWalletBalanceOfCurrency() {
    try {
      Balance balance = client.getWalletBalanceOfCurrency("ADA");
      Checker.checkBalance.accept(balance);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetDepositCriptoAddresses() {
    try {
      List<Address> addresses = client.getDepositCryptoAddresses();
      addresses.forEach(Checker.checkAddress);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetDepositCriptoAddressOfCurrency() {
    try {
      Address addresses = client.getDepositCryptoAddressOfCurrency("EOS");
      Checker.checkAddress.accept(addresses);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testCreateDepositCryptoAddress() {
    try {
      Address addresses = client.createDepositCryptoAddress("EOS");
      Checker.checkAddress.accept(addresses);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testLast10DepositCryptoAddresses() {
    try {
      List<Address> addresses = client.getLast10DepositCryptoAddresses("EOS");
      addresses.forEach(Checker.checkAddress);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testLast10WithdrawalCryptoAddresses() {
    try {
      List<Address> addresses = client.getLast10WithdrawalCryptoAddresses("EOS");
      addresses.forEach(Checker.checkAddress);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testWithdrawCrypto() {
    try {
      Address adaAddress = client.getDepositCryptoAddressOfCurrency("ADA");
      String transaction_id = client.withdrawCrypto(new ParamsBuilder()
          .currency("ADA")
          .amount("0.1")
          .address(adaAddress.getAddress()));
      if (transaction_id.equals("")) {
        fail();
      }
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testWithdrawCryptoCommit() {
    try {
      Address adaAddress = client.getDepositCryptoAddressOfCurrency("ADA");
      String transactionID = client.withdrawCrypto(new ParamsBuilder()
          .currency("ADA")
          .amount("0.1")
          .address(adaAddress.getAddress())
          .autoCommit(false));
      if (transactionID.equals("")) {
        fail();
      }
      boolean result = client.withdrawCryptoCommit(transactionID);
      if (!result) {
        fail();
      }
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testWithdrawCryptoRollback() {
    try {
      Address adaAddress = client.getDepositCryptoAddressOfCurrency("ADA");
      String transactionID = client.withdrawCrypto(new ParamsBuilder()
          .currency("ADA")
          .amount("0.1")
          .address(adaAddress.getAddress())
          .autoCommit(false));
      if (transactionID.equals("")) {
        fail();
      }
      boolean result = client.withdrawCryptoRollback(transactionID);
      if (!result) {
        fail();
      }
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetEstimateWithdrawFee() {
    try {
      String estimate = client.getEstimateWithdrawalFee("EOS", "100");
      if (estimate.equals("")) {
        fail();
      }
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testCryptoAddressBelongsToCurrentAccount() {
    try {
      Address address = client.getDepositCryptoAddressOfCurrency("ADA");
      boolean isMine = client.cryptoAddressBelongsToCurrentAccount(address.getAddress());
      if (!isMine) {
        fail();
      }
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testTransferBetweenWalletAndExchage() {
    try {
      String transactionID = client.transferBetweenWalletAndExchange(new ParamsBuilder()
          .currency("CRO")
          .amount("0.1")
          .source(AccountType.SPOT)
          .destination(AccountType.WALLET));
      if (transactionID.equals("")) {
        fail();
      }
      transactionID = client.transferBetweenWalletAndExchange(new ParamsBuilder()
          .currency("CRO")
          .amount("0.1")
          .source(AccountType.WALLET)
          .destination(AccountType.SPOT));
      if (transactionID.equals("")) {
        fail();
      }
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testTransferMoneyToAnoterUser() {
    // try {
    // client.transferMoneyToAnotherUser("CRO", "01", IdentifyBy.EMAIL, "the
    // email");
    // } catch (CryptomarketSDKException e) {
    // e.printStackTrace();
    // fail();
    // }
  }

  @Test
  public void testGetTransactionHistory() {
    try {
      List<Transaction> transactions = client.getTransactionHistory(new ParamsBuilder());
      transactions.forEach(Checker.checkTransaction);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetTransaction() {
    // see ruby sdk for further information
  }

  @Test
  public void testOffchainAvailable() {
    try {
      Address eosAddrses = client.getDepositCryptoAddressOfCurrency("EOS");
      client.checkIfOffchainIsAvailable("EOS", eosAddrses.getAddress(), null);
    } catch (CryptomarketSDKException e) {
      fail(e.toString());
    }
  }

  @Test
  public void testGetAirdrops() {
  }

  @Test
  public void testClaimAirdrops() {
  }

  @Test
  public void testGetAmountLock() {
  }
}
