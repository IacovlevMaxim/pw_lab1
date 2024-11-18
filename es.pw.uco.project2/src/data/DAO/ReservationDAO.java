package data.DAO;


import business.AdultReservationDTO;
import business.ChildrenReservationDTO;
import business.FamilyReservationDTO;
import business.enums.ReservationType;
import business.factories.IndividualReservationFactory;
import business.factories.PackageReservationFactory;
import classes.ChildrenReservation;
import data.common.DBConnection;
import data.common.QueriesLoader;
import factories.Reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Data Access Object (DAO) for managing reservations in the database.
 * Provides methods for retrieving, inserting, updating, and deleting reservations.
 */
public class ReservationDAO {
    private final QueriesLoader loader;
    private final IndividualReservationFactory individualFactory;
    private final PackageReservationFactory packageFactory;
    private final Connection connection;

    /**
     * Constructs a new instance of {@code ReservationDAO}.
     * Initializes the query loader, reservation factories, and database connection.
     */
    public ReservationDAO() {
        loader = new QueriesLoader();
        individualFactory = new IndividualReservationFactory();
        packageFactory = new PackageReservationFactory();
        connection = new DBConnection().getConnection();
    }

    /**
     * Closes the database connection if it is open.
     */
    public void destroy() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Constructs a {@link Reservation} object from a {@link ResultSet}.
     *
     * @param rs the {@code ResultSet} containing reservation data
     * @return a {@code Reservation} instance
     * @throws Exception if there is an error processing the result set
     */
    private Reservation _reservationFromDb(ResultSet rs) throws Exception {
        ReservationType type = ReservationType.values()[rs.getInt("type")];
        boolean hasPackageId = rs.getInt("packageId") != -1;
        String userId = rs.getString("userId");
        LocalDate date = rs.getDate("date").toLocalDate();
        int duration = rs.getInt("duration");
        String courtId = rs.getString("courtId");
        float price = rs.getFloat("price");
        float discount = rs.getFloat("discount");
        int adultNumber = rs.getInt("adultNumber");
        int childrenNumber = rs.getInt("childrenNumber");
        String packageId = rs.getString("packageId");
        int sessionNumber = rs.getInt("sessionNumber");

        return switch (type) {
            case ADULT -> hasPackageId
                    ? packageFactory.createAdultReservation(userId, date, duration, courtId, price, discount, adultNumber, packageId, sessionNumber)
                    : individualFactory.createAdultReservation(userId, date, duration, courtId, price, discount, adultNumber);
            case CHILDREN -> hasPackageId
                    ? packageFactory.createChildrenReservation(userId, date, duration, courtId, price, discount, adultNumber, packageId, sessionNumber)
                    : individualFactory.createChildrenReservation(userId, date, duration, courtId, price, discount, adultNumber);
            case FAMILY -> hasPackageId
                    ? packageFactory.createFamilyReservation(userId, date, duration, courtId, price, discount, adultNumber, childrenNumber, packageId, sessionNumber)
                    : individualFactory.createFamilyReservation(userId, date, duration, courtId, price, discount, adultNumber, childrenNumber);
        };
    }

    /**
     * Retrieves a reservation by a specific query.
     *
     * @param id    the identifier (e.g., userId, packageId, courtId)
     * @param query the query key to use in the {@code QueriesLoader}
     * @return the corresponding {@code Reservation}, or {@code null} if not found
     */
    private Reservation _getReservationByQuery(String id, String query) {
        Reservation reservation = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(loader.getProperty(query));
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            reservation = _reservationFromDb(rs);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
        return reservation;
    }

    /**
     * Retrieves a reservation by its ID.
     *
     * @param id the ID of the reservation
     * @return the corresponding {@code Reservation}, or {@code null} if not found
     */
    public Reservation getReservationById(String id) {
        return _getReservationByQuery(id, "ReservationIdFilter");
    }

    /**
     * Retrieves a reservation by the user ID.
     *
     * @param userId the user ID associated with the reservation
     * @return the corresponding {@code Reservation}, or {@code null} if not found
     */
    public Reservation getReservationByUserId(String userId) {
        return _getReservationByQuery(userId, "ReservationUserIdFilter");
    }

    /**
     * Retrieves a reservation by the package ID.
     *
     * @param packageId the package ID associated with the reservation
     * @return the corresponding {@code Reservation}, or {@code null} if not found
     */
    public Reservation getReservationByPackageId(String packageId) {
        return _getReservationByQuery(packageId, "ReservationPackageIdFilter");
    }

    /**
     * Retrieves a reservation by the court ID.
     *
     * @param courtId the court ID associated with the reservation
     * @return the corresponding {@code Reservation}, or {@code null} if not found
     */
    public Reservation getReservationByCourtId(String courtId) {
        return _getReservationByQuery(courtId, "ReservationCourtIdFilter");
    }

    /**
     * Prepares a {@link PreparedStatement} for base reservation properties.
     *
     * @param reservation the reservation to use for the statement
     * @param query       the query key to use in the {@code QueriesLoader}
     * @return a prepared statement ready for execution
     * @throws Exception if there is an error preparing the statement
     */
    private PreparedStatement _prepareBaseStatement(Reservation reservation, String query) throws Exception {
        PreparedStatement stmt = connection.prepareStatement(loader.getProperty(query));
        final int offset = query.equals("ModifyReservation") ? 0 : -1;
        if (offset == 0) {
            stmt.setInt(1, Integer.parseInt(reservation.getUserId()));
        }

        Date date = Date.valueOf(reservation.getDate());
        stmt.setDate(2 + offset, date);
        stmt.setInt(3 + offset, reservation.getDuration());
        stmt.setString(4 + offset, reservation.getCourtId());
        stmt.setFloat(5 + offset, reservation.getPrice());
        stmt.setFloat(6 + offset, reservation.getDiscount());
        stmt.setString(7 + offset, reservation.getPackageId());
        stmt.setFloat(8 + offset, reservation.getSessionNumber());
        stmt.setInt(9 + offset, 0); // Default for adults
        stmt.setInt(10 + offset, 0); // Default for children
        stmt.setInt(11 + offset, -1); // Default for type
        return stmt;
    }

    /**
     * Inserts a new adult reservation into the database.
     *
     * @param reservation the {@code AdultReservationDTO} containing the reservation details
     * @return {@code true} if the insertion was successful, {@code false} otherwise
     */
    public boolean insertReservation(AdultReservationDTO reservation) {
        try {
            PreparedStatement stmt = _prepareBaseStatement(reservation, "InsertNewReservation");

            stmt.setInt(9, reservation.getAdultNumber());
            stmt.setInt(11, ReservationType.ADULT.ordinal());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Inserts a new children reservation into the database.
     *
     * @param reservation the {@code ChildrenReservationDTO} containing the reservation details
     * @return {@code true} if the insertion was successful, {@code false} otherwise
     */
    public boolean insertReservation(ChildrenReservationDTO reservation) {
        try {
            PreparedStatement stmt = _prepareBaseStatement(reservation, "InsertNewReservation");

            stmt.setInt(10, reservation.getChildrenNumber());
            stmt.setInt(11, ReservationType.CHILDREN.ordinal());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Inserts a new family reservation into the database.
     *
     * @param reservation the {@code FamilyReservationDTO} containing the reservation details
     * @return {@code true} if the insertion was successful, {@code false} otherwise
     */
    public boolean insertReservation(FamilyReservationDTO reservation) {
        try {
            PreparedStatement stmt = _prepareBaseStatement(reservation, "InsertNewReservation");

            stmt.setInt(9, reservation.getAdultNumber());
            stmt.setInt(10, reservation.getChildrenNumber());
            stmt.setInt(11, ReservationType.FAMILY.ordinal());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Retrieves all reservations of a specific type from the database.
     *
     * @param type the {@link ReservationType} to filter reservations by
     * @return a list of {@code Reservation} objects of the specified type, or {@code null} if an error occurs
     */
    public ArrayList<Reservation> getReservationsByType(ReservationType type) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(loader.getProperty("ReservationTypeFilter"));

            stmt.setInt(1, type.ordinal());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                reservations.add(_reservationFromDb(rs));
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
            return null;
        }
        return reservations;
    }

    /**
     * Deletes a reservation using a specific query and identifier.
     *
     * @param id    the identifier (e.g., userId, packageId, or courtId)
     * @param query the query key to use in the {@code QueriesLoader}
     */
    private void _deleteReservationByQuery(String id, String query) {
        try {
            PreparedStatement stmt = connection.prepareStatement(loader.getProperty(query));
            stmt.setString(1, id);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Deletes a reservation by its ID.
     *
     * @param id the ID of the reservation to delete
     */
    public void deleteReservationById(String id) {
        _deleteReservationByQuery(id, "ReservationDeleteById");
    }

    /**
     * Deletes a reservation by the user ID.
     *
     * @param userId the user ID associated with the reservation
     */
    public void deleteReservationByUserId(String userId) {
        _deleteReservationByQuery(userId, "ReservationDeleteByUserId");
    }

    /**
     * Deletes a reservation by the package ID.
     *
     * @param packageId the package ID associated with the reservation
     */
    public void deleteReservationByPackageId(String packageId) {
        _deleteReservationByQuery(packageId, "ReservationDeleteByPackageId");
    }

    /**
     * Updates an existing adult reservation by the user ID.
     *
     * @param userId      the user ID associated with the reservation
     * @param reservation the updated {@code AdultReservationDTO} containing the new reservation details
     */
    public void modifyReservationByUserId(String userId, AdultReservationDTO reservation) {
        try {
            PreparedStatement stmt = _prepareBaseStatement(reservation, "ModifyReservation");

            stmt.setInt(8, reservation.getAdultNumber());
            stmt.setInt(10, ReservationType.ADULT.ordinal());
            stmt.setInt(11, Integer.parseInt(userId));
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing children reservation by the user ID.
     *
     * @param userId      the user ID associated with the reservation
     * @param reservation the updated {@code ChildrenReservationDTO} containing the new reservation details
     */
    public void modifyReservationByUserId(String userId, ChildrenReservationDTO reservation) {
        try {
            PreparedStatement stmt = _prepareBaseStatement(reservation, "ModifyReservation");

            stmt.setInt(9, reservation.getChildrenNumber());
            stmt.setInt(10, ReservationType.CHILDREN.ordinal());
            stmt.setInt(11, Integer.parseInt(userId));
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing family reservation by the user ID.
     *
     * @param userId      the user ID associated with the reservation
     * @param reservation the updated {@code FamilyReservationDTO} containing the new reservation details
     */
    public void modifyReservationByUserId(String userId, FamilyReservationDTO reservation) {
        try {
            PreparedStatement stmt = _prepareBaseStatement(reservation, "ModifyReservation");

            stmt.setInt(8, reservation.getAdultNumber());
            stmt.setInt(9, reservation.getChildrenNumber());
            stmt.setInt(10, ReservationType.FAMILY.ordinal());
            stmt.setInt(11, Integer.parseInt(userId));
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
