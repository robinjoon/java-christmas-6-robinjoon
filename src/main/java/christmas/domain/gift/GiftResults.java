package christmas.domain.gift;

import java.util.List;

public record GiftResults(List<GiftResult> giftResults) {

    public int getTotalGiftPrice() {
        return giftResults.stream()
                .mapToInt(GiftResult::giftPrice)
                .sum();
    }
}
