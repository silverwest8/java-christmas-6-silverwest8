package christmas;

import java.util.Calendar;

public class BonusEvent extends Event {
    public BonusEvent() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2023, Calendar.DECEMBER, 1);
        eventStartDate = startDate;

        Calendar endDate = Calendar.getInstance();
        endDate.set(2023, Calendar.DECEMBER, 31);
        eventEndDate = endDate;
    }
}
