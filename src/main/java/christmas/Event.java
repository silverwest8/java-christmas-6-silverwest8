package christmas;

import java.util.Calendar;
import java.util.Map;

public class Event {
    private Calendar eventStartDate;
    private Calendar eventEndDate;

    //    public Event(Calendar eventStartDate, Calendar eventEndDate) {
    public Event() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2023, Calendar.DECEMBER, 1);
        this.eventStartDate = startDate;

        Calendar endDate = Calendar.getInstance();
        endDate.set(2023, Calendar.DECEMBER, 31);
        this.eventEndDate = endDate;
    }

    public Calendar getEventStartDate() {
        return eventStartDate;
    }

    public Calendar getEventEndDate() {
        return eventEndDate;
    }

    public void setEventStartDate(Calendar eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public void setEventEndDate(Calendar eventEndDate) {
        this.eventEndDate = eventEndDate;
    }
}
