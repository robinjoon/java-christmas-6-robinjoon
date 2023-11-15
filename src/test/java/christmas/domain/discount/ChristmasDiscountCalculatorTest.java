package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.XMAS;

import christmas.domain.menu.SelectedMenus;
import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;

class ChristmasDiscountCalculatorTest extends DiscountCalculatorTest {

    @Override
    Arguments makeSingleArgument(int day, int selectedCount) {
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
    @ArgumentsSource(ChristmasDiscountCalculatorTest.class)
    void calculateDiscount(LocalDate selectedDate, String menuName, int selectedCount, DiscountResult expected) {
        SelectedMenus selectedMenus = new SelectedMenus(Map.of(menuName, selectedCount));
        DiscountCalculator discountCalculator = new ChristmasDiscountCalculator();
        DiscountResult calculate = discountCalculator.calculate(selectedDate, selectedMenus);
        Assertions.assertThat(calculate)
                .isEqualTo(expected);
    }
}