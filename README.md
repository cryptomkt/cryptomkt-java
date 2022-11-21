# CryptoMarket-Java

[main page](https://www.cryptomkt.com/)

[sign up in CryptoMarket](https://www.cryptomkt.com/account/register).

# Installation

add the Maven dependency

```xml
<dependency>
    <groupId>com.cryptomkt.api</groupId>
    <artifactId>cryptomarket</artifactId>
    <version>1.1.0</version>
</dependency>
```

# Documentation

This sdk makes use of the [api version 3](https://api.exchange.cryptomkt.com) of cryptomarket.

# Quick Start

## rest client

```java

// instance a client
String apiKey = "AB32B3201";
String apiSecret = "21b12401";
CryptomarketRestClient client = new CryptomarketRestClientImpl(apiKey, apiSecret);

// get all currencies
Map<String, Currency> currencies = client.getCurrencies(null);

// get some symbols
List<String> symbolIDs = new ArrayList<String>(Arrays.asList("eoseth","ethbtc"));
Map<String, Symbol> symbols = client.getSymbols(symbolIDs);

// get an order book
OrderBook  orderbook = client.getOrderbookOfSymbol("eoseth");

// get some candles
List<String> symbols = new ArrayList<String>(Arrays.asList("eoseth", "ethbtc"));
Map<String, List<Candle>> candles = client.getCandles(symbols, Period._4_HOURS, Sort.ASC, null, null, null);

// get your account balances
List<Balance> balances = client.getAccountBalance();

// get your trading balances
List<Balance> balances = client.getTradingBalance();

// move balance from account to trading
String result = client.transferBetweenTradingAndAccountBalance('eth', '3.2', TransferType.BANK_TO_EXCHANGE);

// get your active orders
List<Order> orders = client.getActiveOrders('eoseth');


// create a new order
Order order = client.createOrder(new ParamsBuilder()
            .symbol("EOSETH")
            .side(Side.SELL)
            .quantity("2")
            .price("100000")
            .timeInForce(TimeInForce.DAY));
```

## Using the ParamsBuilder

most client methods have a version that accepts a ParamBuilder. This class makes easier to pass parameters.

```java
import com.cryptomarket.params.ParamsBuilder;

// get candles
Map<String, List<Candle>> candles client.getCandles(new ParamsBuilder()
          .symbols(symbols)
          .period(Period._4_HOURS)
          .sort(Sort.ASC));
```

## exception handling

```java

// rest exceptions
CryptomarketRestClient client = new CryptomarketRestClientImpl(api_key, api_secret);

// all rest client methods can throw a CryptomarketSDKException
try {
    List<String> symbolIds = new ArrayList<String>(Arrays.asList("eoseth","ethbtc"));
    List<Ticker> tickers = client.getTickers(symbolIds);
    assertTrue(tickers.size() == 2);
} catch (CryptomarketApiException e) {
    e.printStackTrace();
}

```

## websocket client

There are three websocket clients, the market data client, the spot trading client and the wallet client.
The market data client requires no authentication, while the spot trading client and the wallet client do require it.

All websocket methods accept a `BiConsumer` with the first argument being the resulting data of the request, and the second
argument a possible exception of type `CryptomarketSDKException`. If there is an exception the data is null: `(null, exception) -> {...}`.nd if
there is no exception, the exception argument is null: `(data,null) -> {...}`

websocket subscriptions take a second `BiConsumer` that takes the subscription data as the first argument, and the notification type as the second. The notification type is of type NotificationType, and is either SNAPSHOT, NOTIFICATION or DATA. there are functions to test the type of notification:

```java
BiConsumer<List<Report>, NotificationType> biConsumerExample = (data, notificationType) -> {
  if (notificationType.isSnapshot()) {
    System.out.println("is a snapshot notification");
  }
  if (notificationType.isUpdate()) {
    System.out.println("is a update notification");
  }
  if (notificationType.isData()) {
    System.out.println("is a data notification");
  }
}
```

The documentation of a specific subscriptions explains with of this types of
notification uses.

### MarketDataClient

Example of use of the market data client

```java
// instantiate a market data client
CryptomarketWSMarketDataClient marketDataClient = CryptomarketWSMarketDataClientImpl();

// make a partial orderbook subscription
//    prepare args
List<String> symbols = Arrays.asList("EOSETH");

//    make subscription
marketDataClient.subscribeToPartialOrderBook(
    (partialOrderbookMap, notificationType) -> {
      if (notificationType.isData()) {
        System.out.println("this subscription only recieves data notifications");
      }
      partialOrderbookMap.forEach((symbol, orderbook) -> {
        System.out.println("symbol:" + symbol);
        System.out.println(orderbook);
      });
    },
    Depth._5,
    OBSpeed._500_MILISECONDS,
    symbols,
    null);


```

### SpotTradingClient

Example of use of the spot trading client

```java
// instantiate a spot trading websocket client
String apiKey = "AB32B3201";
String apiSecret= "21b12401";
CryptomarketWSSpotTradingClient tradingClient = new CryptomarketWSSpotTradingClientImpl(apiKey, apiSecret);

// get all the spot trading balances
tradingClient.getSpotTradingBalances(
  (balanceList, exception) -> {
    if (exception != null) {
      System.out.println("something went wrong: "+ exception.toString());
      return;
    }
    balanceList.forEach(System.out::println);
  });

tradingClient.createSpotOrder(
      new ParamsBuilder()
          .side(Side.SELL)
          .symbol("EOSETH")
          .price("10000")
          .quantity("0.01")
          .clientOrderID("123123123123"),
      (report, exception) -> {
        if (exception != null) {
          System.out.println("something went wrong: "+ exception.toString());
          return;
        }
        System.out.println("new order created: " + report.toString());
      });
```

### WalletClient

Example of use of the wallet client

```java
// instantiate a wallet websocket client with a window of 20 seconds
CryptomarketWSWalletClient walletClient = new CryptomarketWSWalletClientImpl(apiKey, apiSecret, 20000);

// get a list of transactions
walletClient.getTransactions((transactionList, exception) -> {
    if (exception != null) {
      System.out.println("something went wrong: "+ exception.toString());
      return;
    }
    transactionList.forEach(System.out::println);
  }, null);

// subscribe to a feed of transactions
walletClient.subscribeToTransactions(
  (data, nType) -> {
    if (notificationType.isUpdate()) {
        System.out.println("this subscription only recieves update notifications");
      }
    data.forEach(transaction -> System.out.println(transaction));
  }, (subscriptionResult, exception) -> {
    if (exception != null) {
      System.out.println("subscription failed");
    }
    if (!result) {
      System.out.println("subscription failed");
    }
  });
```

# Constants

All constants required for calls are in `com.cryptomarket.sdk.params`.
each enum has the name of the argument that needs it.

# Checkout our other SDKs

<!-- agregar links -->

[node sdk](https://github.com/cryptomkt/cryptomkt-node)

[ruby sdk](https://github.com/cryptomkt/cryptomkt-ruby)

[go sdk](https://github.com/cryptomkt/cryptomkt-go)

[python sdk](https://github.com/cryptomkt/cryptomkt-python)
