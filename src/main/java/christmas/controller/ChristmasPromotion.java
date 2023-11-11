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

    public ChristmasPromotion() {
        this.outputView = new OutputView();
        this.inputView = new InputView();
        this.orderProcessor = new OrderProcessor();
    }

    public void order() {
        int day = inputView.readDate();
        String orderMenuAndAmount = inputView.readOrderMenuAndAmount();
        OrderItems orderItems = OrderItems.createOrderItems(orderMenuAndAmount);

        Order order = orderProcessor.order(createOrderRequest(day, orderItems));
        printResult(order);
    }

    private void printResult(Order order) {
        outputView.printOrder(order);
    }
}
