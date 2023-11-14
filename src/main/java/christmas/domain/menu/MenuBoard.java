package christmas.domain.menu;

import static christmas.domain.menu.MenuType.APPETIZER;
import static christmas.domain.menu.MenuType.BEVERAGE;
import static christmas.domain.menu.MenuType.DESSERT;
import static christmas.domain.menu.MenuType.MAIN;

import christmas.exception.NoSuchMenuException;
import java.util.HashMap;
import java.util.Map;

public final class MenuBoard {
    private static final Map<String, Menu> menuBoard = new HashMap<>();

    public static int getMenuPrice(String menuName) {
        Menu menu = getMenu(menuName);
        return menu.getPrice();
    }

    static Menu getMenu(String menuName) {
        if (menuBoard.isEmpty()) {
            initBoard();
        }
        if (menuBoard.containsKey(menuName)) {
            return menuBoard.get(menuName);
        }
        throw new NoSuchMenuException();
    }

    private static void initBoard() {
        if (!menuBoard.isEmpty()) {
            return;
        }
        addMenu("양송이수프", APPETIZER, 6_000);
        addMenu("타파스", APPETIZER, 5_500);
        addMenu("시저샐러드", APPETIZER, 8_000);
        addMenu("티본스테이크", MAIN, 55_000);
        addMenu("바비큐립", MAIN, 54_000);
        addMenu("해산물파스타", MAIN, 35_000);
        addMenu("크리스마스파스타", MAIN, 25_000);
        addMenu("초코케이크", DESSERT, 15_000);
        addMenu("아이스크림", DESSERT, 5_000);
        addMenu("제로콜라", BEVERAGE, 3_000);
        addMenu("레드와인", BEVERAGE, 60_000);
        addMenu("샴페인", BEVERAGE, 25_000);
    }

    private static void addMenu(String menuName, MenuType menuType, int price) {
        menuBoard.put(menuName, new Menu(menuType, menuName, price));
    }
}
