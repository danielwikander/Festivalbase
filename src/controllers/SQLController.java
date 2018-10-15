package controllers;

import models.*;

import java.sql.*;

public class SQLController {
    private static String dbURL         = "jdbc:postgresql://pgserver.mah.se/ah4502projekt";
    private static String dbUser        = "ah4502";
    private static String dbPassword    = "rrdbuol3";
    private static Connection dbConnection;

    private static void addBand(Band band) {
        try {
            Statement stmt = dbConnection.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO bands(band_name, band_country_of_origin, band_info, contact_person_id) " +
                            "VALUES (DEFAULT, '" + band.getBand_name() + "', '" + band.getBand_counry_of_origin() +
                            "', '" + band.getBand_info() + "', '" + band.getContact_person_id() + "');");
        } catch (SQLException e) {
            System.out.println("Couldn't add band to DB");
            e.printStackTrace();
        }
    }

     private static void addWorker(Worker worker) {
        try {
            Statement stmt = dbConnection.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO workers(person_number, name, address) " +
                            "VALUES ('" + worker.getPerson_number() + "', '" + worker.getName() + "', '" + worker.getAddress() + "');");
        } catch (SQLException e) {
            System.out.println("Couldn't add worker to DB");
            e.printStackTrace();
        }
    }

     private static void assignContactPerson(Worker worker, Band band) {
        try {
            Statement stmt = dbConnection.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO bands(contact_person_id) " +
                            "VALUES ('" + worker.getPerson_number() + "');");
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
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT time, band_playing FROM schedule WHERE scene = ? SORT BY time ASC");
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
        return schedule;
    }

    public static void dbConnect() {
        try {
            dbConnection = DriverManager.getConnection(dbURL, dbUser,
                    dbPassword);
        } catch (SQLException e) {
            System.out.println("Unable to connect to database");
            e.printStackTrace();
        }
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (Exception e){
            throw e;
        }
    }
}
