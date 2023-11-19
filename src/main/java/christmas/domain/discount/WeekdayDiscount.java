package christmas.domain.discount;

import christmas.domain.Day;
import christmas.domain.OrderItems;
import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public class WeekdayDiscount implements DiscountRule {
    static final int DISCOUNT_AMOUNT = 2023;
    private DiscountRule nextRule;

    @Override
    public DiscountResponse calculateDiscount(DiscountRequest request, Map<DiscountType, Integer> discountResults) {
        int amount = request.getAmount();
        Day day = request.getDay();
        OrderItems orderItems = request.getOrderItems();

        LocalDate date = day.getLocalDate();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        int discountAmount = 0;
        if (!isWeekend(dayOfWeek)) {
            discountAmount = (orderItems.getDesertMenuQuantity() * DISCOUNT_AMOUNT * -1);
        }
        discountResults.put(DiscountType.WEEKDAY_DISCOUNT, discountAmount);

        if (Objects.isNull(nextRule)) {
            return new DiscountResponse(amount + discountAmount, discountResults);
        } else {
            return nextRule.calculateDiscount(request, discountResults);
        }
    }

    private static boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.FRIDAY;
    }

    @Override
    public void setNextRule(DiscountRule nextRule) {
        this.nextRule = nextRule;
    }
}
