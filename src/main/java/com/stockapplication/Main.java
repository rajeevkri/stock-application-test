package com.stockapplication;

import com.stockapplication.service.ExchangeService;

public class Main {
	public static void main(String[] args) {
		ExchangeService exchangeService = new ExchangeService();
		exchangeService.readAndProcessOrderFile("input.txt");
	}

}
