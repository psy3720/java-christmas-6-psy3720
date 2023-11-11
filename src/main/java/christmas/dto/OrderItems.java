package christmas.dto;

import java.util.List;

public class OrderItems {
    private final List<OrderMenuQuantity> orderMenus; // 주문메뉴

    public OrderItems(List<OrderMenuQuantity> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public static OrderItems createOrderItems(String orderMenuAndAmount) {
        return null;
    }

    public List<OrderMenuQuantity> getOrderMenus() {
        return orderMenus;
    }
}
