# cryptomkt-java

- [Installation](#Installation)
- [Quick Start](#Quick-Start)
- [API Key](#API-Key)
- [Making API Calls](#Making-API-Calls)
  - [Public Endpoints](#Using-the-functions)
    - [Listing available markets](#Listing-available-markets)
    - [Obtain Book](#Obtain-Book)
    - [Obtain ticker info](#Obtain-ticker-info)
  - [Authenticated endpoints](#Authenticated-endpoints)
    - [Get account info](#Get-account-info)
    - [Create an order](#Create-an-order)
    - [Create multiple orders](#Create-multiple-orders)
    - [Obtain active orders](#Obtain-active-orders)
    - [Cancel an order](#Cancel-an-order)
    - [Cancel multiple orders](#Cancel-multiple-orders)
    - [Make a transfer](#Make-a-transfer)
    - [Obtain executed orders](#Obtain-executed-orders)
    - [Obtain order status](#Obtain-order-status)
    - [Obtain account balance](#Obtain-account-balance)
- [Using socket](#Using-socket)
  - [Get socket instance](#Get-socket-instance)
  - [Receive socket events](#Receive-socket-events)
    - [Market subscription](#Market-subscription)
    - [Unsubscribe from market](#Unsubscribe-from-market)
    - [Receive open book info](#Receive-open-book-info)
    - [Receive Historical book info](#Receive-Historical-book-info)
    - [Receive candles info](#Receive-candles-info)
    - [Receive ticker info](#Receive-ticker-info)
    - [Receive balance info](#Receive-balance-info)
    - [Receive user orders info](#Receive-user-orders-info)
    - [Receive historical user orders info](#Receive-historical-user-orders-info)
    - [Receive User´s operated volume](#Receive-Users-operated-volume)


## Installation

`npm install cryptomarket`

## Quick Start

The first thing you'll need to do is [sign up for cryptomkt](https://www.cryptomkt.com).

## API Key

If you're writing code for your own CryptoMarket account, 
[enable an API key](https://www.cryptomkt.com/platform/account#api_tab). 
Next, create a ``Client`` object for interacting with the API:


```java
import com.cryptomkt.api.Client;
import com.cryptomkt.api.ClientImpl;

class Example {
    Client client;

    Example() {
        client = new ClientImpl(apiKey, apiSecret);
    }
}
```

## Making API Calls

With a `client instance`, you can now make API calls. We've included some examples below.  
Each API method returns an ``object`` representing the JSON response from the API.

[(top &uarr;)](#cryptomkt-java)

### Public endpoints

#### Using the functions
Data from cryptomarket usually has useful metadata associated to it, data as 
the success of the operation, the page number from the query, and the number 
of entries per page.

Every call to an endpoint of cryptomarket is an instance of a class that 
inherit from the Response class. The Response class provides this methods for 
getting the status and the pagination (getStatus() and getPagination()).
The actual data requested is accessed via a getter method over the response, 
depending on which class is, how is called the endpoint.

The following code examples show the way to get the requested data. 

```java
class Example {
    Client client = new ClientImpl(apiKey, apiSecret);

    void printPrices() {
        try {
            PricesResponse response = client.getPrices("XLMCLP", "240", 1, 5);
            System.out.println("status: "+ response.getStatus());
            if (response.isSuccess()) {
                Prices prices = response.getPrices();
                List<Candle> askCandles = prices.getAsk();
                for (Candle candle: askCandles) {
                    System.out.println("date "+candle.getCandleDate()+ " - open price "+candle.getOpenPrice());
                }
            }
        } catch (CryptoMarketException e) {
            e.printStackTrace();
        }
    }
}
Example example = new Example();
example.printPrices();
```
##### Expected output
```javascript
//  calling printMarket() from the example gives (depending on the )

status: success
date 2020-02-14 16:00 - open price 66.25
date 2020-02-14 12:00 - open price 67.75
date 2020-02-14 08:00 - open price 65.35
date 2020-02-14 04:00 - open price 66.3
date 2020-02-14 00:00 - open price 67
````
[(top &uarr;)](#cryptomkt-java)

#### Listing available markets
```java
try {
    List<Market> markets = client.getMarkets().getMarkets();
    System.out.println(markets);
} catch (CryptoMarketException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
[ETHCLP,
 ETHARS,
 ETHEUR,
 ETHBRL,
 ETHMXN,
 XLMCLP,
 XLMARS,
 XLMEUR,
...
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Obtain Book
```java
try {
    List<Book> books = client.getBook("ETHCLP", "buy").getBooks();
    System.out.println(books);
} catch (CryptoMarketException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
[Book{timestamp=Sun Apr 19 19:21:10 CLT 2020,
     price=156300.0,
     amount=3.0798}, 
Book{timestamp=Sun Apr 19 19:21:00 CLT 2020,
     price=156280.0,
     amount=27.6503}, 
Book{timestamp=Sun Apr 19 18:57:57 CLT 2020,
     price=156200.0,
     amount=7.9244}, 
Book{timestamp=Sun Apr 19 19:19:01 CLT 2020,
     price=155940.0,
     amount=3.9802}, 
...
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Obtain ticker info

```java
try {
    List<Ticker> tickers = client.getTickers("BTCBRL").getTickers();
    System.out.println(tickers);
} catch (Exception e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
[Ticker{
    market='BTCBRL', 
    timestamp=Sun Apr 19 19:34:15 CLT 2020, 
    ask=37942.0, 
    high=37530.0, 
    bid=36870.0, 
    low=36652.0, 
    lastPrice=36864.0, 
    volume=0.018068926477910262}]
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Obtain Trades

```java
try {
    List<Trade> trades = client.getTrades("ETHARS").getTrades();
    System.out.println(trades);

} catch (Exception e) {
    e.printStackTrace();
}
```

<details>
 <summary>Expected Output</summary>
 
```
[Trade{
    market='ETHARS', 
    timestamp=Sun Apr 19 19:22:27 CLT 2020, 
    marketTaker='buy', 
    price=17972.0, 
    amount=1.9888}, 
Trade{
    market='ETHARS', 
    timestamp=Sun Apr 19 18:34:21 CLT 2020, 
    marketTaker='buy', 
    price=17972.0, 
    amount=0.5514}, 
Trade{
    market='ETHARS', 
    timestamp=Sun Apr 19 17:45:23 CLT 2020, 
    marketTaker='buy', 
    price=17972.0, 
    amount=0.011017137769864233}
...
```
</details>

[(top &uarr;)](#cryptomkt-java)

### Authenticated endpoints

#### Get account info
```java
try {
    Account account = client.getAccount().getAccount();
    System.out.println(account.toString());
} catch (Exception e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
Account{
    name='Jhon Doe', 
    email='jhon.doe@email.com', 
    rate=Rate{marketMaker='0.0039', marketTaker='0.0068'}, 
    bankAccounts=[
        BankAccount{
            id='12345', 
            bank='BANCO DEL ESTADO DE CHILE', 
            description='', 
            country='CL', 
            number='12345678'}]}
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Create an order
&$TODO$& explicación de los diferentes tipos de órdenes. 
```java
try {
    Order order = client.createOrder("XLMCLP", "124", "sell", "limit", "1")
            .getOrder();
    System.out.println(order);
} catch (CryptoMarketException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
Order{
    id='O5792153', 
    status='queued', 
    type='limit',
    side='sell', 
    price=124.0, 
    amount=Amount{
        original=1.0, 
        remaining=null, 
        executed=0.0}, 
    executionPrice=null, 
    avgExecutionPrice=0.0, 
    market='XLMCLP', 
    createdAt=Sun Apr 19 19:39:59 CLT 2020, 
    updatedAt=Sun Apr 19 19:39:59 CLT 2020, 
    executedAt=null}

```
</details>

[(top &uarr;)](#cryptomkt-java)


#### Create multiple orders
```javascript
//  Receives object array that contains multiple orders. "market", "type", "side" and "amount" are mandatory.
//  Example: [{"amount": 1, "market": "XLMCLP", "price": 50.5, "type": "limit", "side": "sell"}, {Order2},...]

client.createMultiOrders(object, (err, output) => {
   if(err){
   console.log('error');
   }
   console.log(output);
});
```
<details>
 <summary>Expected Output</summary>
 
```
CreateMultiOrderResponse{
Response{status='success', error='null', pagination=null}, 
data=CreateMultiOrderData{
    created=[
        CreatedOrder{
            data=Order{
                id='O5795018', 
                status='queued', 
                type='limit', 
                side='sell', 
                price=150.0, 
                amount=Amount{original=1.0, remaining=null, executed=0.0}, 
                executionPrice=null, 
                avgExecutionPrice=0.0, 
                market='XLMCLP', 
                createdAt=Sun Apr 19 20:09:48 CLT 2020, 
                updatedAt=Sun Apr 19 20:09:48 CLT 2020, 
                executedAt=null}, 
            original=OrderDescription{amount='1', market='XLMCLP', price='150', side='sell', type='limit'}}, 
        CreatedOrder{
            data=Order{
                id='O5795019',
                ...}, 
            original=OrderDescription{...}},
    ...
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Obtain active orders
```java
try {
    List<Order> orders = client.getActiveOrders("XLMCLP")
            .getOrders();
    System.out.println(orders);
} catch (CryptoMarketException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
[Order{
    id='O5795020', 
    status='queued', 
    type='limit', 
    side='sell', 
    price=152.0, 
    amount=Amount{original=1.0, remaining=null, executed=0.0}, 
    executionPrice=null, 
    avgExecutionPrice=0.0, 
    market='XLMCLP', 
    createdAt=Sun Apr 19 20:09:48 CLT 2020, 
    updatedAt=Sun Apr 19 20:09:48 CLT 2020, 
    executedAt=null}, 
Order{
    id='O5795019', 
    status='queued', 
    type='limit', 
    side='sell', 
    price=151.0, 
    amount=Amount{original=1.0, remaining=null, executed=0.0}, 
    executionPrice=null, 
    avgExecutionPrice=0.0, 
    market='XLMCLP', 
    createdAt=Sun Apr 19 20:09:48 CLT 2020, 
    updatedAt=Sun Apr 19 20:09:48 CLT 2020, 
    executedAt=null}, 
...
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Cancel an order
```java
try {
    Order order = client.cancelOrder("O5597597")
            .getOrder();
    System.out.println(order);
} catch (CryptoMarketException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
Order{
    id='O5597597', 
    status='cancelled', 
    type='limit', 
    side='sell', 
    price=124.0, 
    amount=Amount{original=1.0, remaining=null, executed=0.0}, 
    executionPrice=null, 
    avgExecutionPrice=0.0, 
    market='XLMCLP', 
    createdAt=Fri Apr 17 18:55:38 CLT 2020, 
    updatedAt=Mon Apr 20 10:10:55 CLT 2020, 
    executedAt=null}

```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Cancel multiple orders

```java
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
```
<details>
 <summary>Expected Output</summary>
 
```
CancelMultiOrderResponse{
    Response{status='success', error='null', pagination=null}, 
    data=CancelMultiOrderData{
        canceledOrders=[
            CanceledOrder{
                data=Order{
                    id='O5795020', 
                    status='cancelled', 
                    type='limit', 
                    side='sell', 
                    price=152.0, 
                    amount=Amount{original=1.0, remaining=null, executed=0.0}, 
                    executionPrice=null, 
                    avgExecutionPrice=0.0, 
                    market='XLMCLP', 
                    createdAt=Sun Apr 19 20:09:48 CLT 2020, 
                    updatedAt=Mon Apr 20 10:25:19 CLT 2020, 
                    executedAt=null}, 
                orderId='O5795020'}, 
            CanceledOrder{
                data=Order{
                    id='O5795019', 
                    status='cancelled', 
                    type='limit', 
                    side='sell', 
                    price=151.0, 
                    amount=Amount{original=1.0, remaining=null, executed=0.0}, 
                    executionPrice=null, 
                    avgExecutionPrice=0.0, 
                    market='XLMCLP', 
                    createdAt=Sun Apr 19 20:09:48 CLT 2020, 
                    updatedAt=Mon Apr 20 10:25:20 CLT 2020, 
                    executedAt=null}, 
                orderId='O5795019'},
            ... 
        notCanceledOrders=[
            NotCanceledOrder{
                message='invalid_request', 
                orderId='11111111'}]}}

```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Make a transfer

```java
try {
    Response result = client.transfer("ABCDEFGHI111ABABAB1111DCDC", "100", "XLM", "12345678");
    System.out.println(result);
} catch (CryptoMarketException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
Response{status='success', error='null', pagination=null}
```

</details>

[(top &uarr;)](#cryptomkt-java)

#### Obtain executed orders
```java
try {
    List<Order> orders = client.getExecutedOrders("XLMCLP")
            .getOrders();
    System.out.println(orders);
} catch (CryptoMarketException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
[Order{
    id='O2830196', 
    status='filled', 
    type='limit', 
    side='sell', 
    price=1.0, 
    amount=Amount{original=1.0, remaining=null, executed=1.0}, 
    executionPrice=null, 
    avgExecutionPrice=31.45, 
    market='XLMCLP', 
    createdAt=Wed Mar 18 19:20:00 CLST 2020, 
    updatedAt=Wed Mar 18 19:20:01 CLST 2020, 
    executedAt=null}, 
Order{
    id='O2826919', 
    status='filled', 
    type='limit', 
    side='sell', 
    price=1.0, 
    amount=Amount{original=1.0, remaining=null, executed=1.0}, 
    executionPrice=null, 
    avgExecutionPrice=31.7, 
    market='XLMCLP', 
    createdAt=Wed Mar 18 18:40:42 CLST 2020, 
    updatedAt=Wed Mar 18 18:40:42 CLST 2020, 
    executedAt=null}, 
...
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Obtain order status
```java
try {
    Order order = client.getOrderStatus("O5850367")
            .getOrder();
    System.out.println(order);
} catch (CryptoMarketException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
Order{
    id='O5850367', 
    status='queued', 
    type='limit', 
    side='sell', 
    price=152.0, 
    amount=Amount{original=1.0, remaining=null, executed=0.0}, 
    executionPrice=null, 
    avgExecutionPrice=0.0, 
    market='XLMCLP', 
    createdAt=Mon Apr 20 10:34:42 CLT 2020, 
    updatedAt=Mon Apr 20 10:34:42 CLT 2020, 
    executedAt=null}
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Obtain account balance
```java
try {
    List<Balance> balance = client.getBalance().getBalances();
    System.out.println(balance);
} catch (CryptoMarketException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>

```
[Balance{
    wallet='CLP', 
    available='120249.25638', 
    balance='100249.25638'}, 
Balance{
    wallet='ETH', 
    available='10.3399', 
    balance='11.3399'}, 
...
```
</details>

[(top &uarr;)](#cryptomkt-java)

## Using socket

Cryptomarket's API v2 integrates Socket.IO! You can now receive real-time events connecting to the sockets.

### Get socket instance

```java
Client client = new ClientImpl(apiKey, apiSecret);
try {
    Socket socket = client.getSocket();
} catch (CryptoMarketException e) {
    e.printStackTrace();
}
```
[(top &uarr;)](#cryptomkt-java)

### Receive socket events

#### Market subscription
```java
socket.subscribe("ETHCLP");
```

#### Unsubscribe from market
```java
socket.unsubscribe("ETHCLP");
```

#### Receive open book info
```java
//  Subscription required.
socket.subscribe("ETHCLP");
socket.onOpenBook(System.out::println);
try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
{"ETHCLP":
    {"buy":
        [{"side":1,
            "amount":"0.648000000000000000000000000000000000",
            "flag":"GENERAL",
            "kind":1,
            "type":2,
            "condition":null,
            "requestId":"OOETHCLP0000275921587394804702",
            "price":"154260.000000000000000000000000000000",
            "dateReceived":1587394804709,
            "stockId":"ETHCLP",
            "limit":null,
            "initAmount":"0.648000000000000000000000000000000000",
            "tradeId":"O5852254"},
        ...],
    "sell":
        [{"side":2,
            "amount":"0.0577000000000000000000000000000000000",
            "flag":"GENERAL",
            "kind":2,
            "type":2,
            "condition":null,
            "requestId":"OOETHCLP0001907651587394749892",
            "price":"156120.000000000000000000000000000000",
            "dateReceived":1587394749920,
            "stockId":"ETHCLP",
            "limit":null,
            "initAmount":"0.0577000000000000000000000000000000000",
            "tradeId":"O5852183"},
        ...]}}
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Receive Historical book info
```java
//  Subscription required.
socket.subscribe("ETHCLP");
socket.onHistoricalBook(System.out::println);
try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
{"ETHCLP":
    [
        {
            "side":1,
            "amount":"0.00000000000000000000000000000000000",
            "flag":"SIMPLE",
            "kind":1,
            "type":1,
            "executed_amount":"0.0384319754035357410000000000000000000",
            "condition":null,
            "executed_date":1587395341002,
            "requestId":"OOETHCLP0002059321587395340979",
            "price":"0.00000000000000000000000000000000000",
            "dateReceived":1587395340988,
            "stockId":"ETHCLP",
            "limit":null,
            "initAmount":"0.00000000000000000000000000000000000",
            "tradeId":"O5852956",
            "executed_price":"156120.000000000000000000000000000000"
        },
        {
            "side":1,
            "amount":"0.00000000000000000000000000000000000",
            "flag":"SIMPLE",
            "kind":1,
            "type":1,
            "executed_amount":"0.0319366377107818080000000000000000000",
            "condition":null,
            "executed_date":1587393804010,
            "requestId":"OOETHCLP0002117771587393803962",
            "price":"0.00000000000000000000000000000000000",
            "dateReceived":1587393803972,
            "stockId":"ETHCLP",
            "limit":null,
            "initAmount":"0.00000000000000000000000000000000000",
            "tradeId":"O5850932",
            "executed_price":"156560.000000000000000000000000000000"
        },...
    ]
}
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Receive candles info
```java
// Subscription required.
socket.subscribe("ETHCLP");
socket.onCandles(System.out::println);
try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
{
  'buy': {
    '1': [
        [{
      date: '21/02/2020 04:56:00',
      stockId: 'ETHCLP',
      type: 1,
      timeFrame: 1,
      lowPrice: 212060,
      hightPrice: 212060,
      openPrice: 212060,
      closePrice: 212100,
      count: 3,
      volume: 0,
      lastBuyPrice: 217900,
      lastSellPrice: 227220
    }],[Object],...],
  '5': [[Object],[Object],...],
  '15':[[Object],[Object],...],
  '60': [[Object],[Object],...],
  '240':[[Object],[Object],...],
  '1440':[[Object],[Object],...],
  '10080':[[Object],[Object],...],
  '44640':[[Object],[Object],...]
}

'sell':{
  '1':[[Object],...],
  '5':...
},
lastBuyPrice: 218880,
lastSellPrice: 227220
}
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Receive ticker info
```
socket.onTicker(System.out::println);
try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
{"EOSCLP": {
    "delta7d":9.588051072030837,
    "ASK":2334.5,
    "delta1d":0,
    "BID":2274.5},
"EOSEUR": {
    "delta7d":9.430438842203547,
    "ASK":2.408,
    "delta1d":-0.34013605442176903,
    "BID":2.344},
"EOSARS": { 
    "delta7d":11.665221162185606,
    "ASK":271.7,
    "delta1d":-0.5407493240633362,
    "BID":257.5},
...
```

</details>

[(top &uarr;)](#cryptomkt-java)

#### Receive balance info

```java
socket.onBalance(System.out::println);
try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
{
  "ETH": {
    "currency": "ETH",
    "countable": "0.0700000000000000000000000000000000000",
    "available": "0.0700000000000000000000000000000000000",
    "currency_kind": 1,
    "currency_name": "ETH",
    "currency_big_name": "Ether",
    "currency_prefix": "",
    "currency_postfix": "ETH",
    "currency_decimals": 4
  },
  ...
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Receive user orders info

```java
socket.onOpenOrders(System.out::println);
try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
{
    "data":[
        {
            "side":2,
            "amount":"1.00000000000000000000000000000000000",
            "flag":"GENERAL",
            "kind":2,
            "traderId":"205857",
            "type":2,
            "condition":null,
            "requestId":"OOXLMCLP0002058571587393282036",
            "price":"150.000000000000000000000000000000000",
            "dateReceived":1587393282056,
            "stockId":"XLMCLP",
            "limit":null,
            "initAmount":"1.00000000000000000000000000000000000",
            "tradeId":"O5850365"
        },
        {
            "side":2,
            "amount":"1.00000000000000000000000000000000000",
            "flag":"GENERAL",
            "kind":2,
            "traderId":"205857",
            "type":2,
            "condition":null,
            "requestId":"OOXLMCLP0002058571587393282204",
            "price":"151.000000000000000000000000000000000",
            "dateReceived":1587393282224,
            "stockId":"XLMCLP",
            "limit":null,
            "initAmount":"1.00000000000000000000000000000000000",
            "tradeId":"O5850366"
        },
        ...
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Receive historical user orders info

```
socket.onHistoricalOrders(System.out::println);
try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
{
    "data":[
        {
            "side":2,
            "amount":"1.00000000000000000000000000000000000",
            "flag":"GENERAL",
            "kind":2,
            "traderId":"205857",
            "type":2,
            "condition":null,
            "requestId":"OOXLMCLP0002058571584981672459",
            "price":"112.000000000000000000000000000000000",
            "dateReceived":1584981672475,
            "dateTriggered":null,
            "stockId":"XLMCLP",
            "limit":null,
            "initAmount":"1.00000000000000000000000000000000000",
            "tradeId":"O3297211"
        },
        {
            "side":2,
            "amount":"1.00000000000000000000000000000000000",
            "flag":"GENERAL",
            "kind":2,
            "traderId":"205857",
            "type":2,
            "condition":null,
            "requestId":"OOXLMCLP0002058571584981672363",
            "price":"111.000000000000000000000000000000000",
            "dateReceived":1584981672371,
            "dateTriggered":null,
            "stockId":"XLMCLP",
            "limit":null,
            "initAmount":"1.00000000000000000000000000000000000",
            "tradeId":"O3297210"
        },
        ...
```

</details>

[(top &uarr;)](#cryptomkt-java)

#### Receive User´s operated volume

```java
socket.onOperated(System.out::println);
```
<details>
 <summary>Expected Output</summary>

```
{
    "flag":"L0",
    "traded":"0.152359022359143420000000000000000000",
    "tk":"0.00680000000000000000000000000000000000",
    "threshold":"0.00000000000000000000000000000000000",
    "mk":"0.00390000000000000000000000000000000000"
}
```
</details>

[(top &uarr;)](#cryptomkt-java)
