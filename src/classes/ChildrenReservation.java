package classes;

import factories.Reservation;

import java.time.LocalDate;
import enums.*;

public class ChildrenReservation extends Reservation {
    private int _childrenNumber;
    private CourtSize courtSize;

    public ChildrenReservation() {
        super();
    }

    public ChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, int childrenNumber) {
        super(userId, date, duration, courtId, price);
        _childrenNumber = childrenNumber;
    }

    public ChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber) {
        super(userId, date, duration, courtId, price, discount);
        _childrenNumber = childrenNumber;
    }

    /**
     * Sets the number of children.
     * @param childrenNumber Number of children as int.
     */
    public void setChildrenNumber(int childrenNumber) {
        _childrenNumber = childrenNumber;
    }

    /**
     * gets the number of children.
     * @return Number of children as int.
     */
    public int getChildrenNumber() {
        return _childrenNumber;
    }
}
