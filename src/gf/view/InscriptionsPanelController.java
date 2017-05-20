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

public class InscriptionsPanelController {

    private MainAppGF mainAppGF;

    private ObservableList<InscriptionAnnuelleFx> listeInscritsAnnuel = FXCollections.observableArrayList();
    private ObservableList<InscriptionCotisationFx> listeInscritsCotisation = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeAnnees = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeCotisations = FXCollections.observableArrayList();
    
    @FXML
    private TableView<InscriptionAnnuelleFx> inscritsAnnuelTable;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Long> idCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> nomCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> prenomCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, LocalDate> dateInscriptionCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Integer> montantCol;
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

    @FXML
    private ComboBox<CotisationFx> annee;
    @FXML
    private ComboBox<CotisationFx> cotisation;
    @FXML
    private Button validerCotisation;
    @FXML
    private Button validerAnnuelCotisation;

    private Cotisation mCotisation;

    public InscriptionsPanelController() {


        Response<Cotisation[]> response = BackendInterface.getCotisationsByType(TypeCotisation.ANNEE);
        if (response.getBody() != null) {
            for (Cotisation cotisation : response.getBody()) {
                listeAnnees.add(new CotisationFx(cotisation));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Annee");
        }


        Response<Cotisation[]> response1 = BackendInterface.getCotisationsByType(TypeCotisation.TONTINE);
        Response<Cotisation[]> response2 = BackendInterface.getCotisationsByType(TypeCotisation.EPARGNE);
        if (response1.getBody() != null || response2.getBody() != null ) {
        	
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
    	if(annee != null){
        	annee.setButtonCell( new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty); 
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getAnnee());
                        mCotisation = new Cotisation(item);
                    }
                }
            });
        	
        	annee.setCellFactory(
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
    	                            setText(item.getAnnee());
    	                        }
    	                    }
    	                };
    	                return cell;
    			}

    			
            });
        	annee.setItems(listeAnnees);
        	}
    	if(cotisation != null){
    	cotisation.setButtonCell( new ListCell<CotisationFx>() {
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
    	
    	cotisation.setCellFactory( 
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
    	
    	cotisation.setConverter(new StringConverter<CotisationFx>() {
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
            cotisation.setItems(listeCotisations);
    	}

        idCol.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        nomCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        dateInscriptionCol.setCellValueFactory(cellData -> cellData.getValue().getDateInscrptionProperty());
        montantCol.setCellValueFactory(cellData -> cellData.getValue().getMontantProperty().asObject());

        idCol1.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        nomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        cotisationCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        numTirageCol.setCellValueFactory(cellData -> cellData.getValue().getNumeroTirageProperty().asObject());
     
        inscritsCotisationTable.setItems(listeInscritsCotisation);
        inscritsAnnuelTable.setItems(listeInscritsAnnuel);


    }


    @FXML
    private void actionOnClickValiderCotisation() {

        if (mCotisation != null) {
            Response<InscriptionCotisation[]> response;

            response = BackendInterface.getInscriptionCotisation(mCotisation);
            if (response.getBody() != null) {
                if (mCotisation.getTypeCotisation() == TypeCotisation.TONTINE) {
                    listeInscritsCotisation.clear();
                    for (InscriptionCotisation inscriptionCotisation : response.getBody()) {
                        listeInscritsCotisation.add(new InscriptionCotisationFx(inscriptionCotisation));
                    }
                }else if (mCotisation.getTypeCotisation() == TypeCotisation.EPARGNE) {
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

        if (mCotisation != null) {
            Response<InscriptionAnnuelle[]> response;

            response = BackendInterface.getInscriptionAnnuelle(mCotisation);
            if (response.getBody() != null) {
                if (mCotisation.getTypeCotisation() == TypeCotisation.ANNEE) {
                    listeInscritsAnnuel.clear();
                    for (InscriptionAnnuelle inscriptionCotisation : response.getBody()) {
                        listeInscritsAnnuel.add(new InscriptionAnnuelleFx(inscriptionCotisation));
                    }
                }


            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }


    }

    @FXML
    private void actionOnclickNouvelleInscptionAnnuelle() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/inscriptionAnnuelle.fxml"));
            BorderPane page = (BorderPane) loader.load();
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouvelle Inscription");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainAppGF.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Member into the controller.
            InscriptionAnnuelleController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setInscriptionsPanelController(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void actionOnclickModifierInscptionAnnuelle() {

        int selectedIndex = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            InscriptionAnnuelleFx mbreInscritFx = inscritsAnnuelTable.getItems().get(selectedIndex);
            int keyInArrayList = listeInscritsAnnuel.indexOf(mbreInscritFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/inscriptionAnnuelle.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Inscription");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                InscriptionAnnuelleController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setInscriptionAnnuelle(mbreInscritFx);
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
        }
    }


    @FXML
    private void actionOnclickSupprimerInscptionAnnuelle() {

        int selectedIndex = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //// TODO: 20/04/2017 Add alert before delete element
            BackendInterface.deleteInscriptionAnnuelle(inscritsAnnuelTable.getItems().get(selectedIndex).getId());

            actionOnClickValiderAnnuelle();
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
    private void actionOnclickNouvelleInscptionCotisation() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/inscriptionCotisation.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouvelle Inscription");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainAppGF.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Member into the controller.
            InscriptionCotisationController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setInscriptionsPanelController(this);

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
                InscriptionCotisationController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setInscriptionCotisation(mbreInscritFx);
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
        }
    }


    @FXML
    private void actionOnclickSupprimerInscptionCotisation() {

        int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //// TODO: 20/04/2017 Add alert before delete element
            BackendInterface.deleteInscriptionCotisation(inscritsCotisationTable.getItems().get(selectedIndex).getId());
            actionOnClickValiderCotisation();
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

    public ObservableList<InscriptionAnnuelleFx> getListMembreInscrits() {
        return listeInscritsAnnuel;
    }

    public ObservableList<InscriptionCotisationFx> getListMembreInscritsCotisation() {
        return listeInscritsCotisation;
    }

}
