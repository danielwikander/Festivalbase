package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class StartViewController {

    @FXML
    ImageView startButton;
    @FXML
    ImageView adminButton;

    @FXML
    private void startNormalApp() throws IOException {
        System.out.println("Started normal app");
        SQLController.dbConnect();
        Main.showMainLayout();
    }

    @FXML
    private void startAdminApp() throws IOException {
        System.out.println("Started admin app");
        SQLController.dbConnect();
        Main.showAdminLayout();
    }

}
