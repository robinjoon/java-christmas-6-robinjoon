package christmas.domain.gift;

import christmas.domain.menu.SelectedMenus;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class GiftResults {
    private final List<GiftResult> giftResults;

    public GiftResults(List<GiftResult> giftResults) {
        this.giftResults = giftResults;
    }

    public static GiftResults calculatedFrom(SelectedMenus selectedMenus) {
        List<GiftResult> giftResults = calculateGiftResults(selectedMenus);
        return new GiftResults(giftResults);
    }

    private static List<GiftResult> calculateGiftResults(SelectedMenus selectedMenus) {
        return Arrays.stream(GiftType.values()).map(giftType -> calculateGift(selectedMenus, giftType)).toList();
    }

    private static GiftResult calculateGift(SelectedMenus selectedMenus, GiftType giftType) {
        GiftCalculator giftCalculator = GiftCalculatorMapper.map(giftType).orElseThrow();
        return giftCalculator.calculate(selectedMenus);
    }

    public int getTotalGiftPrice() {
        return giftResults.stream()
                .mapToInt(GiftResult::giftPrice)
                .sum();
    }

    public Stream<GiftResult> stream() {
        return giftResults.stream();
    }

    public void forEach(Consumer<? super GiftResult> action) {
        giftResults.forEach(action);
    }
}
