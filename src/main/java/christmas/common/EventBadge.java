package christmas.common;

import java.util.Arrays;

public enum EventBadge {
    SANTA(20000, "산타"),
    TREE(10000, "트리"),
    STAR(5000, "별"),
    NONE(0, "없음");

    private final int benefitAmount;
    private final String name;

    EventBadge(int benefitAmount, String name) {
        this.benefitAmount = benefitAmount;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EventBadge getBadgeBenefitAmount(int benefitAmount) {
        return Arrays.stream(values())
                .filter(eventBadge -> eventBadge.benefitAmount <= Math.abs(benefitAmount))
                .findFirst()
                .orElse(NONE);
    }
}
