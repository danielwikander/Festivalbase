package models;

import javafx.collections.ObservableList;

import java.sql.Date;

/**
 * Represents a schedule table for a specific stage.
 */
public class StageSchedule {
    private ObservableList<timeSlice> schedule;

    public void newTimeSlice(Date time, String band_name) {
        timeSlice slice = new timeSlice(time, band_name);
        schedule.add(slice);
    }

    public ObservableList<timeSlice> getSchedule() {
        return schedule;
    }
    /*
     * Represents a row in the schedule. ex: 20:45 - The beatles
     */
    class timeSlice {
        private Date time;
        private String band_name;

        public timeSlice(Date time, String band_name) {
            this.time = time;
            this.band_name = band_name;
        }
    }

}
