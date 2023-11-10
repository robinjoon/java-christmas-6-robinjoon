package christmas.domain.discount;

import java.util.List;

public record DiscountResults(List<DiscountResult> discountResults) {

    public int getTotalDiscountPrice() {
        return discountResults.stream()
                .mapToInt(DiscountResult::discountedPrice)
                .sum();
    }
}
