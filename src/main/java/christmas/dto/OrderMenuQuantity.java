package christmas.dto;

import christmas.common.Menu;

public class OrderMenuQuantity {
    private int quantity;
    private Menu menu;

    public OrderMenuQuantity(int quantity, Menu menu) {
        this.quantity = quantity;
        this.menu = menu;
    }
}
