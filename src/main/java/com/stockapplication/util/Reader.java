package com.stockapplication.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.stockapplication.constants.BuySell;
import com.stockapplication.pojo.Order;

public class Reader {
	private File file;

	public Reader(String pathName) {
		file = new File(pathName);
	}

	public List<Order> read() throws FileNotFoundException {
		Scanner s = new Scanner(file);
		List<Order> orders = new ArrayList<Order>();
		while (s.hasNext()) {
			Order order = new Order();
			String[] input = s.nextLine().split(" ");
			order.setOrderId(Integer.parseInt(input[0].substring(1)));
			order.setTime(Integer.parseInt(input[1].split(":")[0] + input[1].split(":")[1]));
			order.setStock(input[2]);
			order.setBuySell(BuySell.getEnumFromValue(input[3].toUpperCase()));
			order.setQuantity(Integer.parseInt(input[4]));
			order.setPrice(Double.parseDouble(input[5]));
			orders.add(order);
		}
		s.close();
		return orders;
	}

}
