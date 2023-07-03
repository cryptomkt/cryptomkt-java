package com.cryptomarket.sdk.requests;

import java.util.List;

import com.cryptomarket.params.ContingencyType;
import com.cryptomarket.params.OrderBuilder;
import com.squareup.moshi.Json;

@SuppressWarnings("unused")
public class OrderListRequest {

  @Json(name = "contingency_type")
  private ContingencyType contingencyType;

  @Json(name = "order_list_id")
  private String orderListId;

  private List<OrderBuilder> orders;

  public OrderListRequest(ContingencyType contingencyType, String orderListId, List<OrderBuilder> orders) {
    this.contingencyType = contingencyType;
    this.orderListId = orderListId;
    this.orders = orders;
  }
}
