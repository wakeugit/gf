package gf.view;


import java.time.LocalDate;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
import gf.util.ComboBoxAutoComplete;
import gf.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FaireEmpruntController {
	
    private ObservableList<MembreFx> listeMembres = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeAnnees = FXCollections.observableArrayList();
	
    @FXML
    private ComboBox<MembreFx> nomMembre;
    @FXML
    private ComboBox<CotisationFx> cotisation;
    @FXML
    private DatePicker dateInscription;
    @FXML
    private TextField montant;
    @FXML
    private Button valider;
    
    private int keyInArray=0;
    private MembrePanelController membrePanelController;
    private PretsEtRembPanelController pretsEtRembController;
    private Stage dialogStage;
    private Transaction transaction;
    private TransactionFx transactionFx;
    private boolean validerClicked = false;
    private Membre mMembre;
    private Cotisation mCotisation;

    public FaireEmpruntController() {
    	
   	 Response<Membre[]> response = BackendInterface.getMembres();
        if (response.getBody() != null) {
            for (Membre membre : response.getBody()) {
                listeMembres.add(new MembreFx(membre));
            }
        } else {
            //Todo Display error message
       	 System.out.println("An error occured - Membres");
        }
   	
   	Response<Cotisation[]> response1 = BackendInterface.getCotisations(Type.ANNEE);
       if (response1.getBody() != null) {
           for (Cotisation cotisation : response1.getBody()) {
               listeAnnees.add(new CotisationFx(cotisation));
           }
       } else {
           //Todo Display error message
           System.out.println("An error occured - Annee");
       }      
   }
    
    @FXML
    private void initialize() {
    	if(cotisation != null){
    	cotisation.setButtonCell(new ListCell<CotisationFx>() {
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
	                            setText(item.getAnnee());
	                        }
	                    }
	                };
	                return cell;
			}

			
        });
    	
    	}
    	if(nomMembre !=null){
    	nomMembre.setButtonCell( new ListCell<MembreFx>() {
            @Override
            protected void updateItem(MembreFx item, boolean empty) {
                super.updateItem(item, empty); 
                if (empty) {
                    setText("");
                } else {
                    setText(item.getNom()+" "+item.getPrenom());
                    mMembre = new Membre(item);

                }
            }
        });
    	
    	nomMembre.setCellFactory( 
    			new Callback<ListView<MembreFx>, ListCell<MembreFx>>() {
          

			@Override
			public ListCell<MembreFx> call(ListView<MembreFx> arg0) {
	                ListCell<MembreFx> cell = new ListCell<MembreFx>() {
	                    @Override
	                    protected void updateItem(MembreFx item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setText("");
	                        } else {
	                            setText(item.getNom()+" "+item.getPrenom());
	                        }
	                    }
	                };
	                return cell;
			}

			
        });
    	
    	cotisation.setItems(listeAnnees);
    	nomMembre.setItems(listeMembres);
    	
    	
		dateInscription.setValue(LocalDate.now());
		
		new ComboBoxAutoComplete<MembreFx>(nomMembre);
		
    	}
    	
    	
    }
 
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isValiderClicked() {
        return validerClicked;
    }


    @FXML
    private void actionOnClickValider() {
        if (isInputValid()) {
            InscriptionAnnuelle ic = new InscriptionAnnuelle(mCotisation,
                    mMembre,
                    DateUtil.format(dateInscription.getValue()),
                    Integer.parseInt(montant.getText()));

            Response<InscriptionAnnuelle> response;

            if (valider.getText().equals("Valider")) {
                response = BackendInterface.createInscriptionAnnuelle(ic);
                if (response.getBody() != null) {
                    pretsEtRembController.getListMembreInscrits().add(new InscriptionAnnuelleFx(response.getBody()));

                } else {
                    // Todo Display error message
                }
            } else {
                ic.setId(transactionFx.getId());
                ic.setMembre(new Membre(transactionFx.getMembreFx()));
                ic.setCotisation(new Cotisation(transactionFx.getAnneeFx()));
                response = BackendInterface.updateInscriptionAnnuelle(ic);
                if (response.getBody() != null) {
                    pretsEtRembController.getListMembreInscrits().set(keyInArray, new InscriptionAnnuelleFx(response.getBody()));
                } else {
                    // Todo Display error message
                }
            }
            validerClicked = true;
            dialogStage.close();
        }
    }



    @FXML
    private void actionOnClickAnnuler() {
        nomMembre.getSelectionModel().select(null);
        cotisation.getSelectionModel().select(null);
        dateInscription.setValue(null);
        montant.setText("");
    }
    

    private boolean isInputValid() {
        String errorMessage = "";

        if (nomMembre.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Membre invalide!\n";
        }
        if (cotisation.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Annee invalide!\n";
        }
        if (dateInscription.getValue() == null ) {
            errorMessage += "Date Inscription invalide!\n";
        } 

        if (montant.getText() == null || montant.getText().length() == 0 || montant.getText().length() > 9) {
            errorMessage += "Montant invalide!\n";
        } else {
            // try to parse the telephone number into an int.
            try {
                Integer.parseInt(montant.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Montant invalide (ne doit contenir que des chiffres)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("SVP corrigez les champs inavlides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public PretsEtRembPanelController getPretsEtRembPanelController() {
        return pretsEtRembController;
    }

    public void setInscriptionsPanelController(PretsEtRembPanelController PretsEtRembController) {
        this.pretsEtRembController = PretsEtRembController;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionFx transactionFx) {
        valider.setText("Modifier");
        nomMembre.getSelectionModel().select(inscriptionAnnuelleFx.getMembreFx());
        cotisation.getSelectionModel().select(inscriptionAnnuelleFx.getAnneeFx());
        dateInscription.setValue(inscriptionAnnuelleFx.getDateInscrptionProperty().getValue());
        montant.setText("" + inscriptionAnnuelleFx.getMontant());
        this.transactionFx = transactionFx;
    }

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
}
