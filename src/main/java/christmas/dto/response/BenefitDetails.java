package christmas.dto.response;

import christmas.domain.discount.DiscountType;
import java.util.Map;

public class BenefitDetails {
    private Map<DiscountType, Integer> discountResults;

    public BenefitDetails(Map<DiscountType, Integer> discountResults) {
        this.discountResults = discountResults;
    }

    public int getTotalBenefitAmount() {
        int totalDiscountAmount = 0;

        for (int discountAmount : discountResults.values()) {
            totalDiscountAmount += discountAmount;
        }

        return totalDiscountAmount;
    }

    public Map<DiscountType, Integer> getDiscountResults() {
        return discountResults;
    }
}
