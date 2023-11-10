package christmas.domain.discount;

import java.util.List;

public class DiscountResults {
    private final List<DiscountResult> discountResults;

    public DiscountResults(List<DiscountResult> discountResults) {
        this.discountResults = discountResults;
    }

    public int getTotalDiscountPrice() {
        return discountResults.stream()
                .mapToInt(DiscountResult::discountedPrice)
                .sum();
    }
}
