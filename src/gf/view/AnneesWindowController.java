package gf.view;

import java.io.IOException;
import java.time.LocalDate;

import gf.model.Annee;
import gf.model.AnneeFx;
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

public class AnneesWindowController {

    private ObservableList<AnneeFx> listeAnnees = FXCollections.observableArrayList();

    @FXML
    private TableView<AnneeFx> anneeTable;
    @FXML
    private TableColumn<AnneeFx, String> anneeFx;
    @FXML
    private TableColumn<AnneeFx, LocalDate> dateDebut;
    @FXML
    private TableColumn<AnneeFx, LocalDate> dateFin;

    public AnneesWindowController() {
        listeAnnees.add(new AnneeFx(new Annee("2015", "01.01.2015", "13.12.2015")));
        listeAnnees.add(new AnneeFx(new Annee("2016", "01.01.2016", "12.12.2016")));
        listeAnnees.add(new AnneeFx(new Annee("2014", "01.01.2014", "12.12.2014")));
        listeAnnees.add(new AnneeFx(new Annee("2013", "01.01.2013", "14.12.2013")));
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
            AnneeFx anneeFx = anneeTable.getItems().get(selectedIndex);
            int keyInArrayList = listeAnnees.indexOf(anneeFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/annesDetails.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Ann�e");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                //dialogStage.initOwner(getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                AnneesDetailsController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setAnnee(anneeFx);
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
            alert.setTitle("Aucune ligne selection�e");
            alert.setHeaderText("Aucune ligne selection�e");
            alert.setContentText("Svp selectionnez un �lement dans la liste.");

            alert.showAndWait();
        }


    }


    public ObservableList<AnneeFx> getListeAnnees() {
        return listeAnnees;
    }

    public void setListeAnnees(ObservableList<AnneeFx> listeAnnees) {
        this.listeAnnees = listeAnnees;
    }


}
