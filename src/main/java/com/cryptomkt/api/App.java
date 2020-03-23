package com.cryptomkt.api;


import com.cryptomkt.api.entity.*;
import com.cryptomkt.api.entity.orders.Order;
import com.cryptomkt.api.entity.orders.OrdersResponse;
import com.cryptomkt.api.exception.CryptoMarketException;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main( String[] args ) throws IOException, CryptoMarketException {
        CryptoMarket cryptoMarket = new CryptoMarketImpl();
        OrdersResponse ordersResponse = cryptoMarket.getActiveOrders("ETHCLP");
        List<Order> orders = ordersResponse.getOrders();
        Pagination pagination = ordersResponse.getPagination();

        for(Order order : orders){
            System.out.println("ID: " + order.getId());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Type: " + order.getType());
        }

    }
}
