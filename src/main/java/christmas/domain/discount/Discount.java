package christmas.domain.discount;

import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.util.HashMap;

public class Discount {
    public static DiscountResponse calculateDiscount(DiscountRequest discountRequest) {
        DiscountRule discountRuleChain = buildDiscountRuleChain();
        return discountRuleChain.calculateDiscount(discountRequest, new HashMap<>());
    }

    private static DiscountRule buildDiscountRuleChain() {
        DiscountRule christmasDDayDiscount = new ChristmasDDayDiscount();
        DiscountRule weeklyDiscount = new WeekdayDiscount();
        DiscountRule weekendDiscount = new WeekendDiscount();
        DiscountRule specialDiscount = new SpecialDiscount();

        christmasDDayDiscount.setNextRule(weeklyDiscount);
        weeklyDiscount.setNextRule(weekendDiscount);
        weekendDiscount.setNextRule(specialDiscount);

        return christmasDDayDiscount;
    }
}
