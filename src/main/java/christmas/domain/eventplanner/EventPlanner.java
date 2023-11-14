package christmas.domain.eventplanner;

import christmas.domain.menu.SelectedMenus;
import christmas.domain.statistics.Statistics;
import christmas.domain.statistics.StatisticsGenerator;
import christmas.input.InputManager;
import christmas.output.OutputManager;
import java.time.LocalDate;
import java.util.Map;

public final class EventPlanner {

    private EventPlanner() {

    }

    public static void start() {
        LocalDate selectedDate = getSelectedDate();
        SelectedMenus selectedMenus = getSelectedMenus();
        Statistics statistics = StatisticsGenerator.makeStatistics(selectedDate, selectedMenus);
        OutputManager.print(statistics);
    }

    private static SelectedMenus getSelectedMenus() {
        RetryHelper<SelectedMenus> selectedMenusretryHelper = new RetryHelper<>();
        return selectedMenusretryHelper.retry(EventPlanner::getSelectedMenusOnce);
    }

    private static SelectedMenus getSelectedMenusOnce() {
        OutputManager.printMenuInputGuide();
        Map<String, Integer> menuNameToSelectedCount = InputManager.getMenuNameToSelectedCountMapFromConsole();
        return new SelectedMenus(menuNameToSelectedCount);
    }

    private static LocalDate getSelectedDate() {
        RetryHelper<LocalDate> localDateRetryHelper = new RetryHelper<>();
        return localDateRetryHelper.retry(EventPlanner::getLocalDateOnce);
    }

    private static LocalDate getLocalDateOnce() {
        OutputManager.printStartMessage();
        return InputManager.getDateFromConsole();
    }
}
