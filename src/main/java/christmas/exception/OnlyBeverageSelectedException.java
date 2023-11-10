package christmas.exception;

public class OnlyBeverageSelectedException extends IllegalArgumentException {
    public OnlyBeverageSelectedException() {
        super("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
