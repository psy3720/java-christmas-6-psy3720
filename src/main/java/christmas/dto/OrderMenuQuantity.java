package christmas.dto;

import christmas.common.FoodType;
import christmas.common.Menu;

public class OrderMenuQuantity {
    private int quantity;
    private Menu menu;

    public OrderMenuQuantity(int quantity, Menu menu) {
        this.quantity = quantity;
        this.menu = menu;
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
