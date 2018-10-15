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

    public static StageSchedule getSchedule(String stagename) {
        StageSchedule schedule = new StageSchedule();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT time, band_playing" +
                            "FROM schedule WHERE scene = " + stagename +
                            " SORT BY time ASC);");
            if (rs.next()) {
                schedule.newTimeSlice(rs.getDate(1), rs.getString(2));
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
