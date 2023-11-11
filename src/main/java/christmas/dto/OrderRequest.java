package christmas.dto;

public class OrderRequest {
    private int day;
    private OrderItems orderMenuAndAmount;

    public OrderRequest(int day, OrderItems orderMenuAndAmount) {
        this.day = day;
        this.orderMenuAndAmount = orderMenuAndAmount;
    }

    public static OrderRequest createOrderRequest(int day, OrderItems orderMenuAndAmount) {
        return new OrderRequest(day, orderMenuAndAmount);
    }

    public int getDay() {
        return day;
    }

    public OrderItems getOrderMenuAndAmount() {
        return orderMenuAndAmount;
    }
}
