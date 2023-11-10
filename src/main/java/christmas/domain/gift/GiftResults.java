package christmas.domain.gift;

import java.util.List;

public class GiftResults {
    private final List<GiftResult> giftResults;

    public GiftResults(List<GiftResult> giftResults) {
        this.giftResults = giftResults;
    }

    public int getTotalGiftPrice() {
        return giftResults.stream()
                .mapToInt(GiftResult::giftPrice)
                .sum();
    }
}
