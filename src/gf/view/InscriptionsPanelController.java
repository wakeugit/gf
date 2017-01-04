package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.Annee;
import gf.model.AnneeFx;
import gf.model.Cotisation;
import gf.model.CotisationFx;
import gf.model.InscriptionAnnuelleFx;
import gf.model.InscriptionCotisationFx;
import gf.model.Membre;
import gf.model.MembreFx;
import gf.model.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private ObservableList<AnneeFx> listeAnnees = FXCollections.observableArrayList();
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
    private ComboBox<AnneeFx> annee;
    @FXML
    private ComboBox<CotisationFx> cotisation;
    
    public InscriptionsPanelController() {

    	Response<Annee[]> response = BackendInterface.getAnnees();
        if (response.getBody() != null) {
            for (Annee annee : response.getBody()) {
                listeAnnees.add(new AnneeFx(annee));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Annee");
        }
        
        Response<Cotisation[]> response1 = BackendInterface.getCotisations(Type.TONTINE);
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
    	if(annee != null){
        	annee.setButtonCell( new ListCell<AnneeFx>() {
                @Override
                protected void updateItem(AnneeFx item, boolean empty) {
                    super.updateItem(item, empty); 
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getAnnee());
                    }
                }
            });
        	
        	annee.setCellFactory( 
        			new Callback<ListView<AnneeFx>, ListCell<AnneeFx>>() {
              

    			@Override
    			public ListCell<AnneeFx> call(ListView<AnneeFx> arg0) {
    	                ListCell<AnneeFx> cell = new ListCell<AnneeFx>() {
    	                    @Override
    	                    protected void updateItem(AnneeFx item, boolean empty) {
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
    	
    	idCol1.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        nomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
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
            inscritsAnnuelTable.getItems().remove(selectedIndex);
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

    public ObservableList<InscriptionAnnuelleFx> getListMembreInscrits() {
        return listeInscritsAnnuel;
    }

    public ObservableList<InscriptionCotisationFx> getListMembreInscritsCotisation() {
        return listeInscritsCotisation;
    }

}
