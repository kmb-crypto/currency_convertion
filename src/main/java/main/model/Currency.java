package main.model;

public enum Currency {
    RUB("rub"),
    USD("usd"),
    EUR("eur"),
    GBP("gbp");

    private final String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
