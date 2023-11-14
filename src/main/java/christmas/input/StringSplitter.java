package christmas.input;

import java.util.Arrays;
import java.util.List;

final class StringSplitter {

    private final char base;

    StringSplitter(char base) {
        this.base = base;
    }

    List<String> split(String input) {
        validate(input);
        return Arrays.stream(input.split(String.valueOf(base))).toList();
    }

    private void validate(String input) {
        validateNull(input);
        validateEmpty(input);
        validateFirst(input);
        validateLast(input);
    }

    private void validateNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
    }

    private void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateFirst(String input) {
        char first = input.charAt(0);
        if (first == base || String.valueOf(first).isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateLast(String input) {
        char last = input.charAt(input.length() - 1);
        if (last == base || String.valueOf(last).isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
