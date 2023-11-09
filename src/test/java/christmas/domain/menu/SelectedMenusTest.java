package christmas.domain.menu;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SelectedMenusTest {

    @DisplayName("없는 메뉴")
    @ParameterizedTest
    @ValueSource(strings = {"aaa", "bbb", "cxccc", "크림수프"})
    void unKnownMenu(String menuName) {
        Assertions.assertThatThrownBy(() -> new SelectedMenus(Map.of(menuName, 1)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}