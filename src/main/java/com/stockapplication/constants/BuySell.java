package com.stockapplication.constants;

import java.util.HashMap;
import java.util.Map;

public enum BuySell {
	BUY("BUY"), SELL("SELL");

	private static Map<BuySell, String> enumToValueMap = new HashMap<BuySell, String>();

	private static Map<String, BuySell> valueToEnumMap = new HashMap<String, BuySell>();

	BuySell(String buySell) {
	}

	static {
		for (BuySell buySell : BuySell.values()) {
			enumToValueMap.put(buySell, buySell.name());
			valueToEnumMap.put(buySell.name(), buySell);
		}
	}

	public static String getValueFromEnum(BuySell buySell) {
		return enumToValueMap.get(buySell);
	}

	public static BuySell getEnumFromValue(String buySell) {
		return valueToEnumMap.get(buySell);
	}

}
