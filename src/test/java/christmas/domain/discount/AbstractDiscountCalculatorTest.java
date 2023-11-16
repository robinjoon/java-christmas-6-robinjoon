package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.SPECIAL;

import christmas.domain.menu.SelectedMenus;
import java.time.LocalDate;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbstractDiscountCalculatorTest {
    @DisplayName("할인 적용 최소 금액 테스트")
    @Test
    void minTotalPriceForDiscount() {
        DiscountCalculator discountCalculator = new AbstractDiscountCalculator() {
            @Override
            DiscountResult calculateDiscount(LocalDate selectedDate, SelectedMenus selectedMenus) {
                return new DiscountResult(SPECIAL, 10000);
            }

            @Override
            DiscountType getCalculateAbleDiscountType() {
                return SPECIAL;
            }
        };
        SelectedMenus selectedMenus = new SelectedMenus(Map.of("양송이수프", 1));
        Assertions.assertThat(discountCalculator.calculate(LocalDate.now(), selectedMenus))
                .isEqualTo(DiscountResult.zero(SPECIAL));
    }
}