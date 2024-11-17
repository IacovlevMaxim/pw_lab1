package factories;

import classes.AdultReservation;
import classes.ChildrenReservation;
import classes.FamilyReservation;
import exceptions.NoAdultException;

import java.time.LocalDate;

/**
 * Factory class for creating package-based reservations such as AdultReservation, ChildrenReservation, and FamilyReservation.
 * This factory specializes in handling reservations that are part of a package with additional session details.
 */
public class PackageReservationFactory implements IReservationFactory {

    /**
     * Default constructor for PackageReservationFactory.
     */
    public PackageReservationFactory() {
    }

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
     * @return null as this method is not implemented
     */
    @Override
    public Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber) {
        return null;
    }

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
     * @return an instance of {@link AdultReservation} with package details set
     */
    @Override
    public Reservation createAdultReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, String packageId, int sessionNumber) {
        AdultReservation reservation = new AdultReservation(userId, date, duration, courtId, price, discount, adultNumber);
        reservation.setPackageId(packageId);
        reservation.setSessionNumber(sessionNumber);
        return reservation;
    }

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
     * @return null as this method is not implemented
     */
    @Override
    public Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber) {
        return null;
    }

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
     * @return an instance of {@link ChildrenReservation} with package details set
     */
    @Override
    public Reservation createChildrenReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int childrenNumber, String packageId, int sessionNumber) {
        ChildrenReservation reservation = new ChildrenReservation(userId, date, duration, courtId, price, discount, childrenNumber);
        reservation.setPackageId(packageId);
        reservation.setSessionNumber(sessionNumber);
        return reservation;
    }

    /**
     * Creates a family reservation without package details.
     *
     * @param userId      the ID of the user making the reservation
     * @param date        the date of the reservation
     * @param duration    the duration of the reservation in hours
     * @param courtId     the ID of the court reserved
     * @param price       the price of the reservation
     * @param discount    the discount applied to the reservation
     * @param adultNumber the number of adults for the reservation
     * @param childrenNumber the number of children for the reservation
     * @return null as this method is not implemented
     */
    @Override
    public Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, int childrenNumber) {
        return null;
    }

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
     * @return an instance of {@link FamilyReservation} with package details set, or null if creation fails due to missing adults
     */
    public Reservation createFamilyReservation(String userId, LocalDate date, int duration, String courtId, float price, float discount, int adultNumber, int childNumber, String packageId, int sessionNumber) {
        FamilyReservation reservation = null;
        try {
            reservation = new FamilyReservation(userId, date, duration, courtId, price, discount, childNumber, adultNumber);
            reservation.setPackageId(packageId);
            reservation.setSessionNumber(sessionNumber);
        } catch (NoAdultException ex) {
            System.out.println("Unable to create FamilyReservation without adults");
        }
        return reservation;
    }
}
