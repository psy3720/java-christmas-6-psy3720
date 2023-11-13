package christmas.domain;

import christmas.common.Menu;
import java.util.Objects;

public class GiftEvent {
    public static final int DISCOUNT_THRESHOLD_AMOUNT = 120000;
    private Menu giftMenu;

    public GiftEvent(OrderItems orderItems) {
        if (orderItems.getTotalAmount() >= DISCOUNT_THRESHOLD_AMOUNT) {
            giftMenu = Menu.CHAMPAGNE;
        }
    }

    public boolean isGiftEvent() {
        return !Objects.isNull(giftMenu);
    }

    public int getGiftEventMenuPrice() {
        return giftMenu.getPrice();
    }
}
