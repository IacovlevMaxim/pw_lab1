package factories;

import classes.ChildrenReservation;

import java.util.Date;

public class ChildrenReservationFactory implements IReservationFactory {
    @Override
    public Reservation createIndividualReservation(String userId, Date date, int duration, String courtId, float price, float discount) {
        return new ChildrenReservation(userId, date, duration, courtId, price, discount, 0); // assuming 0 children by default
    }

    @Override
    public Reservation createPackageReservation(String userId, Date date, int duration, String courtId, float price, float discount, String packageId, int sessionNumber) {
        ChildrenReservation reservation = new ChildrenReservation(userId, date, duration, courtId, price, discount, 0);
        reservation.setPackageId(packageId);
        reservation.setSessionNumber(sessionNumber);
        return reservation;
    }
}
