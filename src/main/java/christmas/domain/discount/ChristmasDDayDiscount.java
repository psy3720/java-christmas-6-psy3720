package christmas.domain.discount;

import christmas.domain.Day;
import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.util.Map;
import java.util.Objects;

public class ChristmasDDayDiscount implements DiscountRule {
    private DiscountRule nextRule;

    @Override
    public DiscountResponse calculateDiscount(DiscountRequest request, Map<DiscountType, Integer> discountResults) {
        final int start_discount_amount = 1000;
        final int discountPerDay = 100;

        Day day = request.getDay();
        int amount = request.getAmount();

        int discountAmount = 0;
        if (day.isChristmasDay()) {
            int totalDiscount = day.minus(1) * discountPerDay;
            discountAmount = (start_discount_amount + totalDiscount) * -1;
        }

        discountResults.put(DiscountType.CHRISTMAS_D_DAY_DISCOUNT, discountAmount);

        if (Objects.isNull(nextRule)) {
            return new DiscountResponse(amount + discountAmount, discountAmount, discountResults);
        } else {
            return nextRule.calculateDiscount(request, discountResults);
        }
    }

    @Override
    public void setNextRule(DiscountRule nextRule) {
        this.nextRule = nextRule;
    }
}
