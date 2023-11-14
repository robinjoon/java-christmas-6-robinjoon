package christmas.domain.gift;

public record GiftResult(GiftType giftType, int giftCount) {
    public GiftResult(GiftType giftType) {
        this(giftType, 1);
    }

    public static GiftResult zero(GiftType giftType) {
        return new GiftResult(giftType, 0);
    }

    public int giftPrice() {
        if (giftCount == 0) {
            return 0;
        }
        return giftType.getPrice();
    }
}
