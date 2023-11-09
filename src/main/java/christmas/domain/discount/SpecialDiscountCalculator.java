package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.SPECIAL;

import christmas.domain.menu.SelectedMenus;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SpecialDiscountCalculator extends AbstractDiscountCalculator {
    private static final Set<LocalDate> specialDates = new HashSet<>();

    static {
        specialDates.add(LocalDate.of(2023, 12, 3));
        specialDates.add(LocalDate.of(2023, 12, 10));
        specialDates.add(LocalDate.of(2023, 12, 17));
        specialDates.add(LocalDate.of(2023, 12, 24));
        specialDates.add(LocalDate.of(2023, 12, 25));
        specialDates.add(LocalDate.of(2023, 12, 31));
    }

    @Override
    DiscountResult calculateDiscount(LocalDate selectedDate, SelectedMenus selectedMenus) {
        if (specialDates.contains(selectedDate)) {
            return new DiscountResult(getCalculateAbleDiscountType(), 1000);
        }
        return DiscountResult.zero(getCalculateAbleDiscountType());
    }

    @Override
    DiscountType getCalculateAbleDiscountType() {
        return SPECIAL;
    }
}
