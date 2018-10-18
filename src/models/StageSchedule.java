package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a schedule table for a specific stage.
 */
public class StageSchedule {
    private ObservableList<timeSlice> schedule = FXCollections.observableArrayList();

    public void newTimeSlice(String date, String time, String band_name) {
        timeSlice slice = new timeSlice(date, time, band_name);
        schedule.add(slice);
    }

    public ObservableList<timeSlice> getSchedule() {
        return schedule;
    }

    /*
     * Represents a row in the schedule. ex: 20:45 - The beatles
     */
    public class timeSlice {
        private String date;
        private String time;
        private String band_name;

        public timeSlice(String date, String time, String band_name) {
            this.date = date;
            this.time = time;
            this.band_name = band_name;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getBand_name() {
            return band_name;
        }
    }

}
