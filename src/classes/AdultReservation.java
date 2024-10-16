package classes;

import factories.Reservation;

import java.util.Date;
import enums.*;

public class AdultReservation extends Reservation {
    private int _childrenNumber;
    private CourtSize courtSize;

    private int _adultNumber;

    public AdultReservation() {
        super();
    }

    public AdultReservation(String userId, Date date, int duration, String courtId, float price, int adultNumber) {
        super(userId, date, duration, courtId, price);
        _adultNumber = adultNumber;
    }

    public AdultReservation(String userId, Date date, int duration, String courtId, float price, float discount, int adultNumber) {
        super(userId, date, duration, courtId, price, discount);
        _adultNumber = adultNumber;
    }

    /**
     * Sets the number of adults.
     * @param adultNumber Number of children as int.
     */
    public void setAdultNumber(int adultNumber) {
        _adultNumber = adultNumber;
    }

    /**
     * gets the number of adults.
     * @return Number of adults as int.
     */
    public int getAdultNumber() {
        return _adultNumber;
    }
}
