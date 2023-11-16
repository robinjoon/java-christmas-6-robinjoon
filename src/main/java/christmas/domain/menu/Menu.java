package christmas.domain.menu;

final class Menu {

    private final MenuType menuType;
    private final String name;
    private final int price;

    Menu(MenuType menuType, String name, int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
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
