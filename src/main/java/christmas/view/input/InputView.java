package christmas.view.input;

import static christmas.view.input.InputMessage.INQUIRY_DATE;
import static christmas.view.input.InputMessage.PLACE_ORDER;
import static christmas.view.input.InputMessage.WELCOME;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.OrderItems;
import christmas.utils.StringUtils;
import java.util.Arrays;
import java.util.function.Function;

public class InputView {
    public int readDay() {
        return getInput(input -> {
            if (!StringUtils.isDigit(input)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
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
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
        } while (true);
    }
}
