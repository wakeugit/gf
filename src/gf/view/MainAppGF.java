package gf.view;

import gf.model.MembreFx;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
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
        //this.primaryStage.setAlwaysOnTop(true);


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
            dialogStage.initModality(Modality.APPLICATION_MODAL);
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
            dialogStage.setTitle("Annees");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
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
            dialogStage.initModality(Modality.APPLICATION_MODAL);
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
            dialogStage.initModality(Modality.APPLICATION_MODAL);
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
            dialogStage.initModality(Modality.APPLICATION_MODAL);
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
            dialogStage.initModality(Modality.APPLICATION_MODAL);
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
            loader.setLocation(MainAppGF.class.getResource("membresPanel.fxml"));
            BorderPane panel = (BorderPane) loader.load();
            MembrePanelController controller = loader.getController();
            controller.setMainAppGF(this);
            rootLayout.setCenter(panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeWindows(){
        System.exit(0);
    }

    public void actionOnClickInscription() {
        try {
            // Load member pane.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("inscriptionsPanel.fxml"));
            BorderPane panel = (BorderPane) loader.load();
            InscriptionsPanelController controller = loader.getController();
            controller.setMainAppGF(this);
            rootLayout.setCenter(panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionOnClickEpargne() {
        try {
            // Load member pane.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("epargnePanel.fxml"));
            BorderPane panel = (BorderPane) loader.load();
            EpargnePanelController controller = loader.getController();
            controller.setMainAppGF(this);
            rootLayout.setCenter(panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionOnClickTontine() {
        try {
            // Load member pane.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("tontinesPanel.fxml"));
            BorderPane panel = (BorderPane) loader.load();
            TontinePanelController controller = loader.getController();
            controller.setMainAppGF(this);
            rootLayout.setCenter(panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public void actionOnClickPretsEtRemb() {
        try {
            // Load member pane.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("pretsEtRembPanel.fxml"));
            BorderPane panel = (BorderPane) loader.load();
            PretsEtRembPanelController controller = loader.getController();
            controller.setMainAppGF(this);
            rootLayout.setCenter(panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionOnClickAides() {
        try {
            // Load member pane.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("aidesPanel.fxml"));
            BorderPane panel = (BorderPane) loader.load();
            AidesPanelController controller = loader.getController();
            controller.setMainAppGF(this);
            rootLayout.setCenter(panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionOnClickSanctions() {
        try {
            // Load member pane.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("sanctionsPanel.fxml"));
            BorderPane panel = (BorderPane) loader.load();
            SanctionPanelController controller = loader.getController();
            controller.setMainAppGF(this);
            rootLayout.setCenter(panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionOnClickProfilMembre() {
        try {
            // Load member pane.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("profilMembrePanel.fxml"));
            BorderPane panel = (BorderPane) loader.load();
//            MembrePanelController controller = loader.getController();
//            controller.setMainAppGF(this);
            rootLayout.setCenter(panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionOnClickCaisse() {
        try {
            // Load member pane.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("caissesPanel.fxml"));
            BorderPane panel = (BorderPane) loader.load();
//            MembrePanelController controller = loader.getController();
//            controller.setMainAppGF(this);
            rootLayout.setCenter(panel);
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