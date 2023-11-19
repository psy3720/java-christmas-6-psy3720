package christmas.dto.request;

import christmas.domain.Day;
import christmas.domain.OrderItems;

public class OrderRequest {
    private Day day;
    private OrderItems orderItems;

    public OrderRequest(Day day, OrderItems orderItems) {
        this.day = day;
        this.orderItems = orderItems;
    }

    public static OrderRequest createOrderRequest(Day day, OrderItems orderItems) {
        return new OrderRequest(day, orderItems);
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public Day getDay() {
        return day;
    }
}
