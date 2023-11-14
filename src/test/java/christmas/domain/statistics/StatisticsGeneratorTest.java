package christmas.domain.statistics;

import static christmas.domain.badge.Badge.SANTA;

import christmas.domain.menu.SelectedMenus;
import java.time.LocalDate;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatisticsGeneratorTest {

    @DisplayName("정상 테스트")
    @Test
    void makeStatistics() {
        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
        SelectedMenus selectedMenus = new SelectedMenus(
                Map.of("티본스테이크", 1, "바비큐립", 1, "초코케이크", 2, "제로콜라", 1)
        );
        LocalDate selectedDate = LocalDate.of(2023, 12, 3);
        Statistics statistics = statisticsGenerator.makeStatistics(selectedDate, selectedMenus);
        Assertions.assertThat(statistics.badge()).isEqualTo(SANTA);
        Assertions.assertThat(statistics.getTotalPrice()).isEqualTo(142_000);
        Assertions.assertThat(statistics.getTotalBenefitPrice()).isEqualTo(31_246);
        Assertions.assertThat(statistics.getDiscountedTotalPrice()).isEqualTo(135_754);
    }
}