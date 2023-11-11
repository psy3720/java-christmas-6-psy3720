package christmas.dto;

import christmas.domain.OrderItems;

public class OrderRequest {
    private int day;
    private OrderItems orderItems;

    public OrderRequest(int day, OrderItems orderItems) {
        this.day = day;
        this.orderItems = orderItems;
    }

    public static OrderRequest createOrderRequest(int day, OrderItems orderItems) {
        return new OrderRequest(day, orderItems);
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public int getDay() {
        return day;
    }
}
