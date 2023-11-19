package christmas.domain;

import christmas.common.Menu;
import java.util.Objects;

public class GiftEvent {
    public static final int DISCOUNT_THRESHOLD_AMOUNT = 120000;
    private Menu giftMenu;

    public GiftEvent(OrderItems orderItems) {
        if (isTotalAmountAboveDiscountThreshold(orderItems)) {
            giftMenu = Menu.CHAMPAGNE;
        }
    }

    private static boolean isTotalAmountAboveDiscountThreshold(OrderItems orderItems) {
        return orderItems.getTotalAmount() >= DISCOUNT_THRESHOLD_AMOUNT;
    }

    public boolean isGiftEvent() {
        return !Objects.isNull(giftMenu);
    }

    public int getGiftEventMenuPrice() {
        return giftMenu.getPrice();
    }
}
