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
import javafx.util.StringConverter;

public class FaireEmpruntController {
	
	private ObservableList<CotisationFx> listeCotisation = FXCollections.observableArrayList();
    private ObservableList<InscriptionCotisationFx> listeMembresInscrits = FXCollections.observableArrayList();
	
    @FXML
    private ComboBox<InscriptionCotisationFx> nomMembre;
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
    private Cotisation tmpCotisation;

    public FaireEmpruntController() {
    	
    	if (tmpCotisation != null) {
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

            listeCotisation.add(new CotisationFx(tmpCotisation));
        }     
   }
    
    @FXML
    private void initialize() {
    	if (cotisation != null) {
    	cotisation.setButtonCell(new ListCell<CotisationFx>() {
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
    	}
    	if (nomMembre != null) {
        nomMembre.setButtonCell(new ListCell<InscriptionCotisationFx>() {
            @Override
            protected void updateItem(InscriptionCotisationFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText("");
                } else {
                    setText(item.getMembreFx().getNom() + " " + item.getMembreFx().getPrenom());
                    mMembre = new Membre(item.getMembreFx());
                }
            }
        });

        nomMembre.setConverter(new StringConverter<InscriptionCotisationFx>() {
            @Override
            public String toString(InscriptionCotisationFx item) {
                if (item == null) {
                    return null;
                } else {
                    return item.getMembreFx().getNom() + " " + item.getMembreFx().getPrenom();
                }
            }

            @Override
            public InscriptionCotisationFx fromString(String item) {
                return null;
            }
        });

        nomMembre.setCellFactory(
                new Callback<ListView<InscriptionCotisationFx>, ListCell<InscriptionCotisationFx>>() {


                    @Override
                    public ListCell<InscriptionCotisationFx> call(ListView<InscriptionCotisationFx> arg0) {
                        ListCell<InscriptionCotisationFx> cell = new ListCell<InscriptionCotisationFx>() {
                            @Override
                            protected void updateItem(InscriptionCotisationFx item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setText("");
                                } else {
                                    setText(item.getMembreFx().getNom() + " " + item.getMembreFx().getPrenom());
                                }
                            }
                        };
                        return cell;
                    }


                });
        cotisation.setItems(listeCotisation);
        nomMembre.setItems(listeMembresInscrits);
    	
    	
		dateInscription.setValue(LocalDate.now());
		
		//new ComboBoxAutoComplete<MembreFx>(nomMembre);
		
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
                    //pretsEtRembController.getListMembreInscrits().add(new InscriptionAnnuelleFx(response.getBody()));

                } else {
                    // Todo Display error message
                }
            } else {
                ic.setId(transactionFx.getId());
                ic.setMembre(new Membre(transactionFx.getMembreFx()));
                //ic.setCotisation(new Cotisation(transactionFx.getAnneeFx()));
                response = BackendInterface.updateInscriptionAnnuelle(ic);
                if (response.getBody() != null) {
                    //pretsEtRembController.getListMembreInscrits().set(keyInArray, new InscriptionAnnuelleFx(response.getBody()));
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
        //nomMembre.getSelectionModel().select(inscriptionAnnuelleFx.getMembreFx());
        //cotisation.getSelectionModel().select(inscriptionAnnuelleFx.getAnneeFx());
        //dateInscription.setValue(inscriptionAnnuelleFx.getDateInscrptionProperty().getValue());
        //montant.setText("" + inscriptionAnnuelleFx.getMontantOperation());
        //this.transactionFx = transactionFx;
    }

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
}
