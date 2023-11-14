package christmas.domain;

import christmas.common.ErrorMessages;
import christmas.common.FoodType;
import christmas.exception.InputValidationException;
import christmas.exception.OrderQuantityExceededException;
import christmas.view.output.OutputMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderItems {
    public static final String MENU_SEPARATOR = ",";
    public static final String NAME_QUANTITY_SEPARATOR = "-";
    public static final int MAX_ORDER_QUANTITY = 20;

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
        int totalOrderQuantity = getTotalOrderQuantity(orderMenuQuantities);

        if (isMaxOrderQuantity(totalOrderQuantity)) {
            throw new OrderQuantityExceededException(ErrorMessages.ORDER_QUANTITY_EXCEEDED);
        }

        if(isOrderAllDrink(orderMenuQuantities)) {
            throw new OrderQuantityExceededException(ErrorMessages.INVALID_ORDER_ERROR);
        }
    }

    private static boolean isOrderAllDrink(List<OrderMenuQuantity> orderMenuQuantities) {
        return orderMenuQuantities.stream()
                .allMatch(orderMenuQuantity -> orderMenuQuantity.getFoodType() == FoodType.DRINK);
    }

    private static int getTotalOrderQuantity(List<OrderMenuQuantity> orderMenuQuantities) {
        return orderMenuQuantities.stream()
                .mapToInt(orderMenuQuantity -> orderMenuQuantity.getQuantity())
                .sum();
    }

    private static boolean isMaxOrderQuantity(int totalOrderQuantity) {
        return totalOrderQuantity > MAX_ORDER_QUANTITY;
    }

    private static List<OrderMenuQuantity> convertOrderItems(String orderItems) {
        List<OrderMenuQuantity> orderMenuQuantities = new ArrayList<>();
        String[] menus = orderItems.split(MENU_SEPARATOR);

        for (String menu : menus) {
            String name = menu.split(NAME_QUANTITY_SEPARATOR)[0];
            String quantity = menu.split(NAME_QUANTITY_SEPARATOR)[1];

            if (isDuplicateMenu(orderMenuQuantities, name)) {
                throw new InputValidationException(ErrorMessages.INVALID_ORDER_ERROR);
            }

            orderMenuQuantities.add(new OrderMenuQuantity(quantity, name));
        }
        return orderMenuQuantities;
    }

    private static boolean isDuplicateMenu(List<OrderMenuQuantity> orderMenuQuantities, String name) {
        return orderMenuQuantities.stream()
                .anyMatch(orderMenuQuantity -> orderMenuQuantity.equalsMenuName(name));
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
                .sum();
    }

    public int getMainMenuQuantity() {
        return orderMenus.stream()
                .filter(OrderMenuQuantity::isMainMenu)
                .mapToInt(OrderMenuQuantity::getQuantity)
                .sum();
    }

    public String toString() {
        return orderMenus.stream().map(orderMenuQuantity -> String.format(OutputMessage.ORDER_MENU_FORMAT.getMessage(),
                        orderMenuQuantity.getMenuName(),
                        orderMenuQuantity.getQuantity()))
                .collect(Collectors.joining("\n"));
    }
}
