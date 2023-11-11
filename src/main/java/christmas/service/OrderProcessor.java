package christmas.service;

import christmas.Discount;
import christmas.common.EventBadge;
import christmas.dto.BenefitDetails;
import christmas.dto.Order;
import christmas.dto.OrderItems;
import christmas.dto.OrderRequest;

public class OrderProcessor {
    private final Discount discount;

    public OrderProcessor() {
        this.discount = new Discount();
    }

    public Order order(OrderRequest orderRequest) {
        OrderItems orderMenuAndAmount = orderRequest.getOrderMenuAndAmount();

        boolean isGiftMenu = false;
        if (orderMenuAndAmount.getTotalAmount() >= 120000) {
            isGiftMenu = true;
        }

        return new Order(orderMenuAndAmount, isGiftMenu, EventBadge.getBadgeBenefitAmount(5000)
                , new BenefitDetails(0, 0, 0, 0));
    }
}
