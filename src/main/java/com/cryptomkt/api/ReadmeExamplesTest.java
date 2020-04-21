package com.cryptomkt.api;


import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.entity.orders.CancelMultiOrderResponse;
import com.cryptomkt.api.entity.orders.CreateMultiOrderResponse;
import com.cryptomkt.api.entity.orders.MultiOrderRequest;
import com.cryptomkt.api.entity.orders.Order;
import com.cryptomkt.api.exception.CryptoMarketException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ReadmeExamplesTest {

    String apiKey;
    String apiSecret;
    Client client;
    @Before
    public void setUp() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("/home/ismael/cptmkt/keys.txt"));
            apiKey = allLines.get(0);
            apiSecret = allLines.get(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client = new ClientImpl(apiKey, apiSecret);
    }

    @Test
    public void testPaginationExample() {
        try {
            Integer next = 0;
            do {
                PricesResponse response = client.getPrices("XLMCLP", "44640", next, 5);
                System.out.println("status: " + response.getStatus());
                if (response.isSuccess()) {
                    System.out.println(response.getPagination());
                    next = response.getPagination().getNext();
                    Prices prices = response.getPrices();
                    for (Candle candle: prices.getAsk()) {
                        System.out.println(candle);
                    }
                }
            } while (next != 0);
        } catch(CryptoMarketException e){
            e.printStackTrace();
        }
    }


    @Test
    public void testMarketsExample() {
        try {
            List<String> markets = client.getMarkets().getMarkets();
            System.out.println(markets);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBookExample() {
        try {
            List<Book> books = client.getBook("ETHCLP", "buy").getBooks();
            System.out.println(books);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTickerExample() {
        try {
            List<Ticker> tickers = client.getTickers("BTCBRL").getTickers();
            System.out.println(tickers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTradesExample() {
        try {
            List<Trade> trades = client.getTrades("ETHARS").getTrades();
            System.out.println(trades);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAccountExamples() {
        try {
            Account account = client.getAccount().getAccount();
            System.out.println(account.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateOrderExample() {
        try {
            Order order = client.createOrder("XLMCLP", "124", "sell", "limit", "1")
                    .getOrder();
            System.out.println(order);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultiOrderExample() {
        try {
            MultiOrderRequest multiOrderRequest = new MultiOrderRequest()
                    .add("XLMCLP", "limit", "sell", "150", "1")
                    .add("XLMCLP", "limit", "sell", "151", "1")
                    .add("XLMCLP", "limit", "sell", "152", "1")
                    .add("XLMCLP", "limit", "sell", "353", "1000000000");
            CreateMultiOrderResponse createMultiOrderResponse = client.createMultiOrders(multiOrderRequest);
            System.out.println(createMultiOrderResponse);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetActiveOrdersExample() {
        try {
            List<Order> orders = client.getActiveOrders("XLMCLP")
                    .getOrders();
            System.out.println(orders);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCancelOrderExample() {
        try {
            Order order = client.cancelOrder("O5597537")
                    .getOrder();
            System.out.println(order);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCancelMultiOrdersExample() {
        try {
            List<Order> orders = client.getActiveOrders("XLMCLP").getOrders();
            List<String> ordersIds = new ArrayList<>();
            for (Order order : orders) {
                ordersIds.add(order.getId());
            }
            ordersIds.add("11111111");
            CancelMultiOrderResponse cancelMultiOrderResponse = client.cancelMultiOrder(ordersIds);
            System.out.println(cancelMultiOrderResponse);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTransferExample() {
        try {
            Response result = client.transfer("GDMXNQBJMS3FYI4PFSYCCB4XODQMNMTKPQ5HIKOUWBOWJ2P3CF6WASBE", "1", "XLM", "25767435");
            System.out.println(result);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExecutedOrdersExample() {
        try {
            List<Order> orders = client.getExecutedOrders("XLMCLP")
                    .getOrders();
            System.out.println(orders);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOrderStatusExample() {
        try {
            Order order = client.getOrderStatus("O5850367")
                    .getOrder();
            System.out.println(order);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBalanceExample() {
        try {
            List<Balance> balance = client.getBalance().getBalances();
            System.out.println(balance);
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketHandlersExample() {
        Socket socket = null;
        try {
            socket = client.getSocket();
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        assert socket != null;
        class  CandleConsumer implements Consumer<JSONObject> {
            @Override
            public void accept(JSONObject jsonObject) {
                System.out.println("class consumer "+jsonObject);
            }
        }
        // a class consumer of the event data
        socket.onCandles(new CandleConsumer());
        // a lambda expression consumer for the event data
        socket.onCandles(data -> System.out.println("lambda consumer "+data));
        //and the method reference consumer
        socket.onCandles(System.out::println);
        socket.subscribe("ETHARS");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketOpenBookExample() {
        Socket socket = null;
        try {
            socket = client.getSocket();
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        assert socket != null;
        socket.subscribe("ETHCLP");
        socket.onOpenBook(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketHistoricalBookExample() {
        Socket socket = null;
        try {
            socket = client.getSocket();
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        assert socket != null;
        socket.subscribe("ETHCLP");
        socket.onHistoricalBook(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketCandlesExample() {
        Socket socket = null;
        try {
            socket = client.getSocket();
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        assert socket != null;
        socket.subscribe("XLMCLP");
        socket.onCandles(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketTickerExample() {
        Socket socket = null;
        try {
            socket = client.getSocket();
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        assert socket != null;
        socket.onTicker(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketBalanceExample() {
        Socket socket = null;
        try {
            socket = client.getSocket();
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        assert socket != null;
        socket.onBalance(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketOpenOrdersExample() {
        Socket socket = null;
        try {
            socket = client.getSocket();
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        assert socket != null;
        socket.onOpenOrders(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketHistoricalOrdersExample() {
        Socket socket = null;
        try {
            socket = client.getSocket();
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        assert socket != null;
        socket.onHistoricalOrders(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketOperatedExample() {
        Socket socket = null;
        try {
            socket = client.getSocket();
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
        assert socket != null;
        socket.onOperated(System.out::println);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
