package christmas.domain.gift;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class GiftResults {
    private final List<GiftResult> giftResults;

    public GiftResults(List<GiftResult> giftResults) {
        this.giftResults = giftResults;
    }

    public int getTotalGiftPrice() {
        return giftResults.stream()
                .mapToInt(GiftResult::giftPrice)
                .sum();
    }

    public Stream<GiftResult> stream() {
        return giftResults.stream();
    }

    public void forEach(Consumer<? super GiftResult> action) {
        giftResults.forEach(action);
    }
}
