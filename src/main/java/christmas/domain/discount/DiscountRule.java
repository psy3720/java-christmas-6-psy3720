package christmas.domain.discount;

import christmas.dto.request.DiscountRequest;
import christmas.dto.response.DiscountResponse;
import java.util.Map;

public interface DiscountRule {
    DiscountResponse calculateDiscount(DiscountRequest request, Map<DiscountType, Integer> discountResults);

    void setNextRule(DiscountRule nextRule);
}
