package christmas;

import java.util.Calendar;
import java.util.Map;

public class Event {
    private Calendar visitDate;
    private Map<String, Integer> menu;
//    private Integer totalPrice;


    public Calendar getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Calendar visitDate) {
        this.visitDate = visitDate;
    }

    public Map<String, Integer> getMenu() {
        return menu;
    }

    public void setMenu(Map<String, Integer> menu) {
        this.menu = menu;
    }

//    public Integer getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(Integer totalPrice) {
//        this.totalPrice = totalPrice;
//    }
}
