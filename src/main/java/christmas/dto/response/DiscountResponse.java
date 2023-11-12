package christmas.dto.response;

public class DiscountResponse {
    private int amount;
    private int discountAmount;

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
