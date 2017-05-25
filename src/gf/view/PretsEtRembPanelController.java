package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
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
    

    private Cotisation mCotisation;

    public PretsEtRembPanelController() {


        Response<Cotisation[]> response1 = BackendInterface.getCotisationsByType(TypeCotisation.TONTINE);
        if (response1.getBody() != null) {
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
    	
    	if(comboCotisationPret != null){
    		comboCotisationPret.setButtonCell( new ListCell<CotisationFx>() {
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
              if (item == null){
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
        datePret.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        montantPret.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());
        dateRembPret.setCellValueFactory(cellData -> cellData.getValue().dateRemboursementProperty());
        tauxPret.setCellValueFactory(cellData -> cellData.getValue().tauxInteretsProperty().asObject());
        avaliseurPret.setCellValueFactory(cellData -> cellData.getValue().getAvaliseur1().nomProperty());

        datePretSuiviPret.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        nomCotisationSuiviPret.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeSuiviPret.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        montantPlace.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());


        idCol1.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        nomCotisationCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        montantCol.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());
        dateRembCol.setCellValueFactory(cellData -> cellData.getValue().dateRemboursementProperty());
        tauxCol.setCellValueFactory(cellData -> cellData.getValue().tauxInteretsProperty().asObject());

        dateRembSuiviRemb.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        nomCotisationSuiviRemb.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeSuiviRemb.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        montantSuiviRemb.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());
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

            FaireEmpruntController.tmpCotisation = mCotisation;

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
        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/faireUnEmprunt.fxml"));
            BorderPane page = (BorderPane) loader.load();
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Faire un emprunt");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainAppGF.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Member into the controller.
//            FaireEmpruntController controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//            controller.setPretsEtRembController(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void actionOnclickModifierInscptionAnnuelle() {
/*
        int selectedIndex = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            TransactionFx mbreInscritFx = inscritsAnnuelTable.getItems().get(selectedIndex);
            int keyInArrayList = suiviPrets.indexOf(mbreInscritFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/Transaction.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Inscription");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                TransactionController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setTransaction(mbreInscritFx);
                controller.setKeyInArray(keyInArrayList);
                controller.setInscriptionsPanelController(this);

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
        }*/
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
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/faireUnRemb.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Faire remboursement");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainAppGF.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Member into the controller.
            //InscriptionCotisationController controller = loader.getController();
            //controller.setDialogStage(dialogStage);
            //controller.setInscriptionsPanelController(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

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
                dialogStage.initModality(Modality.WINDOW_MODAL);
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
