package christmas.dto.request;

import christmas.domain.OrderItems;

public class DiscountRequest {
    private int day;
    private OrderItems orderItems;
    private int amount;

    public DiscountRequest(int day, OrderItems orderItems, int amount) {
        this.day = day;
        this.orderItems = orderItems;
        this.amount = amount;
    }

    public int getDay() {
        return day;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public int getAmount() {
        return amount;
    }
}
