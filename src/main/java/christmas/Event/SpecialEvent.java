package christmas.Event;

import java.util.Arrays;
import java.util.List;

public class SpecialEvent extends Event {

    private final List<Integer> starDays;
    public static final Integer discountUnit = 1_000;
    private Integer discountAmount = 0;

    public SpecialEvent() {
        this.starDays = Arrays.asList(3, 10, 17, 24, 31);
    }

    public List<Integer> getStarDays() {
        return starDays;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }
}
