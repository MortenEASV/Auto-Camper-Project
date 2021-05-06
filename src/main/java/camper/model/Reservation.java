package camper.model;

import java.time.LocalDate;

public class Reservation {
    private final LocalDate from, to;

    public Reservation(String from, String to) {
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
}