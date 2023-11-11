package christmas.controller;

import christmas.domain.OrderItems;
import christmas.dto.OrderResponse;
import christmas.service.OrderProcessor;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class ChristmasPromotion {
    private final InputView inputView;
    private final OutputView outputView;

    private final OrderProcessor orderProcessor;

    public ChristmasPromotion() {
        this.outputView = new OutputView();
        this.inputView = new InputView();
        this.orderProcessor = new OrderProcessor();
    }

    public void order() {
        int day = inputView.readDay();
        OrderItems orderItems = inputView.readOrderMenuAndAmount();

        OrderResponse orderResponse = orderProcessor.order(day, orderItems);
        printResult(orderResponse);
    }

    private void printResult(OrderResponse orderResponse) {
        outputView.printOrder(orderResponse);
    }
}
