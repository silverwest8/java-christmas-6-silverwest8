package christmas.Event;

import java.util.Calendar;

public class Event {
    public String name;
    public Calendar startDate;
    public Calendar endDate;

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }
}
