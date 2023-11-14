package christmas;

public class Controller {
    EventPlanner eventPlanner = new EventPlanner();
    Customer customer = new Customer();
    View view = new View();

    public void run() {
        showEventGreeting();
        processVisitDate();
        processOrderMenu();
        showPreview(customer);
        showOrderedMenu();
        processTotalPrice();
        checkEvents();
        showBonusMenu();
        showBenefitDetails();
        processTotalBenefit();
        processDiscountedPaymentAmount();
        processGrantBadge();
    }

    private void processGrantBadge() {
        this.eventPlanner.grantBadge(customer);
        showGrantedBadge();
    }

    private void processDiscountedPaymentAmount() {
        this.eventPlanner.calculateDiscountedPaymentAmount(customer);
        showDiscountedPaymentAmount();
    }

    private void processTotalBenefit() {
        this.eventPlanner.calculateTotalBenefit(customer);
        showTotalBenefit();
    }

    private void processTotalPrice() {
        this.eventPlanner.calculateTotalPrice(customer);
        showTotalPrice(customer);
    }

    private void processOrderMenu() {
        String order = this.view.inputOrder(this.eventPlanner.ORDER_QUESTION);
        this.eventPlanner.manageOrder(customer, order);
    }

    private void processVisitDate() {
        String visitDate = this.view.inputVisitDate(this.eventPlanner.VISIT_DATE_QUESTION);
        this.eventPlanner.manageDate(customer, visitDate);
    }

    private void checkEvents() {
        if (this.eventPlanner.isOverMinTotalPrice(customer.getTotalPrice())) {
            this.eventPlanner.judgementBonusMenu(customer);
            this.eventPlanner.judgementChristmasDdayEvent(customer);
            this.eventPlanner.judgementWeekdayEvent(customer);
            this.eventPlanner.judgementWeekendEvent(customer);
            this.eventPlanner.judgementSpecialEvent(customer);
        }
    }

    private void showEventGreeting() {
        this.view.displayEventPlannerGreeting(this.eventPlanner.GREETING);
    }

    private void showPreview(Customer customer) {
        this.view.displayEventBenefitsPreview(customer.getVisitDate());
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
        this.view.displayTotalBenefit(customer);
    }

    private void showDiscountedPaymentAmount() {
        this.view.displayDiscountedPaymentAmount(customer);
    }

    private void showGrantedBadge() {
        this.view.displayGrantedBadge(customer);
    }
}
