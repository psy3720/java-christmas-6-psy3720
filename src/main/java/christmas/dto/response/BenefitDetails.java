package christmas.dto.response;

import christmas.domain.discount.DiscountType;
import java.util.Map;

public class BenefitDetails {
    //    private int christmasDDayDiscount;
//    private int weekdayDiscount;
//    private int weekendDiscount;
//    private int specialDiscount;
    private Map<DiscountType, Integer> discountResults;
    private int giftEvent;

//    public BenefitDetails(int christmasDDayDiscount, int weekdayDiscount, int weekendDiscount, int specialDiscount,
//                          int giftEvent) {
//        this.christmasDDayDiscount = christmasDDayDiscount;
//        this.weekdayDiscount = weekdayDiscount;
//        this.weekendDiscount = weekendDiscount;
//        this.specialDiscount = specialDiscount;
//        this.giftEvent = giftEvent;
//    }

    public BenefitDetails(Map<DiscountType, Integer> discountResults, int giftEvent) {
        this.discountResults = discountResults;
        this.giftEvent = giftEvent;
    }

//    public int getChristmasDDayDiscount() {
//        return christmasDDayDiscount;
//    }
//
//    public int getWeekdayDiscount() {
//        return weekdayDiscount;
//    }
//
//    public int getWeekendDiscount() {
//        return weekendDiscount;
//    }
//
//    public int getSpecialDiscount() {
//        return specialDiscount;
//    }

    public int getTotalBenefitAmount() {
        int totalDiscountAmount = 0;

        for (int discountAmount : discountResults.values()) {
            totalDiscountAmount += discountAmount;
        }

        return totalDiscountAmount + giftEvent;
//        return christmasDDayDiscount + weekdayDiscount + specialDiscount + giftEvent;
    }

    public int getGiftEvent() {
        return giftEvent;
    }

    public Map<DiscountType, Integer> getDiscountResults() {
        return discountResults;
    }
}
