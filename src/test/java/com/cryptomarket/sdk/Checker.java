package com.cryptomarket.sdk;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import com.cryptomarket.sdk.models.Address;
import com.cryptomarket.sdk.models.Balance;
import com.cryptomarket.sdk.models.Candle;
import com.cryptomarket.sdk.models.Commission;
import com.cryptomarket.sdk.models.Currency;
import com.cryptomarket.sdk.models.Fee;
import com.cryptomarket.sdk.models.HistoryPoint;
import com.cryptomarket.sdk.models.NativeTransaction;
import com.cryptomarket.sdk.models.Network;
import com.cryptomarket.sdk.models.Order;
import com.cryptomarket.sdk.models.OrderBook;
import com.cryptomarket.sdk.models.OrderbookLevel;
import com.cryptomarket.sdk.models.Price;
import com.cryptomarket.sdk.models.PriceHistory;
import com.cryptomarket.sdk.models.PublicTrade;
import com.cryptomarket.sdk.models.Report;
import com.cryptomarket.sdk.models.SubAccount;
import com.cryptomarket.sdk.models.SubAccountSettings;
import com.cryptomarket.sdk.models.Symbol;
import com.cryptomarket.sdk.models.Ticker;
import com.cryptomarket.sdk.models.TickerPrice;
import com.cryptomarket.sdk.models.Trade;
import com.cryptomarket.sdk.models.Transaction;
import com.cryptomarket.sdk.models.WSCandle;
import com.cryptomarket.sdk.models.WSOrderBook;
import com.cryptomarket.sdk.models.WSOrderBookTop;
import com.cryptomarket.sdk.models.WSPriceRate;
import com.cryptomarket.sdk.models.WSPublicTrade;
import com.cryptomarket.sdk.models.WSTicker;

public class Checker {

  static Consumer<Boolean> checkBooleanTrue = bool -> {
    if (bool == null || !bool)
      fail("boolean not true");
  };

  static Consumer<String> checkString = str -> {
    if (str == null || str.equals(""))
      fail(str);
  };

  static Consumer<Currency> checkCurrency = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getFullName(),
        obj.getPrecisionTransfer()));
    obj.getNetworks().forEach(Checker.checkNetwork);
    fields.forEach(checkString);
  };
  static Consumer<Network> checkNetwork = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getCode(),
        obj.getNetwork()));
    fields.forEach(checkString);
  };

  static Consumer<Symbol> checkSymbol = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getType(),
        obj.getBaseCurrency(),
        obj.getQuoteCurrency(),
        obj.getStatus(),
        obj.getQuantityIncrement(),
        obj.getTickSize(),
        obj.getTakeRate(),
        obj.getMakeRate(),
        obj.getFeeCurrency()));
    fields.forEach(checkString);
  };
  static Consumer<Ticker> checkTicker = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getHigh(),
        obj.getLow(),
        obj.getVolume(),
        obj.getVolumeQuote(),
        obj.getTimestamp()));
    fields.forEach(checkString);
  };
  static Consumer<WSTicker> checkWSTicker = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getOpen(),
        obj.getClose(),
        obj.getHigh(),
        obj.getLow(),
        obj.getBestAsk(),
        obj.getBestAskQuantity(),
        obj.getBestBid(),
        obj.getBestBidQuantity(),
        obj.getVolumeBase(),
        obj.getVolumeQuote(),
        obj.getTimestamp().toString()));
    fields.forEach(checkString);
  };
  static Consumer<WSOrderBook> checkWSOrderBook = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getTimestamp().toString(),
        obj.getSequence().toString()));
    fields.forEach(checkString);
  };
  static Consumer<WSOrderBookTop> checkWSOrderBookTop = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getTimestamp().toString(),
        obj.getBestAsk(),
        obj.getBestAskQuantity(),
        obj.getBestBid(),
        obj.getBestBidQuantity()));
    fields.forEach(checkString);
  };
  static Consumer<Price> checkPrice = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getTimestamp(),
        obj.getCurrency(),
        obj.getPrice()));
    fields.forEach(checkString);
  };

  public static final Consumer<TickerPrice> checkTickerPrice = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getTimestamp(),
        obj.getPrice()));
    fields.forEach(checkString);
  };

  static Consumer<PriceHistory> checkPriceHistory = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getCurrency()));
    fields.forEach(checkString);
    obj.getHistory().forEach(Checker.checkHistoryPoint);

  };
  static Consumer<HistoryPoint> checkHistoryPoint = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getTimestamp(),
        obj.getOpen(),
        obj.getClose(),
        obj.getMax(),
        obj.getMin()));
    fields.forEach(checkString);
  };
  static Consumer<PublicTrade> checkPublicTrade = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getPrice(),
        obj.getQuantity(),
        obj.getSide(),
        obj.getTimestamp()));
    fields.forEach(checkString);
  };
  static Consumer<WSPublicTrade> checkWSPublicTrade = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getPrice(),
        obj.getQuantity(),
        obj.getSide(),
        obj.getId().toString(),
        obj.getTimestamp().toString()));
    fields.forEach(checkString);
  };
  static Consumer<OrderbookLevel> checkOBLevel = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getPrice(),
        obj.getAmount()));
    fields.forEach(checkString);
  };
  static Consumer<OrderBook> checkOB = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getTimestamp()));
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
        obj.getVolumeQuote()));
    fields.forEach(checkString);
  };
  static Consumer<WSCandle> checkWSCandle = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getOpen(),
        obj.getClose(),
        obj.getHigh(),
        obj.getLow(),
        obj.getBaseVolume(),
        obj.getQuoteVolume()));
    fields.forEach(checkString);
  };
  static Consumer<WSPriceRate> checkWSPriceRate = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        String.valueOf(obj.getTimestamp()),
        obj.getRate()));
    fields.forEach(checkString);
  };
  static Consumer<Balance> checkBalance = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        // obj.getCurrency(),
        obj.getAvailable(),
        obj.getReserved()));
    fields.forEach(checkString);
  };
  static Consumer<Address> checkAddress = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getCurrency(),
        obj.getAddress()));
    fields.forEach(checkString);
  };
  static Consumer<Order> checkOrder = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getId(),
        obj.getClientOrderId(),
        obj.getSymbol(),
        obj.getSide().toString(),
        obj.getStatus().toString(),
        obj.getType().toString(),
        obj.getTimeInForce().toString(),
        obj.getQuantity(),
        obj.getPrice(),
        obj.getQuantityCumulative(),
        obj.getCreatedAt(),
        obj.getUpdatedAt()));
    fields.forEach(checkString);
  };
  static Consumer<Trade> checkTrade = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getOrderId(),
        obj.getClientOrderId(),
        obj.getSymbol(),
        obj.getSide().toString(),
        obj.getQuantity(),
        obj.getPrice(),
        obj.getFee(),
        obj.getTimestamp()));
    fields.forEach(checkString);
  };
  static Consumer<Transaction> checkTransaction = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getStatus().toString(),
        obj.getType().toString(),
        obj.getSubtype().toString(),
        obj.getCreatedAt(),
        obj.getLastActivityAt(),
        obj.getCommitRisk().toString()
    // obj.getUpdatedAt()
    ));
    fields.forEach(checkString);
    if (obj.getId() == 0) {
      fail("0==id");
    }
  };
  static Consumer<NativeTransaction> checkNativeTransaction = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getId(),
        obj.getCurrency(),
        obj.getAmount()));
    fields.forEach(checkString);
  };
  static Consumer<Commission> checkCommission = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getSymbol(),
        obj.getMakeRate(),
        obj.getTakeRate()));
    fields.forEach(checkString);
  };
  static Consumer<Report> checkReport = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getId(),
        obj.getClientOrderId(),
        obj.getSymbol(),
        obj.getSide().toString(),
        obj.getStatus().toString(),
        obj.getType().toString(),
        obj.getTimeInForce(),
        obj.getQuantity(),
        obj.getQuantityCumulative(),
        obj.getPrice(),
        obj.getCreatedAt(),
        obj.getUpdatedAt()));
    // obj.getReportType().toString()
    fields.forEach(checkString);
  };
  static Consumer<SubAccount> checkSubAccount = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getSubAccountId(),
        obj.getEmail(),
        obj.getStatus().toString()));
    fields.forEach(checkString);
  };
  static Consumer<? super SubAccountSettings> checkSubAccountSettings = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getSubAccountId(),
        obj.isDepositAddressGenerationEnabled().toString(),
        obj.getDescription(),
        obj.getCreatedAt(),
        obj.getUpdatedAt()));
    fields.forEach(checkString);
  };
  static Consumer<Fee> checkFee = obj -> {
    List<String> fields = new ArrayList<>(Arrays.asList(
        obj.getFee(),
        // obj.getNetworkFee(),
        obj.getCurrency(),
        obj.getAmount()
    // obj.getUpdatedAt()
    ));
    fields.forEach(checkString);
  };
}
