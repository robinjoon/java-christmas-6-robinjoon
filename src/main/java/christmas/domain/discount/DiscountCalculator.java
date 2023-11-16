package christmas.domain.discount;

import christmas.domain.menu.SelectedMenus;
import java.time.LocalDate;

public sealed interface DiscountCalculator permits AbstractDiscountCalculator {
    DiscountResult calculate(LocalDate selectedDate, SelectedMenus selectedMenus);
}
