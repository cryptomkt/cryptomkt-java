package com.cryptomarket.sdk;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import com.cryptomarket.sdk.exceptions.CryptomarketSDKException;
import com.cryptomarket.sdk.models.Address;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Currency;

import org.junit.Test;

public class TestRestClientAccountManagement {
    CryptomktRestClient client = new CryptomktRestClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret());

    @Test
    public void testGetAccountBalance() {
        try {
            List<Balance>  balances = client.getAccountBalance();
            if (balances.size() == 0) fail();
            for (Balance balance: balances) {
                if (balance.getCurrency() == null || balance.getCurrency().equals("")) fail();
            }
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetDepositCriptoAddress() {
        try {
            Address address = client.getDepositCryptoAddress("EOS");
            if (address.getAddress() == null || address.getAddress().equals("")) fail();
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }   
    }

    @Test
    public void testCreateDepositCryptoAddress() {
        try {
            List<Currency> currencies = client.getCurrencies(null);
            String currency = currencies.get(10).getId();
            Address oldAddress = client.getDepositCryptoAddress(currency);
            Address newAddress = client.createDepositCryptoAddress(currency);
            assertTrue(!oldAddress.getAddress().equals(newAddress.getAddress()));
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }   
    }


    @Test
    public void testLast10DepositCryptoAddresses() {
        try {
            List<Address> addresses = client.getLast10DepositCryptoAddresses("EOS");
            for (Address address: addresses) {
                if (address.getAddress() == null || address.getAddress().equals("")) fail();
            }
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }   
    }

    @Test
    public void testGetUsedCryptoAddresses() {
        // try {
        //     // TODO not working from server side
        //     List<Address> addresses = client.get10UsedCryptoAddresses("EOS");
        //     System.out.println(addresses);
        // } catch (CryptomarketApiException e) {
        //     e.printStackTrace();
        //     fail();
        // }   
        return;
    }

    @Test
    public void testWithdrawCrypto() {
        // try {
        //     // TODO not working from server side
        //     String currency = "EOS";
        //     String amount = "0.1";
        //     String address = "123123123";
        //     String result = client.withdrawCrypto(currency, amount, address, null, null, null);
        //     System.out.println(result);
        // } catch (CryptomarketApiException e) {
        //     e.printStackTrace();
        //     fail();
        // }   
        return;
    }

    @Test
    public void testCommitWithdraw() {
        // try {
        //     // TODO withdraw crypto must work
        //     String transactionId = "someransactionId";
        //     Boolean result = client.commitWithdrawCrypto(transactionId);
        //     System.out.println(result);
        // } catch (CryptomarketApiException e) {
        //     e.printStackTrace();
        //     fail();
        // }   
        return;
    }


    @Test
    public void testTransferConvert() {
    //     try {
    //         // TODO not working from server side
    //         List<String> result = client.transferConvert("EOS", "ETH", "0.01");
    //         System.out.println(result);
    //     } catch (CryptomarketApiException e) {
    //         e.printStackTrace();
    //         fail();
    //     }   
        return;
    }


    @Test
    public void testTransferBetweenBalances() {
        try {
            List<Balance> balances = client.getAccountBalance();
            Balance EOSBalance = null;
            for (Balance balance: balances) {
                if (balance.getCurrency().equals("EOS")) {
                    EOSBalance = balance;
                    break;
                }
            }
            client.transferMoneyFromAccountBalanceToTradingBalance("EOS", "0.01");
            balances = client.getAccountBalance();
            Balance EOSBalance2 = null;
            for (Balance balance: balances) {
                if (balance.getCurrency().equals("EOS")) {
                    EOSBalance2 = balance;
                    break;
                }
            }
            if (EOSBalance.getAvailable().equals(EOSBalance2.getAvailable())) fail();
            client.transferMoneyFromTradingBalanceToAccountBalance("EOS", "0.01");
            balances = client.getAccountBalance();
            Balance EOSBalance3 = null;
            for (Balance balance: balances) {
                if (balance.getCurrency().equals("EOS")) {
                    EOSBalance3 = balance;
                    break;
                }
            }
            if (!EOSBalance.getAvailable().equals(EOSBalance3.getAvailable())) fail();
        } catch (CryptomarketSDKException e) {
            e.printStackTrace();
            fail();
        }
    }
    
}
