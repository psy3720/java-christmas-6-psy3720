package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountTest {

    @DisplayName("크리스마스 디데이 할인, 평일 할인, 특별할인 테스트")
    @Test
    void testMultipleDiscounts() {
        Day day = Day.createDay("3");
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        OrderItems orderItems = OrderItems.createOrderItems(input);
        DiscountResponse response = Discount.calculateDiscount(
                new DiscountRequest(day, orderItems, orderItems.getTotalAmount()));

        Map<DiscountType, Integer> discountResults = response.getDiscountResults();

        int 크리스마스_디데이_할인_금액 = -1200;
        int 평일_할인_금액 = -4046;
        int 특별_할인_금액 = -1000;

        assertThat(discountResults.get(DiscountType.CHRISTMAS_D_DAY_DISCOUNT)).isEqualTo(크리스마스_디데이_할인_금액);
        assertThat(discountResults.get(DiscountType.WEEKDAY_DISCOUNT)).isEqualTo(평일_할인_금액);
        assertThat(discountResults.get(DiscountType.SPECIAL_DISCOUNT)).isEqualTo(특별_할인_금액);
    }

    @DisplayName("크리스마스 디데이 할인, 주말 할인, 특별할인 테스트")
    @Test
    void discountTest() {
        Day day = Day.createDay("8");
        String input = "티본스테이크-1,초코케이크-2,제로콜라-1";

        OrderItems orderItems = OrderItems.createOrderItems(input);
        DiscountResponse response = Discount.calculateDiscount(
                new DiscountRequest(day, orderItems, orderItems.getTotalAmount()));

        Map<DiscountType, Integer> discountResults = response.getDiscountResults();

        int 크리스마스_디데이_할인_금액 = -1700;
        int 주말_할인_금액 = -2023;
        int 특별_할인_금액 = 0;

        assertThat(discountResults.get(DiscountType.CHRISTMAS_D_DAY_DISCOUNT)).isEqualTo(크리스마스_디데이_할인_금액);
        assertThat(discountResults.get(DiscountType.WEEKEND_DISCOUNT)).isEqualTo(주말_할인_금액);
        assertThat(discountResults.get(DiscountType.SPECIAL_DISCOUNT)).isEqualTo(특별_할인_금액);
    }
}
