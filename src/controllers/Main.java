package controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;
    //private static BorderPane mainLayout;
    private static AnchorPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Mörtfors Rock Festival");
        showStartView();
        /* oldstuff
        Parent root = FXMLLoader.load(getClass().getResource("views/startView.fxml"));
        primaryStage.setTitle("Mörtfors Rock Festival");
        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.setResizable(false);
        primaryStage.show();
        */
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void showStartView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/startView.fxml"));
        VBox startLayout = loader.load();
        Scene scene = new Scene(startLayout);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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

    /*
    public static void showAdminLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/adminView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void showDieselTent() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/dieselTentView.fxml"));
        BorderPane dieselTentLayout = loader.load();
        mainLayout.setCenter(dieselTentLayout);
    }

    public static void showMallorcaView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/mallorcaView.fxml"));
        BorderPane mallorcaLayout = loader.load();
        mainLayout.setCenter(mallorcaLayout);
    }

    public static void showTheForumView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/views/theForumView.fxml"));
        BorderPane theForumLayout = loader.load();
        mainLayout.setCenter(theForumLayout);
    }
    */
}



