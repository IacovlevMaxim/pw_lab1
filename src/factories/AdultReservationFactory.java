package factories;

import classes.AdultReservation;

import java.time.LocalDate;

public class AdultReservationFactory implements IReservationFactory {
    @Override
    public Reservation createIndividualReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount) {
        return new AdultReservation(userId, date, duration, courtId, price, discount, 1); // assuming 1 adult by default
    }

    @Override
    public Reservation createPackageReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, String packageId, int sessionNumber) {
        AdultReservation reservation = new AdultReservation(userId, date, duration, courtId, price, discount, 1);
        reservation.setPackageId(packageId);
        reservation.setSessionNumber(sessionNumber);
        return reservation;
    }
}

