package christmas;

import christmas.domain.eventplanner.EventPlanner;
import christmas.domain.statistics.StatisticsGenerator;

public class Application {
    public static void main(String[] args) {
        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
        EventPlanner eventPlanner = new EventPlanner(statisticsGenerator);
        eventPlanner.start();
    }
}
