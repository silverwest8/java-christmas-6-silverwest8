package christmas;

import java.util.*;

public class EventPlanner {
    public final String GREETING = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public final String VISIT_DATE_QUESTION = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public final String ORDER_QUESTION = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    static final String WARNING_MESSAGE = "[WARNING] ";
    private final ChristmasDdayEvent christmasDdayEvent;
    private final BonusEvent bonusEvent;

    public EventPlanner() {
        this.christmasDdayEvent = new ChristmasDdayEvent();
        this.bonusEvent = new BonusEvent();
    }

    public void manageDate(Customer customer, String visitDate) {
        Calendar calendar = Calendar.getInstance();
        Integer date = Integer.parseInt(visitDate);
        calendar.set(2023, Calendar.DECEMBER, date);
        customer.setVisitDate(calendar);
    }

    public void manageOrder(Customer customer, String input) {
        HashMap<Menu, Integer> order = new HashMap<>();
        ArrayList<String> menuAndQuantityList = new ArrayList<>(Arrays.asList(input.trim().split(",")));
        for (String menuAndQuantity : menuAndQuantityList) {
            ArrayList<String> menuAndQuantityPair
                    = new ArrayList<>(Arrays.asList(menuAndQuantity.trim().split("-")));
            Menu menu = Menu.valueOf(menuAndQuantityPair.get(0));
            Integer quantity = Integer.parseInt(menuAndQuantityPair.get(1));
            order.put(menu, quantity);
        }
        validationMenuCount(order);
        validationOnlyDrink(order);
        customer.setOrderedMenu(order);
    }

    private void validationMenuCount(HashMap<Menu, Integer> order) {
        String TOTAL_QUANTITY_WARNING = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.";
        Integer count = 0;
        for (Menu key : order.keySet()) {
            count += order.get(key);
        }
//        System.out.println("count : " + count);
        if (count > 20) {
            System.out.println(WARNING_MESSAGE + TOTAL_QUANTITY_WARNING);
        }
    }

    private void validationOnlyDrink(HashMap<Menu, Integer> order) {
        String ORDER_DRINK_ONLY_WARNING = "음료만 주문 시, 주문할 수 없습니다.";
//        System.out.println(Menu.getMenuOf(MenuCategory.음료));
        for (Menu key : order.keySet()) {
//            System.out.println(Menu.valueOf(key).getMenuKind());
            if (!Menu.getMenuOf(MenuCategory.음료).contains(key)) {
                return;
            }
        }
        System.out.println(WARNING_MESSAGE + ORDER_DRINK_ONLY_WARNING);
    }

    public Integer calculateTotalPrice(Customer customer) {
        Map<Menu, Integer> orderedMenu = customer.getOrderedMenu();
        Integer totalPrice = 0;
        for (Menu menu : orderedMenu.keySet()) {
            totalPrice += menu.getMoney() * orderedMenu.get(menu);
        }
        customer.setTotalPrice(totalPrice);
        return totalPrice;
    }

    public void judgementBonusMenu(Customer customer) {
        if (isDateInRange(customer.getVisitDate(), bonusEvent) && customer.getTotalPrice() >= 120_000) {
            HashMap<Menu, Integer> bonusMenu = new HashMap<>();
            bonusMenu.put(Menu.샴페인, 1);
            customer.setBonusMenu(bonusMenu);
        }
    }

    private Boolean isDateInRange(Calendar targetDate, Event event) {
        return !targetDate.before(event.getEventStartDate()) && !targetDate.after(event.getEventEndDate());
    }

    public void judgementChristmasDdayEvent(Customer customer) {
        if (isDateInRange(customer.getVisitDate(), christmasDdayEvent)) {
            int appliedDay = customer.getVisitDate().get(Calendar.DATE) - 1;
            int discountAmount = ChristmasDdayEvent.minDiscountAmount + appliedDay * ChristmasDdayEvent.discountUnit;
            christmasDdayEvent.setDiscountAmount(discountAmount);
        }
    }

//    public void planEvent(Customer customer) {
//        sayHello();
//
//        promptVisitDate();
//        Calendar visitDate = customer.requestVisitDate();
//
//        promptMenu();
//        Map<Menu, Integer> menu = customer.requestMenu();

    // 이벤트 생성
//        Event event = new Event();
//        event.setVisitDate(visitDate);
//        event.setMenu(menu);
//    }

}