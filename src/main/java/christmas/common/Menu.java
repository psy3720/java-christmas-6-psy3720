package christmas.common;

import static christmas.common.FoodType.APPETIZER;
import static christmas.common.FoodType.DESERT;
import static christmas.common.FoodType.DRINK;
import static christmas.common.FoodType.MAIN;

import java.util.Arrays;
import java.util.Objects;

public enum Menu {
    MUSHROOM_SOUP("양송이 스프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, APPETIZER),
    T_BONE_STEAK("티본스테이크", 55000, MAIN),
    BBQ_RIB("바비큐립", 54000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN),
    CHOCO_CAKE("초코케이크", 15000, DESERT),
    ICE_CREAM("아이스크림", 5000, DESERT),
    ZERO_COLA("제로콜라", 3000, DRINK),
    RED_WINE("레드와인", 60000, DRINK),
    CHAMPAGNE("샴페인", 25000, DRINK);

    private final String name;
    private final int price;
    private final FoodType foodType;

    Menu(String name, int price, FoodType foodType) {
        this.name = name;
        this.price = price;
        this.foodType = foodType;
    }

    public String getName() {
        return name;
    }

    public static Menu of(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> Objects.equals(name, menu.getName()))
                .findFirst()
                .orElse(null);
    }

    public int getPrice() {
        return price;
    }

    public FoodType getFoodType() {
        return foodType;
    }
}
