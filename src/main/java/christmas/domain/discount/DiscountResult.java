package christmas.domain.discount;

public record DiscountResult(DiscountType discountType, int discountedPrice) {
    static DiscountResult zero(DiscountType discountType) {
        return new DiscountResult(discountType, 0);
    }
}
