package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
import gf.util.ComboBoxAutoComplete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class RembourserServiceController {


    public static Operation tmpOperation;
    public static InscriptionAnnuelleFx tmpMembre;
    public static int effectif;
    private final NumberFormat numberFormat;
    private ObservableList<ServiceFx> listeService = FXCollections.observableArrayList();
    private ObservableList<InscriptionAnnuelleFx> listeMembresInscrits = FXCollections.observableArrayList();

    @FXML
    private ComboBox<InscriptionAnnuelleFx> nomMembre;
    @FXML
    private ComboBox<OperationFx> operation;
    @FXML
    private DatePicker date;
    @FXML
    private TextField montantAttendu;
    @FXML
    private TextField montantAvance;
    @FXML
    private TextField reste;
    @FXML
    private TextField penalites;
    @FXML
    private Button valider;
    
    @FXML
    private Label serviceTitel;
    @FXML
    private Label serviceText;

    private int keyInArray = 0;
    private AidesPanelController aidePanelController;
    private SanctionPanelController sanctionPanelController;
    private Stage dialogStage;
    private InscriptionAnnuelle inscriptionAnnuelle;
    private InscriptionAnnuelleFx inscriptionAnnuelleFx;
    private boolean validerClicked = false;
    private Membre mMembre;
    private Service mService;
    private Operation mOperation;

    public RembourserServiceController() {
    	//modifier les labels sur la vue: tite et le label devant le textField
    	if(sanctionPanelController!=null){
    		serviceTitel.setText("Rembourser une sanction");
    		serviceText.setText("Sanction");
    	} else {
    		serviceTitel.setText("Rembourser une aide");
    		serviceText.setText("Aide");
    	}
    	
        /*
        if (tmpOperation != null) {
            Response<InscriptionService[]> response;

            response = BackendInterface.getInscriptionService(tmpOperation);
            if (response.getBody() != null) {
                if (tmpOperation.getType() == TypeService.TONTINE) {
                    listeMembresInscrits.clear();
                    for (InscriptionService inscriptionService : response.getBody()) {
                        listeMembresInscrits.add(new InscriptionServiceFx(inscriptionService));
                    }
                } else if (tmpOperation.getType() == TypeService.EPARGNE) {
                    listeMembresInscrits.clear();
                    for (InscriptionService inscriptionService : response.getBody()) {
                        listeMembresInscrits.add(new InscriptionServiceFx(inscriptionService));
                    }
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderService");
            }

            listeService.add(new ServiceFx(tmpOperation));
        }*/

        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);
    }

    @FXML
    private void initialize() {

        operation.setButtonCell(new ListCell<OperationFx>() {
            @Override
            protected void updateItem(OperationFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText("");
                } else {
                    setText(item.getServiceFx().getMotif() + " - " + item.getMembreFx().getNom() + " - " + item.getMontantOperation());
                    mOperation = new Operation(item);

                }
            }
        });

        operation.setCellFactory(
                new Callback<ListView<OperationFx>, ListCell<OperationFx>>() {


                    @Override
                    public ListCell<OperationFx> call(ListView<OperationFx> arg0) {
                        ListCell<OperationFx> cell = new ListCell<OperationFx>() {
                            @Override
                            protected void updateItem(OperationFx item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setText("");
                                } else {
                                    setText(item.getServiceFx().getMotif() + " - " + item.getMembreFx().getNom() + " - " + item.getMontantOperation());
                                }
                            }
                        };
                        return cell;
                    }


                });


        nomMembre.setButtonCell(new ListCell<InscriptionAnnuelleFx>() {
            @Override
            protected void updateItem(InscriptionAnnuelleFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText("");
                } else {
                    setText(item.getMembreFx().getNom() + " " + item.getMembreFx().getPrenom());
                    //mMembre = new Membre(item.getMembreFx());
                }
            }
        });

        nomMembre.setConverter(new StringConverter<InscriptionAnnuelleFx>() {
            @Override
            public String toString(InscriptionAnnuelleFx item) {
                if (item == null) {
                    return null;
                } else {
                    return item.getMembreFx().getNom() + " " + item.getMembreFx().getPrenom();
                }
            }

            @Override
            public InscriptionAnnuelleFx fromString(String item) {
                return null;
            }
        });

        nomMembre.setCellFactory(
                new Callback<ListView<InscriptionAnnuelleFx>, ListCell<InscriptionAnnuelleFx>>() {


                    @Override
                    public ListCell<InscriptionAnnuelleFx> call(ListView<InscriptionAnnuelleFx> arg0) {
                        ListCell<InscriptionAnnuelleFx> cell = new ListCell<InscriptionAnnuelleFx>() {
                            @Override
                            protected void updateItem(InscriptionAnnuelleFx item, boolean empty) {
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
//        operation.setItems(listeService);
        nomMembre.setItems(listeMembresInscrits);
        //set default value of combox Service
        if (tmpOperation != null) {
            operation.getSelectionModel().select(new OperationFx(tmpOperation));
        }
        //set default value of combox membre
        if (tmpMembre != null) {
            //nomMembre.setValue(tmpMembre);
            nomMembre.getSelectionModel().select(tmpMembre);
        }

        if (montantAttendu != null && effectif > 0 && tmpOperation != null) {
            double ma = tmpOperation.getMontantOperation() / effectif;
            System.out.println("M A: " + ma);
            montantAttendu.setText(numberFormat.format(ma));
            montantAttendu.setEditable(false);
            reste.setEditable(false);

            montantAvance.textProperty().addListener((observable, oldValue, newValue) -> {

                if (!newValue.isEmpty()) {
                    double valueReste = ma - Double.valueOf(newValue);
                    if (valueReste < 0) {
                        valider.setDisable(true);
                        reste.setText("Error");
                    } else {
                        valider.setDisable(false);
                        reste.setText(numberFormat.format(valueReste));
                    }
                } else
                    reste.setText(numberFormat.format(ma));


            });
        }

        date.setValue(LocalDate.now());

        new ComboBoxAutoComplete<InscriptionAnnuelleFx>(nomMembre);
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
            LocalDate localDate = date.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));

            long dateOp = Date.from(instant).getTime();

            double montantOp = Double.parseDouble(montantAvance.getCharacters().toString());
            System.out.println("Montant = " + montantOp);

            mMembre = new Membre(nomMembre.getSelectionModel().getSelectedItem().getMembreFx());
            mService = tmpOperation.getService();

            Operation operation = new Operation();
            operation.setMembre(mMembre);
            operation.setService(mService);
            operation.setDateOperation(dateOp);
            operation.setMontantOperation(montantOp);
            operation.setIdOperationInitiale(tmpOperation.getId());

            if (tmpOperation.getType() == TypeOperation.AIDER) {
                operation.setType(TypeOperation.REMBOURSER_AIDE);
            } else if (tmpOperation.getType() == TypeOperation.SANCTIONER) {
                operation.setType(TypeOperation.REMBOURSER_SANCTION);
            }
            System.out.println("Operation:" + operation);


            Response<Operation> response;

            if (valider.getText().equals("Valider")) {
                response = BackendInterface.createOperation(operation);
                if (response.getBody() != null) {
//                    aidePanelController.getListMembreInscritsService().add(new TransactionFx(response.getBody()));
                    System.out.println(mMembre.getNom() + " a recu une aide.");

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.initOwner(dialogStage);
                    alert.setTitle(operation.getType().name());
                    alert.setHeaderText("Operation Effectuée !!");
                    alert.setContentText(mMembre.getNom());

                    alert.showAndWait();

                } else {
                    // Todo Display error message
                }
            } else {
                //aidePanelController.getListMembreInscritsService().set(keyInArray, inscriptionServiceFx);
            }

            validerClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void actionOnClickAnnuler() {
        nomMembre.getSelectionModel().select(null);
        operation.getSelectionModel().select(null);
        date.setValue(null);
        montantAttendu.setText("");
        dialogStage.close();
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (nomMembre.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Membre invalide!\n";
        }
        if (operation.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Service invalide!\n";
        }
        if (date.getValue() == null) {
            errorMessage += "Date Inscription invalide!\n";
        }

        if (montantAvance.getText() == null || montantAvance.getText().length() == 0 || montantAvance.getText().length() > 9) {
            errorMessage += "Montant invalide!\n";
        } else {
            // try to parse the telephone number into an int.
            try {
                numberFormat.parse(montantAvance.getText());
            } catch (ParseException e) {
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

    public AidesPanelController getAidePanelController() {
        return aidePanelController;
    }

    public SanctionPanelController getSanctionPanelController() {
        return sanctionPanelController;
    }

    public void setAidePanelController(AidesPanelController aidePanelController) {
        this.aidePanelController = aidePanelController;
    }

    public void setSanctionPanelController(SanctionPanelController sanctionPanelController) {
        this.sanctionPanelController = sanctionPanelController;
    }


    public InscriptionAnnuelle getInscriptionAnnuelle() {
        return inscriptionAnnuelle;
    }

    public void setRembourserService(InscriptionAnnuelleFx inscriptionAnnuelleFx) {
        initialize();
        valider.setText("Modifier");
        //nomMembre.getSelectionModel().select(inscriptionAnnuelleFx.getMembreFx());
        //service.getSelectionModel().select(inscriptionAnnuelleFx.getServiceFx());
        //date.setValue(inscriptionAnnuelleFx.getDateInscrptionProperty().getValue());
        //montantAttendu.setText("" + inscriptionAnnuelleFx.getNumeroTirage());
    }


    public int getKeyInArray() {
        return keyInArray;
    }

    public void setKeyInArray(int keyInArray) {
        this.keyInArray = keyInArray;
    }

    public void setService(Operation operation) {
        this.tmpOperation = operation;
    }
}
