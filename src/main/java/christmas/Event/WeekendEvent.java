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

    public List<Integer> getDayNum() {
        return dayNum;
    }

    public MenuCategory getAppliedMenuCategory() {
        return appliedCategory;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }
}
