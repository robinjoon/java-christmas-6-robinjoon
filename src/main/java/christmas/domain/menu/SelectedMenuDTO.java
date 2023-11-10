package christmas.domain.menu;

public final class SelectedMenuDTO {
    private final String name;
    private final int price;
    private final int selectedCount;

    public SelectedMenuDTO(String name, int price, int selectedCount) {
        this.name = name;
        this.price = price;
        this.selectedCount = selectedCount;
    }

    public int getTotalPrice() {
        return selectedCount * price;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public String getName() {
        return name;
    }
}
