package christmas.domain.discount;

import static christmas.domain.discount.DiscountType.SPECIAL;
import static christmas.domain.discount.DiscountType.WEEK_DAY;
import static christmas.domain.discount.DiscountType.WEEK_END;
import static christmas.domain.discount.DiscountType.XMAS;

import java.util.Optional;

public final class DiscountCalculatorMapper {
    public static Optional<DiscountCalculator> map(DiscountType discountType) {
        if (discountType == SPECIAL) {
            return Optional.of(new SpecialDiscountCalculator());
        }
        if (discountType == WEEK_DAY) {
            return Optional.of(new WeekDayDiscountCalculator());
        }
        if (discountType == WEEK_END) {
            return Optional.of(new WeekEndDiscountCalculator());
        }
        if (discountType == XMAS) {
            return Optional.of(new ChristmasDiscountCalculator());
        }
        return Optional.empty();
    }
}
