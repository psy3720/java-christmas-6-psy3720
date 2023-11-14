package christmas.service;

import christmas.common.EventBadge;
import christmas.domain.Day;
import christmas.domain.GiftEvent;
import christmas.domain.OrderItems;
import christmas.domain.discount.Discount;
import christmas.dto.request.DiscountRequest;
import christmas.dto.request.OrderRequest;
import christmas.dto.response.BenefitDetails;
import christmas.dto.response.DiscountResponse;
import christmas.dto.response.OrderResponse;
import java.util.HashMap;

public class OrderProcessor {

    public static final int TOTAL_AMOUNT = 10000;

    public OrderResponse order(OrderRequest request) {
        Day day = request.getDay();
        OrderItems orderItems = request.getOrderItems();

        BenefitDetails benefitDetails = benefitCalculate(day, orderItems);
        GiftEvent giftEvent = new GiftEvent(orderItems);

        return new OrderResponse(orderItems, giftEvent, benefitDetails);
    }

    private BenefitDetails benefitCalculate(Day day, OrderItems orderItems) {
        int amount = orderItems.getTotalAmount();

        if(amount < TOTAL_AMOUNT) {
            return new BenefitDetails(new HashMap<>());
        }

        DiscountResponse discountResponse = Discount.calculateDiscount(new DiscountRequest(day, orderItems, amount));
        return new BenefitDetails(discountResponse.getDiscountResults());
    }
}
