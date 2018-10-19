
package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a schedule table for a specific stage.
 */
public class SecuritySchedule {
    private ObservableList<timeSlice> schedule = FXCollections.observableArrayList();

    public void newTimeSlice(String date, String time, String scene, String worker_personal_number, String workerName) {
        timeSlice slice = new timeSlice(date, time, scene, worker_personal_number, workerName);
        schedule.add(slice);
    }

    public ObservableList<timeSlice> getSchedule() {
        return schedule;
    }

    /*
     * Represents a row in the schedule. ex: 20:45 - Namn Namnsson
     */
    public class timeSlice {
        private String date;
        private String time;
        private String worker_name;
        private String worker_personal_number;
        private String scene;

        public timeSlice(String date, String time, String scene, String worker_personal_number, String worker_name) {
            this.date = date;
            this.time = time;
            this.worker_name = worker_name;
            this.scene = scene;
            this.worker_personal_number = worker_personal_number;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getWorker_name() {
            return worker_name;
        }

        public String getWorker_personal_number() {
            return worker_personal_number;
        }

        public String getScene() {
            return scene;
        }
    }
}
