package christmas;

import java.util.Calendar;
import java.util.Map;

public class Event {
    private Calendar visitDate;
    private Map<Menu, Integer> orderedMenu;
    private Integer totalPrice;
    private Map<Menu, Integer> bonusMenu;

    public Calendar getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Calendar visitDate) {
        this.visitDate = visitDate;
    }

    public Map<Menu, Integer> getOrderedMenu() {
        return orderedMenu;
    }

    public void setOrderedMenu(Map<Menu, Integer> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Menu, Integer> getBonusMenu() {
        return bonusMenu;
    }

    public void setBonusMenu(Map<Menu, Integer> bonusMenu) {
        this.bonusMenu = bonusMenu;
    }
}
