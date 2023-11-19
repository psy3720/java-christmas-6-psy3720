package christmas.domain.discount;

import static christmas.common.Menu.CHOCO_CAKE;
import static christmas.common.Menu.TAPAS;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayDiscountTest {

    @DisplayName("[평일 할인(일요일 ~ 목요일)] 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인한다.")
    @Test
    void weekdayDiscount() {
        Day day = new Day(4);
        OrderItems orderItems = OrderItems.createOrderItems("타파스-1,초코케이크-1");
        DiscountRequest request = new DiscountRequest(day, orderItems,
                TAPAS.getPrice() + CHOCO_CAKE.getPrice());

        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        DiscountResponse response = weekdayDiscount.calculateDiscount(request, new HashMap<>());

        assertThat(response.getFinalAmount()).isEqualTo(TAPAS.getPrice() + CHOCO_CAKE.getPrice() - 2023);
    }
}