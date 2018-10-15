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
        private Date time;
        private String band_name;

        public timeSlice(Date time, String band_name) {
            this.time = time;
            this.band_name = band_name;
        }
    }
}
