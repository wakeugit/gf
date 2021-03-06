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

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EffectuerServiceController {


    public static TypeService typeService;
    public static InscriptionAnnuelleFx tmpMembre;
    private ObservableList<ServiceFx> listeService = FXCollections.observableArrayList();
    private ObservableList<InscriptionAnnuelleFx> listeMembresInscrits = FXCollections.observableArrayList();

    @FXML
    private ComboBox<InscriptionAnnuelleFx> nomMembre;
    @FXML
    private ComboBox<ServiceFx> service;
    @FXML
    private DatePicker date;
    @FXML
    private TextField montant;
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

    public EffectuerServiceController() {
    	//modifier les labels sur la vue: tite et le label devant le textField



        if (tmpMembre != null) {

            listeMembresInscrits.clear();
            listeMembresInscrits.add(tmpMembre);


            Response<Service[]> response;

            response = BackendInterface.getServiceByType(typeService);
            if (response.getBody() != null) {
                listeService.clear();
                    for (Service service : response.getBody()) {
                        listeService.add(new ServiceFx(service));
                    }
            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderService");
            }
        }
    }

    @FXML
    private void initialize() {

        if(typeService==TypeService.SANCTION){
            serviceTitel.setText("Sanctionner un membre");
            serviceText.setText("Sanction");
        }

        service.setButtonCell(new ListCell<ServiceFx>() {
            @Override
            protected void updateItem(ServiceFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText("");
                } else {
                    setText(item.getMotif());
                    //mService = new Service(item);
                }
            }
        });

        service.setCellFactory(
                new Callback<ListView<ServiceFx>, ListCell<ServiceFx>>() {


                    @Override
                    public ListCell<ServiceFx> call(ListView<ServiceFx> arg0) {
                        ListCell<ServiceFx> cell = new ListCell<ServiceFx>() {
                            @Override
                            protected void updateItem(ServiceFx item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setText("");
                                } else {
                                    setText(item.getMotif());
                                }
                            }
                        };
                        return cell;
                    }


                });

        service.setConverter(new StringConverter<ServiceFx>() {
            @Override
            public String toString(ServiceFx item) {
                if (item == null) {
                    return null;
                } else {
                    return item.getMotif();
                }
            }

            @Override
            public ServiceFx fromString(String item) {
                return null;
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

        service.setItems(listeService);
        nomMembre.setItems(listeMembresInscrits);

        //set default value of combox Service NO NEED for service
//        if (tmpOperation != null) {
//            service.getSelectionModel().select(new ServiceFx(tmpOperation));
//        }
        //set default value of combox membre
        if (tmpMembre != null) {
            //nomMembre.setValue(tmpMembre);
            nomMembre.getSelectionModel().select(tmpMembre);
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

            double montantOp = Double.parseDouble(montant.getCharacters().toString());
            System.out.println("Montant = " + montantOp);

            mMembre = new Membre(nomMembre.getSelectionModel().getSelectedItem().getMembreFx());
            mService = new Service(service.getSelectionModel().getSelectedItem());

            Operation operation = new Operation();
            operation.setMembre(mMembre);
            operation.setService(mService);
            operation.setDateOperation(dateOp);
            operation.setMontantOperation(montantOp);

            if (typeService == TypeService.AIDE) {
                operation.setType(TypeOperation.AIDER);
            } else if (typeService == TypeService.SANCTION) {
                operation.setType(TypeOperation.SANCTIONER);
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
                    alert.setTitle(typeService.name());
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
        service.getSelectionModel().select(null);
        date.setValue(null);
        montant.setText("");
        dialogStage.close();
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (nomMembre.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Membre invalide!\n";
        }
        if (service.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Service invalide!\n";
        }
        if (date.getValue() == null) {
            errorMessage += "Date Inscription invalide!\n";
        }

        if (montant.getText() == null || montant.getText().length() == 0 || montant.getText().length() > 9) {
            errorMessage += "Montant invalide!\n";
        } else {
            // try to parse the telephone number into an int.
            try {
                Double.parseDouble(montant.getText());
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

    public void setEffectuerService(InscriptionAnnuelleFx inscriptionAnnuelleFx) {
        initialize();
        valider.setText("Modifier");
        //nomMembre.getSelectionModel().select(inscriptionAnnuelleFx.getMembreFx());
        //service.getSelectionModel().select(inscriptionAnnuelleFx.getServiceFx());
        //date.setValue(inscriptionAnnuelleFx.getDateInscrptionProperty().getValue());
        //montant.setText("" + inscriptionAnnuelleFx.getNumeroTirage());
    }


    public int getKeyInArray() {
        return keyInArray;
    }

    public void setKeyInArray(int keyInArray) {
        this.keyInArray = keyInArray;
    }

    public void setService(TypeService typeService) {
        this.typeService = typeService;
    }
}
