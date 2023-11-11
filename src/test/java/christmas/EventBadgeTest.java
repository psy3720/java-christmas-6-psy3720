package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.common.EventBadge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventBadgeTest {

    @DisplayName("혜택 금액이 5천원 이상이면 별 배지를 반환한다.")
    @Test
    void getBadgeBenefitAmountStar() {
        int benefitAmount = 5500;

        EventBadge eventBadge = EventBadge.getBadgeBenefitAmount(benefitAmount);

        assertThat(eventBadge).isEqualTo(EventBadge.STAR);
    }

    @DisplayName("혜택 금액이 10,000원 이상이면 트리 배지를 반환한다.")
    @Test
    void getBadgeBenefitAmountTree() {
        int benefitAmount = 11000;

        EventBadge eventBadge = EventBadge.getBadgeBenefitAmount(benefitAmount);

        assertThat(eventBadge).isEqualTo(EventBadge.TREE);
    }

    @DisplayName("혜택 금액이 20,000원 이상이면 산타 배지를 반환한다.")
    @Test
    void getBadgeBenefitAmountSanta() {
        int benefitAmount = 20000;

        EventBadge eventBadge = EventBadge.getBadgeBenefitAmount(benefitAmount);

        assertThat(eventBadge).isEqualTo(EventBadge.SANTA);
    }
}
