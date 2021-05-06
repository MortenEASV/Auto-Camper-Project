package camper.model;

public class AutoCamper {
    private int ID, seats, sleeps;
    private double size, price;
    private boolean wc, kitchen;
    private String transmission, fuelType;

    public AutoCamper(int ID, int seats, int sleeps, double size, double price, boolean wc, boolean kitchen, String transmission, String fuelType) {
        this.ID = ID;
        this.seats = seats;
        this.sleeps = sleeps;
        this.size = size;
        this.price = price;
        this.wc = wc;
        this.kitchen = kitchen;
        this.transmission = transmission;
        this.fuelType = fuelType;
    }

    public int getID() {
        return ID;
    }

    public int getSeats() {
        return seats;
    }

    public int getSleeps() {
        return sleeps;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public boolean isWc() {
        return wc;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getFuelType() {
        return fuelType;
    }
}