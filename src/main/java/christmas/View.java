package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.Menu.Menu;
import christmas.Menu.MenuCategory;

import java.text.DecimalFormat;
import java.util.*;

public class View {
    static final String ERROR_MESSAGE = "[ERROR] ";
    static final String regExp = "^[0-9]+$";
    static final Integer MIN_DATE = 1;
    static final Integer MAX_DATE = 31;
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
//        displayEntireMenu();
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
                        + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
        );
        System.out.println();
    }

    private void validateVisitDate(String input) {
        final String DATE_RANGE_ERROR = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
        if (input.length() > 9) {
            throw new IllegalArgumentException(ERROR_MESSAGE + DATE_RANGE_ERROR);
        }
        if (!input.matches(regExp)) {
            throw new IllegalArgumentException(ERROR_MESSAGE + DATE_RANGE_ERROR);
        }
        int number = Integer.parseInt(input);
        if ((number < MIN_DATE || number > MAX_DATE)) {
            throw new IllegalArgumentException(ERROR_MESSAGE + DATE_RANGE_ERROR);
        }
    }

    private void validateOrder(String input) {
        final String WRONG_MENU_ERROR = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

        ArrayList<String> menuAndQuantityList = new ArrayList<>(Arrays.asList(input.trim().split(",")));
        ArrayList<String> orderedMenu = new ArrayList<>();
        for (String menuAndQuantity : menuAndQuantityList) {
            if (!menuAndQuantity.contains("-")) {
                throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
            }
            ArrayList<String> menuAndQuantityPair = new ArrayList<>(Arrays.asList(menuAndQuantity.trim().split("-")));
            List<String> entireMenu = new ArrayList<>();
            for (Menu value : EnumSet.allOf(Menu.class)) {
                entireMenu.add(value.name());
            }

            String menu = menuAndQuantityPair.get(0);
            String quantity = menuAndQuantityPair.get(1);

            orderedMenu.add(menu);
            if (!entireMenu.contains(menu)) {
                throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
            }
            if (quantity.length() > 9) {
                throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
            }
            if (!quantity.matches(regExp)) {
                throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
            }
            if (Integer.parseInt(quantity) < 1) {
                throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
            }

        }

        HashSet<String> uniqueMenuAndQuantities = new HashSet<>(orderedMenu);
        if (uniqueMenuAndQuantities.size() < orderedMenu.size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
        }
    }

    public void displayEntireMenu() {
        for (MenuCategory value : EnumSet.allOf(MenuCategory.class)) {
            System.out.println("<" + value.name() + ">");
            EnumSet.allOf(Menu.class).forEach(menu -> {
                if (menu.getMenuCategory() == value) {
                    System.out.print(menu.name() + "(" + decFormat.format(menu.getMoney()) + ") ");
                }
            });
            System.out.println("\n");
        }
    }

    public void displayOrderedMenu(Customer customer) {
        System.out.println("<주문 메뉴>");
        Map<Menu, Integer> orderedMenu = customer.getOrderedMenu();
        for (Menu menu : orderedMenu.keySet()) {
            System.out.println(menu.name() + " " + orderedMenu.get(menu) + "개");
        }
        System.out.println();
    }

    public void displayTotalPrice(Customer customer) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(decFormat.format(customer.getTotalPrice()) + "원");
        System.out.println();
    }

    public void displayBonusMenu(EventPlanner eventPlanner) {
        System.out.println("<증정 메뉴>");
        Map<Menu, Integer> bonusMenu = eventPlanner.getBonusEvent().getBonusMenu();
        if (bonusMenu.isEmpty()) {
            System.out.println("없음");
            System.out.println();
            return;
        }
        for (Menu menu : bonusMenu.keySet()) {
            System.out.println(menu.name() + " " + bonusMenu.get(menu) + "개");
        }
        System.out.println();
    }

    public void displayBenefitDetails(EventPlanner eventPlanner, Customer customer) {
//        System.out.println(customer.getAppliedEvents());
//        System.out.println(customer.getAppliedEvents().size());
        int christmasDiscount = eventPlanner.getChristmasDdayEvent().getDiscountAmount();
        int weekdayDiscount = eventPlanner.getWeekdayEvent().getDiscountAmount();
        int weekendDiscount = eventPlanner.getWeekendEvent().getDiscountAmount();
        int specialDiscount = eventPlanner.getSpecialEvent().getDiscountAmount();
        int bonusEventDiscount = eventPlanner.getBonusEvent().getDiscountAmount();

        System.out.println("<혜택 내역>");
        if (customer.getAppliedEvents().isEmpty()) {
            System.out.println("없음");
            System.out.println();
            return;
        }
        printBenefit("크리스마스 디데이 할인", christmasDiscount);
        printBenefit("평일 할인", weekdayDiscount);
        printBenefit("주말 할인", weekendDiscount);
        printBenefit("특별 할인", specialDiscount);
        printBenefit("증정 이벤트", bonusEventDiscount);
        System.out.println();
    }

    private static void printBenefit(String benefitName, int amount) {
        if (amount > 0) {
            System.out.printf("%s: -%,d원%n", benefitName, amount);
        }
    }

    public void displayBenefitDetails(Customer customer) {
        System.out.println("<총혜택 금액>");
        String sign = "";
        if (customer.getBenefitPrice() > 0) {
            sign = "-";
        }
        System.out.printf(sign+decFormat.format(customer.getBenefitPrice())+"원");
        System.out.println();
    }

}
