package christmas.view.output;

import static christmas.view.output.DiscountMessage.getDiscountFormat;
import static christmas.view.output.OutputMessage.BENEFIT_HISTORY;
import static christmas.view.output.OutputMessage.EVENT_BADGE;
import static christmas.view.output.OutputMessage.EVENT_PREVIEW_MESSAGE;
import static christmas.view.output.OutputMessage.FINAL_PAYMENT_AMOUNT;
import static christmas.view.output.OutputMessage.FINAL_PAYMENT_AMOUNT_FORMAT;
import static christmas.view.output.OutputMessage.GIFT_EVENT_FORMAT;
import static christmas.view.output.OutputMessage.GIFT_MENU;
import static christmas.view.output.OutputMessage.GIFT_MENU_FORMAT;
import static christmas.view.output.OutputMessage.NONE_FORMAT;
import static christmas.view.output.OutputMessage.ORDER_MENU;
import static christmas.view.output.OutputMessage.ORDER_MENU_FORMAT;
import static christmas.view.output.OutputMessage.TOTAL_BENEFIT_AMOUNT;
import static christmas.view.output.OutputMessage.TOTAL_BENEFIT_AMOUNT_FORMAT;
import static christmas.view.output.OutputMessage.TOTAL_ORDER_AMOUNT;
import static christmas.view.output.OutputMessage.TOTAL_ORDER_AMOUNT_FORMAT;
import static java.lang.String.format;

import christmas.domain.GiftEvent;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountType;
import christmas.dto.response.BenefitDetails;
import christmas.dto.response.OrderResponse;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private StringBuilder output;

    public OutputView() {
        output = new StringBuilder();
    }

    public void printOrder(OrderResponse orderResponse) {
        appendEventPreview();
        appendOrderMenu(orderResponse);
        appendTotalOrderAmount(orderResponse);
        appendGiftMenu(orderResponse);
        appendBenefitHistory(orderResponse);
        appendTotalBenefitAmount(orderResponse);
        appendFinalPaymentAmount(orderResponse);
        appendEventBadge(orderResponse);

        System.out.println(output);
    }

    private void appendEventPreview() {
        output.append(EVENT_PREVIEW_MESSAGE.getMessage())
                .append(LINE_SEPARATOR)
                .append(LINE_SEPARATOR);
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
                            format(ORDER_MENU_FORMAT.getMessage(),
                                    orderMenuQuantity.getMenuName(),
                                    orderMenuQuantity.getQuantity())
                    ));
        });
    }

    private void appendTotalOrderAmount(OrderResponse orderResponse) {
        append(TOTAL_ORDER_AMOUNT, unused -> {
            DecimalFormat df = new DecimalFormat(TOTAL_ORDER_AMOUNT_FORMAT.getMessage());
            output.append(df.format(orderResponse.getTotalOrderAmount()))
                    .append(LINE_SEPARATOR);
        });
    }

    private void appendGiftMenu(OrderResponse orderResponse) {
        append(GIFT_MENU, unused -> {
            String giftMenu = NONE_FORMAT.getMessage();
            if (orderResponse.getGiftMenu().isGiftEvent()) {
                giftMenu = GIFT_MENU_FORMAT.getMessage();
            }
            output.append(giftMenu)
                    .append(LINE_SEPARATOR);
        });
    }

    private void appendBenefitHistory(OrderResponse orderResponse) {
        appendDiscountHistory(orderResponse);
        appendGiftEvent(orderResponse);
    }

    private void appendDiscountHistory(OrderResponse orderResponse) {
        output.append(BENEFIT_HISTORY.getMessage())
                .append(LINE_SEPARATOR);

        BenefitDetails benefitDetails = orderResponse.getBenefitDetails();
        Map<DiscountType, Integer> discountResults = benefitDetails.getDiscountResults();

        if (orderResponse.getBenefitDetails().getTotalBenefitAmount() == 0) {
            output.append(NONE_FORMAT.getMessage())
                    .append(LINE_SEPARATOR);
            return;
        }

        for (DiscountType discountType : discountResults.keySet()) {
            output.append(format(getDiscountFormat(discountType), discountResults.get(discountType)))
                    .append(LINE_SEPARATOR);
        }
    }

    private void appendGiftEvent(OrderResponse orderResponse) {
        GiftEvent giftMenu = orderResponse.getGiftMenu();

        if (giftMenu.isGiftEvent()) {
            output.append(format(GIFT_EVENT_FORMAT.getMessage(), giftMenu.getGiftEventMenuPrice()))
                    .append(LINE_SEPARATOR);
        }
    }

    private void appendTotalBenefitAmount(OrderResponse orderResponse) {
        append(TOTAL_BENEFIT_AMOUNT, unused -> {
            output.append(format(TOTAL_BENEFIT_AMOUNT_FORMAT.getMessage(),
                            orderResponse.getBenefitDetails().getTotalBenefitAmount()))
                    .append(LINE_SEPARATOR);
        });
    }

    private void appendFinalPaymentAmount(OrderResponse orderResponse) {
        append(FINAL_PAYMENT_AMOUNT, unused -> {
            output.append(
                            format(FINAL_PAYMENT_AMOUNT_FORMAT.getMessage()
                                    , orderResponse.getFinalPaymentAmount()))
                    .append(LINE_SEPARATOR);
        });
    }

    private void appendEventBadge(OrderResponse orderResponse) {
        append(EVENT_BADGE, unused -> {
            String badge = NONE_FORMAT.getMessage();
            if (!Objects.isNull(orderResponse.getEventBadge())) {
                badge = orderResponse.getEventBadge().getName();
            }
            output.append(badge)
                    .append(LINE_SEPARATOR);
        });
    }
}
