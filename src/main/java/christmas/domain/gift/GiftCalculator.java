package christmas.domain.gift;

import christmas.domain.menu.SelectedMenus;

public sealed interface GiftCalculator permits AbstractGiftCalculator {
    GiftResult calculate(SelectedMenus selectedMenus);
}
