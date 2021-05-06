package camper.controller;

public enum FuelType {
    DIESEL("Diesel"), GASOLINE("Gasoline");

    public String name;

    FuelType(String name) {
        this.name = name;
    }

    public FuelType getEnum(String name) {
        for (FuelType fuelType : FuelType.values()) {
            if (fuelType.name.equals(name)) {
                return fuelType;
            }
        }

        return null;
    }
}
