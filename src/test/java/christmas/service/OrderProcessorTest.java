package christmas.service;

import static christmas.common.Menu.BBQ_RIB;
import static christmas.common.Menu.CHOCO_CAKE;
import static christmas.common.Menu.T_BONE_STEAK;
import static christmas.common.Menu.ZERO_COLA;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.domain.discount.DiscountType;
import christmas.dto.request.OrderRequest;
import christmas.dto.response.BenefitDetails;
import christmas.dto.response.OrderResponse;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderProcessorTest {

    @DisplayName("주문 시 할인과 증정 이벤트 적용 테스트")
    @Test
    void order() {
        Day day = Day.createDay("3");
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        OrderProcessor orderProcessor = new OrderProcessor();

        OrderRequest request = new OrderRequest(day, OrderItems.createOrderItems(input));
        OrderResponse response = orderProcessor.order(request);
        BenefitDetails benefitDetails = response.getBenefitDetails();

        int 할인_전_총주문_금액 = T_BONE_STEAK.getPrice() + BBQ_RIB.getPrice() +
                (CHOCO_CAKE.getPrice() * 2) + ZERO_COLA.getPrice();
        int 크리스마스_디데이_할인_금액 = -1200;
        int 평일_할인_금액 = -4046;
        int 특별_할인_금액 = -1000;

        Map<DiscountType, Integer> discountResults = benefitDetails.getDiscountResults();
        assertThat(response.getTotalOrderAmount()).isEqualTo(할인_전_총주문_금액);
        assertThat(response.getGiftMenu().isGiftEvent()).isTrue();
        assertThat(discountResults.get(DiscountType.CHRISTMAS_D_DAY_DISCOUNT)).isEqualTo(크리스마스_디데이_할인_금액);
        assertThat(discountResults.get(DiscountType.WEEKDAY_DISCOUNT)).isEqualTo(평일_할인_금액);
        assertThat(discountResults.get(DiscountType.SPECIAL_DISCOUNT)).isEqualTo(특별_할인_금액);
    }
}
