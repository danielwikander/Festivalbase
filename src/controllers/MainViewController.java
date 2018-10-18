package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import models.Band;
import models.StageSchedule;
import models.Worker;

import javax.xml.soap.Text;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * Controls the Main View.
 */
public class MainViewController {
    @FXML
    BorderPane mainLayout;

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

    // ---- Administrator tab ---- //
    // Book a band stuff
    @FXML
    TextField band_nameField;
    @FXML
    TextField band_country_of_originField;
    @FXML
    TextField band_contact_person_idField;
    @FXML
    TextField band_infoField;
    @FXML
    Button    bookBandButton;

    // Assign a contact stuff
    @FXML
    TextField assign_contact_person_idField;
    @FXML
    TextField assign_band_nameField;
    @FXML
    Button    assignContactPersonButton;

    // Specify concert stuff
    @FXML
    TextField specify_concert_band_nameField;
    @FXML
    TextField specifyConcertTimeField;
    @FXML
    ChoiceBox specifyConcertDateChoiceBox;
    @FXML
    ChoiceBox stageChoiceBox;
    @FXML
    Button    specifyConcertButton;

    // Hire worker stuff
    @FXML
    TextField newWorkerNameField;
    @FXML
    TextField newWorkerPersonNumberField;
    @FXML
    TextField newWorkerAddressField;
    @FXML
    Button    hireWorkerButton;

    // Login stuff
    @FXML
    TextField adminUsernameField;
    @FXML
    TextField adminPasswordField;
    @FXML
    Button    loginButton;

    @FXML
    public void initialize() {
        SQLController.dbConnect();
        populateTables();
        populateBandsTable();
        setUpChoiceBoxes();
    }

    private void enableFieldsAndButtons() {
        // Add band buttons and fields
        band_nameField.setDisable(false);
    band_country_of_originField.setDisable(false);
    band_contact_person_idField.setDisable(false);
    band_infoField.setDisable(false);
    bookBandButton.setDisable(false);

    // Assign a contact buttons and fields
    assign_contact_person_idField.setDisable(false);
    assign_band_nameField.setDisable(false);
    assignContactPersonButton.setDisable(false);

    // Specify concert buttons and fields
    specify_concert_band_nameField.setDisable(false);
    specifyConcertTimeField.setDisable(false);
    specifyConcertDateChoiceBox.setDisable(false);
    stageChoiceBox.setDisable(false);
    specifyConcertButton.setDisable(false);
    // Hire worker buttons and fields
    newWorkerNameField.setDisable(false);
    newWorkerPersonNumberField.setDisable(false);
    newWorkerAddressField.setDisable(false);
    hireWorkerButton.setDisable(false);
    }

    private void setUpChoiceBoxes() {
        specifyConcertDateChoiceBox.getItems().addAll("2018-05-10", "2018-05-11", "2018-05-12");
        stageChoiceBox.getItems().addAll("Mallorca", "The Diesel Tent", "The Forum");
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
    private void populateBandsTable()  {
        ObservableList<Band> bands = SQLController.getBands();
        TableColumn band_nameCol = new TableColumn("Band Name");
        band_nameCol.setMinWidth(150);
        band_nameCol.setCellValueFactory(
                new PropertyValueFactory<Band, String>("band_name"));

        TableColumn band_country_of_originCol = new TableColumn("Country of origin");
        band_country_of_originCol.setMinWidth(150);
        band_country_of_originCol.setCellValueFactory(
                new PropertyValueFactory<Band, String>("band_country_of_origin"));

        TableColumn band_infoCol= new TableColumn("Info");
        band_infoCol.setMinWidth(250);
        band_infoCol.setCellValueFactory(
                new PropertyValueFactory<Band, String>("band_info"));

        bandsTable.setItems(bands);
        bandsTable.getColumns().addAll(band_nameCol, band_country_of_originCol, band_infoCol);
    }

    // ---- Administrator tab ---- //

    @FXML
    private void bookBand() {
        Band bandToAdd;
        if(band_contact_person_idField.getText() == null) {
            bandToAdd = new Band(band_nameField.getText(), band_country_of_originField.getText(), band_infoField.getText(), null);
        } else {
            bandToAdd = new Band(band_nameField.getText(), band_country_of_originField.getText(), band_infoField.getText(), band_contact_person_idField.getText());
        }
       SQLController.addBand(bandToAdd);
    }

    @FXML
    private void assignContactPerson() {
        String workerToAssign = assign_contact_person_idField.getText();
        String bandToAssign = assign_band_nameField.getText();
        SQLController.assignContactPerson(bandToAssign, workerToAssign);
    }

    @FXML
    private void specifyConcert() {
        String bandToPlay = specify_concert_band_nameField.getText();
        String stageToPlay = stageChoiceBox.getSelectionModel().getSelectedItem().toString();
        String dayToPlay = specifyConcertDateChoiceBox.getSelectionModel().getSelectedItem().toString();
        String timeToPlay = specifyConcertTimeField.getText() + ":00";
        Date date = java.sql.Date.valueOf(dayToPlay);
        Time time = java.sql.Time.valueOf(timeToPlay);
        SQLController.specifyConcert(date, time, bandToPlay, stageToPlay);
    }

    @FXML
    private void hireWorker() {
        Worker newWorker =  new Worker(newWorkerPersonNumberField.getText(), newWorkerNameField.getText(), newWorkerAddressField.getText());
        SQLController.hireWorker(newWorker);
    }

    @FXML
    private void logIn() {
        boolean successfullLogin = SQLController.logIn(adminUsernameField.getText(), adminPasswordField.getText());
        if (successfullLogin) {
            enableFieldsAndButtons();
        }
    }
}
