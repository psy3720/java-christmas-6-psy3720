package christmas.domain;

import static christmas.utils.StringUtils.isDigit;
import static java.util.Objects.isNull;

import christmas.common.ErrorMessages;
import christmas.exception.InputValidationException;
import java.time.LocalDate;

public class Day {
    public static final int YEAR = 2023;
    public static final int MONTH = 12;
    public static final int CHRISTMAS_EVENT_START_DAY = 1;
    public static final int CHRISTMAS_EVENT_END_DAY = 25;
    public static final int VALID_MIN_DAY = 1;
    public static final int VALID_MAX_DAY = 31;
    private int day;

    public Day(int day) {
        this.day = day;
    }

    public static Day createDay(String day) {
        validateDay(day);
        return new Day(Integer.parseInt(day));
    }

    private static void validateDay(String day) {
        if (isNull(day)) {
            throw new InputValidationException(ErrorMessages.NULL_OR_EMPTY);
        }

        if (!isDigit(day)) {
            throw new InputValidationException(ErrorMessages.INVALID_DATE_ERROR);
        }

        if (!isValidDayRange(day)) {
            throw new InputValidationException(ErrorMessages.INVALID_DATE_ERROR);
        }
    }

    private static boolean isValidDayRange(String day) {
        return Integer.parseInt(day) >= VALID_MIN_DAY && Integer.parseInt(day) <= VALID_MAX_DAY;
    }

    public boolean isChristmasDay() {
        return day >= CHRISTMAS_EVENT_START_DAY && day <= CHRISTMAS_EVENT_END_DAY;
    }

    public boolean equals(int day) {
        return this.day == day;
    }

    public LocalDate getLocalDate() {
        return LocalDate.of(YEAR, MONTH, day);
    }

    public int minus(int day) {
        return this.day - day;
    }
}
