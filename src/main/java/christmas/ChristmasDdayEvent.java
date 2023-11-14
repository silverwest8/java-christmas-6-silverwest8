package christmas;

import java.util.Calendar;

public class ChristmasDdayEvent extends Event {

    public ChristmasDdayEvent() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2023, Calendar.DECEMBER, 1);
        eventStartDate = startDate;

        Calendar endDate = Calendar.getInstance();
        endDate.set(2023, Calendar.DECEMBER, 25);
        eventEndDate = endDate;
    }
}
