package com.cryptomarket.sdk.requests;

import java.util.Map;

import com.cryptomarket.params.ParamsBuilder;
import com.cryptomarket.sdk.ArgNames;
import com.squareup.moshi.Json;

@SuppressWarnings("unused")
public class WithdrawRequest {
  private String currency;
  @Json(name = "network_code")
  private String networkCode;
  private Double amount;
  private String address;
  @Json(name = "payment_id")
  private String paymentId;
  @Json(name = "include_fee")
  private Boolean includeFee;
  @Json(name = "auto_commit")
  private Boolean autoCommit;
  @Json(name = "use_offchain")
  private String useOffchain;
  @Json(name = "public_comment")
  private String publicComment;

  public WithdrawRequest(ParamsBuilder paramsBuilder) {
    Map<String, String> paramsMap = paramsBuilder.build();
    currency = paramsMap.get(ArgNames.CURRENCY);
    networkCode = paramsMap.get(ArgNames.NETWORK_CODE);
    amount = Double.parseDouble((String) paramsMap.get(ArgNames.AMOUNT));
    paymentId = paramsMap.get(ArgNames.PAYMENT_ID);
    includeFee = Boolean.parseBoolean((String) paramsMap.get(ArgNames.INCLUDE_FEE));
    autoCommit = Boolean.parseBoolean(paramsMap.get(ArgNames.AUTO_COMMIT));
    useOffchain = paramsMap.get(ArgNames.USE_OFFCHAIN);
    publicComment = paramsMap.get(ArgNames.PUBLIC_COMMENT);
    address = paramsMap.get(ArgNames.ADDRESS);
  }

}
