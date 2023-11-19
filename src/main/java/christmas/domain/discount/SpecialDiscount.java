package christmas.domain.discount;

import christmas.domain.Day;
import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class SpecialDiscount implements DiscountRule {
    public static final int SPECIAL_DISCOUNT_AMOUNT = -1000;
    private int[] specialDays = new int[]{3, 10, 17, 24, 25, 31};
    private DiscountRule nextRule;

    @Override
    public DiscountResponse calculateDiscount(DiscountRequest request, Map<DiscountType, Integer> discountResults) {
        Day day = request.getDay();
        int amount = request.getAmount();

        int discountAmount = 0;
        if (isSpecialDay(day, specialDays)) {
            discountAmount = SPECIAL_DISCOUNT_AMOUNT;
        }
        discountResults.put(DiscountType.SPECIAL_DISCOUNT, discountAmount);

        if (Objects.isNull(nextRule)) {
            return new DiscountResponse(amount + discountAmount, discountResults);
        } else {
            return nextRule.calculateDiscount(request, discountResults);
        }
    }

    private static boolean isSpecialDay(Day day, int[] specialDays) {
        return Arrays.stream(specialDays).anyMatch(day::equals);
    }

    @Override
    public void setNextRule(DiscountRule nextRule) {
        this.nextRule = nextRule;
    }
}
