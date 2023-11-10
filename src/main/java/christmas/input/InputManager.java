package christmas.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class InputManager {

    public static Map<String, Integer> getMenuNameToSelectedCountMapFromConsole() {
        String input = Console.readLine();
        return getMenuNameToSelectedCountMap(input);
    }

    public static Map<String, Integer> getMenuNameToSelectedCountMap(String input) {
        StringSplitter commaSplitter = new StringSplitter(',');
        List<String> commaSplitInputs = commaSplitter.split(input);
        List<String> barSplitInputs = commaSplitInputs.stream()
                .flatMap(InputManager::splitWithBar)
                .toList();
        try {
            return makeMenuNameToSelectedCountMap(barSplitInputs);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private static Stream<String> splitWithBar(String commaSplitInput) {
        StringSplitter barSplitter = new StringSplitter('-');
        return barSplitter.split(commaSplitInput).stream();
    }

    private static Map<String, Integer> makeMenuNameToSelectedCountMap(List<String> barSplitInputs) {
        if (barSplitInputs.size() <= 1) {
            throw new IllegalArgumentException();
        }
        if (barSplitInputs.size() % 2 == 1) {
            throw new IllegalArgumentException();
        }
        Map<String, Integer> result = new HashMap<>();
        for (int index = 0; index < barSplitInputs.size(); index = index + 2) {
            validateDuplicateMenuName(barSplitInputs, result, index);
            result.put(barSplitInputs.get(index), Integer.parseInt(barSplitInputs.get(index + 1)));
        }
        return result;
    }

    private static void validateDuplicateMenuName(List<String> barSplitInputs, Map<String, Integer> result, int index) {
        if (result.containsKey(barSplitInputs.get(index))) {
            throw new IllegalArgumentException();
        }
    }
}
