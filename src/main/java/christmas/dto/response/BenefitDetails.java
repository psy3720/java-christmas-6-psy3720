package christmas.dto.response;

public class BenefitDetails {
    private int christmasDDayDiscount;
    private int weekdayDiscount;
    private int weekendDiscount;
    private int specialDiscount;
    private int giftEvent;

    public BenefitDetails(int christmasDDayDiscount, int weekdayDiscount, int weekendDiscount, int specialDiscount,
                          int giftEvent) {
        this.christmasDDayDiscount = christmasDDayDiscount;
        this.weekdayDiscount = weekdayDiscount;
        this.weekendDiscount = weekendDiscount;
        this.specialDiscount = specialDiscount;
        this.giftEvent = giftEvent;
    }

    public int getChristmasDDayDiscount() {
        return christmasDDayDiscount;
    }

    public int getWeekdayDiscount() {
        return weekdayDiscount;
    }

    public int getWeekendDiscount() {
        return weekendDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public int getTotalBenefitAmount() {
        return christmasDDayDiscount + weekdayDiscount + specialDiscount + giftEvent;
    }
}
