package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.InscriptionAnnuelleFx;
import gf.model.InscriptionCotisationFx;
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
import java.time.LocalDate;

public class TontinePanelController {

    private MainAppGF mainAppGF;

    private ObservableList<InscriptionCotisationFx> listeInscritsCotisation = FXCollections.observableArrayList();
    
    @FXML
    private TableView<InscriptionCotisationFx> inscritsCotisationTable;
    @FXML
    private TableColumn<InscriptionCotisationFx, Long> idCol1;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> nomCol1;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> prenomCol1;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> cotisationCol1;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> anneeCol1;
    @FXML
    private TableColumn<InscriptionCotisationFx, Integer> numTirageCol;


    public TontinePanelController() {

     /*   Response<Membre[]> response = BackendInterface.getMembres();
        if (response.getBody() != null) {
            for (Membre membre : response.getBody()) {
                listeMembres.add(new MembreFx(membre));
            }
        } else {
            //Todo Display error message
        }
        */
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        idCol1.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        nomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        cotisationCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        numTirageCol.setCellValueFactory(cellData -> cellData.getValue().getNumeroTirageProperty().asObject());
     
        inscritsCotisationTable.setItems(listeInscritsCotisation);

    }


    @FXML
    private void actionOnclickNouvelleCotisation() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/effectuerCotisation.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Effectuer Cotisation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainAppGF.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Member into the controller.
            EffectuerCotisationController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTontinePanelController(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void actionOnclickModifierCotisation() {

        int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	InscriptionCotisationFx mbreInscritFx = inscritsCotisationTable.getItems().get(selectedIndex);
        	 int keyInArrayList = listeInscritsCotisation.indexOf(mbreInscritFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/inscriptionCotisation.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Inscription");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                EffectuerCotisationController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setTontinePanelController(this);
              //  controller.setInscriptionCotisation(mbreInscritFx);
                controller.setKeyInArray(keyInArrayList);
              

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
            alert.setTitle("Aucune ligne selectionnee");
            alert.setHeaderText("Aucune ligne selectionnee");
            alert.setContentText("Svp selectionnez un element dans la liste.");

            alert.showAndWait();
        }
    }


    @FXML
    private void actionOnclickSupprimerCotisation() {

        int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            inscritsCotisationTable.getItems().remove(selectedIndex);
            //BackendInterface.deleteMembre(inscritsAnnuelTable.getItems().get(selectedIndex).getId());
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

    

    public ObservableList<InscriptionCotisationFx> getListMembreInscritsCotisation() {
        return listeInscritsCotisation;
    }

}
