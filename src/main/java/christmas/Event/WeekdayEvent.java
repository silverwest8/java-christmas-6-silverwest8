package christmas.Event;

import christmas.Menu.MenuCategory;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class WeekdayEvent extends Event {
    public static final List<Integer> dayNum = Arrays.asList(
            Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY
    );
    public static final MenuCategory appliedCategory = MenuCategory.디저트;
    public static final Integer discountUnit = 2_023;
    private Integer discountAmount = 0;

    public WeekdayEvent() {
        this.name = "평일 할인";
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }
}
