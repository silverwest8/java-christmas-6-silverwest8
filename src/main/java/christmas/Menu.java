package christmas;

import java.util.EnumSet;

public enum Menu {
    양송이수프(MenuCategory.애피타이저, 6_000),
    타파스(MenuCategory.애피타이저, 5_000),
    시저샐러드(MenuCategory.애피타이저, 8_000),
    티본스테이크(MenuCategory.메인, 55_000),
    바비큐립(MenuCategory.메인, 54_000),
    초코케이크(MenuCategory.디저트, 15_000),
    아이스크림(MenuCategory.디저트, 5_000),
    제로콜라(MenuCategory.음료, 3_000),
    레드와인(MenuCategory.음료, 60_000),
    샴페인(MenuCategory.음료, 25_000);

    private final MenuCategory menuCategory;
    private final Integer money;

    Menu(MenuCategory menuCategory, Integer money) {
        this.menuCategory = menuCategory;
        this.money = money;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public int getMoney() {
        return money;
    }

    static public EnumSet<Menu> getMenuOf(MenuCategory category) {
        EnumSet<Menu> menus = EnumSet.noneOf(Menu.class);
        for (Menu menu: EnumSet.allOf(Menu.class)) {
            if (menu.getMenuCategory() == category) {
                menus.add(menu);
            }
        }
        return menus;
    }
}
