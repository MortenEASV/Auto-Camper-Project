package camper.controller;

public enum PriceCategory {
    BASIC("Basic", 1), STANDARD("Standard", 2), PREMIUM("Luxury", 3);

    public String name;
    public int id;

    PriceCategory(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static PriceCategory getEnum(String name) {
        for (PriceCategory priceCategory : PriceCategory.values()) {
            if (priceCategory.name.equals(name)) {
                return priceCategory;
            }
        }

        return null;
    }
}
