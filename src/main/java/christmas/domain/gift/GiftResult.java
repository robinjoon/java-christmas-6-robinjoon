package christmas.domain.gift;

public record GiftResult(GiftType giftType, int giftPrice, int giftCount) {
    public GiftResult(GiftType giftType) {
        this(giftType, giftType.getPrice(), 1);
    }

    public static GiftResult zero(GiftType giftType) {
        return new GiftResult(giftType, 0, 1);
    }
}
