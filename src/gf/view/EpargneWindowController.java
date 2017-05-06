package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.Cotisation;
import gf.model.CotisationFx;
import gf.model.TypeCotisation;
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

public class EpargneWindowController {

    private ObservableList<CotisationFx> listeEpargne = FXCollections.observableArrayList();

    @FXML
    private TableView<CotisationFx> epargneTable;
    @FXML
    private TableColumn<CotisationFx, String> nomCotisation;
    @FXML
    private TableColumn<CotisationFx, String> type;
    @FXML
    private TableColumn<CotisationFx, LocalDate> dateDebut;
    @FXML
    private TableColumn<CotisationFx, LocalDate> dateFin;
    @FXML
    private TableColumn<CotisationFx, String> annee;

    public EpargneWindowController() {

        Response<Cotisation[]> response = BackendInterface.getCotisationsByType(TypeCotisation.EPARGNE);
        if (response.getBody() != null) {
            for (Cotisation cotisation : response.getBody()) {
                listeEpargne.add(new CotisationFx(cotisation));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Epargne");
        }
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

        nomCotisation.setCellValueFactory(cellData -> cellData.getValue().getnomCotisationProperty());
        type.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        dateDebut.setCellValueFactory(cellData -> cellData.getValue().getDateDebutProperty());
        dateFin.setCellValueFactory(cellData -> cellData.getValue().getDateFinProperty());
        annee.setCellValueFactory(cellData -> cellData.getValue().getAnneeProperty());

        epargneTable.setItems(listeEpargne);

    }

    @FXML
    private void showCotisationDetails() {

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/epargneDetails.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouvelle Epargne");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EpargneDetailsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEpargneWindowCOntroller(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @FXML
    private void showCotisationDetailsModifier() {

        int selectedIndex = epargneTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            CotisationFx cotisationFx = epargneTable.getItems().get(selectedIndex);
            int keyInArrayList = listeEpargne.indexOf(cotisationFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/epargneDetails.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Epargne");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                //dialogStage.initOwner(getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                EpargneDetailsController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setEpargne(cotisationFx);
                controller.setKeyInArray(keyInArrayList);
                controller.setEpargneWindowCOntroller(this);

                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();

                // return controller.isOkClicked();

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            //  alert.initOwner(this.getPrimaryStage());
            alert.setTitle("Aucune ligne selection�e");
            alert.setHeaderText("Aucune ligne selection�e");
            alert.setContentText("Svp selectionnez un �lement dans la liste.");

            alert.showAndWait();
        }


    }


    public ObservableList<CotisationFx> getListeCotisations() {
        return listeEpargne;
    }

    public void setListeCotisations(ObservableList<CotisationFx> listeCotisations) {
        this.listeEpargne = listeCotisations;
    }


}
