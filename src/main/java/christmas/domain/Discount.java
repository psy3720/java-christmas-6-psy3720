package christmas.domain;

import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class Discount {
    public DiscountResponse getChristmasDDayDiscount(DiscountRequest request) {
        final int start_discount_amount = 1000;
        final int discountPerDay = 100;

        int day = request.getDay();
        int amount = request.getAmount();

        int discountAmount = 0;
        if (day >= 1 && day <= 25) {
            int totalDiscount = (day - 1) * discountPerDay;
            discountAmount = (start_discount_amount + totalDiscount) * -1;
        }

        return new DiscountResponse(amount, discountAmount);
    }

    public DiscountResponse getWeekdayDiscount(DiscountRequest request) {
        int day = request.getDay();
        OrderItems orderItems = request.getOrderItems();
        int amount = request.getAmount();

        LocalDate date = LocalDate.of(2023, 12, day);

        final int DISCOUNT_AMOUNT = 2023;
        int discountAmount = 0;

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.FRIDAY) {
            discountAmount = (orderItems.getDesertMenuQuantity() * DISCOUNT_AMOUNT * -1);
        }

        return new DiscountResponse(amount, discountAmount);
    }

    public DiscountResponse getWeekendDiscount(DiscountRequest request) {
        int amount = request.getAmount();
        int day = request.getDay();
        OrderItems orderItems = request.getOrderItems();

        LocalDate date = LocalDate.of(2023, 12, day);

        final int DISCOUNT_AMOUNT = 2023;
        int discountAmount = 0;

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            discountAmount = (orderItems.getMainMenuQuantity() * DISCOUNT_AMOUNT * -1);
        }
        return new DiscountResponse(amount, discountAmount);
    }

    public DiscountResponse getSpecialDiscount(DiscountRequest request) {
        int day = request.getDay();
        int amount = request.getAmount();
        int[] specialDays = new int[]{3, 10, 17, 24, 25, 31};

        int discountAmount = 0;
        if (Arrays.stream(specialDays).anyMatch(value -> day == value)) {
            discountAmount = -1000;
        }

        return new DiscountResponse(amount, discountAmount);
    }
}
