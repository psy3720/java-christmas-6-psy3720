package christmas;

import static christmas.common.Menu.CHOCO_CAKE;
import static christmas.common.Menu.TAPAS;
import static christmas.common.Menu.T_BONE_STEAK;
import static christmas.common.Menu.ZERO_COLA;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.OrderItems;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountTest {

    @DisplayName("[크리스마스 디데이 할인] 12월 1일에는 총 주문 금액에서 1,000원을 할인한다.")
    @Test
    void christmasDDayDiscount() {
        Discount discount = new Discount();

        int day = 1;
        OrderItems orderItems = OrderItems.createOrderItems("타파스-1,제로콜라-1");
        int discountAmount = discount.getChristmasDDayDiscount(day, orderItems, TAPAS.getPrice() + ZERO_COLA.getPrice());

        assertThat(discountAmount).isEqualTo(TAPAS.getPrice() + ZERO_COLA.getPrice() - 1000);
    }

    @DisplayName("[평일 할인(일요일 ~ 목요일)] 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인한다.")
    @Test
    void weekdayDiscount() {
        Discount discount = new Discount();

        int day = 4;
        OrderItems orderItems = OrderItems.createOrderItems("타파스-1,초코케이크-1");

        int discountAmount = discount.getWeekdayDiscount(day, orderItems, TAPAS.getPrice() + CHOCO_CAKE.getPrice());

        assertThat(discountAmount).isEqualTo(TAPAS.getPrice() + CHOCO_CAKE.getPrice() - 2023);
    }

    @DisplayName("[주말 할인(금요일, 토요일)] 주말에는 메인 메뉴를 1개당 2,023원 할인한다.")
    @Test
    void weekendDiscount() {
        Discount discount = new Discount();

        int day = 1;
        OrderItems orderItems = OrderItems.createOrderItems("타파스-1,초코케이크-1,티본스테이크-2");

        int discountAmount = discount.getWeekendDiscount(day, orderItems, TAPAS.getPrice() +
                CHOCO_CAKE.getPrice() +
                (T_BONE_STEAK.getPrice() * 2));

        assertThat(discountAmount)
                .isEqualTo(
                        TAPAS.getPrice() +
                                CHOCO_CAKE.getPrice() +
                                (T_BONE_STEAK.getPrice() * 2) - (2023 * 2)
                );
    }

    @DisplayName("[특별 할인] 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인한다.")
    @Test
    void specialDiscount() {
        Discount discount = new Discount();

        int day = 25;
        OrderItems orderItems = OrderItems.createOrderItems("타파스-1,초코케이크-1,티본스테이크-2");

        int discountAmount = discount.getSpecialDiscount(day, orderItems, TAPAS.getPrice() +
                CHOCO_CAKE.getPrice() +
                (T_BONE_STEAK.getPrice() * 2));

        assertThat(discountAmount).isEqualTo(
                TAPAS.getPrice() +
                        CHOCO_CAKE.getPrice() +
                        (T_BONE_STEAK.getPrice() * 2) - 1000);
    }
}
