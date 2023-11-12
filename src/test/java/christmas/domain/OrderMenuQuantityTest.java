package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.common.ErrorMessages;
import christmas.exception.InputValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderMenuQuantityTest {

    @DisplayName("주문메뉴와 수량을 문자열로 입력하면 주문메뉴 수량이 생성된다.")
    @Test
    void createOrderMenuQuantity() {
        String menu = "샴페인";
        String quantity = "3";

        OrderMenuQuantity orderMenuQuantity = new OrderMenuQuantity(quantity, menu);

        assertThat(orderMenuQuantity.getMenuName()).isEqualTo(menu);
        assertThat(Integer.parseInt(quantity)).isEqualTo(orderMenuQuantity.getQuantity());
    }

    @DisplayName("[에러] 주문수량을 잘못입력하면 에러가 발생한다.(숫자가 아닌 경우)")
    @Test
    void validateInput_quantity_digit() {
        String menu = "샴페인";
        String quantity = "3a";

        assertThatThrownBy(() -> new OrderMenuQuantity(quantity, menu))
                .isInstanceOf(InputValidationException.class)
                .hasMessageContaining(ErrorMessages.INVALID_ORDER_ERROR.getMessage());
    }

    @DisplayName("[에러] 주문수량을 잘못입력하면 에러가 발생한다.(음수)")
    @Test
    void validateInput_quantity_negative() {
        String menu = "샴페인";
        String quantity = "-3";

        assertThatThrownBy(() -> new OrderMenuQuantity(quantity, menu))
                .isInstanceOf(InputValidationException.class)
                .hasMessageContaining(ErrorMessages.INVALID_ORDER_ERROR.getMessage());
    }
}
