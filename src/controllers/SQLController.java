package controllers;

import models.*;

import java.sql.*;
import java.util.LinkedList;

public class SQLController {
    private static String dbURL         = "REPLACE WITH DATABASE URL";
    private static String dbUser        = "REPLACE WITH DATABASE USERNAME";
    private static String dbPassword    = "REPLACE WITH DATABASE PASSWORD";
    private static Connection dbConnection;


    private static void addBand(Band band) {
        try {
            Statement stmt = dbConnection.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO bands(" + "" + ");");
        } catch (SQLException e) {
            System.out.println("Couldn't add band to DB");
            e.printStackTrace();
        }
    }


    public static StageSchedule getSchedule(String stagename) {
        StageSchedule schedule = new StageSchedule();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT time, band_playing" +
                            "FROM schedule WHERE scene = " + stagename + " SORT BY time ASC);");
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
