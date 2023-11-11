package christmas.domain;

import christmas.common.FoodType;
import christmas.common.Menu;
import christmas.utils.StringUtils;
import java.util.Objects;

public class OrderMenuQuantity {
    private int quantity;
    private Menu menu;

    public OrderMenuQuantity(String quantity, String menu) {
        validate(quantity, menu);
        this.quantity = Integer.parseInt(quantity);
        this.menu = Menu.of(menu);
    }

    private void validate(String quantity, String menu) {
        if (!StringUtils.isDigit(quantity)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        if (Integer.parseInt(quantity) < 0) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        if (Objects.isNull(Menu.of(menu))) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
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
}
