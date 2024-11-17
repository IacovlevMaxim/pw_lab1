package factories;

import java.time.LocalDate;

/**
 * Interface for creating various types of reservations including adult, children, and family reservations.
 * Provides method signatures for creating reservations with or without package details.
 */
public interface IReservationFactory {

    /**
     * Creates an adult reservation without package details.
     *
     * @param userId      the ID of the user making the reservation
     * @param date        the date of the reservation
     * @param duration    the duration of the reservation in hours
     * @param courtId     the ID of the court reserved
     * @param price       the price of the reservation
     * @param discount    the discount applied to the reservation
     * @param adultNumber the number of adults for the reservation
     * @return an instance of {@link Reservation} for adults
     */
    Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber);

    /**
     * Creates an adult reservation with package details.
     *
     * @param userId       the ID of the user making the reservation
     * @param date         the date of the reservation
     * @param duration     the duration of the reservation in hours
     * @param courtId      the ID of the court reserved
     * @param price        the price of the reservation
     * @param discount     the discount applied to the reservation
     * @param adultNumber  the number of adults for the reservation
     * @param packageId    the package ID associated with the reservation
     * @param sessionNumber the number of sessions included in the package
     * @return an instance of {@link Reservation} for adults with package details
     */
    Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, String packageId, int sessionNumber);

    /**
     * Creates a children reservation without package details.
     *
     * @param userId        the ID of the user making the reservation
     * @param date          the date of the reservation
     * @param duration      the duration of the reservation in hours
     * @param courtId       the ID of the court reserved
     * @param price         the price of the reservation
     * @param discount      the discount applied to the reservation
     * @param childrenNumber the number of children for the reservation
     * @return an instance of {@link Reservation} for children
     */
    Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber);

    /**
     * Creates a children reservation with package details.
     *
     * @param userId         the ID of the user making the reservation
     * @param date           the date of the reservation
     * @param duration       the duration of the reservation in hours
     * @param courtId        the ID of the court reserved
     * @param price          the price of the reservation
     * @param discount       the discount applied to the reservation
     * @param childrenNumber the number of children for the reservation
     * @param packageId      the package ID associated with the reservation
     * @param sessionNumber  the number of sessions included in the package
     * @return an instance of {@link Reservation} for children with package details
     */
    Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber, String packageId, int sessionNumber);

    /**
     * Creates a family reservation without package details.
     *
     * @param userId        the ID of the user making the reservation
     * @param date          the date of the reservation
     * @param duration      the duration of the reservation in hours
     * @param courtId       the ID of the court reserved
     * @param price         the price of the reservation
     * @param discount      the discount applied to the reservation
     * @param adultNumber   the number of adults for the reservation
     * @param childrenNumber the number of children for the reservation
     * @return an instance of {@link Reservation} for families
     */
    Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, int childrenNumber);

    /**
     * Creates a family reservation with package details.
     *
     * @param userId       the ID of the user making the reservation
     * @param date         the date of the reservation
     * @param duration     the duration of the reservation in hours
     * @param courtId      the ID of the court reserved
     * @param price        the price of the reservation
     * @param discount     the discount applied to the reservation
     * @param adultNumber  the number of adults for the reservation
     * @param childNumber  the number of children for the reservation
     * @param packageId    the package ID associated with the reservation
     * @param sessionNumber the number of sessions included in the package
     * @return an instance of {@link Reservation} for families with package details
     */
    Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, int childNumber, String packageId, int sessionNumber);
}
