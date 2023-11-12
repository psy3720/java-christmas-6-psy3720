package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.common.ErrorMessages;
import christmas.common.Menu;
import christmas.exception.InputValidationException;
import christmas.exception.OrderQuantityExceededException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderItemsTest {

    @DisplayName("주문할 메뉴와 개수를 입력하면 주문 메뉴가 생성된다.")
    @Test
    void createTest() {
        String input = "타파스-1,제로콜라-1";
        OrderItems orderItems = OrderItems.createOrderItems(input);

        List<OrderMenuQuantity> orderMenus = orderItems.getOrderMenus();

        assertThat(orderMenus)
                .extracting("quantity")
                .containsExactly(1, 1);
        assertThat(orderMenus)
                .extracting("menu")
                .containsExactly(Menu.TAPAS, Menu.ZERO_COLA);
    }

    @DisplayName("주문 메뉴의 총 금액을 반환한다.")
    @Test
    void getTotalAmount() {
        String input = "타파스-1,제로콜라-1";
        OrderItems orderItems = OrderItems.createOrderItems(input);

        int amount = orderItems.getTotalAmount();

        assertThat(amount).isEqualTo(Menu.TAPAS.getPrice() + Menu.ZERO_COLA.getPrice());
    }

    @DisplayName("디저트 메뉴의 총 수량을 반환한다.")
    @Test
    void getDesertMenuQuantity() {
        String input = "초코케이크-4,아이스크림-4,티본스테이크-2";
        OrderItems orderItems = OrderItems.createOrderItems(input);

        int desertMenuQuantity = orderItems.getDesertMenuQuantity();

        assertThat(desertMenuQuantity).isEqualTo(8);
    }

    @DisplayName("메인 메뉴의 총 수량을 반환한다.")
    @Test
    void getMainMenuQuantity() {
        String input = "티본스테이크-4,바비큐립-5,제로콜라-3";
        OrderItems orderItems = OrderItems.createOrderItems(input);

        int mainMenuQuantity = orderItems.getMainMenuQuantity();

        assertThat(mainMenuQuantity).isEqualTo(9);
    }

    @DisplayName("[에러] 주문항목의 개수가 20개가 넘어가면 에러가 발생한다.")
    @Test
    void validate_totalQuantity() {
        String input = "타파스-10,제로콜라-11";

        assertThatThrownBy(() -> OrderItems.createOrderItems(input))
                .isInstanceOf(OrderQuantityExceededException.class)
                .hasMessageContaining(ErrorMessages.ORDER_QUANTITY_EXCEEDED.getMessage());
    }

    @DisplayName("[에러] 입력한 메뉴 형식이 다른 경우 에러가 발생한다.(메뉴명-수량을 콤마로 구분하여 작성)")
    @Test
    void inputValidate() {
        String input = "티본스테이크-4,  바비큐립-1";

        assertThatThrownBy(() -> OrderItems.createOrderItems(input))
                .isInstanceOf(InputValidationException.class)
                .hasMessageContaining(ErrorMessages.INVALID_ORDER_ERROR.getMessage());
    }

    @DisplayName("[에러] 중복메뉴를 입력한 경우 에러가 발생한다.")
    @Test
    void inputValidate_duplicate() {
        String input = "시저샐러드-1,시저샐러드-1";

        assertThatThrownBy(() -> OrderItems.createOrderItems(input))
                .isInstanceOf(InputValidationException.class)
                .hasMessageContaining(ErrorMessages.INVALID_ORDER_ERROR.getMessage());
    }
}
