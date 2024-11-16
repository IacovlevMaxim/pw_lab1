package factories;

import java.time.LocalDate;

public interface IReservationFactory {
//    Reservation createIndividualReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount);
//    Reservation createPackageReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, String packageId, int sessionNumber);

    Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber);
    Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, String packageId, int sessionNumber);
    Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber);
    Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber, String packageId, int sessionNumber);
    Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber);
    Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, int childNumber, String packageId, int sessionNumber);
}
