package christmas.view.input;

import static christmas.view.input.InputMessage.INQUIRY_DATE;
import static christmas.view.input.InputMessage.PLACE_ORDER;
import static christmas.view.input.InputMessage.WELCOME;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public int readDate() {
        System.out.println(WELCOME.getMessage());
        System.out.println(INQUIRY_DATE.getMessage());
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

    public String readOrderMenuAndAmount() {
        System.out.println(PLACE_ORDER.getMessage());
        String input = Console.readLine();
        return input;
    }
}
