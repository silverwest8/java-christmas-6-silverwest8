package christmas;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class WeekdayEvent extends Event {
    static final List<Integer> dayNum = Arrays.asList(
            Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY
    );
    ;
    static final MenuCategory appliedCategory = MenuCategory.디저트;
    static final Integer discountUnit = 2_023;
    private Integer discountAmount = 0;

    public List<Integer> getDayNum() {
        return dayNum;
    }

    public MenuCategory getAppliedMenuCategory() {
        return appliedCategory;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }


    public Integer getDiscountAmount() {
        return discountAmount;
    }
}
