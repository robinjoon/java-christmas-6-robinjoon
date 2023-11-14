package christmas.domain.gift;

import java.util.Optional;

public final class GiftCalculatorMapper {
    public static Optional<GiftCalculator> map(GiftType giftType) {
        if (giftType == GiftType.OVER_120000) {
            return Optional.of(new Over120000GiftCalculator());
        }
        return Optional.empty();
    }
}
