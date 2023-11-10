package christmas.domain.statistics;

import christmas.domain.badge.Badge;
import christmas.domain.discount.DiscountResults;
import christmas.domain.gift.GiftResults;
import christmas.domain.menu.SelectedMenuDTO;
import christmas.domain.menu.SelectedMenusDTO;

public record Statistics(SelectedMenusDTO selectedMenusDTO, GiftResults giftResults, DiscountResults discountResults,
                         Badge badge) {

    public int getTotalBenefitPrice() {
        int totalDiscountPrice = discountResults.getTotalDiscountPrice();
        int totalGiftPrice = giftResults.getTotalGiftPrice();
        return totalDiscountPrice + totalGiftPrice;
    }

    public int getDiscountedTotalPrice() {
        int totalDiscountPrice = discountResults.getTotalDiscountPrice();
        return getTotalPrice() - totalDiscountPrice;
    }

    public int getTotalPrice() {
        return selectedMenusDTO.selectedMenuDTOS()
                .stream()
                .mapToInt(SelectedMenuDTO::getTotalPrice)
                .sum();
    }
}
