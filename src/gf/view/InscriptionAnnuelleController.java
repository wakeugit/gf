package gf.view;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.Annee;
import gf.model.AnneeFx;
import gf.model.Cotisation;
import gf.model.CotisationFx;
import gf.model.InscriptionAnnuelle;
import gf.model.InscriptionAnnuelleFx;
import gf.model.Membre;
import gf.model.MembreFx;
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
import javafx.util.StringConverter;

public class InscriptionAnnuelleController {
	
    private ObservableList<MembreFx> listeMembres = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeAnnees = FXCollections.observableArrayList();
	
    @FXML
    private ComboBox<MembreFx> nomMembre;
    @FXML
    private ComboBox<CotisationFx> annee;
    @FXML
    private DatePicker dateInscription;
    @FXML
    private TextField montant;
    @FXML
    private Button valider;
    
    private int keyInArray=0;
    private MembrePanelController membrePanelController;
    private InscriptionsPanelController inscriptionPanelController;
    private Stage dialogStage;
    private InscriptionAnnuelle inscriptionAnnuelle;
    private InscriptionAnnuelleFx inscriptionAnnuelleFx;
    private boolean validerClicked = false;

    @FXML
    private void initialize() {
    	if(annee != null){
    	annee.setButtonCell( new ListCell<CotisationFx>() {
            @Override
            protected void updateItem(CotisationFx item, boolean empty) {
                super.updateItem(item, empty); 
                if (empty) {
                    setText("");
                } else {
                    setText(item.getAnnee());
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
    	if(nomMembre !=null){
    	nomMembre.setButtonCell( new ListCell<MembreFx>() {
            @Override
            protected void updateItem(MembreFx item, boolean empty) {
                super.updateItem(item, empty); 
                if (empty) {
                    setText("");
                } else {
                    setText(item.getNom()+" "+item.getPrenom());
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
    	nomMembre.setItems(listeMembres);
    	
    	
		dateInscription.setValue(LocalDate.now());
    	}
    	
    	
    }

    public InscriptionAnnuelleController() {
    	membrePanelController = new MembrePanelController();
    	
    	listeMembres=membrePanelController.getListMembre();
    	        
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
        	inscriptionAnnuelleFx = new InscriptionAnnuelleFx(
        			new InscriptionAnnuelle( new Cotisation(annee.getSelectionModel().getSelectedItem()),
        					new Membre(nomMembre.getSelectionModel().getSelectedItem()),
        					DateUtil.format(dateInscription.getValue()),
        					Integer.parseInt(montant.getText())));

        	if (valider.getText().equals("Valider")) {
        		inscriptionPanelController.getListMembreInscrits().add(inscriptionAnnuelleFx); 
    		} else {
    			inscriptionPanelController.getListMembreInscrits().set(keyInArray, inscriptionAnnuelleFx);
    		}

            validerClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    private void actionOnClickAnnuler() {
        nomMembre.getSelectionModel().select(null);
        annee.getSelectionModel().select(null);
        dateInscription.setValue(null);
        montant.setText("");
    }
    

    private boolean isInputValid() {
        String errorMessage = "";

        if (nomMembre.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Membre invalide!\n";
        }
        if (annee.getSelectionModel().getSelectedItem() == null) {
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

    public InscriptionsPanelController getInscriptionsPanelController() {
        return inscriptionPanelController;
    }

    public void setInscriptionsPanelController(InscriptionsPanelController inscriptionPanelController) {
        this.inscriptionPanelController = inscriptionPanelController;
    }

    public InscriptionAnnuelle getInscriptionAnnuelle() {
        return inscriptionAnnuelle;
    }

    public void setInscriptionAnnuelle(InscriptionAnnuelleFx inscriptionAnnuelleFx) {
        valider.setText("Modifier");
        nomMembre.getSelectionModel().select(inscriptionAnnuelleFx.getMembreFx());
        annee.getSelectionModel().select(inscriptionAnnuelleFx.getAnneeFx());
        dateInscription.setValue(inscriptionAnnuelleFx.getDateInscrptionProperty().getValue());
        montant.setText("" + inscriptionAnnuelleFx.getMontant());
    }

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
}
