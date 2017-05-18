package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
import gf.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class BeneficierCotisationController {


    private ObservableList<InscriptionCotisationFx> listeMembresInscrits = FXCollections.observableArrayList();
	
    @FXML
    private ComboBox<InscriptionCotisationFx> nomMembre;
    @FXML
    private ComboBox<CotisationFx> cotisation;
    @FXML
    private DatePicker date;
    @FXML
    private TextField montant;
    @FXML
    private Button valider;
    
    private int keyInArray=0;
    private TontinePanelController tontinePanelController;
    private Stage dialogStage;
    private InscriptionCotisation inscriptionCotisation;
    private InscriptionCotisationFx inscriptionCotisationFx;
    private TransactionFx transactionFx;
    private boolean validerClicked = false;

    public static Cotisation tmpCotisation;
    private Membre mMembre;

    @FXML
    private void initialize() {

        if (tmpCotisation != null ) {
            Response<InscriptionCotisation[]> response;


            response = BackendInterface.getInscriptionCotisation(tmpCotisation);
            if (response.getBody() != null) {
                if (tmpCotisation.getTypeCotisation() == TypeCotisation.TONTINE) {
                    listeMembresInscrits.clear();
                    for (InscriptionCotisation inscriptionCotisation : response.getBody()) {
                        listeMembresInscrits.add(new InscriptionCotisationFx(inscriptionCotisation));
                    }
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }

//            listeCotisation.add(new CotisationFx(tmpCotisation));
        }
    	 
    }

    public BeneficierCotisationController() {

//        nomMembre.setButtonCell(new ListCell<InscriptionCotisationFx>() {
//            @Override
//            protected void updateItem(InscriptionCotisationFx item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setText("");
//                } else {
//                    setText(item.getMembreFx().getNom() + " " + item.getMembreFx().getPrenom());
//                    mMembre = new Membre(item.getMembreFx());
//                }
//            }
//        });
//
//        nomMembre.setCellFactory(
//                new Callback<ListView<InscriptionCotisationFx>, ListCell<InscriptionCotisationFx>>() {
//
//
//                    @Override
//                    public ListCell<InscriptionCotisationFx> call(ListView<InscriptionCotisationFx> arg0) {
//                        ListCell<InscriptionCotisationFx> cell = new ListCell<InscriptionCotisationFx>() {
//                            @Override
//                            protected void updateItem(InscriptionCotisationFx item, boolean empty) {
//                                super.updateItem(item, empty);
//                                if (empty) {
//                                    setText("");
//                                } else {
//                                    setText(item.getMembreFx().getNom() + " " + item.getMembreFx().getPrenom());
//                                }
//                            }
//                        };
//                        return cell;
//                    }
//
//
//                });
//        nomMembre.setItems(listeMembresInscrits);
    	        
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
//        	inscriptionCotisationFx = new InscriptionCotisationFx(
//        			new InscriptionCotisation( new Cotisation(
//        					cotisation.getSelectionModel().getSelectedItem()),
//        					new Membre(nomMembre.getSelectionModel().getSelectedItem()),
//        					DateUtil.format(date.getValue()),
//        					Integer.parseInt(montant.getText())));
//
//        	if (valider.getText().equals("Valider")) {
//        		tontinePanelController.getListMembreInscritsCotisation().add(inscriptionCotisationFx);
//    		} else {
//    			tontinePanelController.getListMembreInscritsCotisation().set(keyInArray, inscriptionCotisationFx);
//    		}

            validerClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    private void actionOnClickAnnuler() {
        nomMembre.getSelectionModel().select(null);
        cotisation.getSelectionModel().select(null);
        date.setValue(null);
        montant.setText("");
    }
    

    private boolean isInputValid() {
        String errorMessage = "";

        if (nomMembre.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Membre invalide!\n";
        }
        if (cotisation.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Cotisation invalide!\n";
        }
        if (date.getValue() == null ) {
            errorMessage += "Date Inscription invalide!\n";
        } 

        if (montant.getText() == null || montant.getText().length() == 0 || montant.getText().length() > 9) {
            errorMessage += "Numero tirage invalide!\n";
        } else {
            // try to parse the telephone number into an int.
            try {
                Integer.parseInt(montant.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Numero tirage invalide (ne doit contenir que des chiffres)!\n";
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

    public TontinePanelController getTontinePanelController() {
        return tontinePanelController;
    }

    public void setTontinePanelController(TontinePanelController tontinePanelController) {
        this.tontinePanelController = tontinePanelController;
    }

    public InscriptionCotisation getInscriptionCotisation() {
        return inscriptionCotisation;
    }

    public void setEffectuerCotisation(InscriptionCotisationFx inscriptionCotisationFx) {
    	initialize();
        valider.setText("Modifier");
//        nomMembre.getSelectionModel().select(inscriptionCotisationFx.getMembreFx());
        cotisation.getSelectionModel().select(inscriptionCotisationFx.getCotisationFx());
        date.setValue(inscriptionCotisationFx.getDateInscrptionProperty().getValue());
        montant.setText("" + inscriptionCotisationFx.getNumeroTirage());
    }

    public void setCotisationFx(TransactionFx transactionFx) {
    	initialize();
//        nomMembre.getSelectionModel().select(inscriptionCotisationFx.getMembreFx());
        cotisation.getSelectionModel().select(transactionFx.getCotisationFx());
        date.setValue(transactionFx.getDateOperation());
        montant.setText("" + transactionFx.getMontantProperty().getValue());
    }

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
}
