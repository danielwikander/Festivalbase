package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * Controller for the startup view.
 */
public class StartViewController {

    @FXML
    ImageView startButton;
    @FXML
    ImageView adminButton;

    /**
     * Starts the normal app.
     * @throws IOException  Throws exception if it cannot connect to db.
     */
    @FXML
    private void startNormalApp() throws IOException {
        System.out.println("Started normal app");
        SQLController.dbConnect();
        Main.showMainLayout();
    }

    /**
     * Starts the admin app.
     * @throws IOException  Throws exception if it cannot connect to db.
     */
    @FXML
    private void startAdminApp() throws IOException {
        System.out.println("Started admin app");
        SQLController.dbConnect();
        Main.showAdminLayout();
    }
}
