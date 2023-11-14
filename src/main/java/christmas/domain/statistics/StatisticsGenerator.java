package christmas.domain.statistics;

import christmas.domain.badge.Badge;
import christmas.domain.badge.BadgeCalculator;
import christmas.domain.discount.DiscountResults;
import christmas.domain.gift.GiftResults;
import christmas.domain.menu.SelectedMenus;
import christmas.domain.menu.SelectedMenusDTO;
import java.time.LocalDate;

public final class StatisticsGenerator {

    public Statistics makeStatistics(LocalDate selectedDate, SelectedMenus selectedMenus) {
        DiscountResults discountResults = DiscountResults.calculatedFrom(selectedDate, selectedMenus);
        GiftResults giftResults = GiftResults.calculatedFrom(selectedMenus);
        Badge badge = BadgeCalculator.calculate(discountResults, giftResults);
        SelectedMenusDTO selectedMenusDTO = selectedMenus.to();
        return new Statistics(selectedDate, selectedMenusDTO, giftResults, discountResults, badge);
    }
}
