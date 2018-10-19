package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;

import java.sql.*;

/**
 * Holds all the functions related to the database.
 */
public class SQLController {
    private static String dbURL = "jdbc:postgresql://pgserver.mah.se/ah4502projekt";
    private static String dbUser = "ah4502";
    private static String dbPassword = "rrdbuol3";
    private static Connection dbConnection;

    /**
     * Adds a band to the database.
     *
     * @param band The band to add.
     */
    public static void addBand(Band band) {
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO " +
                    "bands(band_name, band_country_of_origin, band_info, contact_person_id)" +
                    "VALUES(?, ?, ?, ?)");
            stmt.setString(1, band.getBand_name());
            stmt.setString(2, band.getBand_country_of_origin());
            stmt.setString(3, band.getBand_info());
            stmt.setString(4, band.getContact_person_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Couldn't add band to DB");
            e.printStackTrace();
        }
    }

    public static ResponsibilityTable getResponsibilityCount() {
        ResponsibilityTable responsibilityTable = new ResponsibilityTable();
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT w.person_number, w.name, " +
                    "COUNT (ba.bandmember_id) AS contact_connections " +
                    "FROM workers w " +
                    "INNER JOIN bands b ON w.person_number = b.contact_person_id " +
                    "INNER JOIN bandmember_association ba ON b.band_name = ba.band " +
                    "GROUP BY w.person_number, w.name");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String workerPersonNumber = rs.getString("person_number");
                String workerName = rs.getString("name");
                int count = rs.getInt("contact_connections");
                responsibilityTable.addNewRow(workerPersonNumber, workerName, count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't retrieve responsibilitytable");
        }
        return responsibilityTable;
    }

    /**
     * Returns the schedule for a specific scene.
     *
     * @param stagename The name of the stage.
     * @return Returns the schedule for the stage.
     */
    public static StageSchedule getSchedule(String stagename) {
        StageSchedule schedule = new StageSchedule();
        try {
            // PreparedStatement prepares a statement to execute.
            // '?' is replaced with a variable on stmt.setString
            // The number '1' states which  '?' to replace.
            // (In this case there is only one in the statement)
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT day, time, band_playing " +
                    "FROM schedule WHERE scene = ? ORDER BY time ASC");
            stmt.setString(1, stagename);
            ResultSet rs = stmt.executeQuery();

            // Returns values while there are still rows in the retrieved dataset.
            // If only a single row is expected, use an if-statement instead.
            while (rs.next()) {
                String date = rs.getString("day");
                String time = rs.getString("time");
                String band_playing = rs.getString("band_playing");
                schedule.newTimeSlice(date, time, band_playing);
            }

        } catch (SQLException e) {
            System.out.println("Couldn't retrieve" + stagename + "schedule.");
            e.printStackTrace();
        }
        return schedule;
    }

    public static SecuritySchedule getSecuritySchedule() {
        SecuritySchedule securitySchedule = new SecuritySchedule();
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT " +
                    "security_schedule.day, security_schedule.time, security_schedule.scene, " +
                    "security_schedule.responsible_worker, workers.name " +
                    "FROM security_schedule INNER JOIN workers ON workers.person_number = security_schedule.responsible_worker");
            ResultSet rs = stmt.executeQuery();

            // Returns values while there are still rows in the retrieved dataset.
            // If only a single row is expected, use an if-statement instead.
            while (rs.next()) {
                String date = rs.getString("day");
                String time = rs.getString("time");
                String scene = rs.getString("scene");
                String responsibleWorker = rs.getString("responsible_worker");
                String name = rs.getString("Name");
                securitySchedule.newTimeSlice(date, time, scene, responsibleWorker, name);
            }

        } catch (SQLException e) {
            System.out.println("Couldn't retrieve security schedule.");
            e.printStackTrace();
        }
        return securitySchedule;
    }

    public static ObservableList<Band> getBands() {
        ObservableList<Band> bandList = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM bands");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String band_name = rs.getString("band_name");
                String band_country_of_origin = rs.getString("band_country_of_origin");
                String band_info = rs.getString("band_info");
                String contact_person_id = rs.getString("contact_person_id");
                bandList.add(new Band(band_name, band_country_of_origin, band_info, contact_person_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't retrieve bands from db.");
        }
        return bandList;
    }

    public static void addToSecurity(String dateToAdd, String timeToAdd, String scene, String workerPersonNumber) {
        try {
            Date date = java.sql.Date.valueOf(dateToAdd);
            Time time = java.sql.Time.valueOf(timeToAdd + ":00");
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO security_schedule " +
                    "VALUES(?,?,?,?)");
            stmt.setDate(1, date);
            stmt.setTime(2, time);
            stmt.setString(3, scene);
            stmt.setString(4, workerPersonNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't add " + workerPersonNumber + "to security table");
        }
    }

    public static void assignContactPerson(String bandName, String contactPersonID) {
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("UPDATE bands " +
                    "SET contact_person_id = ? " +
                    "WHERE band_name = ?");
            stmt.setString(1, contactPersonID);
            stmt.setString(2, bandName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't assign contact person to " + bandName);
        }
    }

    public static void specifyConcert(Date dayToPlay, Time timeToPlay, String band_name, String stage) {
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO schedule VALUES(?, ?, ?, ?)");
            stmt.setDate(1, dayToPlay);
            stmt.setTime(2, timeToPlay);
            stmt.setString(3, band_name);
            stmt.setString(4, stage);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't book concert.");
        }
    }

    public static void hireWorker(Worker workerToHire) {
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO workers VALUES(?, ?, ?)");
            stmt.setString(1, workerToHire.getPerson_number());
            stmt.setString(2, workerToHire.getName());
            stmt.setString(3, workerToHire.getAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldnt add worker" + workerToHire.getName());
        }
    }

    public static boolean logIn(String username, String password) {
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT username, password FROM system_administrators WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Connects to the database.
     */
    public static void dbConnect() {
        try {
            dbConnection = DriverManager.getConnection(dbURL, dbUser,
                    dbPassword);
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
            e.printStackTrace();
        }
    }

    /**
     * Disconnects from the database.
     */
    public static void dbDisconnect() {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't disconnect from database.");
        }
    }
}
