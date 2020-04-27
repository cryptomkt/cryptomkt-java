# cryptomkt-java

- [cryptomkt-java](#cryptomkt-java)
  - [Installation](#installation)
  - [Quick Start](#quick-start)
  - [API Key](#api-key)
  - [Making API Calls](#making-api-calls)
    - [Public endpoints](#public-endpoints)
      - [Using pagination](#using-pagination)
      - [Listing available markets](#listing-available-markets)
      - [Obtain Book](#obtain-book)
      - [Obtain ticker info](#obtain-ticker-info)
      - [Obtain Trades](#obtain-trades)
    - [Authenticated endpoints](#authenticated-endpoints)
      - [Get account info](#get-account-info)
      - [Create market order](#create-a-market-order)
      - [Create limit order](#create-a-limit-order)
      - [Create multiple orders](#create-multiple-orders)
      - [Obtain active orders](#obtain-active-orders)
      - [Cancel an order](#cancel-an-order)
      - [Cancel multiple orders](#cancel-multiple-orders)
      - [Obtain executed orders](#obtain-executed-orders)
      - [Obtain order status](#obtain-order-status)
      - [Make a transfer](#make-a-transfer)
      - [Obtain account balance](#obtain-account-balance)
  - [Using socket](#using-socket)
    - [Get socket instance](#get-socket-instance)
    - [Receive socket events](#receive-socket-events)
    - [Consumer examples](#consumer-examples)
      - [Market subscription](#market-subscription)
      - [Unsubscribe from market](#unsubscribe-from-market)
      - [Receive open book info](#receive-open-book-info)
      - [Receive historical book info](#receive-historical-book-info)
      - [Receive candles info](#receive-candles-info)
      - [Receive ticker info](#receive-ticker-info)
      - [Receive balance info](#receive-balance-info)
      - [Receive user orders info](#receive-user-orders-info)
      - [Receive historical user orders info](#receive-historical-user-orders-info)
      - [Receive user operated volume](#receive-user-operated-volume)


## Installation
Maven
```
<dependency>
    <groupId>com.cryptomkt.api</groupId>
    <artifactId>cryptomarket</artifactId>
    <version>1.0.4</version>
</dependency>
```

Gradle
```
compile group: 'com.cryptomkt.api', name: 'cryptomarket', version: '1.0.4'
```

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
Each `client` method returns an ``object`` representing the JSON response from the API.

[(top &uarr;)](#cryptomkt-java)

### Public endpoints

#### Using pagination
The following code example shows the use of pagination

```java
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
```

<details>
 <summary>Expected Output</summary>
 
```
status: success
Pagination{previous=0, page=0, next=1, limit=5}
Candle{id='78625', candleDate='2020-02-01 00:00', openPrice='48.85', HighPrice='70', ClosePrice='68.4', LowPrice='47.9', volumeSum='47.9', tickCount='14617'}
Candle{id='78624', candleDate='2020-01-01 00:00', openPrice='35.45', HighPrice='51.15', ClosePrice='48.9', LowPrice='33.55', volumeSum='33.55', tickCount='22055'}
Candle{id='78623', candleDate='2019-12-01 00:00', openPrice='48.4', HighPrice='48.6', ClosePrice='35.4', LowPrice='32.65', volumeSum='32.65', tickCount='17617'}
Candle{id='78622', candleDate='2019-11-01 00:00', openPrice='48.25', HighPrice='66.25', ClosePrice='48.45', LowPrice='45', volumeSum='45', tickCount='19425'}
Candle{id='78621', candleDate='2019-10-01 00:00', openPrice='47.05', HighPrice='51', ClosePrice='48.45', LowPrice='42.05', volumeSum='42.05', tickCount='23509'}
status: success
Pagination{previous=0, page=1, next=2, limit=5}
Candle{id='78620', candleDate='2019-09-01 00:00', openPrice='45.9', HighPrice='65.05', ClosePrice='46.85', LowPrice='40.1', volumeSum='40.1', tickCount='23412'}
Candle{id='78619', candleDate='2019-08-01 00:00', openPrice='59.2', HighPrice='60.85', ClosePrice='45.9', LowPrice='43.8', volumeSum='43.8', tickCount='13216'}
Candle{id='78618', candleDate='2019-07-01 00:00', openPrice='74.2', HighPrice='76.75', ClosePrice='59.25', LowPrice='52.9', volumeSum='52.9', tickCount='33189'}
Candle{id='78617', candleDate='2019-06-01 00:00', openPrice='97.8', HighPrice='100', ClosePrice='73.85', LowPrice='72.6', volumeSum='72.6', tickCount='27806'}
Candle{id='78616', candleDate='2019-05-01 00:00', openPrice='68.35', HighPrice='112.85', ClosePrice='97.65', LowPrice='58.2', volumeSum='58.2', tickCount='51310'}
status: success
Pagination{previous=1, page=2, next=3, limit=5}
Candle{id='78615', candleDate='2019-04-01 00:00', openPrice='71.95', HighPrice='90', ClosePrice='68.4', LowPrice='63', volumeSum='63', tickCount='41274'}
Candle{id='78614', candleDate='2019-03-01 00:00', openPrice='56.45', HighPrice='80', ClosePrice='72', LowPrice='52.3', volumeSum='52.3', tickCount='41335'}
Candle{id='78613', candleDate='2019-02-01 00:00', openPrice='55.4', HighPrice='64.25', ClosePrice='56.5', LowPrice='47.4', volumeSum='47.4', tickCount='40716'}
Candle{id='78612', candleDate='2019-01-01 00:00', openPrice='80.75', HighPrice='89.7', ClosePrice='55.4', LowPrice='53.5', volumeSum='53.5', tickCount='35194'}
Candle{id='78611', candleDate='2018-12-01 00:00', openPrice='109.5', HighPrice='116.65', ClosePrice='80.55', LowPrice='63', volumeSum='63', tickCount='32701'}
status: success
Pagination{previous=2, page=3, next=4, limit=5}
Candle{id='78610', candleDate='2018-11-01 00:00', openPrice='152.5', HighPrice='192', ClosePrice='109.55', LowPrice='88.55', volumeSum='88.55', tickCount='44260'}
Candle{id='78609', candleDate='2018-10-01 00:00', openPrice='174.75', HighPrice='176.5', ClosePrice='152.95', LowPrice='135', volumeSum='135', tickCount='12539'}
Candle{id='78608', candleDate='2018-09-01 00:00', openPrice='152.7', HighPrice='189', ClosePrice='174.7', LowPrice='130', volumeSum='130', tickCount='15744'}
Candle{id='78607', candleDate='2018-08-01 00:00', openPrice='184.85', HighPrice='189', ClosePrice='152.7', LowPrice='130.5', volumeSum='130.5', tickCount='24708'}
Candle{id='78606', candleDate='2018-07-01 00:00', openPrice='129.3', HighPrice='225', ClosePrice='185.45', LowPrice='121.1', volumeSum='121.1', tickCount='28120'}
status: success
Pagination{previous=3, page=4, next=5, limit=5}
Candle{id='78605', candleDate='2018-06-01 00:00', openPrice='186.6', HighPrice='197', ClosePrice='130.25', LowPrice='111.55', volumeSum='111.55', tickCount='41449'}
Candle{id='78604', candleDate='2018-05-01 00:00', openPrice='800', HighPrice='800', ClosePrice='189.5', LowPrice='150', volumeSum='150', tickCount='2158'}
Candle{id='78603', candleDate='2018-04-01 00:00', openPrice='124', HighPrice='148', ClosePrice='124', LowPrice='91', volumeSum='91', tickCount='1248'}
Candle{id='78602', candleDate='2018-03-01 00:00', openPrice='208', HighPrice='249', ClosePrice='123', LowPrice='69', volumeSum='69', tickCount='8491'}
Candle{id='78601', candleDate='2018-02-01 00:00', openPrice='5000', HighPrice='5000', ClosePrice='210', LowPrice='199', volumeSum='199', tickCount='6373'}
status: success
Pagination{previous=4, page=5, next=0, limit=5}
````

</details>

[(top &uarr;)](#cryptomkt-java)

#### Listing available markets
```java
try {
    List<String> markets = client.getMarkets().getMarkets();
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
[
Book{
     timestamp=Sun Apr 19 19:21:10 CLT 2020,
     price=156300.0,
     amount=3.0798
}, Book{
     timestamp=Sun Apr 19 19:21:00 CLT 2020,
     price=156280.0,
     amount=27.6503
}, Book{
     timestamp=Sun Apr 19 18:57:57 CLT 2020,
     price=156200.0,
     amount=7.9244
}, Book{
     timestamp=Sun Apr 19 19:19:01 CLT 2020,
     price=155940.0,
     amount=3.9802
}, ...]
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
[
Ticker{
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
[
Trade{
    market='ETHARS', 
    timestamp=Sun Apr 19 19:22:27 CLT 2020, 
    marketTaker='buy', 
    price=17972.0, 
    amount=1.9888
}, Trade{
    market='ETHARS', 
    timestamp=Sun Apr 19 18:34:21 CLT 2020, 
    marketTaker='buy', 
    price=17972.0, 
    amount=0.5514
}, Trade{
    market='ETHARS', 
    timestamp=Sun Apr 19 17:45:23 CLT 2020, 
    marketTaker='buy', 
    price=17972.0, 
    amount=0.011017137769864233
}, ...]
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

#### Create a market order
```java
//  the 'side' argument can be either "sell" or "buy", also the 'type' argument can be "limit", "stop-limit" or "market". 
//  If you want to execute an 'stop-limit' order you need to specify the "limit" amount.
try {
    Order order = client.createMarketOrder("XLMCLP" "sell", "1")
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
    id='O6560362', 
    status='filled', 
    type='market', 
    side='sell', 
    stop='null', 
    fee='0', 
    price='0', 
    amount=Amount{original=1.0, executed=1.0}, 
    avgExecutionPrice=0.0, 
    market='XLMCLP', 
    createdAt=Mon Apr 27 13:35:13 CLT 2020, 
    updatedAt=Mon Apr 27 13:35:13 CLT 2020, 
    fillers=null
}
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Create a limit order
```java
//  the 'side' argument can be either "sell" or "buy", also the 'type' argument can be "limit", "stop-limit" or "market". 
//  If you want to execute an 'stop-limit' order you need to specify the "limit" amount.
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
    id='O6560492', 
    status='queued', 
    type='limit', 
    side='sell', 
    stop='null', 
    fee='0', 
    price='120', 
    amount=Amount{original=1.0, executed=0.0}, 
    avgExecutionPrice=0.0, 
    market='XLMCLP', 
    createdAt=Mon Apr 27 13:36:45 CLT 2020, 
    updatedAt=Mon Apr 27 13:36:45 CLT 2020, 
    fillers=null
}
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Create multiple orders
```java
try {
    MultiOrderRequest multiOrderRequest = new MultiOrderRequest()
                        .addMarketOrder("XLMCLP", "sell", "1")
                        .addLimitOrder("XLMCLP", "buy", "1", "20")
                        .addLimitOrder("AAAAAA", "sell", "1", "120");
    CreateMultiOrderResponse createMultiOrderResponse = client.createMultiOrders(multiOrderRequest);
    System.out.println(createMultiOrderResponse);
} catch (CryptoMarketException e) {
    e.printStackTrace();
}
```
<details>
 <summary>Expected Output</summary>
 
```
CreateMultiOrderResponse{
Response{status='success', message='null', pagination=null}, 
data=CreateMultiOrderData{
created=[
    CreatedOrder{
        data=Order{
            id='O6572150', 
            status='filled', 
            type='market', 
            side='sell', 
            stop='null', 
            fee='0', 
            price='0', 
            amount=Amount{original=1.0, executed=1.0}, 
            avgExecutionPrice=0.0, 
            market='XLMCLP', 
            createdAt=Mon Apr 27 16:35:41 CLT 2020, 
            updatedAt=Mon Apr 27 16:35:41 CLT 2020, 
            fillers=null}, 
        original=OrderDescription{amount='1', market='XLMCLP', price='null', side='sell', type='market'}
    }, CreatedOrder{
        data=Order{
            id='O6572151', 
            status='queued', 
            type='limit', 
            side='buy', 
            stop='null', 
            fee='0', 
            price='20', 
            amount=Amount{original=1.0, executed=0.0}, 
            avgExecutionPrice=0.0, 
            market='XLMCLP', 
            createdAt=Mon Apr 27 16:35:41 CLT 2020, 
            updatedAt=Mon Apr 27 16:35:41 CLT 2020, 
            fillers=null}, 
        original=OrderDescription{amount='1', market='XLMCLP', price='20', side='buy', type='limit'}
    }], notCreated=[
NotCreatedOrder{
    message='pair_not_active', 
    order=OrderDescription{amount='1', market='AAAAAA', price='120', side='sell', type='limit'}}]}}

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
[
Order{
    id='O6572151', 
    status='queued', 
    type='limit', 
    side='buy', 
    stop='null', 
    fee='0', 
    price='20', 
    amount=Amount{original=1.0, executed=0.0}, 
    avgExecutionPrice=0.0, 
    market='XLMCLP', 
    createdAt=Mon Apr 27 16:35:41 CLT 2020, 
    updatedAt=Mon Apr 27 16:35:41 CLT 2020, 
    fillers=null
}, Order{
    id='O6572090', 
    status='queued', 
    type='limit', 
    side='sell', 
    stop='null', 
    fee='0', 
    price='120', 
    amount=Amount{original=1.0, executed=0.0}, 
    avgExecutionPrice=0.0, 
    market='XLMCLP', 
    createdAt=Mon Apr 27 16:34:49 CLT 2020, 
    updatedAt=Mon Apr 27 16:34:49 CLT 2020, 
    fillers=null
}, ...]
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
    id='O6326637', 
    status='cancelled', 
    type='limit', 
    side='sell', 
    stop='null', 
    fee='0', 
    price='120', 
    amount=Amount{original=1.0, executed=0.0}, 
    avgExecutionPrice=0.0, 
    market='XLMCLP', 
    createdAt=Fri Apr 24 21:46:44 CLT 2020, 
    updatedAt=Mon Apr 27 14:15:23 CLT 2020, 
    fillers=null
}
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
Response{status='success', message='null', pagination=null}, 
data=CancelMultiOrderData{
    canceledOrders=[
        CanceledOrder{
            data=Order{id='O6572151', status='cancelled', ...}, 
            orderId='O6572151'
        }, CanceledOrder{
            data=Order{id='O6572090', status='cancelled', ...}, 
            orderId='O6572090'
        }, CanceledOrder{
            data=Order{id='O6572089', status='cancelled', ...}, 
            orderId='O6572089'
        }, ...], 
    notCanceledOrders=[
        NotCanceledOrder{
            message='invalid_request', 
            orderId='11111111'}]}}

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
[
Order{
    id='O6572150', 
    status='filled', 
    type='market', 
    side='sell', 
    stop='null', 
    fee='0.374', 
    price='0', 
    amount=Amount{original=1.0, executed=1.0}, 
    avgExecutionPrice=55.0, 
    market='XLMCLP', 
    createdAt=Mon Apr 27 16:35:41 CLT 2020, 
    updatedAt=Mon Apr 27 16:35:42 CLT 2020, 
    fillers=[
        Filler{price='55', fee='0.374', amount='55', date=Mon Apr 27 16:35:41 CLT 2020}
    ]
}, Order{
    id='O6572086', 
    status='filled', 
    type='market', 
    side='sell', 
    stop='null', 
    fee='0.374', 
    price='0', 
    amount=Amount{original=1.0, executed=1.0}, 
    avgExecutionPrice=55.0, 
    market='XLMCLP', 
    createdAt=Mon Apr 27 16:34:48 CLT 2020, 
    updatedAt=Mon Apr 27 16:34:49 CLT 2020, 
    fillers=[
        Filler{price='55', fee='0.374', amount='55', date=Mon Apr 27 16:34:48 CLT 2020}
    ]
}, ...]
```
</details>

[(top &uarr;)](#cryptomkt-java)

#### Obtain order status
```java
try {
    Order order = client.getOrderStatus("O6572150")
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
    id='O6572150', 
    status='filled', 
    type='market', 
    side='sell', 
    stop='null', 
    fee='0.374', 
    price='0', 
    amount=Amount{original=1.0, executed=1.0}, 
    avgExecutionPrice=55.0, 
    market='XLMCLP', 
    createdAt=Mon Apr 27 16:35:41 CLT 2020, 
    updatedAt=Mon Apr 27 16:35:42 CLT 2020, 
    fillers=[
        Filler{price='55', fee='0.374', amount='55', date=Mon Apr 27 16:35:41 CLT 2020}
    ]
}
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
[
Balance{
    wallet='CLP', 
    available='120249.25638', 
    balance='100249.25638'
}, Balance{
    wallet='ETH', 
    available='10.3399', 
    balance='11.3399'
}, ...]
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
All socket events receive a consumer to handle the event. this consumers must 
implement the accept method for a JSONObject argument. There are many ways to
implement the consumers. some examples are given in the following code. 

### Consumer examples
```java
// a class consumer of the event data
class  CandleConsumer implements Consumer<JSONObject> {
    @Override
    public void accept(JSONObject jsonObject) {
        System.out.println("class consumer "+jsonObject);
    }
}
socket.onCandles(new CandleConsumer<>());

// a lambda expression consumer for the event data
socket.onCandles(data -> System.out.println("lambda consumer "+data));

//and the method reference consumer
socket.onCandles(System.out::println);

socket.subscribe("ETHARS");
```
<details>
 <summary>Expected Output</summary>
 
```
class consumer {"ETHARS":{"buy":{"10080":[{"date":"17/02/2020 00:00:00",...
{"ETHARS":{"buy":{"10080":[{"date":"17/02/2020 00:00:00",...
lambda consumer {"ETHARS":{"buy":{"10080":[{"date":"17/02/2020 00:00:00",...
```
</details>

[(top &uarr;)](#cryptomkt-java)

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

#### Receive historical book info
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
```java
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

```java
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

#### Receive user operated volume

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
