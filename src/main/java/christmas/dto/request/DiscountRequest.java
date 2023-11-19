package christmas.dto.request;

import christmas.domain.Day;
import christmas.domain.OrderItems;

public class DiscountRequest {
    private Day day;
    private OrderItems orderItems;
    private int amount;

    public DiscountRequest(Day day, OrderItems orderItems, int amount) {
        this.day = day;
        this.orderItems = orderItems;
        this.amount = amount;
    }

    public Day getDay() {
        return day;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }

    public int getAmount() {
        return amount;
    }
}
