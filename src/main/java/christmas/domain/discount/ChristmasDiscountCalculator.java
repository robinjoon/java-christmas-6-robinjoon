package christmas.domain.discount;

import christmas.domain.menu.SelectedMenus;
import java.time.LocalDate;
import java.time.Period;

final class ChristmasDiscountCalculator extends AbstractDiscountCalculator {
    @Override
    DiscountResult calculateDiscount(LocalDate selectedDate, SelectedMenus selectedMenus) {
        if (isBeforeDecember2023(selectedDate)) {
            return DiscountResult.zero(getCalculateAbleDiscountType());
        }
        if (isAfterChristmas2023(selectedDate)) {
            return DiscountResult.zero(getCalculateAbleDiscountType());
        }
        Period period = Period.between(LocalDate.of(2023, 12, 1), selectedDate);
        int periodDays = period.getDays();
        int discountMoney = 1000 + periodDays * 100;
        return new DiscountResult(getCalculateAbleDiscountType(), discountMoney);
    }

    private static boolean isAfterChristmas2023(LocalDate selectedDate) {
        return selectedDate.isAfter(LocalDate.of(2023, 12, 25));
    }

    private static boolean isBeforeDecember2023(LocalDate selectedDate) {
        return selectedDate.isBefore(LocalDate.of(2023, 12, 1));
    }

    @Override
    DiscountType getCalculateAbleDiscountType() {
        return DiscountType.XMAS;
    }
}
