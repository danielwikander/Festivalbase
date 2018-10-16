package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import models.StageSchedule;
import models.Worker;

/**
 * Controls the Main View.
 */
public class MainViewController {
    @FXML
    TableView mallorcaSchedule;
    @FXML
    TableView dieselTentSchedule;
    @FXML
    TableView theForumSchedule;

    @FXML
    BorderPane mainLayout;

    /**
     * Starts when the main view is constructed.
     */
    /*public void MainViewController() {
        SQLController.dbConnect();
        populateTables();
    }*/

    @FXML
    public void initialize() {
        SQLController.dbConnect();
        populateTables();
    }

    /**
     * Populates the tables with their respective schedules.
     */
    private void populateTables() {
        populateMallorcaSchedule();
        populateDieselSchedule();
        populateTheForumSchedule();
    }

    // Retrieves the schedules from the database and populates the tables.

    private void populateMallorcaSchedule() {
        StageSchedule mallorca = SQLController.getSchedule("Mallorca");
        mallorcaSchedule.setItems(mallorca.getSchedule());
    }

    private void populateDieselSchedule() {
        StageSchedule dieselTent= SQLController.getSchedule("The Diesel Tent");
        dieselTentSchedule.setItems(dieselTent.getSchedule());
        dieselTentSchedule.e
    }

    private void populateTheForumSchedule() {
        StageSchedule theForum = SQLController.getSchedule("The Forum");
        theForumSchedule.setItems(theForum.getSchedule());
    }

}
