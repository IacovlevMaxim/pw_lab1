package factories;

import java.util.Date;

public interface IReservationFactory {
    Reservation createIndividualReservation(String userId, Date date, int duration, String courtId, float price, float discount);
    Reservation createPackageReservation(String userId, Date date, int duration, String courtId, float price, float discount, String packageId, int sessionNumber);
}
