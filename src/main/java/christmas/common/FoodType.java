package christmas.common;

public enum FoodType {
    MAIN("메인"),
    DESERT("디저트"),
    DRINK("음료"),
    APPETIZER("애피타이저");

    private final String type;

    FoodType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
