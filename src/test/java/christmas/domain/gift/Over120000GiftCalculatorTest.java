package christmas.domain.gift;

import static christmas.domain.gift.GiftType.OVER_120000;

import christmas.domain.menu.SelectedMenus;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Over120000GiftCalculatorTest {

    static Stream<Arguments> calculateGiftParameter() {
        return IntStream.rangeClosed(1, 20)
                .mapToObj(Over120000GiftCalculatorTest::makeSingleArgument);
    }

    private static Arguments makeSingleArgument(int selectedCount) {
        SelectedMenus selectedMenus = new SelectedMenus(Map.of("티본스테이크", selectedCount));
        if (selectedMenus.getTotalPrice() >= 120_000) {
            return Arguments.of(selectedMenus, new GiftResult(OVER_120000));
        }
        return Arguments.of(selectedMenus, GiftResult.zero(OVER_120000));
    }

    @DisplayName("정상 테스트")
    @ParameterizedTest
    @MethodSource("calculateGiftParameter")
    void calculateGift(SelectedMenus selectedMenus, GiftResult expected) {
        GiftCalculator giftCalculator = new Over120000GiftCalculator();
        Assertions.assertThat(giftCalculator.calculate(selectedMenus))
                .isEqualTo(expected);
    }
}