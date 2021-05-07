package camper.model;

import java.time.LocalDate;

public class Reservation {
    private LocalDate from, to;
    private int customerID;
    private int reservationID;
    private String customerName;


    public Reservation(LocalDate from, LocalDate to, int customerID, int reservationID, String customerName) {
        this.from = from;
        this.to = to;
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

    public String getCustomerName() {
        return customerName;
    }
}