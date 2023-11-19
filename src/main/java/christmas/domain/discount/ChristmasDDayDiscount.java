package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.CHRISTMAS_D_DAY_DISCOUNT;

import christmas.domain.Day;
import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.util.Map;
import java.util.Objects;

public class ChristmasDDayDiscount implements DiscountRule {
    private DiscountRule nextRule;
    static final int START_DISCOUNT_AMOUNT = 1000;
    static final int DISCOUNT_PER_DAY = 100;


    @Override
    public DiscountResponse calculateDiscount(DiscountRequest request, Map<DiscountType, Integer> discountResults) {
        Day day = request.getDay();
        int amount = request.getAmount();

        int discountAmount = 0;
        if (day.isChristmasDay()) {
            int totalDiscount = day.minus(1) * DISCOUNT_PER_DAY;
            discountAmount = (START_DISCOUNT_AMOUNT + totalDiscount) * -1;
        }

        discountResults.put(CHRISTMAS_D_DAY_DISCOUNT, discountAmount);

        if (Objects.isNull(nextRule)) {
            return new DiscountResponse(amount + discountAmount, discountResults);
        } else {
            return nextRule.calculateDiscount(request, discountResults);
        }
    }

    @Override
    public void setNextRule(DiscountRule nextRule) {
        this.nextRule = nextRule;
    }
}
