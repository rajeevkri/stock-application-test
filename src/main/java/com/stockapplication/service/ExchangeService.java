package com.stockapplication.service;

import java.io.FileNotFoundException;
import com.stockapplication.util.Reader;
import java.util.List;

import com.stockapplication.pojo.Order;
import com.stockapplication.pojo.OrderBook;
import com.stockapplication.pojo.OrderMatcher;

public class ExchangeService {

	public void readAndProcessOrderFile(String pathName) {
		Reader reader = new Reader(pathName);
		OrderBook orderBook = new OrderBook();
		OrderMatcher orderMatcher = new OrderMatcher();

		try {
			List<Order> orders = reader.read();
			orderBook.addOrdersToOrderBook(orders);
			orderMatcher.setOrderBook(orderBook);
			orderMatcher.setTimeOutInMs(2000);
			orderMatcher.print(orderMatcher.matchOrders());
		} catch (FileNotFoundException e) {
			System.out.println("Input file is not found");
		}

	}
}
