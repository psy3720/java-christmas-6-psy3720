package christmas.domain.discount;

import static christmas.common.Menu.TAPAS;
import static christmas.common.Menu.ZERO_COLA;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChristmasDDayDiscountTest {

    @DisplayName("[크리스마스 디데이 할인] 12월 1일에는 총 주문 금액에서 1,000원을 할인한다.")
    @Test
    void christmasDDayDiscount() {
        Day day = new Day(1);
        OrderItems orderItems = OrderItems.createOrderItems("타파스-1,제로콜라-1");
        DiscountRequest request = new DiscountRequest(day, orderItems,
                TAPAS.getPrice() + ZERO_COLA.getPrice());

        ChristmasDDayDiscount christmasDDayDiscount = new ChristmasDDayDiscount();
        DiscountResponse response = christmasDDayDiscount.calculateDiscount(request, new HashMap<>());
        int discountAmount = response.getFinalAmount();

        assertThat(discountAmount).isEqualTo(TAPAS.getPrice() + ZERO_COLA.getPrice() - 1000);
    }
}
