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
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;

public class PretsEtRembPanelController {

    private MainAppGF mainAppGF;

    private ObservableList<TransactionFx> suiviPrets = FXCollections.observableArrayList();
    private ObservableList<InscriptionCotisationFx> listeInscritsCotisation = FXCollections.observableArrayList();
    private ObservableList<TransactionFx> listePrets = FXCollections.observableArrayList();
    private ObservableList<TransactionFx> listeRemboursements = FXCollections.observableArrayList();
    private ObservableList<TransactionFx> suiviRemboursements = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeCotisations = FXCollections.observableArrayList();
    
    @FXML
    private TableView<InscriptionCotisationFx> inscritsCotisationTable;
    @FXML
    private TableColumn<InscriptionCotisationFx, Long> idInscription;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> nomInscription;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> prenomInscription;
    @FXML
    private TableColumn<InscriptionCotisationFx, Integer> cniInscription;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> nomCotisationInscription;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> anneeInscription;
    
    @FXML
    private TableView<TransactionFx> pretsTable;
    @FXML
    private TableColumn<TransactionFx, Long> idPret;
    @FXML
    private TableColumn<TransactionFx, String> nomPret;
    @FXML
    private TableColumn<TransactionFx, String> prenomPret;
    @FXML
    private TableColumn<TransactionFx, String> nomCotisationPret;
    @FXML
    private TableColumn<TransactionFx, String> anneePret;
    @FXML
    private TableColumn<TransactionFx, LocalDate> datePret;
    @FXML
    private TableColumn<TransactionFx, Double> montantPret;
    @FXML
    private TableColumn<TransactionFx, LocalDate> dateRembPret;
    @FXML
    private TableColumn<TransactionFx, Double> tauxPret;
    @FXML
    private TableColumn<TransactionFx, String> avaliseurPret;
    
    @FXML
    private TableView<TransactionFx> suiviPretsTable;
    @FXML
    private TableColumn<TransactionFx, LocalDate> datePretSuiviPret;
    @FXML
    private TableColumn<TransactionFx, String> nomCotisationSuiviPret;
    @FXML
    private TableColumn<TransactionFx, String> anneeSuiviPret;
    @FXML
    private TableColumn<TransactionFx, Double> montantPlace;

    
    @FXML
    private TableView<TransactionFx> pretsTable1;
    @FXML
    private TableColumn<TransactionFx, Long> idCol1;
    @FXML
    private TableColumn<TransactionFx, String> nomCol1;
    @FXML
    private TableColumn<TransactionFx, String> prenomCol1;
    @FXML
    private TableColumn<TransactionFx, String> nomCotisationCol1;
    @FXML
    private TableColumn<TransactionFx, String> anneeCol1;
    @FXML
    private TableColumn<TransactionFx, LocalDate> dateCol;
    @FXML
    private TableColumn<TransactionFx, Double> montantCol;
    @FXML
    private TableColumn<TransactionFx, LocalDate> dateRembCol;
    @FXML
    private TableColumn<TransactionFx, Double> tauxCol;

    @FXML
    private TableView<TransactionFx> rembTable;
    @FXML
    private TableColumn<TransactionFx, Long> idRemb;
    @FXML
    private TableColumn<TransactionFx, String> nomRemb;
    @FXML
    private TableColumn<TransactionFx, String> prenomRemb;
    @FXML
    private TableColumn<TransactionFx, String> nomCotisationRemb;
    @FXML
    private TableColumn<TransactionFx, String> typeRemb;
    @FXML
    private TableColumn<TransactionFx, String> anneeRemb;
    @FXML
    private TableColumn<TransactionFx, LocalDate> dateRembRemb;
    @FXML
    private TableColumn<TransactionFx, Double> montantRemb;
    @FXML
    private TableColumn<TransactionFx, Double> montantAvRemb;
    @FXML
    private TableColumn<TransactionFx, Double> penalitesRemb;
    
    @FXML
    private TableView<TransactionFx> suiviRemTable;
    @FXML
    private TableColumn<TransactionFx, LocalDate> dateRembSuiviRemb;
    @FXML
    private TableColumn<TransactionFx, String> nomCotisationSuiviRemb;
    @FXML
    private TableColumn<TransactionFx, String> anneeSuiviRemb;
    @FXML
    private TableColumn<TransactionFx, Double> montantSuiviRemb;
    @FXML
    private TableColumn<TransactionFx, Double> penalitesSuiviRemb;
    
    
    @FXML
    private ComboBox<CotisationFx> comboCotisationPret;
    @FXML
    private ComboBox<CotisationFx> comboCotisationListePret;
    @FXML
    private DatePicker dateListePret;
    @FXML
    private ComboBox<CotisationFx> comboCotisationSuiviPret;
    @FXML
    private ComboBox<CotisationFx> comboCotisationRemb;
    @FXML
    private ComboBox<CotisationFx> comboCotisationListeRemb;
    @FXML
    private DatePicker dateListeRemb;
    @FXML
    private ComboBox<CotisationFx> comboCotisationSuiviRemb;
    @FXML
    private DatePicker dateRemboursement;
    @FXML
    private DatePicker dateDuPret;

    @FXML
    private Button validerPret;
    @FXML
    private Button validerListePrets;
    @FXML
    private Button validerSuiviPrets;
    @FXML
    private Button validerRemb;
    @FXML
    private Button validerListeRemb;
    @FXML
    private Button validerSuiviRemb;

    private long dateRequest;

    private Cotisation mCotisation;

    public PretsEtRembPanelController() {


        Response<Cotisation[]> response1 = BackendInterface.getCotisationsByType(TypeCotisation.TONTINE);
        Response<Cotisation[]> response2 = BackendInterface.getCotisationsByType(TypeCotisation.EPARGNE);
        if (response1.getBody() != null || response2.getBody() != null) {
            for (Cotisation cotisation : response2.getBody()) {
                listeCotisations.add(new CotisationFx(cotisation));
            }
            for (Cotisation cotisation : response1.getBody()) {
                listeCotisations.add(new CotisationFx(cotisation));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Cotisation");
        }
    
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

        if (comboCotisationPret != null) {
            comboCotisationPret.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getnomCotisation() + " " + item.getAnnee());
                        mCotisation = new Cotisation(item);
                    }
                }
            });

            comboCotisationPret.setCellFactory(
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

            comboCotisationPret.setConverter(new StringConverter<CotisationFx>() {
                @Override
                public String toString(CotisationFx item) {
                    if (item == null) {
                        return null;
                    } else {
                        return item.getnomCotisation() + " " + item.getAnnee();
                    }
                }

                @Override
                public CotisationFx fromString(String item) {
                    return null;
                }
            });
            comboCotisationPret.setItems(listeCotisations);
        }

        if (comboCotisationListePret != null) {
            comboCotisationListePret.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getnomCotisation() + " " + item.getAnnee());
                        mCotisation = new Cotisation(item);
                    }
                }
            });

            comboCotisationListePret.setItems(listeCotisations);

            if (comboCotisationSuiviPret != null) {
                comboCotisationSuiviPret.setButtonCell(new ListCell<CotisationFx>() {
                    @Override
                    protected void updateItem(CotisationFx item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item.getnomCotisation() + " " + item.getAnnee());
                            mCotisation = new Cotisation(item);
                        }
                    }
                });

                comboCotisationSuiviPret.setItems(listeCotisations);
            }

            if (comboCotisationRemb != null) {
                comboCotisationRemb.setButtonCell(new ListCell<CotisationFx>() {
                    @Override
                    protected void updateItem(CotisationFx item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item.getnomCotisation() + " " + item.getAnnee());
                            mCotisation = new Cotisation(item);
                        }
                    }
                });

                comboCotisationRemb.setItems(listeCotisations);


            }

            if (comboCotisationListeRemb != null) {
                comboCotisationListeRemb.setButtonCell(new ListCell<CotisationFx>() {
                    @Override
                    protected void updateItem(CotisationFx item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item.getnomCotisation() + " " + item.getAnnee());
                            mCotisation = new Cotisation(item);
                        }
                    }
                });

                comboCotisationListeRemb.setItems(listeCotisations);


            }

            if (comboCotisationSuiviRemb != null) {
                comboCotisationSuiviRemb.setButtonCell(new ListCell<CotisationFx>() {
                    @Override
                    protected void updateItem(CotisationFx item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        } else {
                            setText(item.getnomCotisation() + " " + item.getAnnee());
                            mCotisation = new Cotisation(item);
                        }
                    }
                });

                comboCotisationSuiviRemb.setItems(listeCotisations);


            }
        }

        idInscription.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        nomInscription.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomInscription.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        cniInscription.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().cniProperty().asObject());
        nomCotisationInscription.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getnomCotisationProperty());
        anneeInscription.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());

        idPret.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomPret.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomPret.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        nomCotisationPret.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneePret.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        datePret.setCellValueFactory(cellData -> cellData.getValue().dateTransactionProperty());
        montantPret.setCellValueFactory(cellData -> cellData.getValue().montantTransactionProperty().asObject());
        dateRembPret.setCellValueFactory(cellData -> cellData.getValue().dateRemboursementProperty());
        tauxPret.setCellValueFactory(cellData -> cellData.getValue().tauxInteretsProperty().asObject());
        avaliseurPret.setCellValueFactory(cellData -> cellData.getValue().getAvaliseur1().nomProperty());

        datePretSuiviPret.setCellValueFactory(cellData -> cellData.getValue().dateTransactionProperty());
        nomCotisationSuiviPret.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeSuiviPret.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        montantPlace.setCellValueFactory(cellData -> cellData.getValue().montantTransactionProperty().asObject());


        idCol1.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        nomCotisationCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateTransactionProperty());
        montantCol.setCellValueFactory(cellData -> cellData.getValue().montantTransactionProperty().asObject());
        dateRembCol.setCellValueFactory(cellData -> cellData.getValue().dateRemboursementProperty());
        tauxCol.setCellValueFactory(cellData -> cellData.getValue().tauxInteretsProperty().asObject());

        dateRembSuiviRemb.setCellValueFactory(cellData -> cellData.getValue().dateTransactionProperty());
        nomCotisationSuiviRemb.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeSuiviRemb.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        montantSuiviRemb.setCellValueFactory(cellData -> cellData.getValue().montantTransactionProperty().asObject());
        penalitesSuiviRemb.setCellValueFactory(cellData -> cellData.getValue().montantPenalitesProperty().asObject());


        idRemb.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomRemb.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomRemb.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        nomCotisationRemb.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeRemb.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        dateRembRemb.setCellValueFactory(cellData -> cellData.getValue().dateTransactionProperty());
        montantRemb.setCellValueFactory(cellData -> cellData.getValue().montantTransactionProperty().asObject());
        montantAvRemb.setCellValueFactory(cellData -> cellData.getValue().montantAvanceProperty().asObject());
        typeRemb.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        penalitesRemb.setCellValueFactory(cellData -> cellData.getValue().montantPenalitesProperty().asObject());
        
        //cotisationPret;cotisationListePret;dateListePret;cotisationSuiviPret;cotisationRemb; cotisationListeRemb;dateListeRemb; cotisationSuiviRemb;

       
        inscritsCotisationTable.setItems(listeInscritsCotisation);
        pretsTable.setItems(listePrets);
        suiviPretsTable.setItems(suiviPrets);
        pretsTable1.setItems(listePrets);
        rembTable.setItems(listeRemboursements);
        suiviRemTable.setItems(suiviRemboursements);
        
    }


   
	@FXML
    private void actionOnClickValiderPreter() {
        if (mCotisation != null) {

//            FaireEmpruntController.tmpCotisation = mCotisation;

            Response<InscriptionCotisation[]> response;

            response = BackendInterface.getInscriptionCotisation(mCotisation);
            if (response.getBody() != null) {
                if (mCotisation.getTypeCotisation() == TypeCotisation.TONTINE || mCotisation.getTypeCotisation() == TypeCotisation.EPARGNE) {
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
    private void actionOnClickValiderAnnuelle() {

  /*      if (mCotisation != null) {
            Response<Transaction[]> response;

            response = BackendInterface.getTransaction(mCotisation);
            if (response.getBody() != null) {
                if (mCotisation.getType() == TypeCotisation.ANNEE) {
                    suiviPrets.clear();
                    for (Transaction inscriptionCotisation : response.getBody()) {
                        suiviPrets.add(new TransactionFx(inscriptionCotisation));
                    }
                }


            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }

*/
    }

    @FXML
    private void actionOnclickPreter() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FaireEmpruntController.tmpCotisation = mCotisation;

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/faireUnEmprunt.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Faire un emprunt");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(mainAppGF.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Member into the controller.
            FaireEmpruntController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPretsEtRembPanelController(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void actionOnclickModifierPret() {

        int selectedIndex = pretsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            TransactionFx transactionFx = pretsTable.getItems().get(selectedIndex);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FaireEmpruntController.tmpCotisation = mCotisation;

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/faireUnEmprunt.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Faire un emprunt");
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                FaireEmpruntController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setPretsEtRembPanelController(this);
                controller.setTransactionFx(transactionFx);

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
    private void actionOnclickSupprimerInscptionAnnuelle() {
/*
        int selectedIndex = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //// TODO: 20/04/2017 Add alert before delete element
            BackendInterface.deleteTransaction(inscritsAnnuelTable.getItems().get(selectedIndex).getId());

            actionOnClickValiderAnnuelle();
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainAppGF.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }*/
    }
    

    @FXML
    private void actionOnclickRembourser() {

        int selectedIndex = pretsTable1.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            try {
                FaireRemboursementController.initialTransaction = pretsTable1.getItems().get(selectedIndex);

                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/faireUnRemb.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Faire remboursement");
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                FaireRemboursementController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setPretsEtRembPanelController(this);

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
            alert.setTitle("No Selection");
            alert.setHeaderText("No Transaction Selected");
            alert.setContentText("Please select a transaction in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void actionOnclickModifierInscptionCotisation() {

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
                //InscriptionCotisationController controller = loader.getController();
                //controller.setDialogStage(dialogStage);
                //controller.setInscriptionCotisation(mbreInscritFx);
                //controller.setKeyInArray(keyInArrayList);
                //controller.setInscriptionsPanelController(this);

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
    private void actionOnclickSupprimerInscptionCotisation() {

        int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //// TODO: 20/04/2017 Add alert before delete element
            BackendInterface.deleteInscriptionCotisation(inscritsCotisationTable.getItems().get(selectedIndex).getId());
            //actionOnClickValiderCotisation();
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

    @FXML
    public void actionOnClickValiderRembourser() {
        if (mCotisation != null) {

            Response<Transaction[]> response = null;


            if (dateDuPret != null && dateDuPret.getValue() != null) {
                LocalDate dateFilter = dateDuPret.getValue();
                dateRequest = DateUtil.parseToLong(dateFilter);
                System.out.println("date request=" + dateRequest);

                if (dateRequest != -1)
                    response = BackendInterface.getTransactionByCotisationAndDateAndType(mCotisation, dateRequest, TypeTransaction.EMPRUNTER);

            } else {
                response = BackendInterface.getTransactionsForRemboursementByCotisation(mCotisation);

            }

            if (response != null && response.getBody() != null) {
                listePrets.clear();
                for (Transaction transaction : response.getBody()) {
                    listePrets.add(new TransactionFx(transaction));
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderRembourser");
            }


        }


    }

    @FXML
    public void actionOnClickValiderListeRemboursement() {
        if (mCotisation != null && dateListeRemb != null) {
            LocalDate dateFilter = dateListeRemb.getValue();
            dateRequest = DateUtil.parseToLong(dateFilter);
            System.out.println("date request=" + dateRequest);


            if (dateRequest != -1) {

                Response<Transaction[]> response;

                response = BackendInterface.getTransactionByCotisationAndDateAndType(mCotisation, dateRequest, TypeTransaction.REMBOURSER);
                if (response.getBody() != null) {
                    listeRemboursements.clear();
                    for (Transaction transaction : response.getBody()) {
                        listeRemboursements.add(new TransactionFx(transaction));
                    }

                } else {
                    // Todo Display error message
                    System.out.println("An error occured - ValiderCotisation");
                }
            }
        }


    }

    @FXML
    public void actionOnClickValiderSuiviRemboursement() {
        if (mCotisation != null) {

            Response<Transaction[]> response;

            response = BackendInterface.getTransactionByCotisationAndType(mCotisation, TypeTransaction.REMBOURSER);
            if (response.getBody() != null) {
                suiviRemboursements.clear();
                for (Transaction transaction : response.getBody()) {
                    suiviRemboursements.add(new TransactionFx(transaction));
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }


    }

    @FXML
    public void actionOnClickValiderSuvi() {
        if (mCotisation != null) {

            Response<Transaction[]> response;

            response = BackendInterface.getTransactionByCotisationAndType(mCotisation, TypeTransaction.EMPRUNTER);
            if (response.getBody() != null) {
                suiviPrets.clear();
                for (Transaction transaction : response.getBody()) {
                    suiviPrets.add(new TransactionFx(transaction));
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }
    }

    @FXML
    public void actionOnClickValiderListePret() {
        if (mCotisation != null && dateListePret != null) {
            LocalDate dateFilter = dateListePret.getValue();
            dateRequest = DateUtil.parseToLong(dateFilter);
            System.out.println("date request=" + dateRequest);

            // todo Update Combo Remboursement accordingly
//            comboCotisationRemb.setSelectionModel();

            if (dateRequest != -1) {

                Response<Transaction[]> response;

                response = BackendInterface.getTransactionByCotisationAndDateAndType(mCotisation, dateRequest, TypeTransaction.EMPRUNTER);
                if (response.getBody() != null) {
                    listePrets.clear();
                    for (Transaction transaction : response.getBody()) {
                        listePrets.add(new TransactionFx(transaction));
                    }

                } else {
                    // Todo Display error message
                    System.out.println("An error occured - ValiderCotisation");
                }
            }
        }
    }

    public MainAppGF getMainAppGF() {
        return mainAppGF;
    }

    public void setMainAppGF(MainAppGF mainAppGF) {
        this.mainAppGF = mainAppGF;
    }

    public ObservableList<TransactionFx> getListMembreInscrits() {
        return suiviPrets;
    }

    public ObservableList<InscriptionCotisationFx> getListMembreInscritsCotisation() {
        return listeInscritsCotisation;
    }


}
