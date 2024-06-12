package com.cryptomarket.params;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cryptomarket.sdk.ArgNames;
import com.cryptomarket.sdk.exceptions.CryptomarketArgumentException;

import org.jetbrains.annotations.Nullable;

/** Builds and aggregates the params of a requests */
public class ParamsBuilder {
  private Map<String, Object> params;

  /** Creates a new empty ParamsBuilder */
  public ParamsBuilder() {
    params = new HashMap<String, Object>();
  }

  /**
   * Removes a param from the param builder
   *
   * @param argName The name of the param to remove
   * @return The removed param
   */
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

  /**
   * Checks the presence of a list of params in the ParamsBuilder. Throwing if any
   * is absent
   *
   * @param requiredParams The list of params to check presence
   * @return The ParamsBuilder
   * @throws CryptomarketArgumentException
   */
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

  /**
   * Builds a Map(String, String) out of the params in the ParamsBuilder
   *
   * @return the map of params
   */
  public Map<String, String> build() {
    Map<String, String> mapStrStr = new HashMap<>();
    this.params.forEach((k, v) -> mapStrStr.put(k, v.toString()));
    return mapStrStr;
  }

  /**
   * Builds a Map(String,Object) out of the params in the ParamsBuilder
   *
   * @return the map of params
   */
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

  /**
   * Adds an arbitrary parameter
   *
   * @param key   the name of the parameter
   * @param value the value of the parameter
   * @return The ParamsBuilder
   */
  public ParamsBuilder parameter(String key, String value) {
    return addArg(key, value);
  }

  /**
   * Adds a currencies param to the ParamBuilder.
   *
   * @param currencies A list of currencies
   * @return The ParamsBuilder
   */
  public ParamsBuilder currencies(@Nullable List<String> currencies) {
    return addCommaSeparatedList(ArgNames.CURRENCIES, currencies);
  }

  /**
   * Adds a symbols param to the ParamBuilder.
   *
   * @param symbols A list of symbols
   * @return The ParamsBuilder
   */
  public ParamsBuilder symbols(@Nullable List<String> symbols) {
    return addCommaSeparatedList(ArgNames.SYMBOLS, symbols);
  }

  /**
   * Adds a from param to the ParamBuilder.
   *
   * @param from A date or a currency
   * @return The ParamsBuilder
   */
  public ParamsBuilder from(@Nullable String from) {
    return addArg(ArgNames.FROM, from);
  }

  /**
   * Adds a till param to the ParamBuilder.
   *
   * @param till A till date
   * @return The ParamsBuilder
   */
  public ParamsBuilder till(@Nullable String till) {
    return addArg(ArgNames.TILL, till);
  }

  /**
   * Adds a limit param to the ParamBuilder.
   *
   * @param limit An amount
   * @return The ParamsBuilder
   */
  public ParamsBuilder limit(@Nullable Integer limit) {
    return addArg(ArgNames.LIMIT, limit);
  }

  /**
   * Adds a limit param to the ParamBuilder.
   *
   * @param limit An amount
   * @return The ParamsBuilder
   */
  public ParamsBuilder limit(@Nullable String limit) {
    return addArg(ArgNames.LIMIT, limit);
  }

  /**
   * Adds an offset param to the ParamBuilder.
   *
   * @param offset An amount
   * @return The ParamsBuilder
   */
  public ParamsBuilder offset(@Nullable Integer offset) {
    return addArg(ArgNames.OFFSET, offset);
  }

  /**
   * Adds a sort param to the ParamBuilder.
   *
   * @param sort A sort type
   * @return The ParamsBuilder
   */
  public ParamsBuilder sort(@Nullable Sort sort) {
    return addArg(ArgNames.SORT, sort);
  }

  /**
   * Adds a by param to the ParamBuilder.
   *
   * @param by A sortBy type
   * @return The ParamsBuilder
   */
  public ParamsBuilder by(@Nullable SortBy by) {
    return addArg(ArgNames.BY, by);
  }

  /**
   * Adds an order_by param to the ParamBuilder.
   *
   * @param by An OrderBy type
   * @return The ParamsBuilder
   */
  public ParamsBuilder orderBy(@Nullable OrderBy by) {
    return addArg(ArgNames.ORDER_BY, by);
  }

  /**
   * Adds a to param to the ParamBuilder.
   *
   * @param to A currency
   * @return The ParamsBuilder
   */
  public ParamsBuilder to(@Nullable String to) {
    return addArg(ArgNames.TO, to);
  }

  /**
   * Adds a since param to the ParamBuilder.
   *
   * @param since A date
   * @return The ParamsBuilder
   */
  public ParamsBuilder since(@Nullable String since) {
    return addArg(ArgNames.SINCE, since);
  }

  /**
   * Adds a until param to the ParamBuilder.
   *
   * @param until A date
   * @return The ParamsBuilder
   */
  public ParamsBuilder until(@Nullable String until) {
    return addArg(ArgNames.UNTIL, until);
  }

  /**
   * Adds a period param to the ParamBuilder.
   *
   * @param period A Period type
   * @return The ParamsBuilder
   */
  public ParamsBuilder period(@Nullable Period period) {
    return addArg(ArgNames.PERIOD, period);
  }

  /**
   * Adds a symbol param to the ParamBuilder.
   *
   * @param symbol A symbol
   * @return The ParamsBuilder
   */
  public ParamsBuilder symbol(@Nullable String symbol) {
    return addArg(ArgNames.SYMBOL, symbol);
  }

  /**
   * Adds a side param to the ParamBuilder.
   *
   * @param side A Side type
   * @return The ParamsBuilder
   */
  public ParamsBuilder side(@Nullable Side side) {
    return addArg(ArgNames.SIDE, side);
  }

  /**
   * Adds a quantity param to the ParamBuilder.
   *
   * @param quantity A quantity
   * @return The ParamsBuilder
   */
  public ParamsBuilder quantity(@Nullable String quantity) {
    return addArg(ArgNames.QUANTITY, quantity);
  }

  /**
   * Adds client order id param to the ParamBuilder.
   *
   * @param clientOrderId A client order id for an order
   * @return The ParamsBuilder
   */
  public ParamsBuilder clientOrderId(@Nullable String clientOrderId) {
    return addArg(ArgNames.CLIENT_ORDER_ID, clientOrderId);
  }

  /**
   * Adds a type param to the ParamBuilder.
   *
   * @param orderType A OrderType type
   * @return The ParamsBuilder
   */
  public ParamsBuilder orderType(@Nullable OrderType orderType) {
    return addArg(ArgNames.ORDER_TYPE, orderType);
  }

  /**
   * Adds a price param to the ParamBuilder.
   *
   * @param price A price
   * @return The ParamsBuilder
   */
  public ParamsBuilder price(@Nullable String price) {
    return addArg(ArgNames.PRICE, price);
  }

  /**
   * Adds a stop price param to the ParamBuilder.
   *
   * @param stopPrice A stop price
   * @return The ParamsBuilder
   */
  public ParamsBuilder stopPrice(@Nullable String stopPrice) {
    return addArg(ArgNames.STOP_PRICE, stopPrice);
  }

  /**
   * Adds a time in force param to the ParamBuilder.
   *
   * @param timeInForce A timeInForce type of an order
   * @return The ParamsBuilder
   */
  public ParamsBuilder timeInForce(@Nullable TimeInForce timeInForce) {
    return addArg(ArgNames.TIME_IN_FORCE, timeInForce);
  }

  /**
   * Adds an expire time param to the ParamBuilder.
   *
   * @param expireTime An expire time of an order
   * @return The ParamsBuilder
   */
  public ParamsBuilder expireTime(@Nullable String expireTime) {
    return addArg(ArgNames.EXPIRE_TIME, expireTime);
  }

  /**
   * Adds a strict validate param to the ParamBuilder.
   *
   * @param strictValidate
   * @return The ParamsBuilder
   */
  public ParamsBuilder strictValidate(@Nullable Boolean strictValidate) {
    return addArg(ArgNames.STRICT_VALIDATE, strictValidate);
  }

  /**
   * Adds a post only param to the ParamBuilder.
   *
   * @param postOnly
   * @return The ParamsBuilder
   */
  public ParamsBuilder postOnly(@Nullable Boolean postOnly) {
    return addArg(ArgNames.POST_ONLY, postOnly);
  }

  /**
   * Adds a take rate param to the ParamBuilder.
   *
   * @param takeRate A take rate
   * @return The ParamsBuilder
   */
  public ParamsBuilder takeRate(@Nullable String takeRate) {
    return addArg(ArgNames.TAKE_RATE, takeRate);
  }

  /**
   * Adds a make rate param to the ParamBuilder.
   *
   * @param makeRate A make rate
   * @return The ParamsBuilder
   */
  public ParamsBuilder makeRate(@Nullable String makeRate) {
    return addArg(ArgNames.MAKE_RATE, makeRate);
  }

  /**
   * Adds a new client order id param to the ParamBuilder.
   *
   * @param newClientOrderId The new client order id of an order
   * @return The ParamsBuilder
   */
  public ParamsBuilder newClientOrderId(@Nullable String newClientOrderId) {
    return addArg(ArgNames.NEW_CLIENT_ORDER_ID, newClientOrderId);
  }

  /**
   * Adds a currency to the ParamBuilder.
   *
   * @param currency A currency
   * @return The ParamsBuilder
   */
  public ParamsBuilder currency(@Nullable String currency) {
    return addArg(ArgNames.CURRENCY, currency);
  }

  /**
   * Adds an address param to the ParamBuilder.
   *
   * @param address An address
   * @return The ParamsBuilder
   */
  public ParamsBuilder address(@Nullable String address) {
    return addArg(ArgNames.ADDRESS, address);
  }

  /**
   * Adds an amount param to the ParamBuilder.
   *
   * @param amount An amount
   * @return The ParamsBuilder
   */
  public ParamsBuilder amount(@Nullable String amount) {
    return addArg(ArgNames.AMOUNT, amount);
  }

  /**
   * Adds a payment id param to the ParamBuilder.
   *
   * @param paymentId A payment id
   * @return The ParamsBuilder
   */
  public ParamsBuilder paymentId(@Nullable String paymentId) {
    return addArg(ArgNames.PAYMENT_ID, paymentId);
  }

  /**
   * Adds an include fee param to the ParamBuilder.
   *
   * @param includeFee
   * @return The ParamsBuilder
   */
  public ParamsBuilder includeFee(@Nullable Boolean includeFee) {
    return addArg(ArgNames.INCLUDE_FEE, includeFee);
  }

  /**
   * Adds an auto commit param to the ParamBuilder.
   *
   * @param autoCommit
   * @return The ParamsBuilder
   */
  public ParamsBuilder autoCommit(@Nullable Boolean autoCommit) {
    return addArg(ArgNames.AUTO_COMMIT, autoCommit);
  }

  /**
   * Adds a use offchain param to the ParamBuilder.
   *
   * @param useOffchain A UseOffchain type
   * @return The ParamsBuilder
   */
  public ParamsBuilder useOffchain(@Nullable UseOffchain useOffchain) {
    return addArg(ArgNames.USE_OFFCHAIN, useOffchain);
  }

  /**
   * Adds a public comment param to the ParamBuilder.
   *
   * @param publicComment
   * @return The ParamsBuilder
   */
  public ParamsBuilder publicComment(@Nullable String publicComment) {
    return addArg(ArgNames.PUBLIC_COMMENT, publicComment);
  }

  /**
   * Adds an source param to the ParamBuilder.
   *
   * @param accountType An AccountType type
   * @return The ParamsBuilder
   */
  public ParamsBuilder source(@Nullable AccountType accountType) {
    return addArg(ArgNames.SOURCE, accountType);
  }

  /**
   * Adds an destination param to the ParamBuilder.
   *
   * @param accountType An AccountType type
   * @return The ParamsBuilder
   */
  public ParamsBuilder destination(@Nullable AccountType accountType) {
    return addArg(ArgNames.DESTINATION, accountType);
  }

  /**
   * Adds a types param to the ParamBuilder.
   *
   * @param transactionTypes A list of transaction types
   * @return The ParamsBuilder
   */
  public ParamsBuilder types(@Nullable List<TransactionType> transactionTypes) {
    return addEnumList(ArgNames.TYPES, transactionTypes);
  }

  /**
   * Adds a subtypes param to the ParamBuilder.
   *
   * @param subtypes A list of transaction subtypes
   * @return The ParamsBuilder
   */
  public ParamsBuilder subtypes(@Nullable List<TransactionSubtype> subtypes) {
    return addEnumList(ArgNames.SUBTYPES, subtypes);
  }

  /**
   * Adds a statuses param to the ParamBuilder.
   *
   * @param statuses A list of statuses
   * @return The ParamsBuilder
   */
  public ParamsBuilder statuses(@Nullable List<? extends Enum<?>> statuses) {
    return addEnumList(ArgNames.STATUSES, statuses);
  }

  /**
   * Adds a transaction ids param to the ParamBuilder.
   *
   * @param transactionIds A list of transaction ids
   * @return The ParamsBuilder
   */
  public ParamsBuilder transactionIds(@Nullable List<String> transactionIds) {
    return addCommaSeparatedList(ArgNames.TRANSACTION_IDS, transactionIds);
  }

  /**
   * Adds an id from param to the ParamBuilder.
   *
   * @param idFrom An id
   * @return The ParamsBuilder
   */
  public ParamsBuilder idFrom(@Nullable Integer idFrom) {
    return addArg(ArgNames.ID_FROM, idFrom);
  }

  /**
   * Adds an id till param to the ParamBuilder.
   *
   * @param idTill An id
   * @return The ParamsBuilder
   */
  public ParamsBuilder idTill(@Nullable Integer idTill) {
    return addArg(ArgNames.ID_TILL, idTill);
  }

  /**
   * Adds a base currency param to the ParamBuilder.
   *
   * @param baseCurrency A currency
   * @return The ParamsBuilder
   */
  public ParamsBuilder baseCurrency(@Nullable String baseCurrency) {
    return addArg(ArgNames.BASE_CURRENCY, baseCurrency);
  }

  /**
   * Adds an active at param to the ParamBuilder.
   *
   * @param activeAt
   * @return The ParamsBuilder
   */
  public ParamsBuilder activeAt(@Nullable String activeAt) {
    return addArg(ArgNames.ACTIVE_AT, activeAt);
  }

  /**
   * Adds a transaction id param to the ParamBuilder.
   *
   * @param transactionId An id
   * @return The ParamsBuilder
   */
  public ParamsBuilder transactionId(@Nullable String transactionId) {
    return addArg(ArgNames.TRANSACTION_ID, transactionId);
  }

  /**
   * Adds an active param to the ParamBuilder.
   *
   * @param active
   * @return The ParamsBuilder
   */
  public ParamsBuilder active(@Nullable Boolean active) {
    return addArg(ArgNames.ACTIVE, active);
  }

  /**
   * Adds a volume param to the ParamBuilder.
   *
   * @param volume
   * @return The ParamsBuilder
   */
  public ParamsBuilder volume(@Nullable Integer volume) {
    return addArg(ArgNames.VOLUME, volume);
  }

  /**
   * Adds a from currency param to the ParamBuilder.
   *
   * @param fromCurrency A currency
   * @return The ParamsBuilder
   */
  public ParamsBuilder fromCurrency(@Nullable String fromCurrency) {
    return addArg(ArgNames.FROM_CURRENCY, fromCurrency);
  }

  /**
   * Adds a to currency param to the ParamBuilder.
   *
   * @param toCurrency A currency
   * @return The ParamsBuilder
   */
  public ParamsBuilder toCurrency(@Nullable String toCurrency) {
    return addArg(ArgNames.TO_CURRENCY, toCurrency);
  }

  /**
   * Adds a identify by param to the ParamBuilder.
   *
   * @param identifyBy
   * @return The ParamsBuilder
   */
  public ParamsBuilder identifyBy(@Nullable IdentifyBy identifyBy) {
    return addArg(ArgNames.IDENTIFY_BY, identifyBy);
  }

  /**
   * Adds an identifier param to the ParamBuilder.
   *
   * @param identifier
   * @return The ParamsBuilder
   */
  public ParamsBuilder identifier(@Nullable String identifier) {
    return addArg(ArgNames.IDENTIFIER, identifier);
  }

  /**
   * Adds an order list id param to the ParamBuilder.
   *
   * @param orderListId
   * @return The ParamsBuilder
   */
  public ParamsBuilder orderListId(@Nullable String orderListId) {
    return addArg(ArgNames.ORDER_LIST_ID, orderListId);
  }

  /**
   * Adds a contingency type param to the ParamBuilder.
   *
   * @param contingencyType A ContingencyType type
   * @return The ParamsBuilder
   */
  public ParamsBuilder contingencyType(@Nullable ContingencyType contingencyType) {
    return addArg(ArgNames.CONTINGENCY_TYPE, contingencyType);
  }

  /**
   * Adds a symbols param to the ParamBuilder. The list is converted to a comma
   * separated string. If the list is empty, then an asterisc is used
   *
   * @param symbols A list of symbols
   * @return The ParamsBuilder
   */
  public ParamsBuilder symbolListOrAsteric(@Nullable List<String> symbols) {
    return addListOrAsterisc(ArgNames.SYMBOLS, symbols);
  }

  /**
   * Adds an orders param to the ParamBuilder.
   *
   * @param orders A list of order builders
   * @return The ParamsBuilder
   */
  public ParamsBuilder orderList(@Nullable List<OrderBuilder> orders) {
    this.params.put(ArgNames.ORDERS, orders);
    return this;
  }

  /**
   * Adds a sub-account ids param to the ParamBuilder.
   *
   * @param subAccountIds a list of sub-account ids
   * @return The ParamsBuilder
   */
  public ParamsBuilder subAccountIds(@Nullable List<String> subAccountIds) {
    return addCommaSeparatedList(ArgNames.SUB_ACCOUNT_IDS, subAccountIds);
  }

  /**
   * Adds a deposit address generation enabled param to the ParamBuilder.
   *
   * @param enabled
   * @return The ParamsBuilder
   */
  public ParamsBuilder depositAddressGenerationEnabled(@Nullable Boolean enabled) {
    return addArg(ArgNames.DEPOSIT_ADDRESS_GENERATION_ENABLED, enabled);
  }

  /**
   * Adds a withdraw enabled param to the ParamBuilder.
   *
   * @param enabled
   * @return The ParamsBuilder
   */
  public ParamsBuilder withdrawEnabled(@Nullable Boolean enabled) {
    return addArg(ArgNames.WITHDRAW_ENABLED, enabled);
  }

  /**
   * Adds a created at param to the ParamBuilder.
   *
   * @param createdAt
   * @return The ParamsBuilder
   */
  public ParamsBuilder createdAt(@Nullable String createdAt) {
    return addArg(ArgNames.CREATED_AT, createdAt);
  }

  /**
   * Adds a updated at param to the ParamBuilder.
   *
   * @param updatedAt
   * @return The ParamsBuilder
   */
  public ParamsBuilder updatedAt(@Nullable String updatedAt) {
    return addArg(ArgNames.UPDATED_AT, updatedAt);
  }

  /**
   * Adds a description param to the ParamBuilder.
   *
   * @param description
   * @return The ParamsBuilder
   */
  public ParamsBuilder description(@Nullable String description) {
    return addArg(ArgNames.DESCRIPTION, description);
  }

  /**
   * Adds a sub-account id param to the ParamBuilder.
   *
   * @param subAccountId An id
   * @return The ParamsBuilder
   */
  public ParamsBuilder subAccountId(@Nullable String subAccountId) {
    return addArg(ArgNames.SUB_ACCOUNT_ID, subAccountId);
  }

  /**
   * Adds a transfer type param to the ParamBuilder.
   *
   * @param transferType A SubAccountTransferType
   * @return The ParamsBuilder
   */
  public ParamsBuilder transferType(@Nullable SubAccountTransferType transferType) {
    return addArg(ArgNames.TRANSFER_TYPE, transferType);
  }

  /**
   * Adds a subscription mode param to the ParamBuilder.
   *
   * @param mode A SubscriptionMode type
   * @return The ParamsBuilder
   */
  public ParamsBuilder subcriptionMode(@Nullable SubscriptionMode mode) {
    return addArg(ArgNames.SUBSCRIPTION_MODE, mode);
  }

  /**
   * Adds a network code param to the ParamBuilder.
   *
   * @param networkCode
   * @return The ParamsBuilder
   */
  public ParamsBuilder networkCode(@Nullable String networkCode) {
    return addArg(ArgNames.NETWORK_CODE, networkCode);
  }

  /**
   * Adds a target currency param to the ParamBuilder.
   *
   * @param targetCurrency
   * @return The ParamsBuilder
   */
  public ParamsBuilder targetCurrency(@Nullable String targetCurrency) {
    return addArg(ArgNames.TARGET_CURRENCY, targetCurrency);
  }

  /**
   * Adds a preferred network param to the ParamBuilder.
   *
   * @param preferredNetwork
   * @return The ParamsBuilder
   */
  public ParamsBuilder preferredNetwork(@Nullable String preferredNetwork) {
    return addArg(ArgNames.PREFERRED_NETWORK, preferredNetwork);
  }

  /**
   * Adds a depth param to the ParamBuilder.
   *
   * @param depth
   * @return The ParamsBuilder
   */
  public ParamsBuilder depth(@Nullable Integer depth) {
    return addArg(ArgNames.DEPTH, depth);
  }

  /**
   * Adds a order id param to the ParamBuilder.
   *
   * @param orderId An id
   * @return The ParamsBuilder
   */
  public ParamsBuilder orderId(@Nullable String orderId) {
    return addArg(ArgNames.ORDER_ID, orderId);
  }

  /**
   * Adds a networks param to the ParamBuilder.
   *
   * @param networks
   * @return The ParamsBuilder
   */
  public ParamsBuilder networks(@Nullable List<String> networks) {
    return addCommaSeparatedList(ArgNames.NETWORKS, networks);
  }

  /**
   * Adds an email param to the ParamBuilder.
   *
   * @param email
   * @return The ParamsBuilder
   */
  public ParamsBuilder email(@Nullable String email) {
    return addArg(ArgNames.EMAIL, email);
  }

  /**
   * Adds a status param to the ParamBuilder.
   *
   * @param status A SubAccountStatus type
   * @return The ParamsBuilder
   */
  public ParamsBuilder status(@Nullable SubAccountStatus status) {
    return addArg(ArgNames.STATUS, status);
  }

  /**
   * Adds a symbols param to the ParamBuilder.
   *
   * @param symbols A list of symbols
   * @return The ParamsBuilder
   */
  public ParamsBuilder symbolList(List<String> symbols) {
    return addList(ArgNames.SYMBOLS, symbols);
  }

  /**
   * Adds a currencies param to the ParamBuilder. The list is converted to a comma
   * separated string. If the list is empty, then an asterisc is used
   *
   * @param currencies
   * @return The ParamsBuilder
   */
  public ParamsBuilder currencyListOrAsterisc(@Nullable List<String> currencies) {
    return addListOrAsterisc(ArgNames.CURRENCIES, currencies);
  }

  /**
   * Adds a group_transaction param to the ParamBuilder.
   *
   * @param asGroupTransactions Flag indicating whether the returned transactions will be parts of a single operation
   * @return The ParamsBuilder
   */
  public ParamsBuilder GroupTransactions(Boolean asGroupTransactions) {
    return addArg(ArgNames.GROUP_TRANSACTIONS, asGroupTransactions);
  }
}
