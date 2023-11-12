package christmas.dto.response;

import christmas.common.EventBadge;
import christmas.domain.OrderItems;

public class OrderResponse {
    private OrderItems orderMenus;
    private Boolean isGiftMenu;
    private BenefitDetails benefitDetails;
    private EventBadge eventBadge;

    public OrderResponse(OrderItems orderMenus, boolean isGiftMenu, EventBadge eventBadge,
                         BenefitDetails benefitDetails) {
        this.orderMenus = orderMenus;
        this.eventBadge = eventBadge;
        this.benefitDetails = benefitDetails;
        this.isGiftMenu = isGiftMenu;
    }

    public OrderItems getOrderMenus() {
        return orderMenus;
    }

    public Boolean getGiftMenu() {
        return isGiftMenu;
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
