package camper.controller;

public enum Transmission {
    MANUAL("Manual"), AUTOMATIC("Automatic");

    public String name;

    Transmission(String name) {
        this.name = name;
    }

    public Transmission getEnum(String name) {
        for (Transmission transmission : Transmission.values()) {
            if (transmission.name.equals(name)) {
                return transmission;
            }
        }

        return null;
    }
}
