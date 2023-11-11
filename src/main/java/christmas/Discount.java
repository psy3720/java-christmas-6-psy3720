package christmas;

import christmas.domain.OrderItems;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class Discount {
    public int getChristmasDDayDiscount(int day, OrderItems orderItems, int amount) {
        final int start_discount_amount = 1000;
        final int discountPerDay = 100;

        if (day >= 1 && day <= 25) {
            int totalDiscount = (day - 1) * discountPerDay;
            amount = amount + (start_discount_amount + totalDiscount) * -1;
        }

        return amount;
    }

    public int getWeekdayDiscount(int day, OrderItems orderItems, int amount) {
        LocalDate date = LocalDate.of(2023, 12, day);

        final int DISCOUNT_AMOUNT = 2023;

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.FRIDAY) {
            amount = amount + (orderItems.getDesertMenuQuantity() * DISCOUNT_AMOUNT * -1);
        }

        return amount;
    }

    public int getWeekendDiscount(int day, OrderItems orderItems, int amount) {
        LocalDate date = LocalDate.of(2023, 12, day);

        final int DISCOUNT_AMOUNT = 2023;

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if(dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY) {
            amount = amount + (orderItems.getMainMenuQuantity() * DISCOUNT_AMOUNT * -1);
        }
        return amount;
    }

    public int getSpecialDiscount(int day, OrderItems orderItems, int amount) {
        int[] specialDays = new int[] {3, 10, 17, 24, 25, 31};

        if(Arrays.stream(specialDays).anyMatch(value -> day == value)) {
            amount = amount - 1000;
        }

        return amount;
    }
}
