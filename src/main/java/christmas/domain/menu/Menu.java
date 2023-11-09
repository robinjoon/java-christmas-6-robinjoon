package christmas.domain.menu;

import static christmas.domain.menu.MenuType.APPETIZER;
import static christmas.domain.menu.MenuType.BEVERAGE;
import static christmas.domain.menu.MenuType.DESSERT;
import static christmas.domain.menu.MenuType.MAIN;

import java.util.HashMap;
import java.util.Map;

final class Menu {
    private static final Map<String, Menu> menuBoard = new HashMap<>();

    static {
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

    private final MenuType menuType;
    private final String name;
    private final int price;

    private Menu(MenuType menuType, String name, int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    static Menu from(String menuName) {
        if (menuBoard.containsKey(menuName)) {
            return menuBoard.get(menuName);
        }
        throw new IllegalArgumentException("없는 메뉴");
    }

    static void addMenu(String menuName, MenuType menuType, int price) {
        menuBoard.put(menuName, new Menu(menuType, menuName, price));
    }

    String getName() {
        return name;
    }

    int getPrice() {
        return price;
    }

    MenuType getMenuType() {
        return menuType;
    }
}
