package christmas.exception;

import christmas.common.ErrorMessages;

public class OrderQuantityExceededException extends IllegalStateException {
    private static final String ERROR_MESSAGE = "[ERROR] ";

    public OrderQuantityExceededException(ErrorMessages errorMessage) {
        super(ERROR_MESSAGE.concat(errorMessage.getMessage()));
    }
}
