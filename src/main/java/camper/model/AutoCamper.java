package camper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoCamper {
    private final int id;
    private final String price;
    private final int seats;
    private final int sleeps;
    private final boolean wc;
    private final boolean kitchen;
    private final double width;
    private final double height;
    private final double length;
    private final String transmission;
    private final String fuelType;
    private final ArrayList<Reservation> reservations = new ArrayList<>();

    public AutoCamper(int id, String price, int seats, int sleeps, boolean wc, boolean kitchen, double width, double height, double length, String transmission, String fuelType, Reservation... reservation) {
        this.id = id;
        this.price = price;
        this.seats = seats;
        this.sleeps = sleeps;
        this.wc = wc;
        this.kitchen = kitchen;
        this.width = width;
        this.height = height;
        this.length = length;
        this.transmission = transmission;
        this.fuelType = fuelType;

        reservations.addAll(Arrays.asList(reservation));
    }

    public int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public int getSeats() {
        return seats;
    }

    public int getSleeps() {
        return sleeps;
    }

    public boolean isWc() {
        return wc;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getFuelType() {
        return fuelType;
    }

    public ArrayList<DateInterval> getReservedDates() {
        ArrayList<DateInterval> intervals = new ArrayList<>();

        for (Reservation reservation : reservations) {
            intervals.add(new DateInterval(reservation.getFrom(), reservation.getTo()));
        }

        return intervals;
    }
}