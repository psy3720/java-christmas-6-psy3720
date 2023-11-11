package christmas.controller;

import static christmas.dto.OrderRequest.createOrderRequest;

import christmas.dto.Order;
import christmas.dto.OrderItems;
import christmas.service.OrderProcessor;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class ChristmasPromotion {
    private final OutputView outputView;
    private final InputView inputView;
    private final OrderProcessor orderProcessor;

    public ChristmasPromotion(OutputView outputView, InputView inputView, OrderProcessor orderProcessor) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.orderProcessor = orderProcessor;
    }

    public Order order() {
        int day = inputView.readDate();
        String orderMenuAndAmount = inputView.readOrderMenuAndAmount();
        OrderItems orderItems = OrderItems.createOrderItems(orderMenuAndAmount);

        return orderProcessor.order(createOrderRequest(day, orderItems));
    }
}
