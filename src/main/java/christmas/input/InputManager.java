package christmas.input;

import static christmas.exception.PolicyViolationType.DUPLICATE_MENU_NAME;
import static christmas.exception.PolicyViolationType.NO_SUCH_DATE;
import static christmas.exception.PolicyViolationType.UNABLE_TO_SPLIT_BAR;
import static christmas.exception.PolicyViolationType.UNABLE_TO_SPLIT_COMMA;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.PolicyViolationException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class InputManager {

    public static LocalDate getDateFromConsole() {
        String input = Console.readLine();
        return getDate(input);
    }

    public static LocalDate getDate(String input) {
        int day;
        try {
            day = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new PolicyViolationException(NO_SUCH_DATE);
        }
        if (day < 1 || day > 31) {
            throw new PolicyViolationException(NO_SUCH_DATE);
        }
        return LocalDate.of(2023, 12, day);
    }

    public static Map<String, Integer> getMenuNameToSelectedCountMapFromConsole() {
        String input = Console.readLine();
        return getMenuNameToSelectedCountMap(input);
    }

    public static Map<String, Integer> getMenuNameToSelectedCountMap(String input) {
        List<String> commaSplitInputs = commaSplit(input);
        List<String> barSplitInputs = commaSplitInputs.stream()
                .flatMap(InputManager::splitWithBar)
                .toList();
        try {
            return makeMenuNameToSelectedCountMap(barSplitInputs);
        } catch (NumberFormatException e) {
            throw new PolicyViolationException(UNABLE_TO_SPLIT_BAR);
        }
    }

    private static List<String> commaSplit(String input) {
        try {
            StringSplitter commaSplitter = new StringSplitter(',');
            return commaSplitter.split(input);
        } catch (IllegalArgumentException e) {
            throw new PolicyViolationException(UNABLE_TO_SPLIT_COMMA);
        }
    }

    private static Stream<String> splitWithBar(String commaSplitInput) {
        StringSplitter barSplitter = new StringSplitter('-');
        return barSplitter.split(commaSplitInput).stream();
    }

    private static Map<String, Integer> makeMenuNameToSelectedCountMap(List<String> barSplitInputs) {
        if (barSplitInputs.size() <= 1) {
            throw new PolicyViolationException(UNABLE_TO_SPLIT_BAR);
        }
        if (barSplitInputs.size() % 2 == 1) {
            throw new PolicyViolationException(UNABLE_TO_SPLIT_BAR);
        }
        Map<String, Integer> result = new HashMap<>();
        for (int index = 0; index < barSplitInputs.size(); index = index + 2) {
            validateDuplicateMenuName(barSplitInputs, result, index);
            validateSelectedCountStringContainPlus(barSplitInputs.get(index + 1));
            result.put(barSplitInputs.get(index), Integer.parseInt(barSplitInputs.get(index + 1)));
        }
        return result;
    }

    private static void validateDuplicateMenuName(List<String> barSplitInputs, Map<String, Integer> result, int index) {
        if (result.containsKey(barSplitInputs.get(index))) {
            throw new PolicyViolationException(DUPLICATE_MENU_NAME);
        }
    }

    private static void validateSelectedCountStringContainPlus(String selectedCountString) {
        if (selectedCountString.contains("+")) {
            throw new NumberFormatException();
        }
    }
}
