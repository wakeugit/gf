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

public class AidesPanelController {

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
    private TableColumn<InscriptionAnnuelleFx, String> adresseCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Integer> cniCol;
    
    @FXML
    private TableView<OperationFx> listeAideTable;
    @FXML
    private TableColumn<OperationFx, Long> idAide;
    @FXML
    private TableColumn<OperationFx, String> nomMembre;
    @FXML
    private TableColumn<OperationFx, String> prenomMembre;
    @FXML
    private TableColumn<OperationFx, String> motifAide;
    @FXML
    private TableColumn<OperationFx, LocalDate> dateAide;
    @FXML
    private TableColumn<OperationFx, Double> montantAide;
    @FXML
    private TableColumn<OperationFx, LocalDate> dateRemb;
    
    @FXML
    private TableView<InscriptionAnnuelleFx> listeMembresTable;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Long> idCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> nomCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> prenomCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> adresseCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Integer> cniCol1;
    
    @FXML
    private TableView<OperationFx> etatAideTable;
    @FXML
    private TableColumn<OperationFx, Long> idRemb;
    @FXML
    private TableColumn<OperationFx, String> nomCol2;
    @FXML
    private TableColumn<OperationFx, String> prenomCol2;
    @FXML
    private TableColumn<OperationFx, String> aide;
    @FXML
    private TableColumn<OperationFx, Double> montantAide2;
    @FXML
    private TableColumn<OperationFx, LocalDate> dateRemb2;
    @FXML
    private TableColumn<OperationFx, Double> montantRemb;
   

    @FXML
    private ComboBox<CotisationFx> comboAnneeDonner;
    @FXML
    private ComboBox<CotisationFx> comboAnneeListe;
    @FXML
    private ComboBox<CotisationFx> comboAnneeRemb;
    @FXML
    private ComboBox<ServiceFx> comboAideRemb;
    @FXML
    private ComboBox<CotisationFx> comboAnneeEtat;
    @FXML
    private ComboBox<ServiceFx> comboAideEtat;
    @FXML
    private Button validerDonnerAide;
    @FXML
    private Button validerListeAide;
    @FXML
    private Button validerRembourserAide;
    @FXML
    private Button validerEtatAide;

    private Cotisation mCotisation;
    private Service mService;

    public AidesPanelController() {


        Response<Cotisation[]> response = BackendInterface.getCotisationsByType(TypeCotisation.ANNEE);
        if (response.getBody() != null) {
            for (Cotisation cotisation : response.getBody()) {
                listeAnnees.add(new CotisationFx(cotisation));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Annee");
        }

/*
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
        */
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns
    	if(comboAnneeDonner != null){
    		comboAnneeDonner.setButtonCell( new ListCell<CotisationFx>() {
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
        	
    		comboAnneeDonner.setCellFactory(
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
    		comboAnneeDonner.setItems(listeAnnees);
    	}	
    		if(comboAnneeListe != null){
    			comboAnneeListe.setButtonCell( new ListCell<CotisationFx>() {
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
            	
    			comboAnneeListe.setCellFactory(
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
    			comboAnneeListe.setItems(listeAnnees);
    		}
    		
    		if(comboAnneeRemb != null){
    			comboAnneeRemb.setButtonCell( new ListCell<CotisationFx>() {
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
            	
    			comboAnneeRemb.setCellFactory(
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
    			comboAnneeRemb.setItems(listeAnnees);
    		}
    		
    		if(comboAnneeEtat != null){
    			comboAnneeEtat.setButtonCell( new ListCell<CotisationFx>() {
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
            	
    			comboAnneeEtat.setCellFactory(
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
    			comboAnneeEtat.setItems(listeAnnees);
    		}
        	
    	

        idCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().idProperty().asObject());
        nomCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        adresseCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().adresseProperty());
        cniCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().cniProperty().asObject());

        idCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().idProperty().asObject());
        nomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        adresseCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().adresseProperty());
        cniCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().cniProperty().asObject());
        
        idAide.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomMembre.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomMembre.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        motifAide.setCellValueFactory(cellData -> cellData.getValue().getServiceFx().getMotifProperty());
        dateAide.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        montantAide.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());
        dateRemb.setCellValueFactory(cellData -> cellData.getValue().dateRemboursementProperty());
        

        idRemb.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomCol2.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol2.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        aide.setCellValueFactory(cellData -> cellData.getValue().getOperation());
        dateRemb2.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        montantAide2.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());
 
       
        
        inscritsAnnuelTable.setItems(listeInscritsAnnuel);
        listeMembresTable.setItems(listeInscritsAnnuel);
        //listeAideTable.setItems();
        //etatAideTable.setItems();
        
        


    }


    @FXML
    private void actionOnClickValiderDonnerAide() {

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
    private void actionOnClickValiderListeAide() {

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
    private void actionOnClickValiderRembourserAide() {

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
    private void actionOnClickValiderEtatAides() {

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
    private void actionOnclickNouvelleAide() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
        	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/effectuerService.fxml"));
            BorderPane page = (BorderPane) loader.load();
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Effectuer une aide");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainAppGF.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Member into the controller.
            EffectuerServiceController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAidePanelController(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void actionOnclickModifierAide() {

        int selectedIndex = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            InscriptionAnnuelleFx mbreInscritFx = inscritsAnnuelTable.getItems().get(selectedIndex);
            int keyInArrayList = listeInscritsAnnuel.indexOf(mbreInscritFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/effectuerService.fxml"));
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
    private void actionOnclickSupprimerAide() {

       /* int selectedIndex = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();
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
        }*/
    }
    

    @FXML
    private void actionOnclickNouveauRemboursementAide() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/rembourserService.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouveau Remboursement");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainAppGF.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Member into the controller.
           RembourserServiceController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAidePanelController(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void actionOnclickModifierRemboursementAide() {

        /*int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
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
        }*/
    }


    @FXML
    private void actionOnclickSupprimerRemboursementAide() {

       /* int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
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
        }*/
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
