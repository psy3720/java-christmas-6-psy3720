package christmas.controller;

import static christmas.dto.request.OrderRequest.createOrderRequest;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.dto.request.OrderRequest;
import christmas.dto.response.OrderResponse;
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
        Day day = inputView.readDay();
        OrderItems orderItems = inputView.readOrderMenuAndAmount();
        OrderRequest orderRequest = createOrderRequest(day, orderItems);

        OrderResponse orderResponse = orderProcessor.order(orderRequest);
        printResult(orderResponse);
    }

    private void printResult(OrderResponse orderResponse) {
        outputView.printOrder(orderResponse);
    }
}
