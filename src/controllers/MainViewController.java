package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.*;

import java.sql.Date;

/**
 * Controls the Main View.
 */
public class MainViewController {
    // ---- Schedule tab ---- //
    @FXML
    TableView mallorcaSchedule;
    @FXML
    TableView dieselTentSchedule;
    @FXML
    TableView theForumSchedule;

    // ---- Bands tab ---- //
    @FXML
    TableView bandsTable;

    @FXML
    public void initialize() {
        SQLController.dbConnect();
        populateTables();
        populateBandsTable();
    }

    /**
     * Populates the tables with their respective schedules.
     */
    private void populateTables() {
        populateMallorcaSchedule();
        populateDieselSchedule();
        populateTheForumSchedule();
    }

    // ---- Schedule table ---- //
    private void populateMallorcaSchedule() {
        StageSchedule mallorca = SQLController.getSchedule("Mallorca");

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, Date>("date"));

        TableColumn timeCol = new TableColumn("Time");
        timeCol.setMinWidth(100);
        timeCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, Date>("time"));

        TableColumn bandCol = new TableColumn("Band");
        bandCol.setMinWidth(150);
        bandCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, String>("band_name"));

        mallorcaSchedule.setItems(mallorca.getSchedule());
        mallorcaSchedule.getColumns().addAll(dateCol, timeCol, bandCol);
    }


    private void populateDieselSchedule() {
        StageSchedule dieselTent = SQLController.getSchedule("The Diesel Tent");

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, Date>("date"));

        TableColumn timeCol = new TableColumn("Time");
        timeCol.setMinWidth(100);
        timeCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, Date>("time"));

        TableColumn bandCol = new TableColumn("Band");
        bandCol.setMinWidth(150);
        bandCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, String>("band_name"));

        dieselTentSchedule.setItems(dieselTent.getSchedule());
        dieselTentSchedule.getColumns().addAll(dateCol, timeCol, bandCol);

    }

    private void populateTheForumSchedule() {
        StageSchedule theForum = SQLController.getSchedule("The Forum");

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, Date>("date"));

        TableColumn timeCol = new TableColumn("Time");
        timeCol.setMinWidth(100);
        timeCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, Date>("time"));

        TableColumn bandCol = new TableColumn("Band");
        bandCol.setMinWidth(150);
        bandCol.setCellValueFactory(
                new PropertyValueFactory<StageSchedule, String>("band_name"));

        theForumSchedule.setItems(theForum.getSchedule());
        theForumSchedule.getColumns().addAll(dateCol, timeCol, bandCol);
    }


    // ---- Bands table ---- //
    private void populateBandsTable() {
        ObservableList<Band> bands = SQLController.getBands();
        TableColumn band_nameCol = new TableColumn("Band Name");
        band_nameCol.setMinWidth(150);
        band_nameCol.setCellValueFactory(
                new PropertyValueFactory<Band, String>("band_name"));

        TableColumn band_country_of_originCol = new TableColumn("Country of origin");
        band_country_of_originCol.setMinWidth(150);
        band_country_of_originCol.setCellValueFactory(
                new PropertyValueFactory<Band, String>("band_country_of_origin"));

        TableColumn band_infoCol = new TableColumn("Info");
        band_infoCol.setMinWidth(250);
        band_infoCol.setCellValueFactory(
                new PropertyValueFactory<Band, String>("band_info"));

        bandsTable.setItems(bands);
        bandsTable.getColumns().addAll(band_nameCol, band_country_of_originCol, band_infoCol);
    }

}
