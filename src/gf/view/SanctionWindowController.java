package gf.view;

import gf.model.Service;
import gf.model.ServiceFx;
import gf.model.TypeService;
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

public class SanctionWindowController {

    private ObservableList<ServiceFx> listeSanctions = FXCollections.observableArrayList();

    @FXML
    private TableView<ServiceFx> ServiceTable;
    @FXML
    private TableColumn<ServiceFx, String> motif;

    public SanctionWindowController() {
        listeSanctions.add(new ServiceFx(new Service("Absence", TypeService.SANCTION)));
        listeSanctions.add(new ServiceFx(new Service("Retard", TypeService.SANCTION)));
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

        motif.setCellValueFactory(cellData -> cellData.getValue().getMotifProperty());

        ServiceTable.setItems(listeSanctions);

    }

    @FXML
    private void showServicesDetails() {

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/sanctionDetails.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouvelle Service");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            SanctionDetailsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSanctionWindowController(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @FXML
    private void showSanctionsDetailsModifier() {

        int selectedIndex = ServiceTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            ServiceFx ServiceFx = ServiceTable.getItems().get(selectedIndex);
            int keyInArrayList = listeSanctions.indexOf(ServiceFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/sanctionDetails.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Sanction");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                //dialogStage.initOwner(getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                SanctionDetailsController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setService(ServiceFx);
                controller.setKeyInArray(keyInArrayList);
                controller.setSanctionWindowController(this);

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


    public ObservableList<ServiceFx> getListeSanctions() {
        return listeSanctions;
    }

    public void setListeSanctions(ObservableList<ServiceFx> listeSanctions) {
        this.listeSanctions = listeSanctions;
    }


}
