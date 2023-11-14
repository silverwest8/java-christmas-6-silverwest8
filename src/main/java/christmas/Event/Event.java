package christmas.Event;

import java.util.Calendar;

public class Event {
    public Calendar startDate;
    public Calendar endDate;

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
}
