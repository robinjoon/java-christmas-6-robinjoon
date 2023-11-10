package christmas;

import christmas.domain.badge.BadgeCalculator;
import christmas.domain.menu.SelectedMenus;
import christmas.domain.statistics.Statistics;
import christmas.domain.statistics.StatisticsGenerator;
import christmas.exception.DuplicateMenuNameException;
import christmas.exception.MenuSelectedCountOverflowException;
import christmas.exception.NoSuchDateException;
import christmas.exception.NoSuchMenuException;
import christmas.exception.OnlyBeverageSelectedException;
import christmas.exception.UnableToSplitByBarException;
import christmas.exception.UnableToSplitByCommaException;
import christmas.input.InputManager;
import christmas.output.OutputManager;
import java.time.LocalDate;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        OutputManager.printStartMessage();
        LocalDate selectedDate;
        while (true) {
            try {
                selectedDate = InputManager.getDateFromConsole();
                break;
            } catch (NoSuchDateException e) {
                System.out.println(e.getMessage());
            }
        }
        OutputManager.printMenuInputGuide();
        Map<String, Integer> menuNameToSelectedCount;

        while (true) {
            try {
                menuNameToSelectedCount = InputManager.getMenuNameToSelectedCountMapFromConsole();
                break;
            } catch (UnableToSplitByBarException | UnableToSplitByCommaException | DuplicateMenuNameException e) {
                System.out.println(e.getMessage());
            }
        }
        SelectedMenus selectedMenus;
        while (true) {
            try {
                selectedMenus = new SelectedMenus(menuNameToSelectedCount);
                break;
            } catch (OnlyBeverageSelectedException | MenuSelectedCountOverflowException | NoSuchMenuException e) {
                System.out.println(e.getMessage());
            }
        }
        StatisticsGenerator statisticsGenerator = new StatisticsGenerator(new BadgeCalculator());
        Statistics statistics = statisticsGenerator.makeStatistics(selectedDate, selectedMenus);
        OutputManager.print(statistics);
    }
}
