package christmas;

import java.util.Calendar;

public class Controller {
    EventPlanner eventPlanner = new EventPlanner();
//    Customer customer = new Customer();
    View view = new View();

    public void run() {

        sayHello();

        String visitDate = this.view.inputVisitDate(this.eventPlanner.VISIT_DATE_QUESTION);
        this.eventPlanner.manageDate(visitDate);

        String menu = this.view.inputMenu(this.eventPlanner.MENU_QUESTION);
        this.eventPlanner.manageMenu(menu);
        sayPreview(this.eventPlanner.getEvent().getVisitDate());
//        this.eventPlanner.planEvent(customer);


    }

    private void sayHello() {
        this.view.displayEventPlannerGreeting(this.eventPlanner.SAY_HELLO);
    }

    private void sayPreview(Calendar date) {
        this.view.displayEventBenefitsPreview(date);
    }

}
