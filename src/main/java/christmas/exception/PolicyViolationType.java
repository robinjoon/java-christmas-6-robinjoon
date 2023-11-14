package christmas.exception;

public enum PolicyViolationType {
    DUPLICATE_MENU_NAME("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_SELECTED_COUNT_OVERFLOW("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NO_SUCH_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NO_SUCH_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    BEVERAGE_ONLY_SELECTED("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    UNABLE_TO_SPLIT_BAR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    UNABLE_TO_SPLIT_COMMA("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    private final static String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final String errorMessage;

    PolicyViolationType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return ERROR_MESSAGE_PREFIX + errorMessage;
    }
}
