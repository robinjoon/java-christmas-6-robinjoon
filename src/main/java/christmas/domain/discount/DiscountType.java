package christmas.domain.discount;

public enum DiscountType {
    WEEK_DAY("평일 할인"), WEEK_END("주말 할인"), XMAS("크리스마스 디데이 할인"), SPECIAL("특별 할인");

    private final String description;

    DiscountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
