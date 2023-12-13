package christmas.View;

import camp.nextstep.edu.missionutils.Console;
import christmas.Menu.Menu;

import java.util.*;

public class InputView {
    static final String ERROR_MESSAGE = "[ERROR] ";
    static final String DATE_RANGE_ERROR = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    static final String WRONG_MENU_ERROR = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    static final String REG_EXP = "^[0-9]+$";
    static final Integer MIN_DATE = 1;
    static final Integer MAX_DATE = 31;
    static final String DASH = "-";
    static final String COMMA = ",";

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
}
