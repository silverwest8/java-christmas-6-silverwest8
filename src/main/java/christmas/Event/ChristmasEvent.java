package christmas.Event;

import java.util.Calendar;

public class ChristmasEvent extends Event {
    static Calendar christmasDay;
    public static final Integer minDiscountAmount = 1_000;
    public static final Integer discountUnit = 100;
    private Integer discountAmount = 0;

    public ChristmasEvent() {
        this.name = "크리스마스 디데이 할인";

        Calendar christmasDay = Calendar.getInstance();
        christmasDay.set(2023, Calendar.DECEMBER, 25);
        ChristmasEvent.christmasDay = christmasDay;


        Calendar startDate = Calendar.getInstance();
        startDate.set(2023, Calendar.DECEMBER, 1);
        this.startDate = startDate;

        Calendar endDate = Calendar.getInstance();
        endDate.set(2023, Calendar.DECEMBER, 25);
        this.endDate = endDate;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }
}
