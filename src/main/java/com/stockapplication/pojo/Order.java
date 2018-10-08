package com.stockapplication.pojo;

import com.stockapplication.constants.BuySell;

public class Order {
    private int orderId;

    private int time;

    private String stock;

    private BuySell buySell;

    private int quantity;

    private double price;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public BuySell getBuySell() {
        return buySell;
    }

    public void setBuySell(BuySell buySell) {
        this.buySell = buySell;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (time != order.time) return false;
        if (quantity != order.quantity) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (stock != null ? !stock.equals(order.stock) : order.stock != null) return false;
        return buySell == order.buySell;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + time;
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (buySell != null ? buySell.hashCode() : 0);
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", time=" + time +
                ", stock='" + stock + '\'' +
                ", buySell=" + buySell +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

}
