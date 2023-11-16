package christmas.domain.discount;

import christmas.domain.menu.SelectedMenus;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class DiscountResults {
    private final List<DiscountResult> discountResults;

    public DiscountResults(List<DiscountResult> discountResults) {
        this.discountResults = discountResults;
    }

    public static DiscountResults calculatedFrom(LocalDate selectedDate, SelectedMenus selectedMenus) {
        List<DiscountResult> discountResults = calculateDiscountResults(selectedDate, selectedMenus);
        return new DiscountResults(discountResults);
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

    public int getTotalDiscountPrice() {
        return discountResults.stream()
                .mapToInt(DiscountResult::discountedPrice)
                .sum();
    }

    public Stream<DiscountResult> stream() {
        return discountResults.stream();
    }
}
