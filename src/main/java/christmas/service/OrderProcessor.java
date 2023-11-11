package christmas.service;

import christmas.Discount;
import christmas.common.EventBadge;
import christmas.domain.OrderItems;
import christmas.dto.BenefitDetails;
import christmas.dto.OrderResponse;

public class OrderProcessor {
    private final Discount discount;

    public OrderProcessor() {
        this.discount = new Discount();
    }

    private BenefitDetails benefitCalculate(int day, OrderItems orderItems) {
        int amount = orderItems.getTotalAmount();

        amount = discount.getChristmasDDayDiscount(day, orderItems, amount);

        return new BenefitDetails(0, 0, 0, 0);
    }

    public OrderResponse order(int day, OrderItems orderItems) {
        BenefitDetails benefitDetails = benefitCalculate(day, orderItems);
        boolean isGiftMenu = isGiftMenu(orderItems);

        EventBadge eventBadge = EventBadge.getBadgeBenefitAmount(benefitDetails.getTotalBenefitAmount());
        return new OrderResponse(orderItems, isGiftMenu, eventBadge, benefitDetails);
    }

    private static boolean isGiftMenu(OrderItems orderItems) {
        return orderItems.getTotalAmount() >= 120000;
    }
}
