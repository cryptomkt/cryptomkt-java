# CryptoMarket-Java
[main page](https://www.cryptomkt.com/)


[sign up in CryptoMarket](https://www.cryptomkt.com/account/register).

# Installation
add the Maven dependency
```xml
<dependency>
    <groupId>...</groupId>
    <artifactId>...</artifactId>
    <version>...</version>
</dependency>
```
# Documentation

[The api documentation](https://api.exchange.cryptomkt.com/)


# Quick Start

## rest client
```java

// instance a client
String api_key = "AB32B3201";
String api_secret= "21b12401";
CryptomarketRestClient client = new CryptomarketRestClientImpl(api_key, api_secret);

// get all currencies
List<Currency> currencies = client.getCurrencies(null);

// get some symbols
List<String> symbolIds = new ArrayList<String>(Arrays.asList("eoseth","ethbtc"));
List<Symbol> symbols = client.getSymbols(symbolIds);

// get an order book
OrderBook  orderbook = client.getOrderBook("eoseth", 5);

// get some candles
List<String> symbols = new ArrayList<String>(Arrays.asList("eoseth", "ethbtc"));
Map<String, List<Candle>> candles = client.getCandles(symbols, Period._4_HOURS, null);

// get your account balances
List<Balance> balances = client.getAccountBalance();

// get your trading balances
List<Balance> balances = client.getTradingBalance();

// move balance from account to trading
String result = client.transferBetweenTradingAndAccountBalance('eth', '3.2', TransferType.BANK_TO_EXCHANGE);

// get your active orders
List<Order> orders = client.getActiveOrders('eoseth');


// create a new order
Order order = client.createOrder(new OrderRequest
            .Builder()
            .symbol("eos")
            .side(Side.SELL)
            .price("100000")
            .timeInForce(TimeInForce.DAY)
            .build());
```

## websocket client

All websocket calls work with a Callback class

There are three websocket clients, one for public request (CryptomarketWSPublicClient), one for trading request (CryptomarketWSTradingClient) and one for the account requests (CryptomarketWSAccountClient). The trading client and the account client must be authenticated before any other request.

```java

// instance a client
String apiKey = "AB32B3201";
String apiSecret= "21b12401";


CryptomarketWSPublicClient wsClient = new CryptomarketWSPublicClientImpl();

// get currencies
wsClient.getCurrency("EOS", new Callback<Currency>() {
    @Override
    public void resolve(Currency result) {
        System.out.println(result);
    }
});

CryptomarketWSTradingClient tradingClient = new CryptomarketWSTradingClientImpl(getApiKey, getApiSecret);


// get your trading balances
tradingClient.getTradingBalance(new Callback<List<Balance>>() {
    @Override
    public void resolve(List<Balance> result) {
        // and print my EOS balance
        result.forEach((Balance balance) -> {if (balance.getCurrency().equals("EOS")) System.out.println(balance);});
    }
});


// get your active orders
tradingClient.getActiveOrders(new Callback<List<Report>>() {
    @Override
    public void resolve(List<Report> result) {
        System.out.println(result);
    }
});

// create a new order
tradingClient.createOrder(new OrderRequest
    .Builder()
    .clientOrderId("123123123123")
    .symbol("ETHBTC")
    .side(Side.BUY)
    .quantity("10")
    .price("10")
    .build(),
    new Callback<Report>() {
        @Override
        public void resolve(Report result) {
            System.out.println(result);
        }
    });

CryptomarketWSAccountClient accountClient = new CryptomarketWSAccountClientImpl(getApiKey, getApiSecret);

// get your account Balance
accountClient.getAccountBalance(new Callback<List<Balance>>() {
    @Override
    public void resolve(List<Balance> result) {
        // and print my EOS balance
        result.forEach((Balance balance) -> {if (balance.getCurrency().equals("EOS")) System.out.println(balance);});
    }
});
```

### subscriptions

Subscriptions take two callbacks, one for the feed and one for the request itself

```java
CryptomarketWSPublicClient wsClient = new CryptomarketWSPublicClientImpl();

// get an order book feed, 
// feedCallback is for the subscription feed
// resultCallback is for the subscription result (success or failure)
String symbol = "ETHBTC";
Callback<Boolean> resultCallback = new Callback<Boolean>() {
    @Override
    public void resolve(Boolean result) {
        if (result) System.out.println(String.format("subscription to orderbook %s successfull", symbol));
        else System.out.println("subscription failed");
    }
};
Callback<OrderBook> feedCallback = new Callback<OrderBook>() {
    @Override
    public void resolve(OrderBook result) {
        // the orderbook feed
        System.out.println(result);
        }
    }
};
wsClient.subscribeToOrderbook(symbol, feedCallback, resultCallback);
```
## client lifetime hooks

The three websocket clients exposes the onConnect, onFailure, onClose and close methods to manage their lifetime.
onFailure is called when the authorization fails, and its default behaviour is to print the stack trace

```java
try {
    CryptomarketWSAccountClient wsClient;
    wsClient = new CryptomarketWSAccountClientImpl(KeyLoader.getApiKey(), KeyLoader.getApiSecret()) {
        @Override
        public void onClose(String reason) {
            System.out.println("closing");   
        }

        @Override
        public void onConnect(CryptomarketWSAccountClient client) {
            System.out.println("connected");
            client.getAccountBalance(new Callback<List<Balance>>() {
                @Override
                public void resolve(List<Balance> result) {System.out.println("request returning");}
    
                @Override
                public void reject(Throwable exception) {fail();}
            });
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {fail();}
            client.close();
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
```
## exception handling
```java

// rest exceptions
CryptomarketRestClient client = new CryptomarketRestClientImpl(api_key, api_secret);

// all rest client methods can throw a CryptomarketApiException
try {
    List<String> symbolIds = new ArrayList<String>(Arrays.asList("eoseth","ethbtc"));
    List<Ticker> tickers = client.getTickers(symbolIds);
    assertTrue(tickers.size() == 2);
} catch (CryptomarketApiException e) {
    e.printStackTrace();
}

// websocket exceptions
CryptomarketWSTradingClient wsClient = new CryptomarketWSTradingClientImpl(getApiKey, getApiSecret);

// all Callbacks can override the reject method
wsClient.authenticate(new Callback<Boolean>() {
    @Override
    public void resolve(Boolean result) {
        authenticated = result;
    }
    @Override
    public void reject(Throwable exception) {
        // exceptions from the api are given here
        exception.printStackTrace();
    }
});
```

# Constants

All constants required for calls are in `com.cryptomarket.sdk.params`.
each enum has the name of the argument that needs it.
Here is the full list
```java
import com.cryptomarket.params.*;

// use for candle intervals
Period._1_MINUTES;
Period._3_MINUTES;
Period._5_MINUTES;
Period._15_MINUTES;
Period._30_MINUTES;
Period._1_HOURS;
Period._4_HOURS;
Period._1_DAYS;
Period._7_DAYS;
Period._1_MONTHS;

// used for pagination sorting direction
Sort.DESC;
Sort.ASC;

// used for pagination field for sorting 
By.TIMESTAMP;
By.ID;

// used for creating orders
Side.BUY;
Side.SELL;

// used for creating orders
TimeInForce.GTC; // Good till canceled
TimeInForce.IOC; // Immediate or cancell
TimeInForce.FOK; // Fill or kill
TimeInForce.DAY; // Good for the day
TimeInForce.GTD; // Good till date

// used for creating orders
OrderType.LIMIT;
OrderType.MARKET;
OrderType.STOPLIMIT;
OrderType.STOPMARKET;

// used for transfer to another user
TransferBy.EMAIL;
TransferBy.USERNAME;
```
# OrderRequest
An OrderRequest is used to create orders. Here is an example with all posible parameters
```java
import com.cryptomarket.params.*;
OrderRequest request = new OrderRequest.Builder()
    .clientOrderId("123123123")
    .symbol("eoseth")
    .side(Side.SELL)
    .quantity("11111")
    .orderType(OrderType.STOPLIMIT)
    .price("123")
    .stopPrice("123")
    .timeInForce(TimeInForce.GDT)
    .expireTime("2020-12-16T17:30:00.000Z")
    .strictValidate(true)
    .postOnly(false)
    .build();
```

# Pagination
A Pagination is used to request some data. 
Here is an example with all posible parameters, sorting by id
```java
import com.cryptomarket.params.*;
new Pagination.Builder()
    .sort(Sort.ASC)
    .by(By.ID)
    .from("1000000000")
    .till("1000002000")
    .limit(20)
    .offset(40)
    .build()
```

# Checkout our other SDKs
<!-- agregar links -->
[node sdk](https://github.com/cryptomkt/cryptomkt-node)

[ruby sdk](https://github.com/cryptomkt/cryptomkt-ruby)

[go sdk](https://github.com/cryptomkt/cryptomkt-go)

[python sdk](https://github.com/cryptomkt/cryptomkt-python)