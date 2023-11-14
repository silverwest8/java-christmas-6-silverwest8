package christmas;

import java.util.Calendar;

public class Controller {
    EventPlanner eventPlanner = new EventPlanner();
    Customer customer = new Customer();
    View view = new View();

    public void run() {

        sayHello();

        String visitDate = this.view.inputVisitDate(this.eventPlanner.VISIT_DATE_QUESTION);
        this.eventPlanner.manageDate(customer, visitDate);

        String order = this.view.inputOrder(this.eventPlanner.ORDER_QUESTION);
        this.eventPlanner.manageOrder(customer, order);

        sayPreview(this.customer.getVisitDate());

        showOrderedMenu();

        showTotalPrice(this.eventPlanner.calculateTotalPrice(customer));

        this.eventPlanner.judgementBonusMenu(customer);
        showBonusMenu();

        this.eventPlanner.judgementChristmasDdayEvent(customer);
        this.eventPlanner.judgementWeekdayEvent(customer);
        this.eventPlanner.judgementWeekendEvent(customer);
        this.eventPlanner.judgementSpecialEvent(customer);

        showBenefitDetails();

//        this.eventPlanner.planEvent(customer);


    }

    private void sayHello() {
        this.view.displayEventPlannerGreeting(this.eventPlanner.GREETING);
    }

    private void sayPreview(Calendar date) {
        this.view.displayEventBenefitsPreview(date);
    }

    private void showOrderedMenu() {
        this.view.displayOrderedMenu(customer);
    }

    private void showTotalPrice(Integer totalPrice) {
        this.view.displayTotalPrice(totalPrice);
    }

    private void showBonusMenu() {
        this.view.displayBonusMenu(eventPlanner);
    }

    private void showBenefitDetails() {
        this.view.displayBenefitDetails(eventPlanner, customer);
    }
}
