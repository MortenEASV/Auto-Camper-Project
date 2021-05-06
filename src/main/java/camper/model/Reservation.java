package camper.model;

import java.time.LocalDate;

public class Reservation {
    private final LocalDate from, to;
    private final int customerID;
    private final int reservationID;
    private final int customerName;


    public Reservation(String from, String to, int customerID, int reservationID, int customerName) {
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
        this.customerID = customerID;
        this.reservationID = reservationID;
        this.customerName = customerName;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public int getCustomerName() {
        return customerName;
    }
}