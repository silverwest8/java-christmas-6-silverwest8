package christmas;

import christmas.Event.Event;
import christmas.Menu.Menu;

import java.util.*;

public class Customer {

    private Calendar visitDate;
    private Map<Menu, Integer> orderedMenu = new HashMap<>();
    private Integer totalPrice = 0;
    private List<Event> appliedEvents = new ArrayList<>();
    private Integer benefitPrice = 0;


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

    public Integer getBenefitPrice() {
        return benefitPrice;
    }

    public void setBenefitPrice(Integer benefitPrice) {
        this.benefitPrice = benefitPrice;
    }

}
