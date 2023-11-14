package christmas.domain.badge;

import christmas.domain.discount.DiscountResult;
import christmas.domain.discount.DiscountResults;
import christmas.domain.discount.DiscountType;
import christmas.domain.gift.GiftResult;
import christmas.domain.gift.GiftResults;
import christmas.domain.gift.GiftType;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BadgeCalculatorTest {
    static Stream<Arguments> calculateParameter() {
        return IntStream.of(4_999, 5_000, 9_999, 10_000, 19_999, 20_000)
                .mapToObj(BadgeCalculatorTest::makeSingleArgument);
    }

    private static Arguments makeSingleArgument(int totalPrice) {
        DiscountResults discountResults = makeDiscountResults(totalPrice);
        GiftResults giftResults = new GiftResults(List.of(GiftResult.zero(GiftType.OVER_120000)));
        Badge expected = makeBadge(totalPrice);
        return Arguments.of(discountResults, giftResults, expected);
    }

    private static DiscountResults makeDiscountResults(int totalPrice) {
        DiscountResult first = new DiscountResult(DiscountType.XMAS, totalPrice / 2);
        DiscountResult second = new DiscountResult(DiscountType.XMAS, totalPrice - totalPrice / 2);
        return new DiscountResults(List.of(first, second));
    }

    private static Badge makeBadge(int totalPrice) {
        if (totalPrice >= 20_000) {
            return Badge.SANTA;
        }
        if (totalPrice >= 10_000) {
            return Badge.TREE;
        }
        if (totalPrice >= 5_000) {
            return Badge.STAR;
        }
        return Badge.NONE;
    }

    @DisplayName("정상 테스트")
    @ParameterizedTest
    @MethodSource("calculateParameter")
    void calculate(DiscountResults discountResults, GiftResults giftResults, Badge expected) {
        Badge calculated = BadgeCalculator.calculate(discountResults, giftResults);
        Assertions.assertThat(calculated).isEqualTo(expected);
    }
}