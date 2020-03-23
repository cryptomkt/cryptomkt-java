package com.cryptomkt.api.entitySocket;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.lang.model.type.NullType;
import java.io.Serializable;

public class OrderBookExecuted implements Serializable {

    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("tradeId")
    private String tradeId;

    @JsonProperty("stockId")
    private String stockId;

    @JsonProperty("kind")
    private Integer kind;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("side")
    private Integer side;

    @JsonProperty("price")
    private String price;

    @JsonProperty("limit")
    private NullType limit; //TODO currently not implemented, will be String

    @JsonProperty("condition")
    private NullType condition; //TODO currently not implemented, will be Int

    @JsonProperty("flag")
    private String flag;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("initAmount")
    private String initAmount;

    @JsonProperty("dateReceived")
    private Long dateReceived ;

    @JsonProperty("executed_price")
    private String executed_price;

    @JsonProperty("executed_amount")
    private String executed_amount;

    @JsonProperty("executed_date")
    private Long executed_date;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSide() {
        return side;
    }

    public void setSide(Integer side) {
        this.side = side;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public NullType getLimit() {
        return limit;
    }

    public void setLimit(NullType limit) {
        this.limit = limit;
    }

    public NullType getCondition() {
        return condition;
    }

    public void setCondition(NullType condition) {
        this.condition = condition;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInitAmount() {
        return initAmount;
    }

    public void setInitAmount(String initAmount) {
        this.initAmount = initAmount;
    }

    public Long getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Long dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getExecuted_price() {
        return executed_price;
    }

    public void setExecuted_price(String executed_price) {
        this.executed_price = executed_price;
    }

    public String getExecuted_amount() {
        return executed_amount;
    }

    public void setExecuted_amount(String executed_amount) {
        this.executed_amount = executed_amount;
    }

    public Long getExecuted_date() {
        return executed_date;
    }

    public void setExecuted_date(Long executed_date) {
        this.executed_date = executed_date;
    }


}
