package christmas.domain.discount;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public class WeekendDiscount implements DiscountRule {
    private DiscountRule nextRule;
    static final int DISCOUNT_AMOUNT = 2023;

    @Override
    public DiscountResponse calculateDiscount(DiscountRequest request, Map<DiscountType, Integer> discountResults) {
        int amount = request.getAmount();
        Day day = request.getDay();
        OrderItems orderItems = request.getOrderItems();

        LocalDate date = day.getLocalDate();
        int discountAmount = 0;

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (isWeekend(dayOfWeek)) {
            discountAmount = (orderItems.getMainMenuQuantity() * DISCOUNT_AMOUNT * -1);
        }
        discountResults.put(DiscountType.WEEKEND_DISCOUNT, discountAmount);

        if (Objects.isNull(nextRule)) {
            return new DiscountResponse(amount + discountAmount, discountResults);
        } else {
            return nextRule.calculateDiscount(request, discountResults);
        }
    }

    private static boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    @Override
    public void setNextRule(DiscountRule nextRule) {
        this.nextRule = nextRule;
    }
}
