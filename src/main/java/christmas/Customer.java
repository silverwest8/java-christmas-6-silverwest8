package christmas;

import java.util.*;

public class Customer {

    private Calendar visitDate;
    private Map<Menu, Integer> orderedMenu = new HashMap<>();
    private Integer totalPrice;
    private List<Event> appliedEvents = new ArrayList<>();


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

    public List<Event> getAppliedEvents() {
        return appliedEvents;
    }

    public void setAppliedEvents(List<Event> appliedEvents) {
        this.appliedEvents = appliedEvents;
    }

}
