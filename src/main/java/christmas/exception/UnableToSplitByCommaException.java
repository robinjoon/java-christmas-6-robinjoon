package christmas.exception;

public final class UnableToSplitByCommaException extends IllegalArgumentException {
    public UnableToSplitByCommaException() {
        super("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
