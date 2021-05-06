package camper.model;

import java.time.LocalDate;

public class DateInterval {
    private final LocalDate from, to;

    public DateInterval(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "DateInterval{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
