package com.stockapplication.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.stockapplication.constants.BuySell;

public class OrderBook {
	private TreeSet<Order> buyOrders;

	private Map<String, TreeSet<Order>> sellOrdersStockWiseMap = new HashMap<String, TreeSet<Order>>();

	public OrderBook() {
		this.buyOrders = new TreeSet<Order>(getOrderComparator(BuySell.BUY));
	}

	public TreeSet<Order> getBuyOrders() {
		return buyOrders;
	}

	public Map<String, TreeSet<Order>> getSellOrdersStockWiseMap() {
		return sellOrdersStockWiseMap;
	}

	public void setSellOrdersStockWiseMap(Map<String, TreeSet<Order>> sellOrdersStockWiseMap) {
		this.sellOrdersStockWiseMap = sellOrdersStockWiseMap;
	}

	public void addOrdersToOrderBook(List<Order> orders) {
		for (Order order : orders) {
			if (BuySell.BUY.equals(order.getBuySell())) {
				buyOrders.add(order);
			} else {
				if (sellOrdersStockWiseMap.get(order.getStock()) == null)
					sellOrdersStockWiseMap.put(order.getStock(), new TreeSet<Order>(getOrderComparator(BuySell.SELL)));
				sellOrdersStockWiseMap.get(order.getStock()).add(order);
			}
		}
	}

	private OrderComparator getOrderComparator(BuySell buySell) {
		if (BuySell.BUY.equals(buySell)) {
			return new OrderBuyComparator();
		}
		return new OrderSellComparator();
	}

}
