package christmas.domain.statistics;

import christmas.domain.badge.Badge;
import christmas.domain.badge.BadgeCalculator;
import christmas.domain.discount.DiscountCalculator;
import christmas.domain.discount.DiscountCalculatorMapper;
import christmas.domain.discount.DiscountResult;
import christmas.domain.discount.DiscountResults;
import christmas.domain.discount.DiscountType;
import christmas.domain.gift.GiftCalculator;
import christmas.domain.gift.GiftCalculatorMapper;
import christmas.domain.gift.GiftResult;
import christmas.domain.gift.GiftResults;
import christmas.domain.gift.GiftType;
import christmas.domain.menu.SelectedMenus;
import christmas.domain.menu.SelectedMenusDTO;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public final class StatisticsGenerator {
    private final BadgeCalculator badgeCalculator;

    public StatisticsGenerator(BadgeCalculator badgeCalculator) {
        this.badgeCalculator = badgeCalculator;
    }

    public Statistics makeStatistics(LocalDate selectedDate, SelectedMenus selectedMenus) {
        DiscountResults discountResults = new DiscountResults(calculateDiscountResults(selectedDate, selectedMenus));
        GiftResults giftResults = new GiftResults(calculateGiftResults(selectedMenus));
        Badge badge = badgeCalculator.calculate(discountResults, giftResults);
        SelectedMenusDTO selectedMenusDTO = selectedMenus.to();
        return new Statistics(selectedMenusDTO, giftResults, discountResults, badge);
    }

    private static List<GiftResult> calculateGiftResults(SelectedMenus selectedMenus) {
        return Arrays.stream(GiftType.values()).map(giftType -> calculateGift(selectedMenus, giftType)).toList();
    }

    private static GiftResult calculateGift(SelectedMenus selectedMenus, GiftType giftType) {
        GiftCalculator giftCalculator = GiftCalculatorMapper.map(giftType).orElseThrow();
        return giftCalculator.calculate(selectedMenus);
    }

    private static List<DiscountResult> calculateDiscountResults(LocalDate selectedDate, SelectedMenus selectedMenus) {
        return Arrays.stream(DiscountType.values())
                .map(discountType -> calculateDiscountResult(selectedDate, selectedMenus, discountType)).toList();
    }

    private static DiscountResult calculateDiscountResult(LocalDate selectedDate, SelectedMenus selectedMenus,
                                                          DiscountType discountType) {
        DiscountCalculator discountCalculator = DiscountCalculatorMapper.map(discountType).orElseThrow();
        return discountCalculator.calculate(selectedDate, selectedMenus);
    }
}
