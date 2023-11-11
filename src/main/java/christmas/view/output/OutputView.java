package christmas.view.output;

import static christmas.view.output.OutputMessage.BENEFIT_HISTORY;
import static christmas.view.output.OutputMessage.EVENT_BADGE;
import static christmas.view.output.OutputMessage.FINAL_PAYMENT_AMOUNT;
import static christmas.view.output.OutputMessage.GIFT_MENU;
import static christmas.view.output.OutputMessage.ORDER_MENU;
import static christmas.view.output.OutputMessage.TOTAL_BENEFIT_AMOUNT;
import static christmas.view.output.OutputMessage.TOTAL_ORDER_AMOUNT;

public class OutputView {
    public void printOrder() {
        System.out.println(ORDER_MENU);
        System.out.println(TOTAL_ORDER_AMOUNT);
        System.out.println(GIFT_MENU);
        System.out.println(BENEFIT_HISTORY);
        System.out.println(TOTAL_BENEFIT_AMOUNT);
        System.out.println(FINAL_PAYMENT_AMOUNT);
        System.out.println(EVENT_BADGE);
    }
}
