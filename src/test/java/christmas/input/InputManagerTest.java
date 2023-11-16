package christmas.input;

import static christmas.exception.PolicyViolationType.DUPLICATE_MENU_NAME;
import static christmas.exception.PolicyViolationType.NO_SUCH_DATE;
import static christmas.exception.PolicyViolationType.UNABLE_TO_SPLIT_BAR;
import static christmas.exception.PolicyViolationType.UNABLE_TO_SPLIT_COMMA;

import christmas.exception.PolicyViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputManagerTest {

    @DisplayName("콤마 혹은 공백으로 시작하거나 끝남")
    @ParameterizedTest
    @ValueSource(strings = {"aaa-1,bbb-2,", ",aaa-1,bbb-2", " aaa-1,bb-2", "aaa-1,bbb-2 "})
    void commaOrBlank(String input) {
        Assertions.assertThatThrownBy(() -> InputManager.getMenuNameToSelectedCountMap(input))
                .isInstanceOf(PolicyViolationException.class)
                .hasMessageContaining(UNABLE_TO_SPLIT_COMMA.getMessage());
    }

    @DisplayName("문장-수로 구분되지 않는게 있음")
    @ParameterizedTest
    @ValueSource(strings = {"aaa-1,bbb", "aaa-1,bb-2,cc", "aaa-1,bb,cc-3,dd", "aaa-1,bb-aa,cc-3", "aaa-+2"})
    void withOutBar(String input) {
        Assertions.assertThatThrownBy(() -> InputManager.getMenuNameToSelectedCountMap(input))
                .isInstanceOf(PolicyViolationException.class)
                .hasMessageContaining(UNABLE_TO_SPLIT_BAR.getMessage());
    }

    @DisplayName("똑같은메뉴가 등장함")
    @ParameterizedTest
    @ValueSource(strings = {"aaa-1,aaa-2"})
    void duplicate(String input) {
        Assertions.assertThatThrownBy(() -> InputManager.getMenuNameToSelectedCountMap(input))
                .isInstanceOf(PolicyViolationException.class)
                .hasMessageContaining(DUPLICATE_MENU_NAME.getMessage());
    }

    @DisplayName("날짜 입력에 1 ~ 31 말고 다른 입력")
    @ParameterizedTest
    @ValueSource(strings = {"a", " ", "0", "32"})
    void wrongDate(String input) {
        Assertions.assertThatThrownBy(() -> InputManager.getDate(input))
                .isInstanceOf(PolicyViolationException.class)
                .hasMessageContaining(NO_SUCH_DATE.getMessage());
    }
}