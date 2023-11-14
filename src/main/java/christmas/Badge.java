package christmas;

public enum Badge {
    별(5_000),
    트리(10_000),
    산타(20_000);

    private final Integer price;

    Badge(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
