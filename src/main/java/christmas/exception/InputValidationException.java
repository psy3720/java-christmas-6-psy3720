package christmas.exception;

import christmas.common.ErrorMessages;

public class InputValidationException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "[ERROR] ";

    public InputValidationException(ErrorMessages errorMessage) {
        super(ERROR_MESSAGE.concat(errorMessage.getMessage()));
    }
}
