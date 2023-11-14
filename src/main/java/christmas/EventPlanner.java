package christmas;

import christmas.Event.*;
import christmas.Menu.Menu;
import christmas.Menu.MenuCategory;

import java.util.*;

public class EventPlanner {
    public final String GREETING = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public final String VISIT_DATE_QUESTION = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public final String ORDER_QUESTION = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    static final String WARNING_MESSAGE = "[WARNING] ";
    private final ChristmasDdayEvent christmasDdayEvent;
    private final WeekdayEvent weekdayEvent;
    private final WeekendEvent weekendEvent;
    private final SpecialEvent specialEvent;
    private final BonusEvent bonusEvent;

    public EventPlanner() {
        this.christmasDdayEvent = new ChristmasDdayEvent();
        this.weekdayEvent = new WeekdayEvent();
        this.weekendEvent = new WeekendEvent();
        this.specialEvent = new SpecialEvent();
        this.bonusEvent = new BonusEvent();
    }

    public ChristmasDdayEvent getChristmasDdayEvent() {
        return christmasDdayEvent;
    }

    public WeekdayEvent getWeekdayEvent() {
        return weekdayEvent;
    }

    public WeekendEvent getWeekendEvent() {
        return weekendEvent;
    }

    public SpecialEvent getSpecialEvent() {
        return specialEvent;
    }

    public BonusEvent getBonusEvent() {
        return bonusEvent;
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

    public void calculateTotalPrice(Customer customer) {
        Map<Menu, Integer> orderedMenu = customer.getOrderedMenu();
        Integer totalPrice = 0;
        for (Menu menu : orderedMenu.keySet()) {
            totalPrice += menu.getMoney() * orderedMenu.get(menu);
        }
        customer.setTotalPrice(totalPrice);
    }

    public Boolean isOverMinTotalPrice(Integer totalPrice) {
        return totalPrice >= 10_000;
    }

    public void judgementBonusMenu(Customer customer) {
        if (isDateInRange(customer.getVisitDate(), bonusEvent.getStartDate(), bonusEvent.getEndDate())
                && customer.getTotalPrice() >= 120_000
        ) {
            HashMap<Menu, Integer> bonusMenu = new HashMap<>();
            bonusMenu.put(Menu.샴페인, 1);
            bonusEvent.setBonusMenu(bonusMenu);
            bonusEvent.setDiscountAmount(Menu.샴페인.getMoney());
            //
            customer.getAppliedEvents().add(bonusEvent);
        }
    }

    private Boolean isDateInRange(Calendar targetDate, Calendar startDate, Calendar endDate) {
        return !targetDate.before(startDate) && !targetDate.after(endDate);
    }

    private Boolean isDateInList(Calendar targetDate, List<Integer> startDays) {
        return startDays.contains(targetDate.get(Calendar.DATE));
    }

    public void judgementChristmasDdayEvent(Customer customer) {
        if (isDateInRange(
                customer.getVisitDate(),
                christmasDdayEvent.getStartDate(),
                christmasDdayEvent.getEndDate())
        ) {
            int appliedDay = customer.getVisitDate().get(Calendar.DATE) - 1;
            int discountAmount = ChristmasDdayEvent.minDiscountAmount + appliedDay * ChristmasDdayEvent.discountUnit;
            christmasDdayEvent.setDiscountAmount(discountAmount);
            customer.getAppliedEvents().add(christmasDdayEvent);
        }
    }

    public void judgementWeekdayEvent(Customer customer) {
        Calendar visitDate = customer.getVisitDate();
        Map<Menu, Integer> menu = customer.getOrderedMenu();
        Integer visitDateDayNum = visitDate.get(Calendar.DAY_OF_WEEK);

        if (isDateInRange(visitDate, weekdayEvent.getStartDate(), weekdayEvent.getEndDate())
                && WeekdayEvent.dayNum.contains(visitDateDayNum)
        ) {
            int discountAmount = countMenuOfCategory(menu, WeekdayEvent.appliedCategory) * WeekdayEvent.discountUnit;
            weekdayEvent.setDiscountAmount(discountAmount);
            customer.getAppliedEvents().add(weekdayEvent);
        }
    }

    public void judgementWeekendEvent(Customer customer) {
        Calendar visitDate = customer.getVisitDate();
        Map<Menu, Integer> menu = customer.getOrderedMenu();
        Integer visitDateDayNum = visitDate.get(Calendar.DAY_OF_WEEK);
        if (isDateInRange(visitDate, weekendEvent.getStartDate(), weekendEvent.getEndDate())
                && WeekendEvent.dayNum.contains(visitDateDayNum)
        ) {
            int discountAmount = countMenuOfCategory(menu, WeekendEvent.appliedCategory) * WeekendEvent.discountUnit;
            weekendEvent.setDiscountAmount(discountAmount);
            customer.getAppliedEvents().add(weekendEvent);
        }
    }

    private Integer countMenuOfCategory(Map<Menu, Integer> orderedMenu, MenuCategory menuCategory) {
        Integer count = 0;
        for (Menu menu : orderedMenu.keySet()) {
            if (menu.getMenuCategory().equals(menuCategory)) {
                count += orderedMenu.get(menu);
            }
        }
        return count;
    }

    public void judgementSpecialEvent(Customer customer) {
        Calendar visitDate = customer.getVisitDate();
        if (isDateInRange(visitDate, weekendEvent.getStartDate(), weekendEvent.getEndDate())
                && isDateInList(customer.getVisitDate(), specialEvent.getStarDays())) {
            int discountAmount = SpecialEvent.discountUnit;
            specialEvent.setDiscountAmount(discountAmount);
            customer.getAppliedEvents().add(specialEvent);
        }
    }

    public void calculateTotalBenefit(Customer customer) {
        int christmasDiscount = christmasDdayEvent.getDiscountAmount();
        int weekdayDiscount = weekdayEvent.getDiscountAmount();
        int weekendDiscount = weekendEvent.getDiscountAmount();
        int specialDiscount = specialEvent.getDiscountAmount();
        int bonusEventDiscount = bonusEvent.getDiscountAmount();
        Integer totalBenefit =
                christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount + bonusEventDiscount;
        customer.setBenefitAmount(totalBenefit);
    }

    public void calculateDiscountedPaymentAmount(Customer customer) {
        int christmasDiscount = christmasDdayEvent.getDiscountAmount();
        int weekdayDiscount = weekdayEvent.getDiscountAmount();
        int weekendDiscount = weekendEvent.getDiscountAmount();
        int specialDiscount = specialEvent.getDiscountAmount();
        Integer totalDiscount = christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
        Integer discountedPaymentAmount = customer.getTotalPrice() - totalDiscount;
        customer.setDiscountedPaymentAmount(discountedPaymentAmount);
    }

    public void grantBadge(Customer customer) {
        if (customer.getBenefitAmount() >= Badge.산타.getPrice()) {
            customer.setBadge(Badge.산타);
            return;
        }
        if (customer.getBenefitAmount() >= Badge.트리.getPrice()) {
            customer.setBadge(Badge.트리);
            return;
        }
        if (customer.getBenefitAmount() >= Badge.별.getPrice()) {
            customer.setBadge(Badge.별);
        }
    }

}