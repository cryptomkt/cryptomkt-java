package com.cryptomarket.params;

public class OrderRequest {
    public String clientOrderId;
    public String symbol;
    public Side side;
    public String quantity;
    public OrderType orderType;
    public String price;
    public String stopPrice;
    public TimeInForce timeInForce;
    public String expireTime;
    public Boolean strictValidate;
    public Boolean postOnly;

    public OrderRequest(Builder builder) {
        if (builder.clientOrderId != null) clientOrderId = builder.clientOrderId;
        if (builder.symbol != null) symbol = builder.symbol;
        if (builder.side != null) side = builder.side;
        if (builder.quantity != null) quantity = builder.quantity;
        if (builder.orderType != null) orderType = builder.orderType;
        if (builder.price != null) price = builder.price;
        if (builder.stopPrice != null) stopPrice = builder.stopPrice;
        if (builder.timeInForce != null) timeInForce = builder.timeInForce;
        if (builder.expireTime != null) expireTime = builder.expireTime;
        if (builder.strictValidate != null) strictValidate = builder.strictValidate;
        if (builder.postOnly != null) postOnly = builder.postOnly;
    }

    public static class Builder {
        private String clientOrderId;
        private String symbol;
        private Side side;
        private String quantity;
        private OrderType orderType;
        private String price;
        private String stopPrice;
        private TimeInForce timeInForce;
        private String expireTime;
        private Boolean strictValidate;
        private Boolean postOnly;
        
        /**
         * 
         * @param clientOrderId Must be unique within the trading day, including all active orders.
         */
        public Builder clientOrderId(String clientOrderId) {
            this.clientOrderId = clientOrderId;
            return this;
        }

        /**
         * 
         * @param symbol Trading symbol
         */
        public Builder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        /**
         * 
         * @param side A Side enum
         */
        public Builder side(Side side) {
            this.side = side;
            return this;
        }

        /**
         * 
         * @param quantity Order quantity
         */
        public Builder quantity(String quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * 
         * @param orderType An OrderType enum. default is OrderType.LIMIT
         */
        public Builder orderType(OrderType orderType) {
            this.orderType = orderType;
            return this;
        }

        /**
         * 
         * @param price Required for OrderType.LIMIT and OrderType.STOPLIMIT. limit price of the order
         */
        public Builder price(String price) {
            this.price = price;
            return this;
        }
        
        /**
         * 
         * @param stopPrice Required for OrderType.STOPLIMIT and OrderType.STOPMARKET orders. stop price of the order
         */
        public Builder stopPrice(String stopPrice) {
            this.stopPrice = stopPrice;
            return this;
        }

        /**
         * 
         * @param timeInForce A TimeInForce enum. Default to TimeInForce.GTC
         */
        public Builder timeInForce(TimeInForce timeInForce) {
            this.timeInForce = timeInForce;
            return this;
        }

        /**
         * 
         * @param expireTime Required for orders with timeInForce.GDT
         */
        public Builder expireTime(String expireTime) {
            this.expireTime = expireTime;
            return this;
        }

        /**
         * 
         * @param strictValidate If False, the server rounds half down for tickerSize and quantityIncrement. Example of ETHBTC: tickSize = '0.000001', then price '0.046016' is valid, '0.0460165' is invalid. 
         */
        public Builder strictValidate(Boolean strictValidate) {
            this.strictValidate = strictValidate;
            return this;
        }

        /**
         * 
         * @param postOnly If True, your post_only order causes a match with a pre-existing order as a taker, then the order will be cancelled.
         */
        public Builder postOnly(Boolean postOnly) {
            this.postOnly = postOnly;
            return this;
        }

        public OrderRequest build() {
            return new OrderRequest(this);
        }
        
    }
}
