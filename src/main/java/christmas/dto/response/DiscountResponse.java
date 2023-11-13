package christmas.dto.response;

import christmas.domain.discount.DiscountType;
import java.util.Map;

public class DiscountResponse {
    private int amount;
    private int discountAmount;
    private Map<DiscountType, Integer> discountResults;

    public DiscountResponse(int amount, int discountAmount, Map<DiscountType, Integer> discountResults) {
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.discountResults = discountResults;
    }

    public int getAmount() {
        return amount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public int getFinalAmount() {
        return amount;
    }

    public Map<DiscountType, Integer> getDiscountResults() {
        return discountResults;
    }
}
