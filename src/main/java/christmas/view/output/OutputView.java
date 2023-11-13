package christmas.view.output;

import static christmas.view.output.OutputMessage.BENEFIT_HISTORY;
import static christmas.view.output.OutputMessage.EVENT_BADGE;
import static christmas.view.output.OutputMessage.EVENT_PREVIEW_MESSAGE;
import static christmas.view.output.OutputMessage.FINAL_PAYMENT_AMOUNT;
import static christmas.view.output.OutputMessage.GIFT_MENU;
import static christmas.view.output.OutputMessage.ORDER_MENU;
import static christmas.view.output.OutputMessage.TOTAL_BENEFIT_AMOUNT;
import static christmas.view.output.OutputMessage.TOTAL_ORDER_AMOUNT;

import christmas.domain.OrderItems;
import christmas.dto.response.BenefitDetails;
import christmas.dto.response.OrderResponse;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.function.Consumer;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private StringBuilder output;

    public void printOrder(OrderResponse orderResponse) {
        output = new StringBuilder();

        output.append(EVENT_PREVIEW_MESSAGE.getMessage())
                .append(LINE_SEPARATOR)
                .append(LINE_SEPARATOR);

        appendOrderMenu(orderResponse);
        appendTotalOrderAmount(orderResponse);
        appendGiftMenu(orderResponse);
        appendBenefitHistory(orderResponse);
        appendTotalBenefitAmount(orderResponse);
        appendFinalPaymentAmount(orderResponse);
        appendEventBadge(orderResponse);

        System.out.println(output);
    }

    private void append(OutputMessage outputMessage, Consumer<Void> action) {
        output.append(outputMessage.getMessage())
                .append(LINE_SEPARATOR);

        action.accept(null);
        output.append(LINE_SEPARATOR);
    }

    private void appendOrderMenu(OrderResponse orderResponse) {
        append(ORDER_MENU, unused -> {
            OrderItems orderMenus = orderResponse.getOrderMenus();
            orderMenus.getOrderMenus().stream()
                    .forEach(orderMenuQuantity -> output.append(
                            String.format("%s %s개",
                                    orderMenuQuantity.getMenuName(),
                                    orderMenuQuantity.getQuantity())
                    ));
        });
    }

    private void appendTotalOrderAmount(OrderResponse orderResponse) {
        append(TOTAL_ORDER_AMOUNT, unused -> {
            DecimalFormat df = new DecimalFormat("###,###원");
            output.append(df.format(orderResponse.getTotalOrderAmount()))
                    .append(LINE_SEPARATOR);
        });
    }

    private void appendGiftMenu(OrderResponse orderResponse) {
        append(GIFT_MENU, unused -> {
            String giftMenu = "없음";
            if (orderResponse.getGiftMenu()) {
                giftMenu = "샴페인 1개";
            }
            output.append(giftMenu)
                    .append(LINE_SEPARATOR);
        });
    }

    private void appendBenefitHistory(OrderResponse orderResponse) {
        append(BENEFIT_HISTORY, unused -> {
            BenefitDetails benefitDetails = orderResponse.getBenefitDetails();
            boolean isBenefit = false;
            if (benefitDetails.getChristmasDDayDiscount() > 0) {
                output.append(String.format("크리스마스 디데이 할인: %,d원", benefitDetails.getChristmasDDayDiscount()))
                        .append(LINE_SEPARATOR);
                isBenefit = true;
            }

            if (benefitDetails.getWeekdayDiscount() > 0) {
                output.append(String.format("평일 할인: %,d원", benefitDetails.getWeekdayDiscount()))
                        .append(LINE_SEPARATOR);
                isBenefit = true;
            }

            if (benefitDetails.getWeekendDiscount() > 0) {
                output.append(String.format("주말 할인: %,d원", benefitDetails.getWeekendDiscount()))
                        .append(LINE_SEPARATOR);
                isBenefit = true;
            }

            if (!isBenefit) {
                output.append("없음")
                        .append(LINE_SEPARATOR);
            }
        });
    }

    private void appendTotalBenefitAmount(OrderResponse orderResponse) {
        append(TOTAL_BENEFIT_AMOUNT, unused -> {
            output.append(String.format("%s원", orderResponse.getBenefitDetails().getTotalBenefitAmount()))
                    .append(LINE_SEPARATOR);
        });
    }

    private void appendFinalPaymentAmount(OrderResponse orderResponse) {
        append(FINAL_PAYMENT_AMOUNT, unused -> {
            output.append(orderResponse.getFinalPaymentAmount())
                    .append(LINE_SEPARATOR);
        });
    }

    private void appendEventBadge(OrderResponse orderResponse) {
        append(EVENT_BADGE, unused -> {
            String badge = "없음";
            if (!Objects.isNull(orderResponse.getEventBadge())) {
                badge = orderResponse.getEventBadge().getName();
            }
            output.append(badge)
                    .append(LINE_SEPARATOR);
        });
    }
}
