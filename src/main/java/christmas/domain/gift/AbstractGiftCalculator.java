package christmas.domain.gift;

import christmas.domain.menu.SelectedMenus;

abstract non-sealed class AbstractGiftCalculator implements GiftCalculator {
    @Override
    public final GiftResult calculate(SelectedMenus selectedMenus) {
        if (canGiveGift(selectedMenus)) {
            return calculateGift(selectedMenus);
        }
        return GiftResult.zero(getCalculateAbleGiftType());
    }

    abstract GiftResult calculateGift(SelectedMenus selectedMenus);

    private boolean canGiveGift(SelectedMenus selectedMenus) {
        int totalPrice = selectedMenus.getTotalPrice();
        return totalPrice >= 10_000;
    }

    abstract GiftType getCalculateAbleGiftType();
}
