package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.WEEK_END;

import christmas.domain.menu.SelectedMenus;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;

class WeekEndDiscountCalculatorTest extends DiscountCalculatorTest {

    @Override
    Arguments makeSingleArgument(int day, int selectedCount) {
        LocalDate localDate = LocalDate.of(2023, 12, day);
        if (localDate.getDayOfWeek() == DayOfWeek.FRIDAY || localDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return Arguments.of(localDate, "티본스테이크", selectedCount, new DiscountResult(WEEK_END, selectedCount * 2023));
        }
        return Arguments.of(localDate, "티본스테이크", selectedCount, DiscountResult.zero(WEEK_END));
    }

    @DisplayName("정상 테스트")
    @ParameterizedTest
    @ArgumentsSource(WeekEndDiscountCalculatorTest.class)
    void calculateDiscount(LocalDate selectedDate, String menuName, int selectedCount, DiscountResult expected) {
        SelectedMenus selectedMenus = new SelectedMenus(Map.of(menuName, selectedCount));
        DiscountCalculator discountCalculator = new WeekEndDiscountCalculator();
        DiscountResult calculate = discountCalculator.calculate(selectedDate, selectedMenus);
        Assertions.assertThat(calculate)
                .isEqualTo(expected);
    }
}