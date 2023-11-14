package christmas.domain.menu;

import static christmas.exception.PolicyViolationType.BEVERAGE_ONLY_SELECTED;
import static christmas.exception.PolicyViolationType.MENU_SELECTED_COUNT_OVERFLOW;

import christmas.exception.PolicyViolationException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class SelectedMenus {
    private final List<SelectedMenu> selectedMenus;

    public SelectedMenus(final Map<String, Integer> menuNameToSelectedCount) {
        validate(menuNameToSelectedCount);
        selectedMenus = menuNameToSelectedCount.keySet()
                .stream()
                .map(menuName -> selectMenu(menuNameToSelectedCount, menuName))
                .toList();
    }

    private static SelectedMenu selectMenu(Map<String, Integer> menuNameToSelectedCount, String menuName) {
        Menu menu = MenuBoard.getMenu(menuName);
        return new SelectedMenu(menu, menuNameToSelectedCount.get(menuName));
    }

    private void validate(final Map<String, Integer> menuNameToSelectedCount) {
        validateUnknownMenuName(menuNameToSelectedCount);
        validateTotalMenuCount(menuNameToSelectedCount);
        validateBeverage(menuNameToSelectedCount);
        validateMenuCountIsPositive(menuNameToSelectedCount);
    }

    private void validateUnknownMenuName(final Map<String, Integer> menuNameToSelectedCount) {
        Set<String> menuNames = menuNameToSelectedCount.keySet();
        menuNames.forEach(MenuBoard::getMenu);
    }

    private void validateTotalMenuCount(final Map<String, Integer> menuNameToSelectedCount) {
        int totalMenuCount = calculateTotalMenuCount(menuNameToSelectedCount);
        if (totalMenuCount > 20) {
            throw new PolicyViolationException(MENU_SELECTED_COUNT_OVERFLOW);
        }
    }

    private static int calculateTotalMenuCount(Map<String, Integer> menuNameToSelectedCount) {
        return menuNameToSelectedCount.values().stream().mapToInt(value -> value).sum();
    }

    private void validateBeverage(final Map<String, Integer> menuNameToSelectedCount) {
        boolean isOnlyBeverage = isOnlyBeverage(menuNameToSelectedCount);
        if (isOnlyBeverage) {
            throw new PolicyViolationException(BEVERAGE_ONLY_SELECTED);
        }
    }

    private static boolean isOnlyBeverage(Map<String, Integer> menuNameToSelectedCount) {
        return menuNameToSelectedCount.keySet().stream()
                .map(MenuBoard::getMenu)
                .allMatch(menu -> menu.getMenuType() == MenuType.BEVERAGE);
    }

    private void validateMenuCountIsPositive(Map<String, Integer> menuNameToSelectedCount) {
        if (menuNameToSelectedCount.containsValue(0)) {
            throw new PolicyViolationException(MENU_SELECTED_COUNT_OVERFLOW);
        }
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
