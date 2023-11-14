package christmas.Event;

import christmas.Menu.Menu;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BonusEvent extends Event {
    private Map<Menu, Integer> bonusMenu = new HashMap<>();
    private Integer discountAmount = 0;

    public BonusEvent() {
        this.name = "증정 이벤트";

        Calendar startDate = Calendar.getInstance();
        startDate.set(2023, Calendar.DECEMBER, 1);
        this.startDate = startDate;

        Calendar endDate = Calendar.getInstance();
        endDate.set(2023, Calendar.DECEMBER, 31);
        this.endDate = endDate;
    }

    public Map<Menu, Integer> getBonusMenu() {
        return bonusMenu;
    }

    public void setBonusMenu(Map<Menu, Integer> bonusMenu) {
        this.bonusMenu = bonusMenu;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }
}
