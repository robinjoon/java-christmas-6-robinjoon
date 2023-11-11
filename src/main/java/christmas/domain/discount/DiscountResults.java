package christmas.domain.discount;

import java.util.List;
import java.util.stream.Stream;

public final class DiscountResults {
    private final List<DiscountResult> discountResults;

    public DiscountResults(List<DiscountResult> discountResults) {
        this.discountResults = discountResults;
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
