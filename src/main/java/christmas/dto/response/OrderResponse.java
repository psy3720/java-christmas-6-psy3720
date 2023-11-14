package christmas.dto.response;

import christmas.common.EventBadge;
import christmas.domain.GiftEvent;
import christmas.domain.OrderItems;

public class OrderResponse {
    private OrderItems orderMenus;
    private GiftEvent giftEvent;
    private BenefitDetails benefitDetails;
    private EventBadge eventBadge;

    public OrderResponse(OrderItems orderMenus, GiftEvent giftEvent,
                         BenefitDetails benefitDetails) {
        this.orderMenus = orderMenus;
        this.benefitDetails = benefitDetails;
        this.giftEvent = giftEvent;
        this.eventBadge = EventBadge.getBadgeBenefitAmount(getBenefitTotalAmount());
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

    public int getBenefitTotalAmount() {
        int totalBenefitAmount = benefitDetails.getTotalBenefitAmount();

        if (giftEvent.isGiftEvent()) {
            totalBenefitAmount += (-1 * giftEvent.getGiftEventMenuPrice());
        }

        return totalBenefitAmount;
    }
}
