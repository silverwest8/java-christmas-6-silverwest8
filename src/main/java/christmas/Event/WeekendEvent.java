package christmas.Event;

import christmas.Menu.MenuCategory;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class WeekendEvent extends Event {
    public static final List<Integer> dayNum = Arrays.asList(Calendar.FRIDAY, Calendar.SATURDAY);
    public static final MenuCategory appliedCategory = MenuCategory.메인;
    public static final Integer discountUnit = 2_023;
    private Integer discountAmount = 0;

    public WeekendEvent() {
        this.name = "주말 할인";
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }
}
