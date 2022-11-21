package com.cryptomarket.sdk.websocket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cryptomarket.sdk.Adapter;
import com.cryptomarket.sdk.models.OrderBook;
import com.cryptomarket.sdk.models.OrderbookLevel;
import com.cryptomarket.sdk.models.WSJsonResponse;

public class OrderbookCache {
    private Adapter adapter = new Adapter();
    private Map<String, OrderBook> orderbooks = new HashMap<String, OrderBook>();
    private Map<String, OrderBookState> orderbookStates = new HashMap<String, OrderBookState>();
    private Integer ASCENDING = 0;
    private Integer DESCENDING = 1;


	public void update(String method, String key, WSJsonResponse response) {
        switch (method) {
            case "snapshotOrderbook":
                orderbookStates.put(key, OrderBookState.UPDATING);
                OrderBook orderbook = adapter.objectFromValue(response.getParams(), OrderBook.class);
                orderbooks.put(key, orderbook);
                break;
            case "updateOrderbook":
                if (!orderbookStates.get(key).equals(OrderBookState.UPDATING)) return;
                OrderBook orderbookUpdate = adapter.objectFromValue(response.getParams(), OrderBook.class);
                OrderBook oldOrderbook = orderbooks.get(key);
                if (orderbookUpdate.getSequence() - oldOrderbook.getSequence() != 1) {
                    orderbookStates.put(key, OrderBookState.BROKEN);
                    return;
                }
                oldOrderbook.setTimestamp(orderbookUpdate.getTimestamp());
                oldOrderbook.setSequence(orderbookUpdate.getSequence());
                List<OrderbookLevel> asksUpdates = orderbookUpdate.getAsk();
                if (asksUpdates != null) {
                    oldOrderbook.setAsk(updateBookSide(oldOrderbook.getAsk(), asksUpdates, ASCENDING));
                }
                List<OrderbookLevel> bidsUpdates = orderbookUpdate.getBid();
                if (bidsUpdates != null) {
                    oldOrderbook.setBid(updateBookSide(oldOrderbook.getBid(), bidsUpdates, DESCENDING));
                }
                break;
        }
    }

	private List<OrderbookLevel> updateBookSide(List<OrderbookLevel> oldList, List<OrderbookLevel> updateList, Integer sortDirection) {
        List<OrderbookLevel> newList = new ArrayList<OrderbookLevel>();
        OrderbookLevel oldEntry;
        int oldIdx = 0;
        OrderbookLevel updateEntry;
        int updateIdx = 0;
        Integer order;
        while (oldIdx < oldList.size() && updateIdx < updateList.size()) {
            updateEntry = updateList.get(updateIdx);
            oldEntry = oldList.get(oldIdx);
            order = priceOrder(oldEntry, updateEntry, sortDirection);
            if (order == 0) {
                if (!zeroSize(updateEntry)) {
                    newList.add(updateEntry);
                }
                oldIdx++;
                updateIdx++;
            } else if (order == 1) { // old value first
                newList.add(oldEntry);
                oldIdx++;
            } else { // (order == -1)
                newList.add(updateEntry);
                updateIdx++;
            }
        }
        if (updateIdx == updateList.size()) {
            for (int idx = oldIdx; idx < oldList.size(); idx++) {
                oldEntry = oldList.get(idx);
                newList.add(oldEntry);
            }
        }
        if (oldIdx == oldList.size()) {
            for (int idx = updateIdx; idx < updateList.size(); idx++) {
                updateEntry = updateList.get(idx);
                if (!zeroSize(updateEntry)) {
                    newList.add(updateEntry);
                }
            }
        }
		return newList;
	}

	private boolean zeroSize(OrderbookLevel entry) {
        BigDecimal size = new BigDecimal(entry.getAmount());
        return size.compareTo(new BigDecimal("0.00")) == 0;
    }

    private Integer priceOrder(OrderbookLevel oldEntry, OrderbookLevel updateEntry, Integer sortDirection) {
        BigDecimal oldPrice = new BigDecimal(oldEntry.getPrice());
        BigDecimal updatePrice = new BigDecimal(updateEntry.getPrice());
        int direction = oldPrice.compareTo(updatePrice);
        if (sortDirection.equals(ASCENDING)) return -direction;
        return direction;
    }

    public OrderBook getOrderbook(String key) {
        if (!orderbooks.containsKey(key)) return null;
        return orderbooks.get(key);
    }

    public boolean orderbookBroken(String key) {
        return orderbookStates.get(key).equals(OrderBookState.BROKEN);
    }

    public void waitOrderbook(String key) {
        orderbookStates.put(key, OrderBookState.WAITING);
    }

	public boolean orderbookWaiting(String key) {
		return orderbookStates.get(key).equals(OrderBookState.WAITING);
	}

}
