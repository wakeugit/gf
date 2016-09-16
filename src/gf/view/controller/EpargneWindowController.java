package gf.view.controller;

import java.io.IOException;
import java.time.LocalDate;

import gf.model.Cotisation;
import gf.view.MainAppGF;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EpargneWindowController {

    private ObservableList<Cotisation> listeEpargne = FXCollections.observableArrayList();

    @FXML
    private TableView<Cotisation> epargneTable;
    @FXML
    private TableColumn<Cotisation, String> nomCotisation;
    @FXML
    private TableColumn<Cotisation, String> type;
    @FXML
    private TableColumn<Cotisation, LocalDate> dateDebut;
    @FXML
    private TableColumn<Cotisation, LocalDate> dateFin;
    @FXML
    private TableColumn<Cotisation, String> annee;

    public EpargneWindowController() {
        listeEpargne.add(new Cotisation("Epargne 2015", "Epargne", "01.01.2015", "13.12.2015", "2015"));
        listeEpargne.add(new Cotisation("Epargne 2016", "Epargne", "01.01.2016", "12.12.2016", "2016"));
        listeEpargne.add(new Cotisation("Epargne 2014", "Epargne", "01.01.2014", "12.12.2014", "2014"));
        listeEpargne.add(new Cotisation("Epargne 2013", "Epargne", "01.01.2013", "14.12.2013", "2013"));
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

        nomCotisation.setCellValueFactory(cellData -> cellData.getValue().getnomCotisationProperty());
        type.setCellValueFactory(cellData -> cellData.getValue().getType());
        dateDebut.setCellValueFactory(cellData -> cellData.getValue().getDateDebut());
        dateFin.setCellValueFactory(cellData -> cellData.getValue().getDateFin());
        annee.setCellValueFactory(cellData -> cellData.getValue().getAnnee());

        epargneTable.setItems(listeEpargne);

    }

    @FXML
    private void showCotisationDetails() {

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/fxml/epargneDetails.fxml"));
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
            Cotisation cotisation = epargneTable.getItems().get(selectedIndex);
            int keyInArrayList = listeEpargne.indexOf(cotisation);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/fxml/epargneDetails.fxml"));
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
                controller.setEpargne(cotisation);
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


    public ObservableList<Cotisation> getListeCotisations() {
        return listeEpargne;
    }

    public void setListeCotisations(ObservableList<Cotisation> listeCotisations) {
        this.listeEpargne = listeCotisations;
    }


}
