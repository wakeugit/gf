package gf.view;

import java.io.IOException;

import gf.model.Utilisateur;
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

    private ObservableList<Utilisateur> listeUtilisateurs = FXCollections.observableArrayList();

    @FXML
    private TableView<Utilisateur> utilisateurTable;
    @FXML
    private TableColumn<Utilisateur, String> nomUtilisateur;
    @FXML
    private TableColumn<Utilisateur, Integer> niveau;
    @FXML
    private TableColumn<Utilisateur, String> nom;
    @FXML
    private TableColumn<Utilisateur, String> prenom;
    @FXML
    private TableColumn<Utilisateur, String> poste;

    public UtilisateurWindowController() {
        listeUtilisateurs.add(new Utilisateur("toto", 1, "Tagne", "Paul", "admin"));
        listeUtilisateurs.add(new Utilisateur("papa", 2, "Magne", "Marie", "Commissaire"));
        listeUtilisateurs.add(new Utilisateur("maman", 3, "Pagne", "Lari", "SG"));
        listeUtilisateurs.add(new Utilisateur("fou", 4, "Nagne", "Lyonnel", "President"));
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
            dialogStage.initModality(Modality.WINDOW_MODAL);
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
            Utilisateur Utilisateur = utilisateurTable.getItems().get(selectedIndex);
            int keyInArrayList = listeUtilisateurs.indexOf(Utilisateur);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/utilisateurDetails.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Tontine");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                //dialogStage.initOwner(getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                UtilisateurDetailsController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setUtilisateur(Utilisateur);
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


    public ObservableList<Utilisateur> getListeUtilisateurs() {
        return listeUtilisateurs;
    }

    public void setListeUtilisateurs(ObservableList<Utilisateur> listeUtilisateurs) {
        this.listeUtilisateurs = listeUtilisateurs;
    }


}
