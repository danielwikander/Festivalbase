package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;
import java.sql.*;

/**
 * Holds all the functions related to the database.
 */
public class SQLController {
    private static String dbURL         = "jdbc:postgresql://pgserver.mah.se/ah4502projekt";
    private static String dbUser        = "ah4502";
    private static String dbPassword    = "rrdbuol3";
    private static Connection dbConnection;

    /**
     * Adds a band to the database.
     * @param band  The band to add.
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

    /**
     * Adds a new worker to the database.
     */
     private static void addWorker(Worker worker) {
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO " +
                    "workers(person_number, name, address) " +
                    "VALUES(?, ?, ?)");
            stmt.setString(1, worker.getPerson_number());
            stmt.setString(2, worker.getName());
            stmt.setString(3, worker.getAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Couldn't add worker to DB");
            e.printStackTrace();
        }
    }


    /**
     * Assigns a contact person to a band.
     */
     private static void assignContactPerson(Worker worker, Band band) {
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO " +
                    "bands(contact_person_id) " +
                    "VALUES (?) WHERE bands.id = ?");
            stmt.setString(1, worker.getPerson_number());
            stmt.setString(2, band.getBand_name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Couldn't add contact person to DB");
            e.printStackTrace();
        }
    }

    /**
     * Returns the schedule for a specific scene.
     * @param stagename     The name of the stage.
     * @return              Returns the schedule for the stage.
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
            while(rs.next()) {
                String date         = rs.getString("day");
                String time         = rs.getString("time");
                String band_playing = rs.getString("band_playing");
                schedule.newTimeSlice(date, time, band_playing);
            }

        } catch (SQLException e) {
            System.out.println("Couldn't retrieve" + stagename + "schedule.");
            e.printStackTrace();
        }
        // Print for testing purporses
        System.out.println(schedule);

        return schedule;
    }

    public static ObservableList<Band> getBands() {
        ObservableList<Band> bandList = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM bands");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String band_name              = rs.getString("band_name");
                String band_country_of_origin = rs.getString("band_country_of_origin");
                String band_info              = rs.getString("band_info");
                String contact_person_id      = rs.getString("contact_person_id");
                bandList.add(new Band(band_name, band_country_of_origin, band_info, contact_person_id ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't retrieve bands from db.");
        }
        return bandList;
    }

    public static void assignContactPerson(String bandName, String contactPersonID)  {
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
            stmt.setString(1, workerToHire.getPerson_number() );
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
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Couldn't disconnect from database.");
        }
    }
}
