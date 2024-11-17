package data.DAO;


import business.AdultReservationDTO;
import business.ChildrenReservationDTO;
import business.FamilyReservationDTO;
import business.enums.ReservationType;
import business.factories.IndividualReservationFactory;
import business.factories.PackageReservationFactory;
import data.common.DBConnection;
import data.common.QueriesLoader;
import factories.Reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;


public class ReservationDAO {
    QueriesLoader loader;
    IndividualReservationFactory individualFactory;
    PackageReservationFactory packageFactory;
    Connection connection;
    public ReservationDAO() {
        loader = new QueriesLoader();
        individualFactory = new IndividualReservationFactory();
        packageFactory = new PackageReservationFactory();
        connection = new DBConnection().getConnection();
    }

    public void destroy() {
        try {
            if(this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch(Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

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

    private Reservation _getReservationByQuery(String id, String query){
        Reservation player = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(loader.getProperty(query));
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if(!rs.next()) {
                return null;
            }

            stmt.close();

            player = _reservationFromDb(rs);
            rs.close();

        } catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
        return player;
    }

    public Reservation getReservationById(String id) {
        return _getReservationByQuery(id, "ReservationIdFilter");
    }

    public Reservation getReservationByUserId(String userId) {
        return _getReservationByQuery(userId, "ReservationUserIdFilter");
    }

    public Reservation getReservationByPackageId(String packageId) {
        return _getReservationByQuery(packageId, "ReservationPackageIdFilter");
    }

    public Reservation getReservationByCourtId(String courtId) {
        return _getReservationByQuery(courtId, "ReservationCourtIdFilter");
    }

    private PreparedStatement _prepareBaseStatement(Reservation reservation, String query) throws Exception {
        PreparedStatement stmt = connection.prepareStatement(loader.getProperty(query));
        final int offset = query.equals("ModifyReservation") ? 0 : -1;
        if(offset == 0) {
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
        //Setting adults and children to 0 by default
        stmt.setFloat(9 + offset, 0);
        stmt.setFloat(10 + offset, 0);
        //Setting type to -1 to identify objects without set type
        stmt.setFloat(11 + offset, -1);

        return stmt;
    }

    public boolean insertReservation(AdultReservationDTO reservation) {
        try {
            PreparedStatement stmt = _prepareBaseStatement(reservation, "InsertNewReservation");

            stmt.setInt(9, reservation.getAdultNumber());
            stmt.setInt(11,  ReservationType.ADULT.ordinal());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e){
            System.err.println(e);
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public boolean insertReservation(ChildrenReservationDTO reservation) {
        try {
            PreparedStatement stmt = _prepareBaseStatement(reservation, "InsertNewReservation");

            stmt.setInt(10, reservation.getChildrenNumber());
            stmt.setInt(11,  ReservationType.CHILDREN.ordinal());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e){
            System.err.println(e);
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public boolean insertReservation(FamilyReservationDTO reservation) {
        try {
            PreparedStatement stmt = _prepareBaseStatement(reservation, "InsertNewReservation");

            stmt.setInt(9, reservation.getAdultNumber());
            stmt.setInt(10, reservation.getChildrenNumber());
            stmt.setInt(11, ReservationType.FAMILY.ordinal());

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e){
            System.err.println(e);
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public ArrayList<Reservation> getReservationsByType(ReservationType type) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(loader.getProperty("ReservationTypeFilter"));

            stmt.setInt(1, type.ordinal());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                reservations.add(_reservationFromDb(rs));
            }

            rs.close();
            stmt.close();
        } catch (Exception e){
            System.err.println(e);
            e.printStackTrace();

            return null;
        }

        return reservations;
    }

    private void _deleteReservationByQuery(String id, String query){
        try {
            PreparedStatement stmt = connection.prepareStatement(loader.getProperty(query));
            stmt.setString(1, id);

            stmt.executeUpdate();
            stmt.close();

        } catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void deleteReservationById(String id) {
        _deleteReservationByQuery(id, "ReservationDeleteById");
    }

    public void deleteReservationByUserId(String userId) {
        _deleteReservationByQuery(userId, "ReservationDeleteByUserId");
    }

    public void deleteReservationByPackageId(String packageId) {
        _deleteReservationByQuery(packageId, "ReservationDeleteByPackageId");
    }

    public void modifyReservationByUserId(String userId, AdultReservationDTO reservation) {
        try {
            PreparedStatement stmt = _prepareBaseStatement(reservation, "ModifyReservation");

            stmt.setInt(11, Integer.parseInt(userId));
            stmt.executeUpdate();

            stmt.close();
        } catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
