package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Candle;
import com.cryptomarket.sdk.models.Currency;
import com.cryptomarket.sdk.models.Order;
import com.cryptomarket.sdk.models.OrderBook;
import com.cryptomarket.sdk.models.OrderbookLevel;
import com.cryptomarket.sdk.models.PublicTrade;
import com.cryptomarket.sdk.models.Report;
import com.cryptomarket.sdk.models.Symbol;
import com.cryptomarket.sdk.models.Ticker;
import com.cryptomarket.sdk.models.Trade;
import com.cryptomarket.sdk.models.Transaction;

public class Checker {
    static Consumer<String> checkString = str -> {
        if (str == null || str.equals("")) fail(str);
    };
    static Consumer<Symbol> checkSymbol = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getId(),
            obj.getBaseCurrency(), 
            obj.getQuoteCurrency(),
            obj.getFeeCurrency(),
            obj.getQuantityIncrement(),
            obj.getTickSize(),
            obj.getProvideLiquidityRate()
        ));
        fields.forEach(checkString);
    };
    static Consumer<Currency> checkCurrency = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getId(),
            obj.getFullName()
        ));
        fields.forEach(checkString);
    };
    static Consumer<Ticker> checkTicker = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getSymbol(),
            obj.getAsk(),
            obj.getBid(),
            obj.getHigh(),
            obj.getLow(),
            obj.getLast(),
            obj.getOpen(),
            obj.getVolume(),
            obj.getVolumeQuote(),
            obj.getTimestamp()
        ));
        fields.forEach(checkString);
    };
    static Consumer<PublicTrade> checkPublicTrade = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getPrice(),
            obj.getQuantity(),
            obj.getSide(),
            obj.getTimestamp()
        ));
        fields.forEach(checkString);
    };
    static Consumer<OrderbookLevel> checkOBLevel = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getPrice(),
            obj.getSize()
        ));
        fields.forEach(checkString);
    };
    static Consumer<OrderBook> checkOB = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getSymbol(),
            obj.getTimestamp()
        ));
        fields.forEach(checkString);
        obj.getAsk().forEach(checkOBLevel);
        obj.getBid().forEach(checkOBLevel);
    };
    static Consumer<Candle> checkCandle = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getTimestamp(),
            obj.getOpen(),
            obj.getClose(),
            obj.getMin(),
            obj.getMax(),
            obj.getVolume(),
            obj.getVolumeQuote()
        ));
        fields.forEach(checkString);
    };
    static Consumer<Balance> checkBalance = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getCurrency(),
            obj.getAvailable(),
            obj.getReserved()
        ));
        fields.forEach(checkString);
    };
    static Consumer<Order> checkOrder = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getId(),
            obj.getClientOrderId(),
            obj.getSymbol(),
            obj.getSide(),
            obj.getStatus(),
            obj.getType(),
            obj.getTimeInForce(),
            obj.getQuantity(),
            obj.getPrice(),
            obj.getCumQuantity(),
            obj.getCreatedAt(),
            obj.getUpdatedAt()
        ));
        fields.forEach(checkString);
    };
    static Consumer<Trade> checkTrade = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getOrderId(),
            obj.getClientOrderId(),
            obj.getSymbol(),
            obj.getSide(),
            obj.getQuantity(),
            obj.getPrice(),
            obj.getFee(),
            obj.getTimestamp()
        ));
        fields.forEach(checkString);
    };
    static Consumer<Transaction> checkTransaction = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getId(),
            obj.getIndex(),
            obj.getCurrency(),
            obj.getAmount(),
            obj.getStatus(),
            obj.getType(),
            obj.getCreatedAt(),
            obj.getUpdatedAt()
        ));
        fields.forEach(checkString);
    };
    static Consumer<Report> checkReport = obj -> {
        List<String> fields = new ArrayList<>(Arrays.asList(
            obj.getId(),
            obj.getClientOrderId(),
            obj.getSymbol(),
            obj.getSide(),
            obj.getStatus(),
            obj.getType(),
            obj.getTimeInForce(),
            obj.getQuantity(),
            obj.getPrice(),
            obj.getCumQuantity(),
            obj.getCreatedAt(),
            obj.getUpdatedAt(),
            obj.getReportType()
        ));
        fields.forEach(checkString);
    };    
}
