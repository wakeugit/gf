package gf.view;

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

public class EffectuerCotisationController {
	
    
    private ObservableList<InscriptionCotisationFx> listeMembresInscrits = FXCollections.observableArrayList();
	
    @FXML
    private ComboBox<MembreFx> nomMembre;
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
    private EpargnePanelController epargnePanelController;
    private Stage dialogStage;
    private InscriptionCotisation inscriptionCotisation;
    private InscriptionCotisationFx inscriptionCotisationFx;
    private boolean validerClicked = false;

    @FXML
    private void initialize() {

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
    	
    	nomMembre.setConverter(new StringConverter<MembreFx>() {
             @Override
             public String toString(MembreFx item) {
               if (item == null){
                 return null;
               } else {
                 return item.getNom()+" "+item.getPrenom();
               }
             }

           @Override
           public MembreFx fromString(String item) {
               return null;
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
    	//cotisation.setItems(listeMembresInscrits);
    	//nomMembre.setItems(listeMembres);
    }

    public EffectuerCotisationController() {
 
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
        	inscriptionCotisationFx = new InscriptionCotisationFx(
        			new InscriptionCotisation( new Cotisation(
        					cotisation.getSelectionModel().getSelectedItem()),
        					new Membre(nomMembre.getSelectionModel().getSelectedItem()),
        					DateUtil.format(date.getValue()),
        					Integer.parseInt(montant.getText())));

        	if (valider.getText().equals("Valider")) {
        		//tontinePanelController.getListMembreInscritsCotisation().add(inscriptionCotisationFx); 
    		} else {
    			//tontinePanelController.getListMembreInscritsCotisation().set(keyInArray, inscriptionCotisationFx);
    		}

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
    
    public EpargnePanelController getEpargnePanelController() {
        return epargnePanelController;
    }

    public void setTontinePanelController(TontinePanelController tontinePanelController) {
        this.tontinePanelController = tontinePanelController;
    }
    
    public void setEpargnePanelController(EpargnePanelController epargnePanelController) {
        this.epargnePanelController = epargnePanelController;
    }

    
    public InscriptionCotisation getInscriptionCotisation() {
        return inscriptionCotisation;
    }

    public void setEffectuerCotisation(InscriptionCotisationFx inscriptionCotisationFx) {
    	initialize();
        valider.setText("Modifier");
        nomMembre.getSelectionModel().select(inscriptionCotisationFx.getMembreFx());
        cotisation.getSelectionModel().select(inscriptionCotisationFx.getCotisationFx());
        date.setValue(inscriptionCotisationFx.getDateInscrptionProperty().getValue());
        montant.setText("" + inscriptionCotisationFx.getNumeroTirage());
    }

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
}
