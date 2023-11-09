package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.XMAS;

import christmas.domain.menu.SelectedMenus;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChristmasDiscountCalculatorTest {

    static Stream<Arguments> calculateDiscountParameter() {
        List<Arguments> arguments = new ArrayList<>();
        for (int day = 1; day <= 31; day++) {
            for (int selectedCount = 1; selectedCount <= 20; selectedCount++) {
                arguments.add(makeSingleArgument(day, selectedCount));
            }
        }
        return arguments.stream();
    }

    static Arguments makeSingleArgument(int day, int selectedCount) {
        LocalDate localDate = LocalDate.of(2023, 12, day);
        if (localDate.isAfter(LocalDate.of(2023, 12, 25))) {
            return Arguments.of(localDate, "초코케이크", selectedCount, DiscountResult.zero(XMAS));
        }
        Period period = Period.between(LocalDate.of(2023, 12, 1), localDate);
        int discountMoney = 1000 + period.getDays() * 100;
        return Arguments.of(localDate, "초코케이크", selectedCount, new DiscountResult(XMAS, discountMoney));
    }

    @DisplayName("정상 테스트")
    @ParameterizedTest
    @MethodSource("calculateDiscountParameter")
    void calculateDiscount(LocalDate selectedDate, String menuName, int selectedCount, DiscountResult expected) {
        SelectedMenus selectedMenus = new SelectedMenus(Map.of(menuName, selectedCount));
        DiscountCalculator discountCalculator = new ChristmasDiscountCalculator();
        DiscountResult calculate = discountCalculator.calculate(selectedDate, selectedMenus);
        Assertions.assertThat(calculate)
                .isEqualTo(expected);
    }
}