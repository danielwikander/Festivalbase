package controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is responsible for starting the main application.
 */
public class Main extends Application {
    private static Stage primaryStage;
    private static AnchorPane mainLayout;
    private static AnchorPane adminLayout;

    /**
     * Starts the main application.
     * @param primaryStage  The primary UI stage to present.
     * @throws Exception    Throws an exception if stage cannot be found.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Mörtfors Rock Festival");
        showStartView();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Presents the startView, the view users are presented with upon startup.
     * @throws IOException  Throws an exception if the view cannot be found.
     */
    public static void showStartView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/startView.fxml"));
        VBox startLayout = loader.load();
        Scene scene = new Scene(startLayout);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Presents the mainView, the view normal users are presented with that shows
     * the festival schedule and bandlist.
     */
    public static void showMainLayout() {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("/views/mainView.fxml"));
                // loader.setRoot("/views/mainView.fxml");
                mainLayout = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(mainLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        });
    }

    /**
     * Presents the administratorView, the view administrators are presented with upon startup.
     */
    public static void showAdminLayout() {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("/views/adminView.fxml"));
                adminLayout = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(adminLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        });
    }
}



