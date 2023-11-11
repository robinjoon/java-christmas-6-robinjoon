package christmas.domain.eventplanner;

import christmas.domain.menu.SelectedMenus;
import christmas.domain.statistics.Statistics;
import christmas.domain.statistics.StatisticsGenerator;
import christmas.input.InputManager;
import christmas.output.OutputManager;
import java.time.LocalDate;
import java.util.Map;

public final class EventPlanner {
    private final StatisticsGenerator statisticsGenerator;

    public EventPlanner(StatisticsGenerator statisticsGenerator) {
        this.statisticsGenerator = statisticsGenerator;
    }

    public void start() {
        LocalDate selectedDate = getSelectedDate();
        SelectedMenus selectedMenus = getSelectedMenus();
        Statistics statistics = statisticsGenerator.makeStatistics(selectedDate, selectedMenus);
        OutputManager.print(statistics);
    }

    private SelectedMenus getSelectedMenus() {
        RetryHelper<SelectedMenus> selectedMenusretryHelper = new RetryHelper<>();
        return selectedMenusretryHelper.retry(this::getSelectedMenusOnce);
    }

    private SelectedMenus getSelectedMenusOnce() {
        OutputManager.printMenuInputGuide();
        Map<String, Integer> menuNameToSelectedCount = InputManager.getMenuNameToSelectedCountMapFromConsole();
        return new SelectedMenus(menuNameToSelectedCount);
    }

    private LocalDate getSelectedDate() {
        RetryHelper<LocalDate> localDateRetryHelper = new RetryHelper<>();
        return localDateRetryHelper.retry(this::getLocalDateOnce);
    }

    private LocalDate getLocalDateOnce() {
        OutputManager.printStartMessage();
        return InputManager.getDateFromConsole();
    }
}
