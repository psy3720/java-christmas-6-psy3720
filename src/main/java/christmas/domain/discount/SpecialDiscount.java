package christmas.domain.discount;

import christmas.domain.Day;
import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class SpecialDiscount implements DiscountRule {
    private DiscountRule nextRule;

    @Override
    public DiscountResponse calculateDiscount(DiscountRequest request, Map<DiscountType, Integer> discountResults) {
        Day day = request.getDay();
        int amount = request.getAmount();
        int[] specialDays = new int[]{3, 10, 17, 24, 25, 31};

        int discountAmount = 0;
        if (Arrays.stream(specialDays).anyMatch(day::equals)) {
            discountAmount = -1000;
        }

        discountResults.put(DiscountType.SPECIAL_DISCOUNT, discountAmount);

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
