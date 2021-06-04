package com.cryptomarket.sdk.websocket;

import java.util.List;

import com.cryptomarket.params.By;
import com.cryptomarket.params.Pagination;
import com.cryptomarket.params.Period;
import com.cryptomarket.params.Sort;
import com.cryptomarket.sdk.Callback;
import com.cryptomarket.sdk.models.Candle;
import com.cryptomarket.sdk.models.Currency;
import com.cryptomarket.sdk.models.OrderBook;
import com.cryptomarket.sdk.models.PublicTrade;
import com.cryptomarket.sdk.models.Symbol;
import com.cryptomarket.sdk.models.Ticker;

import org.jetbrains.annotations.Nullable;

/**
 * PublicClient connects via websocket to cryptomarket to get market information of the exchange.
 */
public interface CryptomarketWSPublicClient extends CryptomarketWS {

    /**
     * Get a list all available currencies on the exchange.
     * <p>
     * https://api.exchange.cryptomkt.com/#get-currencies
     * 
     * @param callback A Callback to call with the result data. 
     * 
     * @return A list of all available currencies.
     */
    public void getCurrencies(Callback<List<Currency>> callback);

    /**
     * Get the data of a currency.
     * <p>
     * https://api.exchange.cryptomkt.com/#get-currencies
     * 
     * @param currency A currency id.
     * @param callback A Callback to call with the result data. 
     * 
     * @return A currency.
     */
    public void getCurrency(String currency, Callback<Currency> callback);

    /**
     * Get a list of the specified symbols or all of them if no symbols are specified.
     * <p>
     * A symbol is the combination of the base currency (first one) and quote currency (second one).
     * <p>
     * https://api.exchange.cryptomkt.com/#get-symbols
     * 
     * @param callback A Callback to call with the result data. 
     * 
     * @return A list of currency symbols traded on the exchange.
     */
    public void getSymbols(Callback<List<Symbol>> callback);

    /**
     * Get a symbol by its id.
     * <p>
     * A symbol is the combination of the base currency (first one) and quote currency (second one).
     * <p>
     * https://api.exchange.cryptomkt.com/#get-symbols
     * 
     * 
     * @param symbol A symbol id.
     * @param callback A Callback to call with the result data. 
     * 
     * @return A symbol traded on the exchange.
     */
    public void getSymbol(String symbol, Callback<Symbol> callback);

    /**
     * Subscribe to a ticker of a symbol.
     * <p>
     * https://api.exchange.cryptomkt.com/#subscribe-to-ticker
     * <p>
     * @param symbol A symbol to subscribe.
     * @param callback A Callback to call with the result data. 
     * @param resultCallback Optional. A Callback to call with the subscription result. 
     * 
     * @return Tickers of the symbol.
     */
    public void subscribeToTicker(String symbol, Callback<Ticker> callback, @Nullable Callback<Boolean> resultCallback);

    /**
     * Unsubscribe to a ticker of a symbol.
     * <p>
     * https://api.exchange.cryptomkt.com/#subscribe-to-ticker
     * <p>
     * @param symbol The symbol to stop the ticker subscribption.
     * @param callback Optional. A Callback to call with the result data. 
     * 
     * @return The operation result.
     */
    public void unsubscribeToTicker(String symbol, @Nullable Callback<Boolean> callback);

    /**
     * Subscribe to the order book of a symbol.
     * <p>
     * An Order Book is an electronic list of buy and sell orders for a specific symbol, structured by price level.
     * <p>
     * https://api.exchange.cryptomkt.com/#subscribe-to-order-book
     * 
     * @param symbol The symbol of the orderbook.
     * @param callback A Callback to call with the result data. 
     * @param resultCallback A Callback to call with the subscription result. 
     * 
     * @return Order books of the symbol.
     */
    public void subscribeToOrderbook(String symbol, Callback<OrderBook> callback, @Nullable Callback<Boolean> resultCallback);

    /**
     * Unsubscribe to an order book of a symbol.
     * <p>
     * An OrderBook is an electronic list of buy and sell orders for a specific symbol, structured by price level.
     * <p>
     * https://api.exchange.cryptomkt.com/#subscribe-to-order-book
     * 
     * @param symbol The symbol of the orderbook.
     * @param callback Optional. A Callback to call with the result data. 
     * 
     * @return The operation result. true if success
     */
    public void unsubscribeToOrderbook(String symbol, @Nullable Callback<Boolean> callback);

    /**
     * Subscribe to the trades of a symbol.
     * <p>
     * https://api.exchange.cryptomkt.com/#subscribe-to-trades
     * <p>
     * @param symbol The symbol of the trades.
     * @param limit Optional. Maximum number of trades in the cache
     * @param callback A Callback to call with the result data. 
     * @param resultCallback Optional. A Callback to call with the subscription result. 
     * 
     * @return Trades of the symbol
     */
    public void subscribeToTrades(String symbol, @Nullable Integer limit, Callback<List<PublicTrade>> callback, @Nullable Callback<Boolean> resultCallback);

    /**
     * Unsubscribe to a trades of a symbol.
     * <p>
     * https://api.exchange.cryptomkt.com/#subscribe-to-trades
     * <p>
     * @param symbol The symbol of the trades.
     * @param callback Optional. A Callback to call with the result data. 
     * 
     * @return The operation result.
     */
    public void unsubscribeToTrades(String symbol, @Nullable Callback<Boolean> callback);

    /**
     * Get trades of the specified symbol.
     * <p>
     * https://api.exchange.cryptomkt.com/#get-trades
     * <p>
     * @param symbol The symbol to get the trades.
     * @param sort Optional. a Sort enum type. Sort.ASC (ascending) or Sort.DESC (descending). Default is Sort.DESC. sorting direction
     * @param by Optional. A By enum type. By.Id or By.Timestamp, default is By.TIMESTAMP, field to use for sorting
     * @param from Optional. Initial value of the queried interval. As id or as timestamp
     * @param till Optional. Last value of the queried interval. As id or as timestamp
     * @param limit Optional. Trades per query. Defaul is 100. Max is 1000
     * @param offset Optional. Default is 0. Max is 100000
     * @param callback A Callback to call with the result data. 
     * 
     * @return Trades information of the symbol.
     */
    public void getTrades(
        String symbol, 
        @Nullable Sort sort, 
        @Nullable By by, 
        @Nullable String from, 
        @Nullable String till, 
        @Nullable Integer limit, 
        @Nullable Integer offset, 
        Callback<List<PublicTrade>> callback);

    /**
     * Get trades of the specified symbol.
     * <p>
     * https://api.exchange.cryptomkt.com/#get-trades
     * 
     * @param symbol The symbol to get the trades
     * @param pagination Optional. A Pagination instance, buildable with new Pagination.Builder().someArg(arg).build()
     * @param callback A Callback to call with the result data. 
     * 
     * @return a list of trades
     */
    public void getTrades(String symbol, @Nullable Pagination pagination, Callback<List<PublicTrade>> callback);

    /**
     * Subscribe to the candles of a symbol, at the given period.
     * <p>
     * Candels are used for OHLC representation.
     * <p>
     * https://api.exchange.cryptomkt.com/#subscribe-to-candles
     * 
     * @param symbol A list of symbol ids.
     * @param period Optional. A Period enum type. default is Period._30_MINUTES, valid intervals are 1, 3, 5, 15 and 30 minutes, 1 and 4 hours, 1 and 7 days and 1 month.
     * @param limit Optional. Maximum number of candles in the snapshot
     * @param callback A Callback to call with the result data. 
     * @param resultCallback Optional. A callable to call with the subscription result. 
     * 
     * @return The candles of the symbol and the given period.
     */
    public void subscribeToCandles(
        String symbol,
        @Nullable Period period,
        @Nullable Integer limit,
        Callback<List<Candle>> callback, 
        @Nullable Callback<Boolean> resultCallback);

    /**
     * Unsubscribe to the candles of a symbol at a given period.
     * <p>
     * https://api.exchange.cryptomkt.com/#subscribe-to-candles
     * 
     * @param symbol The symbol of the candles.
     * @param period Optional. A Period enum type. default is Period._30_MINUTES, valid intervals are 1, 3, 5, 15 and 30 minutes, 1 and 4 hours, 1 and 7 days and 1 month.
     * @param callback Optional. A Callback to call with the result data. 
     * 
     * @return The operation result.
     */
    public void unsubscribeToCandles(String symbol, @Nullable Period period, @Nullable Callback<Boolean> callback);
}
