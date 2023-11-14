package christmas;

import java.util.Calendar;
import java.util.Map;

public class Event {
    public Calendar eventStartDate;
    public Calendar eventEndDate;

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
