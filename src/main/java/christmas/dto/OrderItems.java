package christmas.dto;

import static christmas.common.Menu.of;

import java.util.ArrayList;
import java.util.List;

public class OrderItems {
    private final List<OrderMenuQuantity> orderMenus; // 주문메뉴

    public OrderItems(List<OrderMenuQuantity> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public static OrderItems createOrderItems(String orderMenuAndAmount) {
        List<OrderMenuQuantity> orderMenuQuantities = new ArrayList<>();

        String[] menus = orderMenuAndAmount.split(",");
        for (String menu : menus) {
            String name = menu.split("-")[0];
            int quantity = Integer.parseInt(menu.split("-")[1]);

            orderMenuQuantities.add(new OrderMenuQuantity(quantity, of(name)));
        }

        return new OrderItems(orderMenuQuantities);
    }

    public List<OrderMenuQuantity> getOrderMenus() {
        return orderMenus;
    }

    public int getTotalAmount() {
        return orderMenus.stream()
                .mapToInt(OrderMenuQuantity::getTotalAmount)
                .sum();
    }

    public int getDesertMenuQuantity() {
        return orderMenus.stream()
                .filter(OrderMenuQuantity::isDesertMenu)
                .mapToInt(OrderMenuQuantity::getQuantity)
                .reduce(1, (acc, value) -> acc * value);
    }

    public int getMainMenuQuantity() {
        return orderMenus.stream()
                .filter(OrderMenuQuantity::isMainMenu)
                .mapToInt(OrderMenuQuantity::getQuantity)
                .reduce(1, (acc, value) -> acc * value);
    }
}
