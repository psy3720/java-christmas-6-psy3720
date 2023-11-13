package christmas.view.output;

public enum OutputMessage {
    EVENT_PREVIEW_MESSAGE("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_ORDER_AMOUNT("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFIT_HISTORY("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    FINAL_PAYMENT_AMOUNT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>"),

    ORDER_MENU_FORMAT("%s %s개"),
    TOTAL_ORDER_AMOUNT_FORMAT("###,###원"),
    FINAL_PAYMENT_AMOUNT_FORMAT("###,###원"),
    TOTAL_BENEFIT_AMOUNT_FORMAT("%s원"),
    NONE_FORMAT("없음");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
