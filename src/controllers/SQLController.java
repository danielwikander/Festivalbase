package controllers;

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
    private static void addBand(Band band) {
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO " +
                    "bands(band_name, band_country_of_origin, band_info, contact_person_id)" +
                    "VALUES(?, ?, ?, ?)");
            stmt.setString(1, band.getBand_name());
            stmt.setString(2, band.getBand_country_of_origin());
            stmt.setString(3, band.getBand_info());
            stmt.setInt(4, band.getContact_person_id());

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
            stmt.setInt(1, worker.getPerson_number());
            stmt.setString(2, worker.getName());
            stmt.setString(3, worker.getAddress());

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
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO bands(contact_person_id) " +
                    "VALUES (?) WHERE bands.id = ?");
            stmt.setInt(1, worker.getPerson_number());
            stmt.setInt(2, band.getBand_id());

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
            // The number 1 states which  '?' to replace.
            // (In this case there is only one in the statement)
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT time, band_playing " +
                    "FROM schedule WHERE scene = ? ORDER BY time ASC");
            stmt.setString(1, stagename);
            ResultSet rs = stmt.executeQuery();

            // Returns values while there are still rows in the retrieved dataset.
            // If only a single row is expected, use an if-statement instead.
            while(rs.next()) {
                Date   time         = rs.getDate("time");
                String band_playing = rs.getString("band_playing");
                schedule.newTimeSlice(time, band_playing);
            }

        } catch (SQLException e) {
            System.out.println("Couldn't retrieve" + stagename + "schedule.");
            e.printStackTrace();
        }
        // Print for testing purporses
        System.out.println(schedule);

        return schedule;
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
