package com.atockapplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;
import com.stockapplication.constants.BuySell;
import com.stockapplication.pojo.Order;
import com.stockapplication.pojo.OrderBook;

import junit.framework.Assert;

public class OrderBookTest {
	private OrderBook orderBook;

	private String[] stocks;

	private Integer[] timeList;

	private Double[] prices;

	private List<Order> prepareRandomOrders(int N) {
		Random random = new Random();
		List<Order> orders = new ArrayList<Order>();
		while (N-- > 0) {
			Order order = new Order();
			order.setBuySell(BuySell.values()[random.nextInt(10) % 2]);
			order.setPrice(prices[random.nextInt(10) % prices.length]);
			order.setQuantity(random.nextInt(1000));
			order.setStock(stocks[random.nextInt(10) % stocks.length]);
			order.setTime(timeList[random.nextInt(10) % timeList.length]);
			order.setOrderId(N);
			orders.add(order);
		}
		return orders;
	}

	@Before
	public void setUp() throws Exception {
		Random random = new Random();
		stocks = new String[] { "XAM", "TCS", "INFY" };
		timeList = new Integer[] { 940, 945, 1040, 1130 };
		prices = new Double[] { 109.10, 109.10, random.nextDouble() * 1000, random.nextDouble() * 1000 };
		orderBook = new OrderBook();
	}

	@After
	public void tearDown() throws Exception {
		stocks = null;
		timeList = null;
		prices = null;
	}

	@Test
	public void testAddOrdersToOrderBook() {
		List<Order> orders = prepareRandomOrders(10);
		orderBook.addOrdersToOrderBook(orders);
		TreeSet<Order> buyOrders = orderBook.getBuyOrders();
		Map<String, TreeSet<Order>> sellOrdersStockWiseMap = orderBook.getSellOrdersStockWiseMap();
		Iterator<Order> itr = buyOrders.iterator();
		while (itr.hasNext()) {
			Order curr = itr.next();
			Order next = itr.hasNext() ? itr.next() : null;
			if (Objects.nonNull(next))
				Assert.assertTrue(curr.getTime() <= next.getTime());
		}
		for (String str : sellOrdersStockWiseMap.keySet()) {
			TreeSet<Order> sellOrders = sellOrdersStockWiseMap.get(str);
			itr = sellOrders.iterator();
			while (itr.hasNext()) {
				Order curr = itr.next();
				Order next = itr.hasNext() ? itr.next() : null;
				if (Objects.nonNull(next)) {
					if (curr.getPrice() == next.getPrice())
						Assert.assertTrue(curr.getTime() < next.getTime());
					else
						Assert.assertTrue(curr.getPrice() < next.getPrice());
				}
			}
		}
	}
}
