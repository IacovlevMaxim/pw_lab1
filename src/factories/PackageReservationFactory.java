package factories;

import classes.AdultReservation;
import classes.ChildrenReservation;
import classes.FamilyReservation;
import exceptions.NoAdultException;

import java.time.LocalDate;

public class PackageReservationFactory implements IReservationFactory {
    @Override
    public Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber) {
        return null;
    }

    @Override
    public Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, String packageId, int sessionNumber) {
        AdultReservation reservation = new AdultReservation(userId, date, duration, courtId, price, discount, adultNumber);
        reservation.setPackageId(packageId);
        reservation.setSessionNumber(sessionNumber);
        return reservation;
    }

    @Override
    public Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber) {
        return null;
    }

    @Override
    public Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber, String packageId, int sessionNumber) {
        ChildrenReservation reservation = new ChildrenReservation(userId, date, duration, courtId, price, discount, childrenNumber);
        reservation.setPackageId(packageId);
        reservation.setSessionNumber(sessionNumber);
        return reservation;
    }

    @Override
    public Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber) {
        return null;
    }

    public Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, int childNumber, String packageId, int sessionNumber) {
        FamilyReservation reservation = null;
        try {
            reservation = new FamilyReservation(userId, date, duration, courtId, price, discount, childNumber, adultNumber);
            reservation.setPackageId(packageId);
            reservation.setSessionNumber(sessionNumber);
        } catch(NoAdultException ex) {
            System.out.println("Unable to create FamilyReservation without adults");
        }

        return reservation;
    }


}
