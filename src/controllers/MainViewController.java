package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.StageSchedule;
import models.Worker;

import java.sql.Date;

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
        TableColumn dateCol = new TableColumn("Time");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, Date>("time"));

        TableColumn bandCol = new TableColumn("Band");
        bandCol.setMinWidth(100);
        bandCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, String>("band_name"));

        mallorcaSchedule.setItems(mallorca.getSchedule());
        mallorcaSchedule.getColumns().addAll(dateCol, bandCol);
    }


    private void populateDieselSchedule() {
        StageSchedule dieselTent = SQLController.getSchedule("The Diesel Tent");
        TableColumn dateCol = new TableColumn("Time");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, Date>("time"));

        TableColumn bandCol = new TableColumn("Band");
        bandCol.setMinWidth(100);
        bandCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, String>("band_name"));

        dieselTentSchedule.setItems(dieselTent.getSchedule());
        dieselTentSchedule.getColumns().addAll(dateCol, bandCol);

    }

    private void populateTheForumSchedule() {
        StageSchedule theForum = SQLController.getSchedule("The Forum");
        TableColumn dateCol = new TableColumn("Time");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, Date>("time"));

        TableColumn bandCol = new TableColumn("Band");
        bandCol.setMinWidth(100);
        bandCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, String>("band_name"));

        theForumSchedule.setItems(theForum.getSchedule());
        theForumSchedule.getColumns().addAll(dateCol, bandCol);
    }

}
