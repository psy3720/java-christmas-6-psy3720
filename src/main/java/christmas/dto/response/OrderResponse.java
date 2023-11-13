package christmas.dto.response;

import christmas.common.EventBadge;
import christmas.domain.GiftEvent;
import christmas.domain.OrderItems;

public class OrderResponse {
    private OrderItems orderMenus;
    private GiftEvent giftEvent;
    private BenefitDetails benefitDetails;
    private EventBadge eventBadge;

    public OrderResponse(OrderItems orderMenus, GiftEvent giftEvent, EventBadge eventBadge,
                         BenefitDetails benefitDetails) {
        this.orderMenus = orderMenus;
        this.eventBadge = eventBadge;
        this.benefitDetails = benefitDetails;
        this.giftEvent = giftEvent;
    }

    public OrderItems getOrderMenus() {
        return orderMenus;
    }

    public GiftEvent getGiftMenu() {
        return giftEvent;
    }

    public BenefitDetails getBenefitDetails() {
        return benefitDetails;
    }

    public EventBadge getEventBadge() {
        return eventBadge;
    }

    public int getTotalOrderAmount() {
        return orderMenus.getTotalAmount();
    }

    public int getFinalPaymentAmount() {
        return orderMenus.getTotalAmount() + benefitDetails.getTotalBenefitAmount();
    }
}
