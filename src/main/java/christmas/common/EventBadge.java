package christmas.common;

import java.util.Arrays;
import java.util.Comparator;

public enum EventBadge {

    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타");

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
        EventBadge[] values = values();
        Arrays.sort(values, Comparator.reverseOrder());

        for(EventBadge eventBadge : values) {
            if(benefitAmount >= eventBadge.benefitAmount) {
                return eventBadge;
            }
        }
        return null;
    }
}
