package christmas.domain.gift;

public record GiftResult(GiftType giftType, int giftPrice) {
    public GiftResult(GiftType giftType) {
        this(giftType, giftType.getPrice());
    }

    public static GiftResult zero(GiftType giftType) {
        return new GiftResult(giftType, 0);
    }
}
