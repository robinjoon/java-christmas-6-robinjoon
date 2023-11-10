package christmas.domain.menu;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SelectedMenus {
    private final List<SelectedMenu> selectedMenus;

    public SelectedMenus(final Map<String, Integer> menuNameToSelectedCount) {
        validate(menuNameToSelectedCount);
        selectedMenus = menuNameToSelectedCount.keySet()
                .stream()
                .map(menuName -> selectMenu(menuNameToSelectedCount, menuName))
                .toList();
    }

    private static SelectedMenu selectMenu(Map<String, Integer> menuNameToSelectedCount, String menuName) {
        Menu menu = Menu.from(menuName);
        return new SelectedMenu(menu, menuNameToSelectedCount.get(menuName));
    }

    private void validate(final Map<String, Integer> menuNameToSelectedCount) {
        validateUnknownMenuName(menuNameToSelectedCount);
        validateTotalMenuCount(menuNameToSelectedCount);
    }

    private void validateUnknownMenuName(final Map<String, Integer> menuNameToSelectedCount) {
        Set<String> menuNames = menuNameToSelectedCount.keySet();
        menuNames.forEach(Menu::from);
    }

    private void validateTotalMenuCount(final Map<String, Integer> menuNameToSelectedCount) {
        int totalMenuCount = calculateTotalMenuCount(menuNameToSelectedCount);
        if (totalMenuCount > 20) {
            throw new IllegalArgumentException();
        }
    }

    private static int calculateTotalMenuCount(Map<String, Integer> menuNameToSelectedCount) {
        return menuNameToSelectedCount.values().stream().mapToInt(value -> value).sum();
    }

    public int getTotalPrice() {
        return selectedMenus.stream()
                .mapToInt(SelectedMenu::getTotalPrice)
                .sum();
    }

    public int getMenuCountThatMenuTypeEqual(MenuType menuType) {
        return selectedMenus.stream()
                .filter(selectedMenu -> selectedMenu.getMenuType() == menuType)
                .mapToInt(SelectedMenu::getSelectedCount)
                .sum();
    }

    public SelectedMenusDTO to() {
        List<SelectedMenuDTO> selectedMenuDTOS = selectedMenus.stream()
                .map(SelectedMenu::to)
                .toList();
        return new SelectedMenusDTO(selectedMenuDTOS);
    }
}
