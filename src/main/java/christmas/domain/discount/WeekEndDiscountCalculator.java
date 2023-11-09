package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.WEEK_END;

import christmas.domain.menu.MenuType;
import christmas.domain.menu.SelectedMenus;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekEndDiscountCalculator extends AbstractDiscountCalculator {
    @Override
    DiscountResult calculateDiscount(LocalDate selectedDate, SelectedMenus selectedMenus) {
        if (selectedDate.getDayOfWeek() == DayOfWeek.FRIDAY || selectedDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            int discountedMenuCount = selectedMenus.getMenuCountThatMenuTypeEqual(MenuType.MAIN);
            return new DiscountResult(getCalculateAbleDiscountType(), discountedMenuCount * 2023);
        }
        return DiscountResult.zero(getCalculateAbleDiscountType());
    }

    @Override
    DiscountType getCalculateAbleDiscountType() {
        return WEEK_END;
    }
}
