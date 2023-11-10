package christmas.domain.gift;

import static christmas.domain.gift.GiftType.OVER_120000;

import christmas.domain.menu.SelectedMenus;

class Over120000GiftCalculator extends AbstractGiftCalculator {
    @Override
    GiftResult calculateGift(SelectedMenus selectedMenus) {
        if (selectedMenus.getTotalPrice() >= 120_000) {
            return new GiftResult(OVER_120000);
        }
        return GiftResult.zero(OVER_120000);
    }

    @Override
    GiftType getCalculateAbleGiftType() {
        return OVER_120000;
    }
}
