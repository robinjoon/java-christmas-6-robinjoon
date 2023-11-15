package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.SPECIAL;

import christmas.domain.menu.SelectedMenus;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;

class SpecialDiscountCalculatorTest extends DiscountCalculatorTest {

    @Override
    Arguments makeSingleArgument(int day, int selectedCount) {
        LocalDate localDate = LocalDate.of(2023, 12, day);
        List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
        if (specialDays.contains(day)) {
            return Arguments.of(localDate, "초코케이크", selectedCount, new DiscountResult(SPECIAL, 1000));
        }
        return Arguments.of(localDate, "초코케이크", selectedCount, DiscountResult.zero(SPECIAL));
    }

    @DisplayName("정상 테스트")
    @ParameterizedTest
    @ArgumentsSource(SpecialDiscountCalculatorTest.class)
    void calculateDiscount(LocalDate selectedDate, String menuName, int selectedCount, DiscountResult expected) {
        SelectedMenus selectedMenus = new SelectedMenus(Map.of(menuName, selectedCount));
        DiscountCalculator discountCalculator = new SpecialDiscountCalculator();
        DiscountResult calculate = discountCalculator.calculate(selectedDate, selectedMenus);
        Assertions.assertThat(calculate)
                .isEqualTo(expected);
    }
}