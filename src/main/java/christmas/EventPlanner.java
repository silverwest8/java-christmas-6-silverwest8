package christmas;

import java.util.*;

public class EventPlanner {
    public final String SAY_HELLO = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public final String VISIT_DATE_QUESTION = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public final String MENU_QUESTION = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final Event event;

    public Event getEvent() {
        return event;
    }

    public EventPlanner() {
        this.event = new Event();
    }

    public void manageDate(String visitDate) {
        Calendar calendar = Calendar.getInstance();
        Integer date = Integer.parseInt(visitDate);
        calendar.set(2023, Calendar.DECEMBER, date);
        event.setVisitDate(calendar);
    }

    public void manageMenu(String menu) {
        HashMap<String, Integer> menus = new HashMap<>();
        ArrayList<String> menuAndQuantities = new ArrayList<>(Arrays.asList(menu.trim().split(",")));
        for (String menuAndQuantity : menuAndQuantities) {
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(menuAndQuantity.trim().split("-")));
            menus.put(temp.get(0), Integer.parseInt(temp.get(1)));
        }
        event.setMenu(menus);
    }

    public void planEvent(Customer customer) {
//        sayHello();
//
//        promptVisitDate();
//        Calendar visitDate = customer.requestVisitDate();
//
//        promptMenu();
//        Map<String, Integer> menu = customer.requestMenu();

        // 이벤트 생성
//        Event event = new Event();
//        event.setVisitDate(visitDate);
//        event.setMenu(menu);
    }

}