package com.cryptomarket.params;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cryptomarket.sdk.exceptions.CryptomarketArgumentException;

import org.jetbrains.annotations.Nullable;

public class ParamsBuilder {
  private Map<String, Object> params;

  public ParamsBuilder() {
    params = new HashMap<String, Object>();
  }

  public Object remove(String argName) {
    return params.remove(argName);
  }

  static private String fromSnakeCaseToCamelCase(String arg) {
    String[] parts = arg.split("_", 0);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < parts.length; i++) {
      if (i == 0) {
        builder.append(parts[i]);
        continue;
      }
      builder.append(parts[i].substring(0, 1).toUpperCase());
      builder.append(parts[i].substring(1));
    }
    return builder.toString();
  }

  public ParamsBuilder checkRequired(List<String> requiredParams) throws CryptomarketArgumentException {
    List<String> missing = new ArrayList<String>();
    requiredParams.forEach(required -> {
      if (!params.containsKey(required)) {
        missing.add(required);
      }
    });
    if (missing.size() > 0) {
      missing.replaceAll(ParamsBuilder::fromSnakeCaseToCamelCase);
      throw new CryptomarketArgumentException("Missing required parameters: " + String.join(", ", missing));
    }
    return this;
  }

  public Map<String, String> build() {
    Map<String, String> mapStrStr = new HashMap<>();
    this.params.forEach((k, v) -> mapStrStr.put(k, v.toString()));
    return mapStrStr;
  }

  public Map<String, Object> buildObjectMap() {
    return this.params;
  }

  private ParamsBuilder addCommaSeparatedList(String key, @Nullable List<String> list) {
    if (list != null && list.size() > 0)
      params.put(key, String.join(",", list));
    return this;
  }

  private ParamsBuilder addListOrAsterisc(String key, @Nullable List<String> list) {
    if (list == null || list.size() == 0) {
      params.put(key, Collections.singletonList("*"));
    } else {
      params.put(key, list);
    }
    return this;
  }

  private ParamsBuilder addList(String key, @Nullable List<String> list) {
    if (list != null && list.size() != 0) {
      params.put(key, list);
    }
    return this;
  }

  private ParamsBuilder addEnumList(String key, @Nullable List<? extends Enum<?>> list) {
    List<String> strList = new ArrayList<>();
    if (list != null && list.size() > 0) {
      list.forEach(elem -> strList.add(elem.name()));
    }
    return this.addCommaSeparatedList(key, strList);
  }

  private ParamsBuilder addArg(String key, @Nullable Boolean arg) {
    if (arg != null)
      params.put(key, arg.toString());
    return this;
  }

  private ParamsBuilder addArg(String key, @Nullable String arg) {
    if (arg != null)
      params.put(key, arg);
    return this;
  }

  private ParamsBuilder addArg(String key, @Nullable Integer arg) {
    if (arg != null)
      params.put(key, arg.toString());
    return this;
  }

  private ParamsBuilder addArg(String key, @Nullable Enum<?> arg) {
    if (arg != null)
      params.put(key, arg.toString());
    return this;
  }

  public ParamsBuilder currencies(@Nullable List<String> currencies) {
    return addCommaSeparatedList(ArgNames.CURRENCIES, currencies);
  }

  public ParamsBuilder symbols(@Nullable List<String> symbols) {
    return addCommaSeparatedList(ArgNames.SYMBOLS, symbols);
  }

  public ParamsBuilder from(@Nullable String from) {
    return addArg(ArgNames.FROM, from);
  }

  public ParamsBuilder till(@Nullable String till) {
    return addArg(ArgNames.TILL, till);
  }

  public ParamsBuilder limit(@Nullable Integer limit) {
    return addArg(ArgNames.LIMIT, limit);
  }

  public ParamsBuilder limit(@Nullable String limit) {
    return addArg(ArgNames.LIMIT, limit);
  }

  public ParamsBuilder offset(@Nullable Integer offset) {
    return addArg(ArgNames.OFFSET, offset);
  }

  public ParamsBuilder sort(@Nullable Sort sort) {
    return addArg(ArgNames.SORT, sort);
  }

  public ParamsBuilder by(@Nullable SortBy by) {
    return addArg(ArgNames.BY, by);
  }

  public ParamsBuilder to(@Nullable String to) {
    return addArg(ArgNames.TO, to);
  }

  public ParamsBuilder since(@Nullable String since) {
    return addArg(ArgNames.SINCE, since);
  }

  public ParamsBuilder until(@Nullable String until) {
    return addArg(ArgNames.UNTIL, until);
  }

  public ParamsBuilder period(@Nullable Period period) {
    return addArg(ArgNames.PERIOD, period);
  }

  public ParamsBuilder symbol(@Nullable String symbol) {
    return addArg(ArgNames.SYMBOL, symbol);
  }

  public ParamsBuilder side(@Nullable Side side) {
    return addArg(ArgNames.SIDE, side);
  }

  public ParamsBuilder quantity(@Nullable String quantity) {
    return addArg(ArgNames.QUANTITY, quantity);
  }

  public ParamsBuilder clientOrderId(@Nullable String clientOrderId) {
    return addArg(ArgNames.CLIENT_ORDER_ID, clientOrderId);
  }

  public ParamsBuilder orderType(@Nullable OrderType orderType) {
    return addArg(ArgNames.ORDER_TYPE, orderType);
  }

  public ParamsBuilder price(@Nullable String price) {
    return addArg(ArgNames.PRICE, price);
  }

  public ParamsBuilder stopPrice(@Nullable String stopPrice) {
    return addArg(ArgNames.STOP_PRICE, stopPrice);
  }

  public ParamsBuilder timeInForce(@Nullable TimeInForce timeInForce) {
    return addArg(ArgNames.TIME_IN_FORCE, timeInForce);
  }

  public ParamsBuilder expireTime(@Nullable String expireTime) {
    return addArg(ArgNames.EXPIRE_TIME, expireTime);
  }

  public ParamsBuilder strictValidate(@Nullable Boolean strictValidate) {
    return addArg(ArgNames.STRICT_VALIDATE, strictValidate);
  }

  public ParamsBuilder postOnly(@Nullable Boolean postOnly) {
    return addArg(ArgNames.POST_ONLY, postOnly);
  }

  public ParamsBuilder takeRate(@Nullable String takeRate) {
    return addArg(ArgNames.TAKE_RATE, takeRate);
  }

  public ParamsBuilder makeRate(@Nullable String makeRate) {
    return addArg(ArgNames.MAKE_RATE, makeRate);
  }

  public ParamsBuilder newClientOrderId(@Nullable String newClientOrderId) {
    return addArg(ArgNames.NEW_CLIENT_ORDER_ID, newClientOrderId);
  }

  public ParamsBuilder currency(@Nullable String currency) {
    return addArg(ArgNames.CURRENCY, currency);
  }

  public ParamsBuilder address(@Nullable String address) {
    return addArg(ArgNames.ADDRESS, address);
  }

  public ParamsBuilder amount(@Nullable String amount) {
    return addArg(ArgNames.AMOUNT, amount);
  }

  public ParamsBuilder paymentId(@Nullable String paymentId) {
    return addArg(ArgNames.PAYMENT_ID, paymentId);
  }

  public ParamsBuilder includeFee(@Nullable Boolean includeFee) {
    return addArg(ArgNames.INCLUDE_FEE, includeFee);
  }

  public ParamsBuilder autoCommit(@Nullable Boolean autoCommit) {
    return addArg(ArgNames.AUTO_COMMIT, autoCommit);
  }

  public ParamsBuilder useOffchain(@Nullable UseOffchain useOffchain) {
    return addArg(ArgNames.USE_OFFCHAIN, useOffchain);
  }

  public ParamsBuilder publicComment(@Nullable String publicComment) {
    return addArg(ArgNames.PUBLIC_COMMENT, publicComment);
  }

  public ParamsBuilder source(@Nullable AccountType accountType) {
    return addArg(ArgNames.SOURCE, accountType);
  }

  public ParamsBuilder destination(@Nullable AccountType accountType) {
    return addArg(ArgNames.DESTINATION, accountType);
  }

  public ParamsBuilder types(@Nullable List<TransactionType> transactionTypes) {
    return addEnumList(ArgNames.TYPES, transactionTypes);
  }

  public ParamsBuilder subtypes(@Nullable List<TransactionSubtype> subtypes) {
    return addEnumList(ArgNames.SUBTYPES, subtypes);
  }

  public ParamsBuilder statuses(@Nullable List<? extends Enum<?>> statuses) {
    return addEnumList(ArgNames.STATUSES, statuses);
  }

  public ParamsBuilder transactionIds(@Nullable List<String> transactionIds) {
    return addCommaSeparatedList(ArgNames.TRANSACTION_IDS, transactionIds);
  }

  public ParamsBuilder idFrom(@Nullable Integer idFrom) {
    return addArg(ArgNames.ID_FROM, idFrom);
  }

  public ParamsBuilder idTill(@Nullable Integer idTill) {
    return addArg(ArgNames.ID_TILL, idTill);
  }

  public ParamsBuilder showSenders(@Nullable Boolean showSenders) {
    return addArg(ArgNames.SHOW_SENDERS, showSenders);
  }

  public ParamsBuilder baseCurrency(@Nullable String baseCurrency) {
    return addArg(ArgNames.BASE_CURRENCY, baseCurrency);
  }

  public ParamsBuilder activeAt(@Nullable String activeAt) {
    return addArg(ArgNames.ACTIVE_AT, activeAt);
  }

  public ParamsBuilder transactionId(@Nullable String transactionId) {
    return addArg(ArgNames.TRANSACTION_ID, transactionId);
  }

  public ParamsBuilder active(@Nullable Boolean active) {
    return addArg(ArgNames.ACTIVE, active);
  }

  public ParamsBuilder volume(@Nullable Integer volume) {
    return addArg(ArgNames.VOLUME, volume);
  }

  public ParamsBuilder fromCurrency(@Nullable String fromCurrency) {
    return addArg(ArgNames.FROM_CURRENCY, fromCurrency);
  }

  public ParamsBuilder toCurrency(@Nullable String toCurrency) {
    return addArg(ArgNames.TO_CURRENCY, toCurrency);
  }

  public ParamsBuilder identifyBy(@Nullable IdentifyBy identifyBy) {
    return addArg(ArgNames.IDENTIFY_BY, identifyBy);
  }

  public ParamsBuilder identifier(@Nullable String identifier) {
    return addArg(ArgNames.IDENTIFIER, identifier);
  }

  public ParamsBuilder orderListId(@Nullable String orderListId) {
    return addArg(ArgNames.ORDER_LIST_ID, orderListId);
  }

  public ParamsBuilder contingencyType(@Nullable ContingencyType contingencyType) {
    return addArg(ArgNames.CONTINGENCY_TYPE, contingencyType);
  }

  public ParamsBuilder symbolListOrAsteric(@Nullable List<String> symbols) {
    return addListOrAsterisc(ArgNames.SYMBOLS, symbols);
  }

  public ParamsBuilder orderList(@Nullable List<OrderBuilder> orderListData) {
    this.params.put(ArgNames.ORDERS, orderListData);
    return this;
  }

  public ParamsBuilder subAccountIds(@Nullable List<String> subAccountIds) {
    return addCommaSeparatedList(ArgNames.SUB_ACCOUNT_IDS, subAccountIds);
  }

  public ParamsBuilder depositAddressGenerationEnabled(@Nullable Boolean enabled) {
    return addArg(ArgNames.DEPOSIT_ADDRESS_GENERATION_ENABLED, enabled);
  }

  public ParamsBuilder withdrawEnabled(@Nullable Boolean enabled) {
    return addArg(ArgNames.WITHDRAW_ENABLED, enabled);
  }

  public ParamsBuilder createdAt(@Nullable String createdAt) {
    return addArg(ArgNames.CREATED_AT, createdAt);
  }

  public ParamsBuilder updatedAt(@Nullable String updatedAt) {
    return addArg(ArgNames.UPDATED_AT, updatedAt);
  }

  public ParamsBuilder description(@Nullable String description) {
    return addArg(ArgNames.DESCRIPTION, description);
  }

  public ParamsBuilder subAccountId(@Nullable String subAccountId) {
    return addArg(ArgNames.SUB_ACCOUNT_ID, subAccountId);
  }

  public ParamsBuilder transferType(@Nullable SubAccountTransferType transferType) {
    return addArg(ArgNames.TRANSFER_TYPE, transferType);
  }

  public ParamsBuilder subcriptionMode(@Nullable SubscriptionMode mode) {
    return addArg(ArgNames.SUBSCRIPTION_MODE, mode);
  }

  public ParamsBuilder networkCode(@Nullable String networkCode) {
    return addArg(ArgNames.NETWORK_CODE, networkCode);
  }

  public ParamsBuilder targetCurrency(@Nullable String targetCurrency) {
    return addArg(ArgNames.TARGET_CURRENCY, targetCurrency);
  }

  public ParamsBuilder preferredNetwork(@Nullable String preferredNetwork) {
    return addArg(ArgNames.PREFERRED_NETWORK, preferredNetwork);
  }

  public ParamsBuilder depth(@Nullable Integer depth) {
    return addArg(ArgNames.DEPTH, depth);
  }

  public ParamsBuilder orderId(@Nullable String orderId) {
    return addArg(ArgNames.ORDER_ID, orderId);
  }

  public ParamsBuilder networks(@Nullable List<String> networks) {
    return addCommaSeparatedList(ArgNames.NETWORKS, networks);
  }

  public ParamsBuilder email(@Nullable String email) {
    return addArg(ArgNames.EMAIL, email);
  }

  public ParamsBuilder status(@Nullable SubAccountStatus status) {
    return addArg(ArgNames.STATUS, status);
  }

  public ParamsBuilder symbolList(List<String> symbols) {
    return addList(ArgNames.SYMBOLS, symbols);
  }

  public ParamsBuilder currencyListOrAsteric(@Nullable List<String> currencies) {
    return addListOrAsterisc(ArgNames.CURRENCIES, currencies);
  }
}
