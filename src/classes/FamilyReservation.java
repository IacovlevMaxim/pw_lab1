package classes;

import exceptions.NoAdultException;
import factories.Reservation;

import java.util.Date;

public class FamilyReservation extends Reservation {
    private int _childrenNumber;
    private int _adultNumber;

    public FamilyReservation() {
        super();
    }


    public FamilyReservation(String userId, Date date, int duration, String courtId, float price, int childrenNumber, int adultNumber) throws NoAdultException {
        super(userId, date, duration, courtId, price);
        _childrenNumber = childrenNumber;
        if(adultNumber < 1) {
            throw new NoAdultException("FamilyReservation must include at least one adult");
        }

        _adultNumber = adultNumber;
    }

    public FamilyReservation(String userId, Date date, int duration, String courtId, float price, float discount, int childrenNumber, int adultNumber) throws NoAdultException  {
        super(userId, date, duration, courtId, price, discount);
        _childrenNumber = childrenNumber;

        if(adultNumber < 1) {
            throw new NoAdultException("FamilyReservation must include at least one adult");
        }

        _adultNumber = adultNumber;
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

    /**
     * Sets the number of adults.
     * @param adultNumber Number of adults as int.
     */
    public void setAdultNumber(int adultNumber) throws NoAdultException {
        if(adultNumber < 1) {
            throw new NoAdultException("FamilyReservation must include at least one adult");
        }

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
