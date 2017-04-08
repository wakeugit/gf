package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.Cotisation;
import gf.model.CotisationFx;
import gf.model.Type;
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

public class AnneesWindowController {

    private ObservableList<CotisationFx> listeAnnees = FXCollections.observableArrayList();

    @FXML
    private TableView<CotisationFx> anneeTable;
    @FXML
    private TableColumn<CotisationFx, String> anneeFx;
    @FXML
    private TableColumn<CotisationFx, LocalDate> dateDebut;
    @FXML
    private TableColumn<CotisationFx, LocalDate> dateFin;

    public AnneesWindowController() {

        Response<Cotisation[]> response = BackendInterface.getCotisations(Type.ANNEE);
        if (response.getBody() != null) {
            for (Cotisation annee : response.getBody()) {
                listeAnnees.add(new CotisationFx(annee));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Année");
        }
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        anneeFx.setCellValueFactory(cellData -> cellData.getValue().getAnneeProperty());
        dateDebut.setCellValueFactory(cellData -> cellData.getValue().getDateDebutProperty());
        dateFin.setCellValueFactory(cellData -> cellData.getValue().getDateFinProperty());

        anneeTable.setItems(listeAnnees);
    }

    @FXML
    private void showAnneeDetails() {

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/annesDetails.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouvelle Annee");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AnneesDetailsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAnneesWindowCOntroller(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @FXML
    private void showAnneeDetailsModifier() {

        int selectedIndex = anneeTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            CotisationFx CotisationFx = anneeTable.getItems().get(selectedIndex);
            int keyInArrayList = listeAnnees.indexOf(CotisationFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/annesDetails.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Annee");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                //dialogStage.initOwner(getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                AnneesDetailsController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setAnnee(CotisationFx);
                controller.setKeyInArray(keyInArrayList);
                controller.setAnneesWindowCOntroller(this);

                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();

                // return controller.isOkClicked();

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            //  alert.initOwner(this.getPrimaryStage());
            alert.setTitle("Aucune ligne selectionee");
            alert.setHeaderText("Aucune ligne selectionee");
            alert.setContentText("Svp selectionnez un element dans la liste.");

            alert.showAndWait();
        }


    }


    public ObservableList<CotisationFx> getListeAnnees() {
        return listeAnnees;
    }

    public void setListeAnnees(ObservableList<CotisationFx> listeAnnees) {
        this.listeAnnees = listeAnnees;
    }


}
