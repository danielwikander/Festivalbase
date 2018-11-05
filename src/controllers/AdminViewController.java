package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;

import java.sql.Date;
import java.sql.Time;

/**
 * Controls the Main View.
 */
public class AdminViewController {

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
    Button bookBandButton;

    // Assign a contact stuff
    @FXML
    TextField assign_contact_person_idField;
    @FXML
    TextField assign_band_nameField;
    @FXML
    Button assignContactPersonButton;

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
    Button specifyConcertButton;

    // Add new bandmember stuff
    @FXML
    TextField newBandMemberNameField;
    @FXML
    TextField newBandMemberInfoField;
    @FXML
    Button addBandMemberButton;

    // Hire worker stuff
    @FXML
    TextField newWorkerNameField;
    @FXML
    TextField newWorkerPersonNumberField;
    @FXML
    TextField newWorkerAddressField;
    @FXML
    Button hireWorkerButton;

    // Login stuff
    @FXML
    TextField adminUsernameField;
    @FXML
    PasswordField adminPasswordField;
    @FXML
    Button loginButton;
    @FXML
    Label loginLabel;

    // ---- Security & worker connections ---- //
    @FXML
    TableView securityScheduleTable;
    @FXML
    TableView workerContactTable;
    @FXML
    ChoiceBox securityStageChoiceBox;
    @FXML
    TextField securityTimeField;
    @FXML
    ChoiceBox securityDateChoiceBox;
    @FXML
    TextField securityPersonNumberField;
    @FXML
    Button securityAddToScheduleButton;

    // ---- Worker table ---- //
    @FXML
    TableView workerTable;

    /**
     * This method is called when the view is initialized.
     * It connects to the database and populates the UI with information.
     */
    @FXML
    public void initialize() {
        SQLController.dbConnect();
        setUpChoiceBoxes();
        populateSecuritySchedule();
        populateWorkerContactTable();
        populateWorkerTable();
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

        // Security fields and buttons
        securityStageChoiceBox.setDisable(false);
        securityTimeField.setDisable(false);
        securityDateChoiceBox.setDisable(false);
        securityPersonNumberField.setDisable(false);
        securityAddToScheduleButton.setDisable(false);

        // New bandmember fields and buttons
        newBandMemberInfoField.setDisable(false);
        newBandMemberNameField.setDisable(false);
        addBandMemberButton.setDisable(false);
    }

    private void setUpChoiceBoxes() {
        specifyConcertDateChoiceBox.getItems().addAll("2018-05-10", "2018-05-11", "2018-05-12");
        stageChoiceBox.getItems().addAll("Mallorca", "The Diesel Tent", "The Forum");
        securityDateChoiceBox.getItems().addAll("2018-05-10", "2018-05-11", "2018-05-12");
        securityStageChoiceBox.getItems().addAll("Mallorca", "The Diesel Tent", "The Forum");
    }

    // ---- Administrator tab ---- //

    /**
     * Books a band
     */
    @FXML
    private void bookBand() {
        Band bandToAdd;
        if (band_contact_person_idField.getText() == null) {
            bandToAdd = new Band(band_nameField.getText(),
                    band_country_of_originField.getText(),
                    band_infoField.getText(),
                    null);
        } else {
            bandToAdd = new Band(band_nameField.getText(),
                    band_country_of_originField.getText(),
                    band_infoField.getText(),
                    band_contact_person_idField.getText());
        }
        SQLController.addBand(bandToAdd);
    }

    /**
     * Assigns a contact person to a band.
     */
    @FXML
    private void assignContactPerson() {
        String workerToAssign = assign_contact_person_idField.getText();
        String bandToAssign = assign_band_nameField.getText();
        SQLController.assignContactPerson(bandToAssign, workerToAssign);
    }

    /**
     * Adds a concert to the database.
     */
    @FXML
    private void specifyConcert() {
        String bandToPlay = specify_concert_band_nameField.getText();
        String stageToPlay = stageChoiceBox.getSelectionModel().getSelectedItem().toString();
        String dayToPlay = specifyConcertDateChoiceBox.getSelectionModel().getSelectedItem().toString();
        String timeToPlay = specifyConcertTimeField.getText() + ":00";
        Date date = Date.valueOf(dayToPlay);
        Time time = Time.valueOf(timeToPlay);
        SQLController.specifyConcert(date, time, bandToPlay, stageToPlay);
    }

    /**
     * Adds a worker to the database.
     */
    @FXML
    private void hireWorker() {
        Worker newWorker = new Worker(newWorkerPersonNumberField.getText(),
                newWorkerNameField.getText(),
                newWorkerAddressField.getText());
        SQLController.hireWorker(newWorker);
    }

    /**
     * Adds a bandmember to the database.
     */
    @FXML
    private void addBandMember() {
        SQLController.addBandMember(newBandMemberNameField.getText(), newBandMemberInfoField.getText());
    }

    /**
     * Attemts to log in as admin.
     */
    @FXML
    private void logIn() {
        boolean successfullLogin = SQLController.logIn(adminUsernameField.getText(), adminPasswordField.getText());
        if (successfullLogin) {
            enableFieldsAndButtons();
            loginLabel.setStyle("-fx-background-color:green;");
            loginButton.setStyle("-fx-border-color:green;");
        } else {
            loginLabel.setStyle("-fx-background-color:red;");
            loginButton.setStyle("-fx-border-color:red;");
        }
    }

    // ---- SECURITY TABLE ---- //
    @FXML
    private void populateSecuritySchedule() {
        SecuritySchedule securitySchedule = SQLController.getSecuritySchedule();

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(80);
        dateCol.setCellValueFactory(new PropertyValueFactory<SecuritySchedule, String>("date"));

        TableColumn timeCol = new TableColumn("Time");
        timeCol.setMinWidth(80);
        timeCol.setCellValueFactory(new PropertyValueFactory<SecuritySchedule, String>("time"));

        TableColumn sceneCol = new TableColumn("Scene");
        sceneCol.setMinWidth(120);
        sceneCol.setCellValueFactory(new PropertyValueFactory<SecuritySchedule, String>("scene"));

        TableColumn responsibleWorkerNameCol = new TableColumn("Name of responsible worker");
        responsibleWorkerNameCol.setMinWidth(150);
        responsibleWorkerNameCol.setCellValueFactory(new PropertyValueFactory<SecuritySchedule, String>("worker_name"));

        TableColumn responsibleWorkerPersonalNumberCol = new TableColumn("Worker personal number");
        responsibleWorkerPersonalNumberCol.setMinWidth(150);
        responsibleWorkerPersonalNumberCol.setCellValueFactory(new PropertyValueFactory<SecuritySchedule, String>("worker_personal_number"));

        securityScheduleTable.setItems(securitySchedule.getSchedule());
        securityScheduleTable.getColumns().addAll(dateCol, timeCol, sceneCol, responsibleWorkerNameCol, responsibleWorkerPersonalNumberCol);

    }
    // ---- WORKER TABLE ---- //
    @FXML
    private void populateWorkerTable() {
        ObservableList<Worker> workers = SQLController.getWorkers();

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(80);
        nameCol.setCellValueFactory(new PropertyValueFactory<SecuritySchedule, String>("name"));

        TableColumn addressCol = new TableColumn("Address");
        addressCol.setMinWidth(80);
        addressCol.setCellValueFactory(new PropertyValueFactory<SecuritySchedule, String>("address"));

        TableColumn personnbrCol = new TableColumn("Personalnumber");
        personnbrCol.setMinWidth(120);
        personnbrCol.setCellValueFactory(new PropertyValueFactory<SecuritySchedule, String>("person_number"));

        workerTable.setItems(workers);
        workerTable.getColumns().addAll(nameCol, addressCol, personnbrCol);
    }

    // ---- CONTACT TABLE ---- //
    @FXML
    private void populateWorkerContactTable() {
        ResponsibilityTable responsibilityTable = SQLController.getResponsibilityCount();

        TableColumn nameCol = new TableColumn("Name of worker");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<ResponsibilityTable, String>("workerName"));

        TableColumn personNbrCol = new TableColumn("Worker personnumber");
        personNbrCol.setMinWidth(80);
        personNbrCol.setCellValueFactory(
                new PropertyValueFactory<ResponsibilityTable, String>("workerPersonNumber"));

        TableColumn nbrConCol = new TableColumn("Number of contact connections");
        nbrConCol.setMinWidth(50);
        nbrConCol.setCellValueFactory(
                new PropertyValueFactory<SecuritySchedule, String>("responsibilityCount"));

        workerContactTable.setItems(responsibilityTable.getRowList());
        workerContactTable.getColumns().addAll(nameCol, personNbrCol, nbrConCol);
    }

    @FXML
    private void addToSecuritySchedule() {
        SQLController.addToSecurity(securityDateChoiceBox.getSelectionModel().getSelectedItem().toString(),
                securityTimeField.getText(),
                securityStageChoiceBox.getSelectionModel().getSelectedItem().toString(),
                securityPersonNumberField.getText());
    }
}
