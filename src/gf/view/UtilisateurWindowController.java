package gf.view;

import java.io.IOException;

import gf.model.Utilisateur;
import gf.model.UtilisateurFx;
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

public class UtilisateurWindowController {

    private ObservableList<UtilisateurFx> listeUtilisateurs = FXCollections.observableArrayList();

    @FXML
    private TableView<UtilisateurFx> utilisateurTable;
    @FXML
    private TableColumn<UtilisateurFx, String> nomUtilisateur;
    @FXML
    private TableColumn<UtilisateurFx, Integer> niveau;
    @FXML
    private TableColumn<UtilisateurFx, String> nom;
    @FXML
    private TableColumn<UtilisateurFx, String> prenom;
    @FXML
    private TableColumn<UtilisateurFx, String> poste;

    public UtilisateurWindowController() {
        listeUtilisateurs.add(new UtilisateurFx(new Utilisateur("toto", 1, "Tagne", "Paul", "admin")));
        listeUtilisateurs.add(new UtilisateurFx(new Utilisateur("papa", 2, "Magne", "Marie", "Commissaire")));
        listeUtilisateurs.add(new UtilisateurFx(new Utilisateur("maman", 3, "Pagne", "Lari", "SG")));
        listeUtilisateurs.add(new UtilisateurFx(new Utilisateur("fou", 4, "Nagne", "Lyonnel", "President")));
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

        nomUtilisateur.setCellValueFactory(cellData -> cellData.getValue().getNomUtilisateurProperty());
        niveau.setCellValueFactory(cellData -> cellData.getValue().getNiveauProperty().asObject());
        nom.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
        prenom.setCellValueFactory(cellData -> cellData.getValue().getPrenomProperty());
        poste.setCellValueFactory(cellData -> cellData.getValue().getPosteProperty());

        utilisateurTable.setItems(listeUtilisateurs);

    }

    @FXML
    private void showUtilisateurDetails() {

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/utilisateurDetails.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouveau utilisateur");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            //dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            UtilisateurDetailsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUtilisateurWindowCOntroller(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @FXML
    private void showUtilisateurDetailsModifier() {

        int selectedIndex = utilisateurTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            UtilisateurFx UtilisateurFx = utilisateurTable.getItems().get(selectedIndex);
            int keyInArrayList = listeUtilisateurs.indexOf(UtilisateurFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/utilisateurDetails.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Tontine");
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                //dialogStage.initOwner(getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                UtilisateurDetailsController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setUtilisateur(UtilisateurFx);
                controller.setKeyInArray(keyInArrayList);
                controller.setUtilisateurWindowCOntroller(this);

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


    public ObservableList<UtilisateurFx> getListeUtilisateurs() {
        return listeUtilisateurs;
    }

    public void setListeUtilisateurs(ObservableList<UtilisateurFx> listeUtilisateurs) {
        this.listeUtilisateurs = listeUtilisateurs;
    }


}
