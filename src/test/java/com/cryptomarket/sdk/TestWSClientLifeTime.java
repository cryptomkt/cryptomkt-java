package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Currency;
import com.cryptomarket.sdk.websocket.CryptomarketWSAccountClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSAccountClientImpl;
import com.cryptomarket.sdk.websocket.CryptomarketWSPublicClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSPublicClientImpl;
import com.cryptomarket.sdk.websocket.CryptomarketWSTradingClient;
import com.cryptomarket.sdk.websocket.CryptomarketWSTradingClientImpl;

import org.junit.Test;

public class TestWSClientLifeTime {

    @Test
    public void testPublicClientLifetime() {
        try {
            CryptomarketWSPublicClient wsClient;
            wsClient = new CryptomarketWSPublicClientImpl() {
                @Override
                public void onClose(String reason) {
                    System.out.println("closing");
                    
                }

                @Override
                public void onConnect() {
                    System.out.println("connected");
                    this.getCurrency("EOS", new Callback<Currency>() {
                        @Override
                        public void resolve(Currency result) {System.out.println("request returning");}
            
                        @Override
                        public void reject(Throwable exception) {fail();}
                    });
                    try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}
                    this.close();
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            };
            wsClient.connect();
            try {TimeUnit.SECONDS.sleep(8);} catch (InterruptedException e) {fail();}
        } catch (Exception e) {
            e.printStackTrace();
        }   
        
    }


    @Test
    public void testTradingClientLifetime() {
        try {
            CryptomarketWSTradingClient wsClient;
            wsClient = new CryptomarketWSTradingClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret()) {
                @Override
                public void onClose(String reason) {
                    System.out.println("closing");
                    
                }

                @Override
                public void onConnect() {
                    System.out.println("connected");
                    this.getTradingBalance(new Callback<List<Balance>>() {
                        @Override
                        public void resolve(List<Balance> result) {System.out.println("request returning");}
            
                        @Override
                        public void reject(Throwable exception) {fail();}
                    });
                    try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}
                    this.close();
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            };
            wsClient.connect();
            try {TimeUnit.SECONDS.sleep(8);} catch (InterruptedException e) {fail();}
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }


    @Test
    public void testAccountClientLifetime() {
        try {
            CryptomarketWSAccountClient wsClient;
            wsClient = new CryptomarketWSAccountClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret()) {
                @Override
                public void onClose(String reason) {
                    System.out.println("closing");
                    
                }

                @Override
                public void onConnect() {
                    System.out.println("connected");
                    this.getAccountBalance(new Callback<List<Balance>>() {
                        @Override
                        public void resolve(List<Balance> result) {System.out.println("request returning");}
            
                        @Override
                        public void reject(Throwable exception) {fail();}
                    });
                    try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}
                    this.close();
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            };
            wsClient.connect();
            try {TimeUnit.SECONDS.sleep(8);} catch (InterruptedException e) {fail();}
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    @Test
    public void testFailedAuth() {
        try {
            CryptomarketWSAccountClient wsClient = new CryptomarketWSAccountClientImpl("uno","dois");
            wsClient.connect();
            try {TimeUnit.SECONDS.sleep(8);} catch (InterruptedException e) {fail();}
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}
