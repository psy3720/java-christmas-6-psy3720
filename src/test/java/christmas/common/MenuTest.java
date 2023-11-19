package christmas.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {

    @DisplayName("음식이름으로 메뉴를 조회한다.")
    @Test
    void of() {
        String name = "샴페인";

        Menu menu = Menu.of(name);

        assertThat(menu).isEqualTo(Menu.CHAMPAGNE);
    }
}
