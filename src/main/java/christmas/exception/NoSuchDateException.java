package christmas.exception;

public class NoSuchDateException extends IllegalArgumentException {
    public NoSuchDateException() {
        super("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
