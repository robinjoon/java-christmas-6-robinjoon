package christmas.input;

import christmas.exception.DuplicateMenuNameException;
import christmas.exception.UnableToSplitByBarException;
import christmas.exception.UnableToSplitByCommaException;
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
                .isInstanceOf(UnableToSplitByCommaException.class);
    }

    @DisplayName("문장-수로 구분되지 않는게 있음")
    @ParameterizedTest
    @ValueSource(strings = {"aaa-1,bbb", "aaa-1,bb-2,cc", "aaa-1,bb,cc-3,dd", "aaa-1,bb-aa,cc-3"})
    void withOutBar(String input) {
        Assertions.assertThatThrownBy(() -> InputManager.getMenuNameToSelectedCountMap(input))
                .isInstanceOf(UnableToSplitByBarException.class);
    }

    @DisplayName("똑같은게 등장함")
    @ParameterizedTest
    @ValueSource(strings = {"aaa-1,aaa-2"})
    void duplicate(String input) {
        Assertions.assertThatThrownBy(() -> InputManager.getMenuNameToSelectedCountMap(input))
                .isInstanceOf(DuplicateMenuNameException.class);
    }
}