package factories;

import java.time.LocalDate;

public interface IReservationFactory {
    Reservation createIndividualReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount);
    Reservation createPackageReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, String packageId, int sessionNumber);
}
