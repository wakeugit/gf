package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
import gf.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;

public class TontinePanelController {

    private MainAppGF mainAppGF;
    // pour effectuer cotisation
    private ObservableList<InscriptionCotisationFx> listeInscritsCotisation = FXCollections.observableArrayList();
    private ObservableList<MembreFx> listeTontineurs = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeTontines = FXCollections.observableArrayList();

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

 // pour Beneficier cotisation
    private ObservableList<EffectuerFx> listesTontines_Membres = FXCollections.observableArrayList();
    
    @FXML
    private TableView<EffectuerFx> cotisations;
    @FXML
    private TableColumn<EffectuerFx, Long> idCol;
    @FXML
    private TableColumn<EffectuerFx, LocalDate> dateCol;
    @FXML
    private TableColumn<EffectuerFx, String> cotisationCol;
    @FXML
    private TableColumn<EffectuerFx, String> typeCol;
    @FXML
    private TableColumn<EffectuerFx, String> anneeCol;
    @FXML
    private TableColumn<EffectuerFx, Integer> montantCol;

    @FXML
    private ComboBox<CotisationFx> comboEffectuer;

    @FXML
    private ComboBox<CotisationFx> comboTontineur;

    @FXML
    private ComboBox<CotisationFx> comboBeneficiaire;

    @FXML
    private DatePicker dateTontineur;

    @FXML
    private DatePicker dateBeneficiaire;

    @FXML
    private Button validerEffectuer;

    @FXML
    private Button validerTontineur;

    @FXML
    private Button validerBeneficiaire;

    private Cotisation mCotisation;

    private String dateRequest;


    public TontinePanelController() {

        Response<Cotisation[]> response = BackendInterface.getCotisations(TypeCotisation.TONTINE);
        if (response.getBody() != null) {
            for (Cotisation cotisation : response.getBody()) {
                listeTontines.add(new CotisationFx(cotisation));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Tontine");
        }
    }


    @FXML
    private void initialize() {
        if(comboEffectuer != null){
            comboEffectuer.setButtonCell( new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mCotisation = new Cotisation(item);
                        setText(item.getnomCotisation());
                    }
                }
            });
/*
            comboEffectuer.setCellFactory(
                    new Callback<ListView<CotisationFx>, ListCell<CotisationFx>>() {

                        @Override
                        public ListCell<CotisationFx> call(ListView<CotisationFx> arg0) {
                            ListCell<CotisationFx> cell = new ListCell<CotisationFx>() {
                                @Override
                                protected void updateItem(CotisationFx item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setText("");
                                    } else {
                                        setText(item.getnomCotisation());
                                    }
                                }
                            };
                            return cell;
                        }


                    });*/
            comboEffectuer.setItems(listeTontines);
        }

        if(comboTontineur != null){
            comboTontineur.setButtonCell( new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mCotisation = new Cotisation(item);
                        setText(item.getnomCotisation());
                    }
                }
            });

            comboTontineur.setItems(listeTontines);
        }

        if(comboBeneficiaire != null){
            comboBeneficiaire.setButtonCell( new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mCotisation = new Cotisation(item);
                        setText(item.getnomCotisation());
                    }
                }
            });

            comboBeneficiaire.setItems(listeTontines);
        }


        // Effectuer cotisation.
        idCol1.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        nomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        cotisationCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        numTirageCol.setCellValueFactory(cellData -> cellData.getValue().getNumeroTirageProperty().asObject());
     
        inscritsCotisationTable.setItems(listeInscritsCotisation);
        
        //Beneficier cotisation
        idCol.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        cotisationCol.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        typeCol.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getTypeProperty());
        anneeCol.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        montantCol.setCellValueFactory(cellData -> cellData.getValue().getMontantProperty().asObject());

        cotisations.setItems(listesTontines_Membres);

    }

    @FXML
    private void actionOnClickValiderEffectuer(){
        if (mCotisation != null) {


                // TODO: 27/04/2017 Update the view according the request result.

//            Response<InscriptionCotisation[]> response;
//
//            response = BackendInterface.getTransactionByCotisationAndDate(mCotisation, dateRequest);
//            if (response.getBody() != null) {
//                if (mCotisation.getType() == Type.TONTINE) {
//                    listeInscritsCotisation.clear();
//                    for (InscriptionCotisation inscriptionCotisation : response.getBody()) {
//                        listeInscritsCotisation.add(new InscriptionCotisationFx(inscriptionCotisation));
//                    }
//                }
//
//            } else {
//                // Todo Display error message
//                System.out.println("An error occured - ValiderCotisation");
//            }
            }
    }


    @FXML
    private void actionOnClickValiderTontineur(){
        if (mCotisation != null && dateTontineur != null) {
            LocalDate dateFilter = dateTontineur.getValue();
            dateRequest = DateUtil.format(dateFilter);
            System.out.println("date request="+ dateRequest);

            if (dateRequest!=null && !dateRequest.isEmpty()) {


                // TODO: 27/04/2017 Update the view according the request result.

//            Response<InscriptionCotisation[]> response;
//
//            response = BackendInterface.getTransactionByCotisationAndDate(mCotisation, dateRequest);
//            if (response.getBody() != null) {
//                if (mCotisation.getType() == Type.TONTINE) {
//                    listeInscritsCotisation.clear();
//                    for (InscriptionCotisation inscriptionCotisation : response.getBody()) {
//                        listeInscritsCotisation.add(new InscriptionCotisationFx(inscriptionCotisation));
//                    }
//                }
//
//            } else {
//                // Todo Display error message
//                System.out.println("An error occured - ValiderCotisation");
//            }
            }
        }
    }

    @FXML
    private void actionOnClickValiderBeneficiaire(){
        if (mCotisation != null && dateBeneficiaire != null) {
            LocalDate dateFilter = dateBeneficiaire.getValue();
            dateRequest = DateUtil.format(dateFilter);
            System.out.println("date request="+ dateRequest);

            if (dateRequest!=null && !dateRequest.isEmpty()) {


                // TODO: 27/04/2017 Update the view according the request result.

//            Response<InscriptionCotisation[]> response;
//
//            response = BackendInterface.getTransactionByCotisationAndDate(mCotisation, dateRequest);
//            if (response.getBody() != null) {
//                if (mCotisation.getType() == Type.TONTINE) {
//                    listeInscritsCotisation.clear();
//                    for (InscriptionCotisation inscriptionCotisation : response.getBody()) {
//                        listeInscritsCotisation.add(new InscriptionCotisationFx(inscriptionCotisation));
//                    }
//                }
//
//            } else {
//                // Todo Display error message
//                System.out.println("An error occured - ValiderCotisation");
//            }
            }
        }
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
    private void actionOnclickNouveauBeneficiaire() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/beneficierCotisation.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Beneficier Cotisation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainAppGF.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Member into the controller.
            BeneficierCotisationController controller = loader.getController();
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
