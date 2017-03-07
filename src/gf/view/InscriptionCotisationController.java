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

public class InscriptionCotisationController {
	
    private ObservableList<MembreFx> listeMembres = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeCotisation = FXCollections.observableArrayList();
	
    @FXML
    private ComboBox<MembreFx> nomMembre;
    @FXML
    private ComboBox<CotisationFx> cotisation;
    @FXML
    private DatePicker dateInscription;
    @FXML
    private TextField numeroTirage;
    @FXML
    private Button valider;
    
    private int keyInArray=0;
    private MembrePanelController membrePanelController;
    private InscriptionsPanelController inscriptionPanelController;
    private Stage dialogStage;
    private InscriptionCotisation inscriptionCotisation;
    private InscriptionCotisationFx inscriptionCotisationFx;
    private boolean validerClicked = false;
    private Cotisation mCotisation;
    private Membre mMembre;

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
    	cotisation.setItems(listeCotisation);
    	nomMembre.setItems(listeMembres);
    }

    public InscriptionCotisationController() {
    	membrePanelController = new MembrePanelController();
    	
    	listeMembres=membrePanelController.getListMembre();

        Response<Cotisation[]> response1 = BackendInterface.getCotisations(Type.TONTINE);
        if (response1.getBody() != null) {
            for (Cotisation cotisation : response1.getBody()) {
                listeCotisation.add(new CotisationFx(cotisation));
            }
            //Todo share data between panel and controller
            //if (listeCotisation.size() != 0) {
            //cotisation.setValue();
            //}
        } else {
            //Todo Display error message
            System.out.println("An error occured - Cotisation");
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

            InscriptionCotisation ic = new InscriptionCotisation(mCotisation,
                    mMembre,
                    DateUtil.format(dateInscription.getValue()),
                    Integer.parseInt(numeroTirage.getText()));

            Response<InscriptionCotisation> response;

        	if (valider.getText().equals("Valider")) {
                response = BackendInterface.createInscriptionCotisation(ic);
                if (response.getBody() != null) {
                    inscriptionPanelController.getListMembreInscritsCotisation().add(new InscriptionCotisationFx(response.getBody()));

                } else {
                    // Todo Display error message
                }
//        		inscriptionPanelController.getListMembreInscritsCotisation().add(inscriptionCotisationFx);
            } else {
                response = BackendInterface.updateInscriptionCotisation(ic);
                if (response.getBody() != null) {
                    inscriptionPanelController.getListMembreInscritsCotisation().set(keyInArray, new InscriptionCotisationFx(response.getBody()));

                } else {
                    // Todo Display error message
                }
//    			inscriptionPanelController.getListMembreInscritsCotisation().set(keyInArray, inscriptionCotisationFx);
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
        numeroTirage.setText("");
    }
    

    private boolean isInputValid() {
        String errorMessage = "";

        if (nomMembre.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Membre invalide!\n";
        }
        if (cotisation.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Cotisation invalide!\n";
        }
        if (dateInscription.getValue() == null ) {
            errorMessage += "Date Inscription invalide!\n";
        } 

        if (numeroTirage.getText() == null || numeroTirage.getText().length() == 0 || numeroTirage.getText().length() > 9) {
            errorMessage += "Numero tirage invalide!\n";
        } else {
            // try to parse the telephone number into an int.
            try {
                Integer.parseInt(numeroTirage.getText());
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

    public InscriptionsPanelController getInscriptionsPanelController() {
        return inscriptionPanelController;
    }

    public void setInscriptionsPanelController(InscriptionsPanelController inscriptionPanelController) {
        this.inscriptionPanelController = inscriptionPanelController;
    }

    public InscriptionCotisation getInscriptionCotisation() {
        return inscriptionCotisation;
    }

    public void setInscriptionCotisation(InscriptionCotisationFx inscriptionCotisationFx) {
    	initialize();
        valider.setText("Modifier");
        nomMembre.getSelectionModel().select(inscriptionCotisationFx.getMembreFx());
        cotisation.getSelectionModel().select(inscriptionCotisationFx.getCotisationFx());
        dateInscription.setValue(inscriptionCotisationFx.getDateInscrptionProperty().getValue());
        numeroTirage.setText("" + inscriptionCotisationFx.getNumeroTirage());
    }

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
}
