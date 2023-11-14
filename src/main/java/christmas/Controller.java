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

        this.eventPlanner.calculateTotalPrice(customer);
        showTotalPrice(customer);

        if (this.eventPlanner.isOverMinTotalPrice(customer.getTotalPrice())) {
            this.eventPlanner.judgementBonusMenu(customer);
            this.eventPlanner.judgementChristmasDdayEvent(customer);
            this.eventPlanner.judgementWeekdayEvent(customer);
            this.eventPlanner.judgementWeekendEvent(customer);
            this.eventPlanner.judgementSpecialEvent(customer);
        }

        showBonusMenu();

        showBenefitDetails();

        this.eventPlanner.calculateTotalBenefit(customer);
        showTotalBenefit();

        this.eventPlanner.calculateDiscountedPaymentAmount(customer);
        showDiscountedPaymentAmount();

        this.eventPlanner.grantBadge(customer);
        showGrantedBadge();
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

    private void showTotalPrice(Customer customer) {
        this.view.displayTotalPrice(customer);
    }

    private void showBonusMenu() {
        this.view.displayBonusMenu(eventPlanner);
    }

    private void showBenefitDetails() {
        this.view.displayBenefitDetails(eventPlanner, customer);
    }

    private void showTotalBenefit() {
        this.view.displayBenefitDetails(customer);
    }

    private void showDiscountedPaymentAmount() {
        this.view.displayDiscountedPaymentAmount(customer);
    }

    private void showGrantedBadge() {
        this.view.displayGrantedBadge(customer);
    }
}
