package business;

import enums.CourtSize;
import factories.Reservation;

import java.time.LocalDate;

public class AdultReservationDTO extends Reservation {
    private int _childrenNumber;
    private CourtSize courtSize;

    private int _adultNumber;

    public AdultReservationDTO() {
        super();
    }

    public AdultReservationDTO(String userId, LocalDate date, int duration, String courtId, float price, int adultNumber) {
        super(userId, date, duration, courtId, price);
        _adultNumber = adultNumber;
    }

    public AdultReservationDTO(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber) {
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
