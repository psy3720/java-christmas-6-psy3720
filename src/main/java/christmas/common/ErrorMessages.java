package christmas.common;

public enum ErrorMessages {
    INVALID_DATE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDER_QUANTITY_EXCEEDED("주문 개수가 최대 허용치를 초과하였습니다.");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
