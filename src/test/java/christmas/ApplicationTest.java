package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @DisplayName("모든 타이틀 출력")
    @Test
    void allTitlePrint() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @DisplayName("혜택 내역 없음 출력")
    @Test
    void noBenefit() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @DisplayName("날짜 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a", "0", "32"})
    void 날짜_예외_테스트(String input) {
        assertSimpleTest(() -> {
            run(input, "26", "타파스-1,제로콜라-1");
            assertThat(output())
                    .contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.", "<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @DisplayName("주문 목록 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a", "a-1", "a-1,", ",a-1", " a-1,b-2", "a-1,b-1 ", "양송이수프-0", "양송이수프-1,양송이수프-1",
            "양송이수프-10,제로콜라-11"})
    void wrongSelectedMenu(String input) {
        assertSimpleTest(() -> {
            run("26", input, "타파스-1,제로콜라-1");
            assertThat(output())
                    .contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.", "<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
