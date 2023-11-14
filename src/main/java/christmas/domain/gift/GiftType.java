package christmas.domain.gift;

import static christmas.exception.PolicyViolationType.NO_SUCH_MENU;

import christmas.domain.menu.MenuBoard;
import christmas.exception.PolicyViolationException;

public enum GiftType {
    OVER_120000("샴페인", 25_000);

    private final String name;
    private final int price;

    GiftType(String name, int price) {
        this.name = name;
        validateMenu(name, price);
        this.price = price;
    }

    private static void validateMenu(String name, int price) {
        if (MenuBoard.getMenuPrice(name) != price) {
            throw new PolicyViolationException(NO_SUCH_MENU);
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
