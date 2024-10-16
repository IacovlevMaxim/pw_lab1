package factories;

import java.util.Date;

abstract public class Reservation {
    private String _userId;
    private Date _date;
    private int _duration;
    private String _courtId;
    private float _price;
    private float _discount;
    private String _packageId;
    private int _sessionNumber;

    public Reservation() {

    }

    public Reservation(String userId, Date date, int duration, String courtId, float price) {
        _userId = userId;
        _date = date;
        _duration = duration;
        _courtId = courtId;
        _price = price;
        _discount = 0;
    }

    public Reservation(String userId, Date date, int duration, String courtId, float price, float discount) {
        _userId = userId;
        _date = date;
        _duration = duration;
        _courtId = courtId;
        _price = price;
        _discount = discount;
    }

    /**
     * Gets the user ID of the reservation.
     * @return The user ID as a String.
     */
    public String getUserId() {
        return _userId;
    }

    /**
     * Sets the user ID for the reservation.
     * @param userId The user ID as a String.
     */
    public void setUserId(String userId) {
        _userId = userId;
    }

    /**
     * Gets the date of the reservation.
     * @return The reservation date as a Date object.
     */
    public Date getDate() {
        return _date;
    }

    /**
     * Sets the date for the reservation.
     * @param date The reservation date as a Date object.
     */
    public void setDate(Date date) {
        _date = date;
    }

    /**
     * Gets the duration of the reservation.
     * @return The duration in hours as an int.
     */
    public int getDuration() {
        return _duration;
    }

    /**
     * Sets the duration for the reservation.
     * @param duration The duration in hours as an int.
     */
    public void setDuration(int duration) {
        _duration = duration;
    }

    /**
     * Gets the court ID of the reservation.
     * @return The court ID as a String.
     */
    public String getCourtId() {
        return _courtId;
    }

    /**
     * Sets the court ID for the reservation.
     * @param courtId The court ID as a String.
     */
    public void setCourtId(String courtId) {
        _courtId = courtId;
    }

    /**
     * Gets the price of the reservation.
     * @return The price as a float.
     */
    public float getPrice() {
        return _price;
    }

    /**
     * Sets the price for the reservation.
     * @param price The price as a float.
     */
    public void setPrice(float price) {
        _price = price;
    }

    /**
     * Gets the discount applied to the reservation.
     * @return The discount as a float.
     */
    public float getDiscount() {
        return _discount;
    }

    /**
     * Sets the discount for the reservation.
     * @param discount The discount as a float.
     */
    public void setDiscount(float discount) {
        _discount = discount;
    }

    /**
     * Sets the package ID for the reservation in case of package reservation.
     * @param packageId The package ID as a string.
     */
    public void setPackageId(String packageId) {
        _packageId = packageId;
    }

    /**
     * Gets the package ID of the reservation.
     * @return Package ID as string.
     */
    public String getPackageId() {
        return _packageId;
    }

    /**
     * Sets the session number for the reservation in case of package reservation.
     * @param sessionNumber The session number as an int.
     */
    public void setSessionNumber(int sessionNumber) {
        _sessionNumber = sessionNumber;
    }

    /**
     * Gets the session number for the reservation.
     * @return Session number as int.
     */
    public int getSessionNumber() {
        return _sessionNumber;
    }
}
