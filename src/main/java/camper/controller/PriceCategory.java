package camper.controller;

public enum PriceCategory {
    BASIC("Basic"), STANDARD("Standard"), PREMIUM("Luxury");

    public String name;

    PriceCategory(String name) {
        this.name = name;
    }

    public PriceCategory getEnum(String name) {
        for (PriceCategory priceCategory : PriceCategory.values()) {
            if (priceCategory.name.equals(name)) {
                return priceCategory;
            }
        }

        return null;
    }
}
