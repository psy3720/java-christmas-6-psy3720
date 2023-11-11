package christmas.dto;

import christmas.common.Badge;
import christmas.common.Menu;
import java.util.List;
import java.util.Map;

public class Order {
    private final OrderItems orderMenus; // 주문메뉴
    private final int totalOrderAmount; // 할인전 총 주문 금액
    private final Badge badge; // 12월 산타이벤트 뱃지

    public Order(OrderItems orderMenus, int totalOrderAmount, Badge badge) {
        this.orderMenus = orderMenus;
        this.totalOrderAmount = totalOrderAmount;
        this.badge = badge;
    }

    public List<Map<Menu, Integer>> getOrderMenus() {
        return null;
    }

    public int getTotalOrderAmount() {
        return 0;
    }

    public Badge getBadge() {
        return null;
    }
}
