package christmas.dto.response;

public class DiscountResponse {
    private int amount; // 할인전 총 주문 금액
    private int discountAmount; // 할인 금액

    public DiscountResponse(int amount, int discountAmount) {
        this.amount = amount;
        this.discountAmount = discountAmount;
    }

    public int getAmount() {
        return amount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public int getFinalAmount() {
        return discountAmount + amount;
    }
}
