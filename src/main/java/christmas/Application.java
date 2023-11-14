package christmas;

import christmas.domain.eventplanner.EventPlanner;

public class Application {
    public static void main(String[] args) {
        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.start();
    }
}
