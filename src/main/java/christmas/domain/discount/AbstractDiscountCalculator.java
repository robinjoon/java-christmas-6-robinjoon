package christmas.domain.discount;

import christmas.domain.menu.SelectedMenus;
import java.time.LocalDate;

public abstract non-sealed class AbstractDiscountCalculator implements DiscountCalculator {
    @Override
    public final DiscountResult calculate(LocalDate selectedDate, SelectedMenus selectedMenus) {
        if (isDiscountAble(selectedMenus)) {
            return calculateDiscount(selectedDate, selectedMenus);
        }
        return DiscountResult.zero(getCalculateAbleDiscountType());
    }

    abstract DiscountResult calculateDiscount(LocalDate selectedDate, SelectedMenus selectedMenus);

    private boolean isDiscountAble(SelectedMenus selectedMenus) {
        int totalPrice = selectedMenus.getTotalPrice();
        return totalPrice >= 10_000;
    }

    abstract DiscountType getCalculateAbleDiscountType();
}
