package factories;

import classes.AdultReservation;
import classes.ChildrenReservation;
import classes.FamilyReservation;
import exceptions.NoAdultException;

import java.time.LocalDate;

/**
 * Factory class for creating individual reservations such as AdultReservation, ChildrenReservation, and FamilyReservation.
 */
public class IndividualReservationFactory implements IReservationFactory {

    /**
     * Default constructor for IndividualReservationFactory.
     */
    public IndividualReservationFactory() {
    }

    /**
     * Creates an adult reservation.
     *
     * @param userId      the ID of the user making the reservation
     * @param date        the date of the reservation
     * @param duration    the duration of the reservation in hours
     * @param courtId     the ID of the court reserved
     * @param price       the price of the reservation
     * @param discount    the discount applied to the reservation
     * @param adultNumber the number of adults for the reservation
     * @return an instance of {@link AdultReservation}
     */
    @Override
    public Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber) {
        return new AdultReservation(userId, date, duration, courtId, price, discount, adultNumber);
    }

    /**
     * Creates an adult reservation with additional package details.
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
     * @return null as this method is not implemented
     */
    @Override
    public Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, String packageId, int sessionNumber) {
        return null;
    }

    /**
     * Creates a children reservation.
     *
     * @param userId        the ID of the user making the reservation
     * @param date          the date of the reservation
     * @param duration      the duration of the reservation in hours
     * @param courtId       the ID of the court reserved
     * @param price         the price of the reservation
     * @param discount      the discount applied to the reservation
     * @param childrenNumber the number of children for the reservation
     * @return an instance of {@link ChildrenReservation}
     */
    @Override
    public Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber) {
        return new ChildrenReservation(userId, date, duration, courtId, price, discount, childrenNumber);
    }

    /**
     * Creates a children reservation with additional package details.
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
     * @return null as this method is not implemented
     */
    @Override
    public Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber, String packageId, int sessionNumber) {
        return null;
    }

    /**
     * Creates a family reservation.
     *
     * @param userId      the ID of the user making the reservation
     * @param date        the date of the reservation
     * @param duration    the duration of the reservation in hours
     * @param courtId     the ID of the court reserved
     * @param price       the price of the reservation
     * @param discount    the discount applied to the reservation
     * @param adultNumber the number of adults for the reservation
     * @return an instance of {@link FamilyReservation}, or null if creation fails due to missing adults
     */
    @Override
    public Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, int childrenNumber) {
        try {
            return new FamilyReservation(userId, date, duration, courtId, price, discount, childrenNumber, adultNumber);
        } catch (NoAdultException ex) {
            System.out.println("Unable to create FamilyReservation without adults");
        }
        return null;
    }

    /**
     * Creates a family reservation with additional package details.
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
     * @return null as this method is not implemented
     */
    @Override
    public Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, int childNumber, String packageId, int sessionNumber) {
        return null;
    }
}
