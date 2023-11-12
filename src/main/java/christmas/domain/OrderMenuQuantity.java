package christmas.domain;

import christmas.common.ErrorMessages;
import christmas.common.FoodType;
import christmas.common.Menu;
import christmas.exception.InputValidationException;
import christmas.utils.StringUtils;
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
        if (!StringUtils.isDigit(quantity)) {
            throw new InputValidationException(ErrorMessages.INVALID_ORDER_ERROR);
        }

        if (Integer.parseInt(quantity) < ORDER_MIN_QUANTITY) {
            throw new InputValidationException(ErrorMessages.INVALID_ORDER_ERROR);
        }

        if (Objects.isNull(Menu.of(menu))) {
            throw new InputValidationException(ErrorMessages.INVALID_ORDER_ERROR);
        }
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
}
