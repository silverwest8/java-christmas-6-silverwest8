package christmas;

import java.util.Calendar;
import java.util.Map;

public class Controller {
    EventPlanner eventPlanner = new EventPlanner();
//    Customer customer = new Customer();
    View view = new View();

    public void run() {

        sayHello();

        String visitDate = this.view.inputVisitDate(this.eventPlanner.VISIT_DATE_QUESTION);
        this.eventPlanner.manageDate(visitDate);

        String order = this.view.inputOrder(this.eventPlanner.ORDER_QUESTION);
        this.eventPlanner.manageOrder(order);

        sayPreview(this.eventPlanner.getEvent().getVisitDate());

        showOrderedMenu();

        showTotalPrice(this.eventPlanner.calculateTotalPrice());

//        this.eventPlanner.planEvent(customer);


    }

    private void sayHello() {
        this.view.displayEventPlannerGreeting(this.eventPlanner.GREETING);
    }

    private void sayPreview(Calendar date) {
        this.view.displayEventBenefitsPreview(date);
    }

    private void showOrderedMenu() {
        this.view.displayOrderedMenu(this.eventPlanner.getEvent());
    }

    private void showTotalPrice(Integer totalPrice) {
        this.view.displayTotalPrice(totalPrice);
    }
}
