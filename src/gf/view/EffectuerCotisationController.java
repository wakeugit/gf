package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
import gf.util.ComboBoxAutoComplete;
import gf.util.DateUtil;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class EffectuerCotisationController {


    public static Cotisation tmpCotisation;
    private ObservableList<CotisationFx> listeCotisation = FXCollections.observableArrayList();
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

    private int keyInArray = 0;
    private TontinePanelController tontinePanelController;
    private EpargnePanelController epargnePanelController;
    private Stage dialogStage;
    private InscriptionCotisation inscriptionCotisation;
    private InscriptionCotisationFx inscriptionCotisationFx;
    private boolean validerClicked = false;
    private Membre mMembre;
    private Cotisation mCotisation;

    public EffectuerCotisationController() {

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
        
        
        
        new ComboBoxAutoComplete<InscriptionCotisationFx>(nomMembre);
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

            Transaction transaction = new Transaction();
            transaction.setMembre(mMembre);
            transaction.setCotisation(mCotisation);
            transaction.setDateOperation(dateOp);
            transaction.setMontantOperation(montantOp);
            transaction.setType(TypeTransaction.TONTINER);

            System.out.println("Transaction:" + transaction);


            Response<Transaction> response;

            if (valider.getText().equals("Valider")) {
                response = BackendInterface.createTransaction(transaction);
                if (response.getBody() != null) {
//                    tontinePanelController.getListMembreInscritsCotisation().add(new TransactionFx(response.getBody()));
                    System.out.println(mMembre.getNom() + " a tontine!");

                } else {
                    // Todo Display error message
                }
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
        this.dialogStage.close();
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (nomMembre.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Membre invalide!\n";
        }
        if (cotisation.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Cotisation invalide!\n";
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
//        nomMembre.getSelectionModel().select(inscriptionCotisationFx.getMembreFx());
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

    public void setCotisation(Cotisation cotisation) {
        this.tmpCotisation = cotisation;
    }
}
