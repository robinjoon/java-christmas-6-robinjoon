package christmas.domain.gift;

public enum GiftType {
    OVER_120000("샴페인", 25_000);

    private final String name;
    private final int price;

    GiftType(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
