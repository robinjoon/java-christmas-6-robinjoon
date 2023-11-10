package christmas.input.exception;

public class DuplicateMenuNameException extends IllegalArgumentException {
    public DuplicateMenuNameException() {
        super("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
