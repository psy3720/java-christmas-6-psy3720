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

class WeekendDiscountTest {

    @DisplayName("[주말 할인(금요일, 토요일)] 주말에는 메인 메뉴를 1개당 2,023원 할인한다.")
    @Test
    void weekendDiscount() {
        Day day = new Day(1);
        OrderItems orderItems = OrderItems.createOrderItems("타파스-1,초코케이크-1,티본스테이크-2");

        DiscountRequest request = new DiscountRequest(day, orderItems, TAPAS.getPrice() +
                CHOCO_CAKE.getPrice() +
                (T_BONE_STEAK.getPrice() * 2));
        WeekendDiscount weekendDiscount = new WeekendDiscount();
        DiscountResponse response = weekendDiscount.calculateDiscount(request, new HashMap<>());

        assertThat(response.getFinalAmount())
                .isEqualTo(TAPAS.getPrice() + CHOCO_CAKE.getPrice() + (T_BONE_STEAK.getPrice() * 2) - (2023 * 2));
    }
}