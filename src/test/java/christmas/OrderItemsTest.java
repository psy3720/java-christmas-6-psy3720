package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.common.Menu;
import christmas.dto.OrderItems;
import christmas.dto.OrderMenuQuantity;
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
}
