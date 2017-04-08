package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.Membre;
import gf.model.MembreFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MembrePanelController {

    private MainAppGF mainAppGF;

    private ObservableList<MembreFx> listeMembres = FXCollections.observableArrayList();

    @FXML
    private TableView<MembreFx> membreTable;
    @FXML
    private TableColumn<MembreFx, String> photoCol;
    @FXML
    private TableColumn<MembreFx, String> nomCol;
    @FXML
    private TableColumn<MembreFx, String> prenomCol;
    @FXML
    private TableColumn<MembreFx, Integer> telephoneCol;
    @FXML
    private TableColumn<MembreFx, String> adresseCol;
    @FXML
    private TableColumn<MembreFx, Integer> cniCol;


    public MembrePanelController() {

        Response<Membre[]> response = BackendInterface.getMembres();
        if (response.getBody() != null) {
            for (Membre membre : response.getBody()) {
                listeMembres.add(new MembreFx(membre));
            }
        } else {
            //Todo Display error message
        	System.out.println("An error occured - Membres");
        }
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

        photoCol.setCellValueFactory(cellData -> cellData.getValue().photoProperty());
        nomCol.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        prenomCol.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        telephoneCol.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty().asObject());
        adresseCol.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());
        cniCol.setCellValueFactory(cellData -> cellData.getValue().cniProperty().asObject());

        membreTable.setItems(listeMembres);

    }

    @FXML
    private void showMemberDetailsDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/membreDetails.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouveau Membre");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainAppGF.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Member into the controller.
            MembreDetailsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMembrePanelController(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void actionOnclickModifier() {

        int selectedIndex = membreTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            MembreFx mbreFx = membreTable.getItems().get(selectedIndex);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/membreDetails.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Membre");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                MembreDetailsController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setMembre(mbreFx);
                controller.setMembrePanelController(this);

                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();

                // return controller.isOkClicked();

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainAppGF.getPrimaryStage());
            alert.setTitle("Aucune ligne selection�e");
            alert.setHeaderText("Aucune ligne selection�e");
            alert.setContentText("Svp selectionnez un �lement dans la liste.");

            alert.showAndWait();
        }
    }


    @FXML
    private void actionOnclickSupprimer() {

        int selectedIndex = membreTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            membreTable.getItems().remove(selectedIndex);
            BackendInterface.deleteMembre(membreTable.getItems().get(selectedIndex).getId());
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainAppGF.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }


    public MainAppGF getMainAppGF() {
        return mainAppGF;
    }

    public void setMainAppGF(MainAppGF mainAppGF) {
        this.mainAppGF = mainAppGF;
    }

    public ObservableList<MembreFx> getListMembre() {
        return listeMembres;
    }


}
