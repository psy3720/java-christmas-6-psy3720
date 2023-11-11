package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderItems {
    public static final String MENU_SEPARATOR = ",";
    public static final String NAME_QUANTITY_SEPARATOR = "-";

    private final List<OrderMenuQuantity> orderMenus;

    public OrderItems(List<OrderMenuQuantity> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public static OrderItems createOrderItems(String orderItems) {
        List<OrderMenuQuantity> orderMenuQuantities = convertOrderItems(orderItems);

        validate(orderMenuQuantities);

        return new OrderItems(orderMenuQuantities);
    }

    private static void validate(List<OrderMenuQuantity> orderMenuQuantities) {
        int totalOrderQuantity = orderMenuQuantities.stream()
                .mapToInt(orderMenuQuantity -> orderMenuQuantity.getQuantity())
                .sum();

        if (totalOrderQuantity > 20) {
            throw new IllegalArgumentException();
        }
    }

    private static List<OrderMenuQuantity> convertOrderItems(String orderItems) {
        List<OrderMenuQuantity> orderMenuQuantities = new ArrayList<>();
        String[] menus = orderItems.split(MENU_SEPARATOR);

        for (String menu : menus) {
            String name = menu.split(NAME_QUANTITY_SEPARATOR)[0];
            String quantity = menu.split(NAME_QUANTITY_SEPARATOR)[1];

            orderMenuQuantities.add(new OrderMenuQuantity(quantity, name));
        }
        return orderMenuQuantities;
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