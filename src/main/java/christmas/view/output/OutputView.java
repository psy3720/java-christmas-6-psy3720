package christmas.view.output;

import static christmas.view.output.OutputMessage.BENEFIT_HISTORY;
import static christmas.view.output.OutputMessage.EVENT_BADGE;
import static christmas.view.output.OutputMessage.EVENT_PREVIEW_MESSAGE;
import static christmas.view.output.OutputMessage.FINAL_PAYMENT_AMOUNT;
import static christmas.view.output.OutputMessage.GIFT_MENU;
import static christmas.view.output.OutputMessage.ORDER_MENU;
import static christmas.view.output.OutputMessage.TOTAL_BENEFIT_AMOUNT;
import static christmas.view.output.OutputMessage.TOTAL_ORDER_AMOUNT;

import christmas.dto.BenefitDetails;
import christmas.dto.Order;
import christmas.dto.OrderItems;
import java.text.DecimalFormat;

public class OutputView {
    public void printOrder(Order order) {
        System.out.println(EVENT_PREVIEW_MESSAGE.getMessage());
        System.out.println();

        printOrderMenu(order);
        printTotalOrderAmount(order);
        printGiftMenu(order);
        printBenefitHistory(order);
        printTotalBenefitAmount(order);
        printFinalPaymentAmount(order);
        printEventBadge(order);
    }

    private void printEventBadge(Order order) {
        System.out.println(EVENT_BADGE.getMessage());
        System.out.println(order.getEventBadge().getName());
        System.out.println();
    }

    private void printFinalPaymentAmount(Order order) {
        System.out.println(FINAL_PAYMENT_AMOUNT.getMessage());
        System.out.println(order.getFinalPaymentAmount());
        System.out.println();
    }

    private void printTotalBenefitAmount(Order order) {
        System.out.println(TOTAL_BENEFIT_AMOUNT.getMessage());
        System.out.printf("%s원\n", order.getBenefitDetails().getTotalBenefitAmount());
        System.out.println();
    }

    private void printBenefitHistory(Order order) {
        System.out.println(BENEFIT_HISTORY.getMessage());
        BenefitDetails benefitDetails = order.getBenefitDetails();

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
        System.out.println();
    }

    private void printGiftMenu(Order order) {
        System.out.println(GIFT_MENU.getMessage());

        String giftMenu = "없음";

        if (order.getGiftMenu()) {
            giftMenu = "샴페인 1개";
        }
        System.out.println(giftMenu);
        System.out.println();
    }

    private void printTotalOrderAmount(Order order) {
        System.out.println(TOTAL_ORDER_AMOUNT.getMessage());
        DecimalFormat df = new DecimalFormat("###,###원");
        System.out.println(df.format(order.getTotalOrderAmount()));
        System.out.println();
    }

    private void printOrderMenu(Order order) {
        System.out.println(ORDER_MENU.getMessage());
        OrderItems orderMenus = order.getOrderMenus();

        orderMenus.getOrderMenus().stream()
                .forEach(orderMenuQuantity -> System.out.printf("%s %s개\n", orderMenuQuantity.getMenuName(),
                        orderMenuQuantity.getQuantity()));
        System.out.println();
    }
}
