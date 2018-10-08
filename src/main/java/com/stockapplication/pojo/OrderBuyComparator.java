package com.stockapplication.pojo;

import com.google.common.collect.ComparisonChain;

public class OrderBuyComparator implements OrderComparator {

    public int compare(Order o1, Order o2) {
        return ComparisonChain.start()
                .compare(o1.getTime(), o2.getTime()).result();
    }
}
