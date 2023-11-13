package christmas.view.output;

import christmas.domain.discount.DiscountType;
import java.util.Arrays;

public enum DiscountMessage {
    CHRISTMAS_D_DAY_DISCOUNT_FORMAT("크리스마스 디데이 할인: %,d원", DiscountType.CHRISTMAS_D_DAY_DISCOUNT),
    WEEKDAY_DISCOUNT_FORMAT("평일 할인: %,d원", DiscountType.WEEKDAY_DISCOUNT),
    WEEKEND_DISCOUNT_FORMAT("주말 할인: %,d원", DiscountType.WEEKEND_DISCOUNT),
    SPECIAL_DISCOUNT_FORMAT("특별 할인: %,d원", DiscountType.SPECIAL_DISCOUNT);

    private String format;
    private DiscountType discountType;

    DiscountMessage(String format, DiscountType discountType) {
        this.format = format;
        this.discountType = discountType;
    }

    public String getFormat() {
        return format;
    }

    public static String getDiscountFormat(DiscountType discountType) {
        return Arrays.stream(values())
                .filter(discountMessage -> discountMessage.discountType == discountType)
                .findFirst()
                .map(DiscountMessage::getFormat)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
