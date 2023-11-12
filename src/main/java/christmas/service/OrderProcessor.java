package christmas.service;

import christmas.domain.Discount;
import christmas.common.EventBadge;
import christmas.common.Menu;
import christmas.domain.OrderItems;
import christmas.dto.response.BenefitDetails;
import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import christmas.dto.request.OrderRequest;
import christmas.dto.response.OrderResponse;

public class OrderProcessor {
    public static final int DISCOUNT_THRESHOLD_AMOUNT = 120000;
    private final Discount discount;

    public OrderProcessor() {
        this.discount = new Discount();
    }

    public OrderResponse order(OrderRequest request) {
        int day = request.getDay();
        OrderItems orderItems = request.getOrderItems();

        BenefitDetails benefitDetails = benefitCalculate(day, orderItems);
        boolean isGiftMenu = isGiftMenu(orderItems);

        EventBadge eventBadge = EventBadge.getBadgeBenefitAmount(benefitDetails.getTotalBenefitAmount());
        return new OrderResponse(orderItems, isGiftMenu, eventBadge, benefitDetails);
    }

    private BenefitDetails benefitCalculate(int day, OrderItems orderItems) {
        int amount = orderItems.getTotalAmount();

        int giftEventAmount = 0;
        if (isGiftMenu(orderItems)) {
            giftEventAmount = Menu.CHAMPAGNE.getPrice();
        }

        DiscountResponse christmasDDayDiscount = discount.getChristmasDDayDiscount(
                new DiscountRequest(day, orderItems, amount));
        DiscountResponse weekdayDiscount = discount.getWeekdayDiscount(
                new DiscountRequest(day, orderItems,
                        christmasDDayDiscount.getFinalAmount()));
        DiscountResponse weekendDiscount = discount.getWeekendDiscount(
                new DiscountRequest(day, orderItems,
                        weekdayDiscount.getFinalAmount()));
        DiscountResponse specialDiscount = discount.getSpecialDiscount(
                new DiscountRequest(day, orderItems,
                        weekendDiscount.getFinalAmount()));

        return new BenefitDetails(
                christmasDDayDiscount.getDiscountAmount(),
                weekdayDiscount.getDiscountAmount(),
                weekendDiscount.getDiscountAmount(),
                specialDiscount.getDiscountAmount(),
                giftEventAmount);
    }

    private static boolean isGiftMenu(OrderItems orderItems) {
        return orderItems.getTotalAmount() >= DISCOUNT_THRESHOLD_AMOUNT;
    }
}
