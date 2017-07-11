package gf.view;

import java.io.IOException;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
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

public class AidesWindowController {

    private ObservableList<ServiceFx> listeAides = FXCollections.observableArrayList();

    @FXML
    private TableView<ServiceFx> aideTable;
    @FXML
    private TableColumn<ServiceFx, String> motif;

    public AidesWindowController() {

        loadData();
    }

    private void loadData() {
        Response<Service[]> response = BackendInterface.getServiceByType(TypeService.AIDE);
        if (response.getBody() != null) {
            listeAides.clear();
            for (Service service : response.getBody()) {
                listeAides.add(new ServiceFx(service));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Service");
        }
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

        motif.setCellValueFactory(cellData -> cellData.getValue().getMotifProperty());

        aideTable.setItems(listeAides);

    }

    @FXML
    private void showAidesDetails() {

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/aideDetails.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouvelle Aide");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            //dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AideDetailsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAideWindowController(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @FXML
    private void showAidesDetailsModifier() {

        int selectedIndex = aideTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            ServiceFx ServiceFx = aideTable.getItems().get(selectedIndex);
            int keyInArrayList = listeAides.indexOf(ServiceFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/aideDetails.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier ServiceFx");
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                //dialogStage.initOwner(getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                AideDetailsController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setService(ServiceFx);
                controller.setKeyInArray(keyInArrayList);
                controller.setAideWindowController(this);

                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();

                // return controller.isOkClicked();

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            //  alert.initOwner(this.getPrimaryStage());
            alert.setTitle("Aucune ligne selectionn�e");
            alert.setHeaderText("Aucune ligne selectionn�e");
            alert.setContentText("Svp selectionnez un �lement dans la liste.");
            alert.showAndWait();
        }

    }


    @FXML
    private void aideSupprimer() {

        int selectedIndex = aideTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            ServiceFx ServiceFx = aideTable.getItems().get(selectedIndex);
            int keyInArrayList = listeAides.indexOf(ServiceFx);

            //// TODO: 06/07/2017 ALERT THE USER BEFORE DELETE
            BackendInterface.deleteService(ServiceFx.getId());
            loadData();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            //  alert.initOwner(this.getPrimaryStage());
            alert.setTitle("Aucune ligne selectionn�e");
            alert.setHeaderText("Aucune ligne selectionn�e");
            alert.setContentText("Svp selectionnez un �lement dans la liste.");
            alert.showAndWait();
        }

    }


    public ObservableList<ServiceFx> getListeAides() {
        return listeAides;
    }

    public void setListeAides(ObservableList<ServiceFx> listeAides) {
        this.listeAides = listeAides;
    }


}
