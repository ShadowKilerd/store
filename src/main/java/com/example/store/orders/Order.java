package com.example.store.orders;

import java.util.List;

public class Order {
    private List<OrderItem> orderItems;
    private String userId;

    public <T> void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
