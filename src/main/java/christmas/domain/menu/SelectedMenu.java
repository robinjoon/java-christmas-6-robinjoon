package christmas.domain.menu;

class SelectedMenu {
    private final Menu menu;
    private final int selectedCount;

    SelectedMenu(Menu menu, int selectedCount) {
        this.menu = menu;
        this.selectedCount = selectedCount;
    }

    String getName() {
        return menu.getName();
    }

    int getSelectedCount() {
        return selectedCount;
    }

    int getTotalPrice() {
        return menu.getPrice() * selectedCount;
    }

    MenuType getMenuType() {
        return menu.getMenuType();
    }
}
