package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.Menu.Menu;

import java.text.DecimalFormat;
import java.util.*;

public class View {
    static final String ERROR_MESSAGE = "[ERROR] ";
    static final String PREVIEW__MESSAGE = "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    static final String DATE_RANGE_ERROR = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    static final String WRONG_MENU_ERROR = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    static final String REG_EXP = "^[0-9]+$";
    static final Integer MIN_DATE = 1;
    static final Integer MAX_DATE = 31;
    static final String ORDERED_MENU = "<주문 메뉴>";
    static final String TOTAL_PRICE = "<할인 전 총주문 금액>";
    static final String BONUS_MENU = "<증정 메뉴>";
    static final String BENEFIT_DETAILS = "<혜택 내역>";
    static final String TOTAL_BENEFIT = "<총혜택 금액>";
    static final String DISCOUNTED_PAYMENT_AMOUNT = "<할인 후 예상 결제 금액>";
    static final String GRANTED_BADGE = "<12월 이벤트 배지>";
    static final String NOTHING = "없음";
    static final String WON = "원";
    static final String UNIT = "개";
    static final String DASH = "-";
    static final String MINUS = "-";
    static final String COMMA = ",";
    DecimalFormat decFormat = new DecimalFormat("###,###");

    public void displayEventPlannerGreeting(String message) {
        System.out.println(message);
    }

    public String inputVisitDate(String message) {
        System.out.println(message);
        String input = Console.readLine();
        try {
            validateVisitDate(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            input = inputVisitDate(message);
        }
        return input;
    }

    public String inputOrder(String message) {
        System.out.println(message);
        String order = Console.readLine();
        try {
            validateOrder(order);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            order = inputOrder(message);
        }
        return order;
    }

    public void displayEventBenefitsPreview(Calendar date) {
        System.out.println(
                date.get(Calendar.MONTH) + 1 + "월 "
                        + date.get(Calendar.DATE) + "일"
                        + PREVIEW__MESSAGE
        );
        System.out.println();
    }

    private static void validateVisitDate(String input) {
        if (input.length() > 9) {
            throw new IllegalArgumentException(ERROR_MESSAGE + DATE_RANGE_ERROR);
        }
        if (!input.matches(REG_EXP)) {
            throw new IllegalArgumentException(ERROR_MESSAGE + DATE_RANGE_ERROR);
        }
        int number = Integer.parseInt(input);
        if ((number < MIN_DATE || number > MAX_DATE)) {
            throw new IllegalArgumentException(ERROR_MESSAGE + DATE_RANGE_ERROR);
        }
    }

    private static void validateOrder(String input) {
        ArrayList<String> menuAndQuantityList = new ArrayList<>(Arrays.asList(input.trim().split(COMMA)));
        ArrayList<String> orderedMenu = new ArrayList<>();
        for (String menuAndQuantity : menuAndQuantityList) {
            if (!menuAndQuantity.contains(DASH)) {
                throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
            }
            validateMenuAndQuantity(menuAndQuantity, orderedMenu);
        }
        validateDuplicateMenu(orderedMenu);
    }

    private static void validateDuplicateMenu(ArrayList<String> orderedMenu) {
        HashSet<String> uniqueMenuAndQuantities = new HashSet<>(orderedMenu);
        if (uniqueMenuAndQuantities.size() < orderedMenu.size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
        }
    }

    private static void validateMenuAndQuantity(String menuAndQuantity, ArrayList<String> orderedMenu) {
        ArrayList<String> menuAndQuantityPair = new ArrayList<>(Arrays.asList(menuAndQuantity.trim().split("-")));
        String menu = menuAndQuantityPair.get(0);
        String quantity = menuAndQuantityPair.get(1);

        orderedMenu.add(menu);
        if (!Menu.getAllMenuNames().contains(menu)) {
            throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
        }
        if (!quantity.matches(REG_EXP) || quantity.length() > 9) {
            throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
        }
        if (Integer.parseInt(quantity) < 1) {
            throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
        }
    }

    public void displayOrderedMenu(Customer customer) {
        System.out.println(ORDERED_MENU);
        Map<Menu, Integer> orderedMenu = customer.getOrderedMenu();
        for (Menu menu : orderedMenu.keySet()) {
            System.out.println(menu.name() + " " + orderedMenu.get(menu) + UNIT);
        }
        System.out.println();
    }

    public void displayTotalPrice(Customer customer) {
        System.out.println(TOTAL_PRICE);
        System.out.println(decFormat.format(customer.getTotalPrice()) + WON);
        System.out.println();
    }

    public void displayBonusMenu(EventPlanner eventPlanner) {
        System.out.println(BONUS_MENU);
        Map<Menu, Integer> bonusMenu = eventPlanner.getBonusEvent().getBonusMenu();
        if (bonusMenu.isEmpty()) {
            System.out.println(NOTHING);
            System.out.println();
            return;
        }
        for (Menu menu : bonusMenu.keySet()) {
            System.out.println(menu.name() + " " + bonusMenu.get(menu) + UNIT);
        }
        System.out.println();
    }

    public void displayBenefitDetails(EventPlanner eventPlanner, Customer customer) {
        System.out.println(BENEFIT_DETAILS);
        if (customer.getAppliedEvents().isEmpty()) {
            System.out.println(NOTHING);
            System.out.println();
            return;
        }
        printBenefit(eventPlanner.getChristmasDdayEvent().name, eventPlanner.getChristmasDdayEvent().getDiscountAmount());
        printBenefit(eventPlanner.getWeekdayEvent().name, eventPlanner.getWeekdayEvent().getDiscountAmount());
        printBenefit(eventPlanner.getWeekendEvent().name, eventPlanner.getWeekendEvent().getDiscountAmount());
        printBenefit(eventPlanner.getSpecialEvent().name, eventPlanner.getSpecialEvent().getDiscountAmount());
        printBenefit(eventPlanner.getBonusEvent().name, eventPlanner.getBonusEvent().getDiscountAmount());
        System.out.println();
    }

    private static void printBenefit(String benefitName, int amount) {
        if (amount > 0) {
            System.out.printf("%s: -%,d원%n", benefitName, amount);
        }
    }

    public void displayTotalBenefit(Customer customer) {
        System.out.println(TOTAL_BENEFIT);
        String sign = "";
        if (customer.getBenefitAmount() > 0) {
            sign = MINUS;
        }
        System.out.println(sign + decFormat.format(customer.getBenefitAmount()) + WON);
        System.out.println();
    }

    public void displayDiscountedPaymentAmount(Customer customer) {
        System.out.println(DISCOUNTED_PAYMENT_AMOUNT);
        Integer discountedPaymentAmount = customer.getDiscountedPaymentAmount();
        String sign = "";
        if (discountedPaymentAmount < 0) {
            sign = MINUS;
        }
        System.out.println(sign + decFormat.format(discountedPaymentAmount) + WON);
        System.out.println();
    }

    public void displayGrantedBadge(Customer customer) {
        System.out.println(GRANTED_BADGE);
        if (customer.getBadge() == null) {
            System.out.println(NOTHING);
            System.out.println();
            return;
        }
        System.out.println(customer.getBadge().name());
        System.out.println();
    }
}
