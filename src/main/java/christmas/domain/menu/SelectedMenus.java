package christmas.domain.menu;

import java.util.List;
import java.util.Map;

public class SelectedMenus {
    private final List<SelectedMenu> selectedMenus;

    public SelectedMenus(final Map<String, Integer> menuNameToSelectedCount) {
        selectedMenus = menuNameToSelectedCount.keySet()
                .stream()
                .map(menuName -> {
                    Menu menu = Menu.from(menuName);
                    return new SelectedMenu(menu, menuNameToSelectedCount.get(menuName));
                })
                .toList();
    }

    public int getTotalPrice() {
        return selectedMenus.stream()
                .mapToInt(SelectedMenu::getTotalPrice)
                .sum();
    }
}
