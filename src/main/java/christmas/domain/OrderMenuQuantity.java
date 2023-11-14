package christmas.domain;

import static christmas.utils.StringUtils.isDigit;
import static java.util.Objects.isNull;

import christmas.common.ErrorMessages;
import christmas.common.FoodType;
import christmas.common.Menu;
import christmas.exception.InputValidationException;
import java.util.Objects;

public class OrderMenuQuantity {
    public static final int ORDER_MIN_QUANTITY = 1;
    private int quantity;
    private Menu menu;

    public OrderMenuQuantity(String quantity, String menu) {
        validate(quantity, menu);
        this.quantity = Integer.parseInt(quantity);
        this.menu = Menu.of(menu);
    }

    private void validate(String quantity, String menu) {
        if (!isDigit(quantity)) {
            throw new InputValidationException(ErrorMessages.INVALID_ORDER_ERROR);
        }

        if (isOrderMinQuantity(quantity)) {
            throw new InputValidationException(ErrorMessages.INVALID_ORDER_ERROR);
        }

        if (isNull(Menu.of(menu))) {
            throw new InputValidationException(ErrorMessages.INVALID_ORDER_ERROR);
        }
    }

    private static boolean isOrderMinQuantity(String quantity) {
        return Integer.parseInt(quantity) < ORDER_MIN_QUANTITY;
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalAmount() {
        return menu.getPrice() * quantity;
    }

    public boolean isDesertMenu() {
        return menu.getFoodType() == FoodType.DESERT;
    }

    public boolean isMainMenu() {
        return menu.getFoodType() == FoodType.MAIN;
    }

    public boolean equalsMenuName(String name) {
        return Objects.equals(menu.getName(), name);
    }

    public FoodType getFoodType() {
        return menu.getFoodType();
    }
}
