package com.stockapplication.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class OrderMatcher {
	private OrderBook orderBook;

	private long timeOutInMs;

	public OrderBook getOrderBook() {
		return orderBook;
	}

	public void setOrderBook(OrderBook orderBook) {
		this.orderBook = orderBook;
	}

	public long getTimeOutInMs() {
		return timeOutInMs;
	}

	public void setTimeOutInMs(long timeOutInMs) {
		this.timeOutInMs = timeOutInMs;
	}

	public List<OrderMatcherResponse> matchOrders() {
		TreeSet<Order> buyOrders = orderBook.getBuyOrders();
		Map<String, TreeSet<Order>> sellOrdersStockWiseMap = orderBook.getSellOrdersStockWiseMap();
		Iterator<Order> itr = buyOrders.iterator();
		List<OrderMatcherResponse> responses = new ArrayList<OrderMatcherResponse>();
		OrderMatcherResponse response = null;
		if (!Objects.nonNull(buyOrders) && buyOrders.isEmpty())
			return Collections.emptyList();

		long end = System.currentTimeMillis() + this.timeOutInMs;
		while (System.currentTimeMillis() <= end) {
			while (itr.hasNext()) {
				Order buyOrder = itr.next();
				Order sellOrder = getTopMostSellStock(sellOrdersStockWiseMap, buyOrder.getStock());
				while (Objects.nonNull(sellOrder) && buyOrder.getPrice() > sellOrder.getPrice()
						&& buyOrder.getQuantity() > 0) {
					int quantityMatched = sellOrder.getQuantity() > buyOrder.getQuantity() ? buyOrder.getQuantity()
							: sellOrder.getQuantity();
					int remainingQuantity = sellOrder.getQuantity() - quantityMatched;

					response = new OrderMatcherResponse();
					response.setSellOrderId(sellOrder.getOrderId());
					response.setQuantity(quantityMatched);
					response.setSellPrice(sellOrder.getPrice());
					response.setBuyOrderId(buyOrder.getOrderId());

					sellOrder.setQuantity(remainingQuantity);
					buyOrder.setQuantity(buyOrder.getQuantity() - quantityMatched);
					if (remainingQuantity == 0) {
						sellOrdersStockWiseMap.get(buyOrder.getStock()).remove(sellOrder);
						sellOrder = getTopMostSellStock(sellOrdersStockWiseMap, buyOrder.getStock());
					}

					responses.add(response);
				}
			}
		}
		return responses;
	}

	public void print(List<OrderMatcherResponse> responses) {
		for (OrderMatcherResponse response : responses) {
			System.out.println(response.toString());
		}
	}

	private Order getTopMostSellStock(Map<String, TreeSet<Order>> sellOrdersStockWiseMap, String stock) {
		return Objects.nonNull(sellOrdersStockWiseMap.get(stock)) && !sellOrdersStockWiseMap.get(stock).isEmpty()
				? sellOrdersStockWiseMap.get(stock).first()
				: null;
	}

}
