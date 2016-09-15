package gf.view;

import gf.model.Membre;
import gf.model.MembreFx;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAppGF extends Application {

    private Stage primaryStage;
    private static BorderPane rootLayout;
    private TableView<MembreFx> membreTable;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GF");


        initRootLayout();

        // actionOnClickMembre ();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("mainWindow.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the members overview inside the root layout.
     */
    @FXML
    private void showAidesWindow() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("aidesWindow.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Aides");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

           
            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void showAnneesWindow() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("anneesWindow.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Années");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

           
            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void showEpargneWindow() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("epargneWindow.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Epargnes");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

           
            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void showTontineWindow() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("tontineWindow.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Tontines");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

           
            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    
    @FXML
    private void showSanctionWindow() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("sanctionWindow.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Sanctions");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

           
            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void showUtilisateurWindow() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("utilisateurWindow.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Utilisateurs");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

           
            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    
    public void actionOnClickMembre() {
        try {
            // Load member pane.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MembrePanelController.class.getResource("membresPanel.fxml"));
            BorderPane membres = (BorderPane) loader.load();
            MembrePanelController controller = loader.getController();
            controller.setMainAppGF(this);
            rootLayout.setCenter(membres);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionOnClickInscription() {
        try {
            // Load member pane.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MembrePanelController.class.getResource("inscriptionsPanel.fxml"));
            BorderPane membres = (BorderPane) loader.load();
//            MembrePanelController controller = loader.getController();
//            controller.setMainAppGF(this);
            rootLayout.setCenter(membres);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}