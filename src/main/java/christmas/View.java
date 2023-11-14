package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.text.DecimalFormat;
import java.util.*;

public class View {
    static final String ERROR_MESSAGE = "[ERROR] ";
    static final String regExp = "^[0-9]+$";
    static final Integer MIN_DATE = 1;
    static final Integer MAX_DATE = 31;
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
            inputVisitDate(message);
        }
        return input;
    }

    public String inputMenu(String message) {
        System.out.println(message);
//        displayEntireMenu();
        String input = Console.readLine();
        try {
            validateMenu(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputMenu(message);
        }
        return input;
    }

    public void displayEventBenefitsPreview(Calendar date) {
        System.out.println(
                date.get(Calendar.MONTH) + 1 + "월 "
                        + date.get(Calendar.DATE) + "일"
                        + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
        );
    }

    private void validateVisitDate(String input) {
        String DATE_RANGE_ERROR = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
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

    private void validateMenu(String input) {
        String WRONG_MENU_ERROR = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
//        String WRONG_QUANTITY_WARNING = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.";
        //고객이 메뉴판에 없는 메뉴를 입력하는 경우: 에러 메시지 출력, 다시 입력받기
        //메뉴의 개수가 1 이상의 숫자가 아닌 경우: 에러 메시지 출력, 다시 입력받기
        //메뉴 형식이 예시와 다른 경우: 에러 메시지 출력, 다시 입력받기
        //중복 메뉴를 입력한 경우: 에러 메시지 출력, 다시 입력받기
//        Integer count = 0;
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
//            System.out.println(name);
//            System.out.println(quantity);
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
//            count+=Integer.parseInt(quantity);
//            System.out.println("count : " + count);
//            if (count > 20) {
//                throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_QUANTITY_WARNING);
//            }
        }

        HashSet<String> uniqueMenuAndQuantities = new HashSet<>(orderedMenu);
        if (uniqueMenuAndQuantities.size() < orderedMenu.size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE + WRONG_MENU_ERROR);
        }
    }

    public void displayEntireMenu() {
        DecimalFormat decFormat = new DecimalFormat("###,###");
        for (MenuCategory value : EnumSet.allOf(MenuCategory.class)) {
            System.out.println("<"+value.name()+">");
            EnumSet.allOf(Menu.class).forEach(menu -> {
                if (menu.getMenuKind() == value) {
                    System.out.print(menu.name()+"("+decFormat.format(menu.getMoney())+") ");
                }
            });
            System.out.println("\n");
        }
    }


}
