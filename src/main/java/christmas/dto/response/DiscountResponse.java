package christmas.dto.response;

import christmas.domain.discount.DiscountType;
import java.util.Collections;
import java.util.Map;

public class DiscountResponse {
    private int amount;
    private Map<DiscountType, Integer> discountResults;

    public DiscountResponse(int amount, Map<DiscountType, Integer> discountResults) {
        this.amount = amount;
        this.discountResults = discountResults;
    }

    public int getFinalAmount() {
        return amount;
    }

    public Map<DiscountType, Integer> getDiscountResults() {
        return Collections.unmodifiableMap(discountResults);
    }
}
