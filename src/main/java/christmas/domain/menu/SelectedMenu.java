package christmas.domain.menu;

final class SelectedMenu {
    private final Menu menu;
    private final int selectedCount;

    SelectedMenu(Menu menu, int selectedCount) {
        this.menu = menu;
        this.selectedCount = selectedCount;
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

    SelectedMenuDTO to() {
        return new SelectedMenuDTO(menu.getName(), menu.getPrice(), selectedCount);
    }
}
