package christmas.view.input;

import static christmas.view.input.InputMessage.INQUIRY_DATE;
import static christmas.view.input.InputMessage.PLACE_ORDER;
import static christmas.view.input.InputMessage.WELCOME;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.ErrorMessages;
import christmas.domain.OrderItems;
import christmas.exception.InputValidationException;
import christmas.utils.StringUtils;
import java.util.Arrays;
import java.util.function.Function;

public class InputView {
    public int readDay() {
        return getInput(input -> {
            if (!StringUtils.isDigit(input)) {
                throw new InputValidationException(ErrorMessages.INVALID_DATE_ERROR);
            }
            return Integer.parseInt(input);
        }, WELCOME, INQUIRY_DATE);
    }

    public OrderItems readOrderMenuAndAmount() {
        return getInput(OrderItems::createOrderItems, PLACE_ORDER);
    }

    public <T> T getInput(Function<String, T> parser, InputMessage... inputMessage) {
        Arrays.stream(inputMessage)
                .forEach(message -> message.getMessage());
        do {
            try {
                String input = Console.readLine();
                return parser.apply(input);
            } catch (InputValidationException e) {
                System.out.println(e);
            }
        } while (true);
    }
}
