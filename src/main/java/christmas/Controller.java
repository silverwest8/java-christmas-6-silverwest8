package christmas;

import christmas.View.InputView;
import christmas.View.OutputView;

public class Controller {
    EventPlanner eventPlanner = new EventPlanner();
    Customer customer = new Customer();
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();

    static final String WARNING_MESSAGE = "[WARNING] ";

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

    private void showEventGreeting() {
        this.outputView.displayEventPlannerGreeting(this.eventPlanner.GREETING);
    }

    private void processVisitDate() {
        String visitDate = this.inputView.inputVisitDate(this.eventPlanner.VISIT_DATE_QUESTION);
        this.eventPlanner.manageDate(customer, visitDate);
    }

    private void processOrderMenu() {
        String order = this.inputView.inputOrder(this.eventPlanner.ORDER_QUESTION);
        this.eventPlanner.manageOrder(customer, order);
    }

    private void showPreview(Customer customer) {
        this.outputView.displayEventBenefitsPreview(customer.getVisitDate());
    }

    private void showOrderedMenu() {
        this.outputView.displayOrderedMenu(customer);
    }

    private void processTotalPrice() {
        this.eventPlanner.calculateTotalPrice(customer);
        showTotalPrice(customer);
    }

    private void showTotalPrice(Customer customer) {
        this.outputView.displayTotalPrice(customer);
    }

    private void checkEvents() {
        if (this.eventPlanner.isOverMinTotalPrice(customer.getTotalPrice())) {
            this.eventPlanner.judgementBonusMenu(customer);
            this.eventPlanner.judgementChristmasDdayEvent(customer);
            this.eventPlanner.judgementWeekdayEvent(customer);
            this.eventPlanner.judgementWeekendEvent(customer);
            this.eventPlanner.judgementSpecialEvent(customer);
            return;
        }
        String MIN_TOTAL_WARNING = "총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.";
        System.out.println(WARNING_MESSAGE + MIN_TOTAL_WARNING);
    }

    private void showBonusMenu() {
        this.outputView.displayBonusMenu(eventPlanner);
    }

    private void showBenefitDetails() {
        this.outputView.displayBenefitDetails(eventPlanner, customer);
    }

    private void showTotalBenefit() {
        this.outputView.displayTotalBenefit(customer);
    }

    private void processDiscountedPaymentAmount() {
        this.eventPlanner.calculateDiscountedPaymentAmount(customer);
        showDiscountedPaymentAmount();
    }

    private void showDiscountedPaymentAmount() {
        this.outputView.displayDiscountedPaymentAmount(customer);
    }

    private void processGrantBadge() {
        this.eventPlanner.grantBadge(customer);
        showGrantedBadge();
    }

    private void showGrantedBadge() {
        this.outputView.displayGrantedBadge(customer);
    }

    private void processTotalBenefit() {
        this.eventPlanner.calculateTotalBenefit(customer);
        showTotalBenefit();
    }
}
