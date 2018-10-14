package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;


public class MainViewController {
    @FXML
    TableView mallorcaSchedule;
    @FXML
    TableView dieselTentSchedule;
    @FXML
    TableView theForumSchedule;

    @FXML
    BorderPane mainLayout;

    public void MainViewController() {
        SQLController.dbConnect();


    }


    private void populateTables() {
        populateMallorcaSchedule();
        populateDieselSchedule();
        populateTheForumSchedule();
    }

    private void populateMallorcaSchedule() {

    }

    private void populateDieselSchedule() {

    }

    private void populateTheForumSchedule() {

    }
}
