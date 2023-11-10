package christmas.domain.menu;

import christmas.exception.MenuSelectedCountOverflowException;
import christmas.exception.NoSuchMenuException;
import christmas.exception.OnlyBeverageSelectedException;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SelectedMenusTest {

    @DisplayName("없는 메뉴")
    @ParameterizedTest
    @ValueSource(strings = {"aaa", "bbb", "cxccc", "크림수프"})
    void unKnownMenu(String menuName) {
        Assertions.assertThatThrownBy(() -> new SelectedMenus(Map.of(menuName, 1)))
                .isInstanceOf(NoSuchMenuException.class);
    }

    @DisplayName("총 주문 개수 초과")
    @Test
    void totalMenuCountOverFlow() {
        Assertions.assertThatThrownBy(() -> new SelectedMenus(Map.of("양송이수프", 21)))
                .isInstanceOf(MenuSelectedCountOverflowException.class);
    }

    @DisplayName("오직 음료만")
    @Test
    void onlyBeverage() {
        Assertions.assertThatThrownBy(() -> new SelectedMenus(Map.of("제로콜라", 1, "레드와인", 1, "샴페인", 1)))
                .isInstanceOf(OnlyBeverageSelectedException.class);
    }
}