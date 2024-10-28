package classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ReservationManager {
    private UserManager userManager;
    private CourtManager courtManager;
    private ArrayList<Reservation> reservations;
    //private List<Package> packages;
    //private int nextPackageId;

    /**
     * Constructs a new ReservationManager with the specified UserManager and CourtManager.
     *
     * @param userManager the UserManager instance to manage user-related operations
     * @param courtManager the CourtManager instance to manage court-related operations
     */
    public ReservationManager(UserManager userManager, CourtManager courtManager) {
        this.userManager = userManager;
        this.courtManager = courtManager;
        this.reservations = new ArrayList<>();
    }

    
    /**
     * Calculates the price based on the duration of the reservation.
     *
     * @param duration the duration of the reservation in minutes. Valid values are 60, 90, and 120.
     * @return the price corresponding to the given duration. Returns 20.0 for 60 minutes, 
     *         30.0 for 90 minutes, 40.0 for 120 minutes, and 0.0 for any other duration.
     */
    private float calculatePrice(int duration) {
        switch (duration) {
            case 60: return 20.0f;
            case 90: return 30.0f;
            case 120: return 40.0f;
            default: return 0.0f; 
        }
    }

    /**
     * Calculates the discount based on the user's seniority.
     *
     * @param userId the ID of the user
     * @return the discount percentage. Returns 10.0 for users with seniority greater than 2 years, and 0.0 otherwise.
     */
    private float calculateDiscount(int userId) {
        if(userManager.getUser(userId).calculateSeniority() > 2) {
            System.out.println("The user has a 10% discount!\n");
            return 10.0f;
        }
        return 0.0f;
    }

    /**
     * Checks if the given date is valid for making a reservation.
     *
     * @param date the date of the reservation
     * @return {@code true} if the date is at least 24 hours in the future, and {@code false} otherwise
     */
    private boolean isValidReservationDate(LocalDate date) {
        if(ChronoUnit.DAYS.between(LocalDate.now(), date) > 1) {
            return true;
        }
        return false;
    }
    
    /**
     * Makes an individual reservation for the specified user.
     *
     * @param userId the ID of the user
     * @param date the date of the reservation
     * @param duration the duration of the reservation in minutes
     * @param courtId the ID of the court
     * @param max_num the maximum number of players allowed in the court
     * @param type the type of the court we want to reserve (indoor or outdoor)
     * @return {@code true} if the reservation is successfully made, and {@code false} otherwise
     */
    public boolean makeIndividualReservation(int userId, LocalDate date, int duration, int courtId, int max_num, boolean type) {

        boolean found = false;
        float price, discount;

        //first we check if the user is registered
        if(!userManager.isRegistered(userId)) { 
            System.out.println("The user is not registered\n");
            return false;
        }
        
        // check court availability
        ArrayList<Court> courts = courtManager.listFittingCourts(max_num, type);
        
        for(Court court : courts) {
            if(court.getName().equals(courtId)) {
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("The court is not available\n");
            return false;
        }

        // check if the date is valid
        if(!isValidReservationDate(date)) {
            System.out.println("Reservation cannot be made less than 24 hours before the start date\n");
            return false;
        }

        // calculate price and check discount
        if((price = calculatePrice(duration)) == 0) {
            System.out.println("The duration is not valid\n");
            return false;
        } else {
            System.out.println("The price is: " + price + "\n");
        }
        discount = calculateDiscount(userId);

        // create reservation
        Reservation reservation = new Reservation(userId, date, duration, courtId, price, discount); 
    }

    
    /**
     * Cancels a reservation for a given user and court.
     *
     * @param userId the ID of the user who made the reservation
     * @param courtId the ID of the court for which the reservation was made
     * @return {@code true} if the reservation was successfully canceled, {@code false} otherwise.
     * The reservation can only be canceled if it is more than 24 hours before the start date.
     * If the reservation is less than 24 hours before the start date, a message will be printed
     * and the cancellation will not proceed.
     */
    public boolean cancelReservation(int userId, int courtId) {
        for(Reservation reservation : reservations) {
            if(reservation.getUserId().equals(userId) && reservation.getCourtId().equals(courtId)) {
                if(!isValidReservationDate(reservation.getDate())) {
                    System.out.println("Reservation cannot be canceled less than 24 hours before the start date\n");
                    return false;
                }
                reservations.remove(reservation);
                return true;
            }
        }
        return false;
    }

    /**
     * Modifies a reservation for a given user and court.
     *
     * @param userId the ID of the user who made the reservation
     * @param courtId the ID of the court for which the reservation was made
     * @param date the new date of the reservation
     * @param duration the new duration of the reservation in minutes
     * @return {@code true} if the reservation was successfully modified, {@code false} otherwise.
     * The reservation can only be modified if it is more than 24 hours before the start date.
     * If the reservation is less than 24 hours before the start date, a message will be printed
     * and the modification will not proceed.
     */
    public boolean modifyReservation(int userId, int courtId, LocalDate date, int duration) {

        if(!isValidReservationDate(date)) {
            System.out.println("Reservation cannot be modified less than 24 hours before the start date\n");
            return false;
        }

        for(Reservation reservation : reservations) {
            if(reservation.getUserId().equals(userId) && reservation.getCourtId().equals(courtId)) {
                reservation.setUserId(userId);
                reservation.setCourtId(courtId);
                reservation.setDate(date);
                reservation.setDuration(duration);
                reservation.setPrice(calculatePrice(duration));
                reservation.setDiscount(calculateDiscount(userId));
                return true;
            }
        }
        System.out.println("Reservation not found\n");
        return false;
    }
    
}
