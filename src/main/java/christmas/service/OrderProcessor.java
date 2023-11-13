package christmas.service;

import christmas.common.EventBadge;
import christmas.common.Menu;
import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.Discount;
import christmas.dto.request.DiscountRequest;
import christmas.dto.request.OrderRequest;
import christmas.dto.response.BenefitDetails;
import christmas.dto.response.DiscountResponse;
import christmas.dto.response.OrderResponse;

public class OrderProcessor {
    public static final int DISCOUNT_THRESHOLD_AMOUNT = 120000;

    public OrderProcessor() {

    }

    public OrderResponse order(OrderRequest request) {
        Day day = request.getDay();
        OrderItems orderItems = request.getOrderItems();

        BenefitDetails benefitDetails = benefitCalculate(day, orderItems);
        boolean isGiftMenu = isGiftMenu(orderItems);

        EventBadge eventBadge = EventBadge.getBadgeBenefitAmount(benefitDetails.getTotalBenefitAmount());
        return new OrderResponse(orderItems, isGiftMenu, eventBadge, benefitDetails);
    }

    private BenefitDetails benefitCalculate(Day day, OrderItems orderItems) {
        int amount = orderItems.getTotalAmount();

        int giftEventAmount = 0;
        if (isGiftMenu(orderItems)) {
            giftEventAmount = Menu.CHAMPAGNE.getPrice() * -1;
        }

        DiscountResponse discountResponse = Discount.calculateDiscount(new DiscountRequest(day, orderItems, amount));

        return new BenefitDetails(discountResponse.getDiscountResults(), giftEventAmount);
    }

    private static boolean isGiftMenu(OrderItems orderItems) {
        return orderItems.getTotalAmount() >= DISCOUNT_THRESHOLD_AMOUNT;
    }
}
