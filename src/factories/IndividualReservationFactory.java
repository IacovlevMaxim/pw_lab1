package factories;

import classes.AdultReservation;
import classes.ChildrenReservation;
import classes.FamilyReservation;
import exceptions.NoAdultException;

import java.time.LocalDate;

public class IndividualReservationFactory implements IReservationFactory {

    @Override
    public Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber) {
        return new AdultReservation(userId, date, duration, courtId, price, discount, adultNumber);
    }

    @Override
    public Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, String packageId, int sessionNumber) {
        return null;
    }

    @Override
    public Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber) {
        return new ChildrenReservation(userId, date, duration, courtId, price, discount, childrenNumber);
    }

    @Override
    public Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber, String packageId, int sessionNumber) {
        return null;
    }

    @Override
    public Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber) {
        try {
            return new FamilyReservation(userId, date, duration, courtId, price, discount, 0, 1); // assuming 1 adult and 0 children by default
        } catch(NoAdultException ex) {
            System.out.println("Unable to create FamilyReservation without adults");
        }

        return null;
    }

    @Override
    public Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, int childNumber, String packageId, int sessionNumber) {
        return null;
    }
}
