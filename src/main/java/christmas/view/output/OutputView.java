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

public class OutputView {
    public void printOrder(OrderResponse orderResponse) {
        System.out.println(EVENT_PREVIEW_MESSAGE.getMessage());
        printEmptyLine();

        printOrderMenu(orderResponse);
        printTotalOrderAmount(orderResponse);
        printGiftMenu(orderResponse);
        printBenefitHistory(orderResponse);
        printTotalBenefitAmount(orderResponse);
        printFinalPaymentAmount(orderResponse);
        printEventBadge(orderResponse);
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    private void printEventBadge(OrderResponse orderResponse) {
        System.out.println(EVENT_BADGE.getMessage());
        String badge = "없음";
        if (!Objects.isNull(orderResponse.getEventBadge())) {
            badge = orderResponse.getEventBadge().getName();
        }
        System.out.println(badge);
        printEmptyLine();
    }

    private void printFinalPaymentAmount(OrderResponse orderResponse) {
        System.out.println(FINAL_PAYMENT_AMOUNT.getMessage());
        System.out.println(orderResponse.getFinalPaymentAmount());
        printEmptyLine();
    }

    private void printTotalBenefitAmount(OrderResponse orderResponse) {
        System.out.println(TOTAL_BENEFIT_AMOUNT.getMessage());
        System.out.printf("%s원\n", orderResponse.getBenefitDetails().getTotalBenefitAmount());
        printEmptyLine();
    }

    private void printBenefitHistory(OrderResponse orderResponse) {
        System.out.println(BENEFIT_HISTORY.getMessage());
        BenefitDetails benefitDetails = orderResponse.getBenefitDetails();

        boolean isBenefit = false;
        if (benefitDetails.getChristmasDDayDiscount() > 0) {
            System.out.printf("크리스마스 디데이 할인: %,d원", benefitDetails.getChristmasDDayDiscount());
            isBenefit = true;
        }

        if (benefitDetails.getWeekdayDiscount() > 0) {
            System.out.printf("평일 할인: %,d원", benefitDetails.getWeekdayDiscount());
            isBenefit = true;
        }

        if (!isBenefit) {
            System.out.println("없음");
        }
        printEmptyLine();
    }

    private void printGiftMenu(OrderResponse orderResponse) {
        System.out.println(GIFT_MENU.getMessage());

        String giftMenu = "없음";

        if (orderResponse.getGiftMenu()) {
            giftMenu = "샴페인 1개";
        }
        System.out.println(giftMenu);
        printEmptyLine();
    }

    private void printTotalOrderAmount(OrderResponse orderResponse) {
        System.out.println(TOTAL_ORDER_AMOUNT.getMessage());
        DecimalFormat df = new DecimalFormat("###,###원");
        System.out.println(df.format(orderResponse.getTotalOrderAmount()));
        printEmptyLine();
    }

    private void printOrderMenu(OrderResponse orderResponse) {
        System.out.println(ORDER_MENU.getMessage());
        OrderItems orderMenus = orderResponse.getOrderMenus();

        orderMenus.getOrderMenus().stream()
                .forEach(orderMenuQuantity -> System.out.printf("%s %s개\n", orderMenuQuantity.getMenuName(),
                        orderMenuQuantity.getQuantity()));
        printEmptyLine();
    }
}
