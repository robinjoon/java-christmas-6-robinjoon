package christmas.output;

import christmas.domain.badge.Badge;
import christmas.domain.discount.DiscountResults;
import christmas.domain.discount.DiscountType;
import christmas.domain.gift.GiftResults;
import christmas.domain.gift.GiftType;
import christmas.domain.menu.SelectedMenusDTO;
import christmas.domain.statistics.Statistics;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class OutputManager {
    public static void print(Statistics statistics) {
        printDate(statistics);
        printMenu(statistics);
        printTotalPrice(statistics);
        printGift(statistics);
        printBenefit(statistics);
        printTotalBenefitPrice(statistics);
        printBadge(statistics);
    }

    private static void printDate(Statistics statistics) {
        LocalDate selectedDate = statistics.selectedDate();
        int month = selectedDate.getMonthValue();
        int day = selectedDate.getDayOfMonth();
        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", month, day);
    }

    private static void printMenu(Statistics statistics) {
        System.out.print("\n<주문 메뉴>\n");
        SelectedMenusDTO selectedMenusDTO = statistics.selectedMenusDTO();
        selectedMenusDTO.selectedMenuDTOS().forEach(selectedMenuDTO -> {
            System.out.printf("%s %d개\n", selectedMenuDTO.getName(), selectedMenuDTO.getSelectedCount());
        });
    }

    private static void printTotalPrice(Statistics statistics) {
        System.out.print("\n<할인 전 총주문 금액>\n");
        int totalPrice = statistics.getTotalPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        System.out.printf("%s원\n", decimalFormat.format(totalPrice));
    }

    private static void printGift(Statistics statistics) {
        System.out.print("\n<증정 메뉴>\n");
        GiftResults giftResults = statistics.giftResults();
        if (giftResults.getTotalGiftPrice() == 0) {
            System.out.println("없음");
            return;
        }
        giftResults.giftResults().forEach(giftResult -> {
            System.out.printf("%s %d개", giftResult.giftType().getName(), giftResult.giftCount());
        });
    }

    private static void printBenefit(Statistics statistics) {
        System.out.print("\n<혜택 내역>\n");
        GiftResults giftResults = statistics.giftResults();
        DiscountResults discountResults = statistics.discountResults();
        if (giftResults.getTotalGiftPrice() == 0 && discountResults.getTotalDiscountPrice() == 0) {
            System.out.println("없음");
            return;
        }
        discountResults.discountResults().forEach(discountResult -> {
            DiscountType discountType = discountResult.discountType();
            System.out.printf("%s: %d\n", discountType.getDescription(), -discountResult.discountedPrice());
        });
        giftResults.giftResults().forEach(giftResult -> {
            GiftType giftType = giftResult.giftType();
            System.out.printf("%s: %d\n", giftType.name(), -giftResult.giftPrice());
        });
    }

    private static void printTotalBenefitPrice(Statistics statistics) {
        System.out.print("\n<총혜택 금액>\n");
        int totalBenefitPrice = statistics.getTotalBenefitPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        System.out.printf("%s원\n", decimalFormat.format(-totalBenefitPrice));
    }

    private static void printBadge(Statistics statistics) {
        System.out.print("\n<12월 이벤트 배지>\n");
        Badge badge = statistics.badge();
        System.out.printf(badge.getDescription());
    }

    private static void printDiscountedTotalPrice(Statistics statistics) {
        System.out.print("\n<할인 후 예상 결제 금액>\n");
        int discountedTotalPrice = statistics.getDiscountedTotalPrice();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        System.out.printf("%s원\n", decimalFormat.format(discountedTotalPrice));
    }
}
