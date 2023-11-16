package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.WEEK_DAY;

import christmas.domain.menu.MenuType;
import christmas.domain.menu.SelectedMenus;
import java.time.DayOfWeek;
import java.time.LocalDate;

final class WeekDayDiscountCalculator extends AbstractDiscountCalculator {
    @Override
    DiscountResult calculateDiscount(LocalDate selectedDate, SelectedMenus selectedMenus) {
        if (selectedDate.getDayOfWeek() == DayOfWeek.FRIDAY || selectedDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return DiscountResult.zero(getCalculateAbleDiscountType());
        }
        int discountedMenuCount = selectedMenus.getMenuCountThatMenuTypeEqual(MenuType.DESSERT);
        return new DiscountResult(getCalculateAbleDiscountType(), discountedMenuCount * 2023);
    }

    @Override
    DiscountType getCalculateAbleDiscountType() {
        return WEEK_DAY;
    }
}
