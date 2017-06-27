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
    private ObservableList<TransactionFx> listeTontinesBeneficiables = FXCollections.observableArrayList();

    @FXML
    private TableView<TransactionFx> cotisations;
    @FXML
    private TableColumn<TransactionFx, Long> idCol;
    @FXML
    private TableColumn<TransactionFx, LocalDate> dateCol;
    @FXML
    private TableColumn<TransactionFx, String> cotisationCol;
    @FXML
    private TableColumn<TransactionFx, String> typeCol;
    @FXML
    private TableColumn<TransactionFx, String> anneeCol;
    @FXML
    private TableColumn<TransactionFx, Double> montantCol;


    // pour Tontineur
    private ObservableList<TransactionFx> listeDesTontineur = FXCollections.observableArrayList();

    @FXML
    private TableView<TransactionFx> tontineurs;
    @FXML
    private TableColumn<TransactionFx, Long> idTontineur;
    @FXML
    private TableColumn<TransactionFx, String> nomMembreTontineur;
    @FXML
    private TableColumn<TransactionFx, String> prenomMembreTontineur;
    @FXML
    private TableColumn<TransactionFx, LocalDate> dateOperationTontineur;
    @FXML
    private TableColumn<TransactionFx, Double> montantTontineur;


    // pour Beneficiaire
    private ObservableList<TransactionFx> listeDesBeneficiares = FXCollections.observableArrayList();

    @FXML
    private TableView<TransactionFx> tableBeneficiaire;
    @FXML
    private TableColumn<TransactionFx, String> nomMembre21;
    @FXML
    private TableColumn<TransactionFx, String> prenomMembre21;
    @FXML
    private TableColumn<TransactionFx, LocalDate> dateOpBen;
    @FXML
    private TableColumn<TransactionFx, Double> montantPlace;
    @FXML
    private TableColumn<TransactionFx, Double> montantRetenu;
    @FXML
    private TableColumn<TransactionFx, Double> interet;
    @FXML
    private TableColumn<TransactionFx, Double> montantBeneficie;


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
    private DatePicker dateBeneficier;

    @FXML
    private Button validerEffectuer;

    @FXML
    private Button validerTontineur;

    @FXML
    private Button validerBeneficiaire;

    @FXML
    private Button validerBeneficier;

    private Cotisation mCotisation;

    private long dateRequest;


    public TontinePanelController() {

//        comboBeneficiaire.setVisible(false);

        Response<Cotisation[]> response = BackendInterface.getCotisationsByType(TypeCotisation.TONTINE);
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
        if (comboEffectuer != null) {
            comboEffectuer.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mCotisation = new Cotisation(item);
                        setText(item.getnomCotisation() + " " + item.getAnnee());
                    }
                }
            });

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
                                        setText(item.getnomCotisation() + " " + item.getAnnee());
                                    }
                                }
                            };
                            return cell;
                        }
                    });
            comboEffectuer.setItems(listeTontines);
        }

        if (comboTontineur != null) {
            comboTontineur.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mCotisation = new Cotisation(item);
                        setText(item.getnomCotisation() + " " + item.getAnnee());
                    }
                }
            });

            comboTontineur.setCellFactory(
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
                                        setText(item.getnomCotisation() + " " + item.getAnnee());
                                    }
                                }
                            };
                            return cell;
                        }
                    });

            comboTontineur.setItems(listeTontines);
        }

        if (comboBeneficiaire != null) {
            comboBeneficiaire.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mCotisation = new Cotisation(item);
                        setText(item.getnomCotisation() + " " + item.getAnnee());
                    }
                }
            });

            comboBeneficiaire.setCellFactory(
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
                                        setText(item.getnomCotisation() + " " + item.getAnnee());
                                    }
                                }
                            };
                            return cell;
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
        idCol.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getIdProperty().asObject());
        cotisationCol.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateTransactionProperty());
        anneeCol.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        typeCol.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getTypeProperty());
        montantCol.setCellValueFactory(cellData -> cellData.getValue().montantTransactionProperty().asObject());


        cotisations.setItems(listeTontinesBeneficiables);

        //Tontineur
        idTontineur.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        dateOperationTontineur.setCellValueFactory(cellData -> cellData.getValue().dateTransactionProperty());
        montantTontineur.setCellValueFactory(cellData -> cellData.getValue().montantTransactionProperty().asObject());
        nomMembreTontineur.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomMembreTontineur.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());

        tontineurs.setItems(listeDesTontineur);

        //Beneficiare
        nomMembre21.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomMembre21.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        dateOpBen.setCellValueFactory(cellData -> cellData.getValue().dateTransactionProperty());
        montantPlace.setCellValueFactory(cellData -> cellData.getValue().montantPlaceProperty().asObject());
        montantRetenu.setCellValueFactory(cellData -> cellData.getValue().montantRetenuProperty().asObject());
        montantBeneficie.setCellValueFactory(cellData -> cellData.getValue().montantBeneficieProperty().asObject());
        interet.setCellValueFactory(cellData -> cellData.getValue().tauxInteretsProperty().asObject());

        tableBeneficiaire.setItems(listeDesBeneficiares);

    }

    @FXML
    private void actionOnClickValiderEffectuer() {
        if (mCotisation != null) {

            EffectuerCotisationController.tmpCotisation = mCotisation;

            Response<InscriptionCotisation[]> response;

            response = BackendInterface.getInscriptionCotisation(mCotisation);
            if (response.getBody() != null) {
                if (mCotisation.getTypeCotisation() == TypeCotisation.TONTINE) {
                    listeInscritsCotisation.clear();
                    for (InscriptionCotisation inscriptionCotisation : response.getBody()) {
                        listeInscritsCotisation.add(new InscriptionCotisationFx(inscriptionCotisation));
                    }
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }
    }


    @FXML
    private void actionOnClickValiderTontineur() {
        if (mCotisation != null && dateTontineur != null) {
            LocalDate dateFilter = dateTontineur.getValue();
            dateRequest = DateUtil.parseToLong(dateFilter);
            System.out.println("date request=" + dateRequest);

            if (dateRequest != -1) {


                Response<Transaction[]> response;

                response = BackendInterface.getTransactionByCotisationAndDateAndType(mCotisation, dateRequest, TypeTransaction.TONTINER);
                if (response.getBody() != null) {
                    listeDesTontineur.clear();
                    for (Transaction transaction : response.getBody()) {
                        listeDesTontineur.add(new TransactionFx(transaction));
                    }

                } else {
                    // Todo Display error message
                    System.out.println("An error occured - ValiderCotisation");
                }
            }
        }
    }

    @FXML
    private void actionOnClickValiderBeneficiaire() {
        if (mCotisation != null && dateTontineur != null) {
            LocalDate dateFilter = dateBeneficiaire.getValue();
            dateRequest = DateUtil.parseToLong(dateFilter);
            System.out.println("date request=" + dateRequest);

            if (dateRequest != -1) {


                Response<Transaction[]> response;

                response = BackendInterface.getTransactionByCotisationAndDateAndType(mCotisation, dateRequest, TypeTransaction.BENEFICIER);
                if (response.getBody() != null) {
                    listeDesBeneficiares.clear();
                    for (Transaction transaction : response.getBody()) {
                        listeDesBeneficiares.add(new TransactionFx(transaction));
                    }

                } else {
                    // Todo Display error message
                    System.out.println("An error occured - ValiderCotisation");
                }
            }
        }
    }


    @FXML
    private void actionOnClickValiderBeneficer() {
        if (dateBeneficier != null && dateBeneficier.getValue() != null) {
            LocalDate dateFilter = dateBeneficier.getValue();
            dateRequest = DateUtil.parseToLong(dateFilter);
            System.out.println("date request=" + dateRequest);

            if (dateRequest != -1) {
                Response<Transaction[]> response;

                response = BackendInterface.getWinneableTontinesByDate(dateRequest);
                if (response.getBody() != null) {
                    listeTontinesBeneficiables.clear();
                    for (Transaction transaction : response.getBody()) {
                        listeTontinesBeneficiables.add(new TransactionFx(transaction));
                    }

                } else {
                    // Todo Display error message
                    System.out.println("An error occured - ValiderCotisation");
                }
            }
        }
    }


    @FXML
    private void actionOnclickNouvelleCotisation() {
        try {
            int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                InscriptionCotisationFx incriptCotisationFx = inscritsCotisationTable.getItems().get(selectedIndex);
                EffectuerCotisationController.tmpCotisation = new Cotisation(incriptCotisationFx.getCotisationFx());
                EffectuerCotisationController.tmpMembre = incriptCotisationFx;
            }
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/effectuerCotisation.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Effectuer Cotisation");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
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

            int selectedIndex = cotisations.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                TransactionFx transactionFx = cotisations.getItems().get(selectedIndex);
                BeneficierCotisationController.tmpCotisation = new Cotisation(transactionFx.getCotisationFx());
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/beneficierCotisation.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Beneficier Cotisation");
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                BeneficierCotisationController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setTontinePanelController(this);
                controller.setCotisationFx(transactionFx);

                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();

                // return controller.isOkClicked();
            } else {
                // Nothing selected.
                Alert alert = new Alert(AlertType.WARNING);
                alert.initOwner(mainAppGF.getPrimaryStage());
                alert.setTitle("Attention");
                alert.setHeaderText("Aucune ligne selectionée");
                alert.setContentText("Svp selectionnez un élement dans la liste.");

                alert.showAndWait();
            }

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
                dialogStage.initModality(Modality.APPLICATION_MODAL);
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
