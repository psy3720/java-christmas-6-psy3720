package christmas.dto;

import christmas.common.EventBadge;

public class Order {
    private OrderItems orderMenus; // 주문메뉴
    private Boolean isGiftMenu; // 증정메뉴 여부
    private BenefitDetails benefitDetails; // 혜택 내역
    private EventBadge eventBadge; // 12월 산타이벤트 뱃지

    public Order(OrderItems orderMenus, boolean isGiftMenu, EventBadge eventBadge, BenefitDetails benefitDetails) {
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
