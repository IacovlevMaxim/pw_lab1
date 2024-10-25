package factories;

import classes.FamilyReservation;
import exceptions.NoAdultException;

import java.time.LocalDate;

public class FamilyReservationFactory implements IReservationFactory {
    @Override
    public Reservation createIndividualReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount) {
        try {
            return new FamilyReservation(userId, date, duration, courtId, price, discount, 0, 1); // assuming 1 adult and 0 children by default
        } catch(NoAdultException ex) {
            System.out.println("Unable to create FamilyReservation without adults");
        }

        return null;
    }

    @Override
    public Reservation createPackageReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, String packageId, int sessionNumber) {
        FamilyReservation reservation = null;
        try {
            reservation = new FamilyReservation(userId, date, duration, courtId, price, discount, 1, 0);
            reservation.setPackageId(packageId);
            reservation.setSessionNumber(sessionNumber);
        } catch(NoAdultException ex) {
            System.out.println("Unable to create FamilyReservation without adults");
        }

        return reservation;
    }
}

