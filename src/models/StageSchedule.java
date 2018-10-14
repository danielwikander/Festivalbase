package models;

import java.sql.Date;
import java.util.LinkedList;


public class StageSchedule {
    private LinkedList schedule;

    public void newTimeSlice(Date time, String band_name) {
        timeSlice slice = new timeSlice(time, band_name);
        schedule.add(slice);
    }

    class timeSlice {

        public timeSlice(Date time, String band_name) {

        }
    }
}
