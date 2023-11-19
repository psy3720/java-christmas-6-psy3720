package christmas.domain.discount;

import static christmas.common.Menu.CHOCO_CAKE;
import static christmas.common.Menu.TAPAS;
import static christmas.common.Menu.T_BONE_STEAK;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialDiscountTest {

    @DisplayName("[특별 할인] 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인한다.")
    @Test
    void specialDiscount() {
        Day day = new Day(25);
        OrderItems orderItems = OrderItems.createOrderItems("타파스-1,초코케이크-1,티본스테이크-2");

        DiscountRequest request = new DiscountRequest(day, orderItems, TAPAS.getPrice() +
                CHOCO_CAKE.getPrice() +
                (T_BONE_STEAK.getPrice() * 2));
        SpecialDiscount specialDiscount = new SpecialDiscount();
        DiscountResponse response = specialDiscount.calculateDiscount(request, new HashMap<>());

        assertThat(response.getFinalAmount()).isEqualTo(
                TAPAS.getPrice() +
                        CHOCO_CAKE.getPrice() +
                        (T_BONE_STEAK.getPrice() * 2) - 1000);
    }
}