package christmas.dto;

public class BenefitDetails {
    private int christmasDDayDiscount;
    private int weekdayDiscount;
    private int specialDiscount;
    private int giftEvent;

    public BenefitDetails(int christmasDDayDiscount, int weekdayDiscount, int specialDiscount, int giftEvent
                         ) {
        this.christmasDDayDiscount = christmasDDayDiscount;
        this.weekdayDiscount = weekdayDiscount;
        this.specialDiscount = specialDiscount;
        this.giftEvent = giftEvent;
    }

    public int getChristmasDDayDiscount() {
        return christmasDDayDiscount;
    }

    public int getWeekdayDiscount() {
        return weekdayDiscount;
    }

    public int getTotalBenefitAmount() {
        return christmasDDayDiscount + weekdayDiscount + specialDiscount + giftEvent;
    }
}
