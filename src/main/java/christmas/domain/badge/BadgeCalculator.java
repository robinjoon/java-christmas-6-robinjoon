package christmas.domain.badge;

import static christmas.domain.badge.Badge.NONE;
import static christmas.domain.badge.Badge.SANTA;
import static christmas.domain.badge.Badge.STAR;
import static christmas.domain.badge.Badge.TREE;

import christmas.domain.discount.DiscountResults;
import christmas.domain.gift.GiftResults;

public final class BadgeCalculator {
    public Badge calculate(DiscountResults discountResults, GiftResults giftResults) {
        int benefitPrice = discountResults.getTotalDiscountPrice() + giftResults.getTotalGiftPrice();
        if (benefitPrice >= 20_000) {
            return SANTA;
        }
        if (benefitPrice >= 10_000) {
            return TREE;
        }
        if (benefitPrice >= 5_000) {
            return STAR;
        }
        return NONE;
    }
}
