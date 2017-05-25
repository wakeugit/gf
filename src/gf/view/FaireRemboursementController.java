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

public class FaireRemboursementController {


    public static Cotisation tmpCotisation;
    public static InscriptionCotisationFx tmpMembre;
    private ObservableList<CotisationFx> listeCotisation = FXCollections.observableArrayList();
    private ObservableList<InscriptionCotisationFx> listeMembresInscrits = FXCollections.observableArrayList();

    @FXML
    private ComboBox<InscriptionCotisationFx> nomMembre;
    @FXML
    private ComboBox<CotisationFx> cotisation;
    @FXML
    private DatePicker dateOperation;
    @FXML
    private TextField montantAttendu;
    @FXML
    private TextField montantAvance;
    @FXML
    private TextField reste;
    @FXML
    private TextField penalites;
   
   
    private Button valider;

    private int keyInArray = 0;
    private PretsEtRembPanelController pretsEtRembPanelController;
    private Stage dialogStage;
    private InscriptionCotisation inscriptionCotisation;
    private InscriptionCotisationFx inscriptionCotisationFx;
    private boolean validerClicked = false;
    private Membre mMembre;
    private Cotisation mCotisation;

    public FaireRemboursementController() {

        if (tmpCotisation != null) {
            Response<InscriptionCotisation[]> response;

            response = BackendInterface.getInscriptionCotisation(tmpCotisation);
            if (response.getBody() != null) {
                if (tmpCotisation.getTypeCotisation() == TypeCotisation.TONTINE) {
                    listeMembresInscrits.clear();
                    for (InscriptionCotisation inscriptionCotisation : response.getBody()) {
                        listeMembresInscrits.add(new InscriptionCotisationFx(inscriptionCotisation));
                    }
                } else if (tmpCotisation.getTypeCotisation() == TypeCotisation.EPARGNE) {
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
                    //mCotisation = new Cotisation(item);
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
                    //mMembre = new Membre(item.getMembreFx());
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
       
        //set default value of combox cotisation
        if (tmpCotisation != null) {
            cotisation.getSelectionModel().select(new CotisationFx(tmpCotisation));
        }
        //set default value of combox membre
        if (tmpMembre != null) {
            //nomMembre.setValue(tmpMembre);
            nomMembre.getSelectionModel().select(tmpMembre);
        }

        dateOperation.setValue(LocalDate.now());

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
            LocalDate localDate = dateOperation.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));

            long dateOp = Date.from(instant).getTime();

            double montantOp = Double.parseDouble(montantAttendu.getCharacters().toString());
            System.out.println("Montant = " + montantOp);

            mMembre = new Membre(nomMembre.getSelectionModel().getSelectedItem().getMembreFx());
            mCotisation = new Cotisation(cotisation.getSelectionModel().getSelectedItem());

            Transaction transaction = new Transaction();
            transaction.setMembre(mMembre);
            transaction.setCotisation(mCotisation);
            transaction.setDateOperation(dateOp);
            transaction.setMontantOperation(montantOp);

            if (tmpCotisation.getTypeCotisation() == TypeCotisation.TONTINE) {
                transaction.setType(TypeTransaction.TONTINER);
            } else if (tmpCotisation.getTypeCotisation() == TypeCotisation.EPARGNE) {
                transaction.setType(TypeTransaction.EPARGNER);
            }
            System.out.println("Transaction:" + transaction);


            Response<Transaction> response;

            if (valider.getText().equals("Valider")) {
                response = BackendInterface.createTransaction(transaction);
                if (response.getBody() != null) {
//                    pretsEtRembPanelController.getListMembreInscritsCotisation().add(new TransactionFx(response.getBody()));
                    System.out.println(mMembre.getNom() + " a tontine!");

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.initOwner(dialogStage);
                    alert.setTitle(response.getBody().getCotisation().getTypeCotisation().name());
                    alert.setHeaderText("Operation Effectuée !!");
                    alert.setContentText(mMembre.getNom());

                    alert.showAndWait();

                } else {
                    // Todo Display error message
                }
            } else {
                //pretsEtRembPanelController.getListMembreInscritsCotisation().set(keyInArray, inscriptionCotisationFx);
            }

            validerClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void actionOnClickAnnuler() {
        nomMembre.getSelectionModel().select(null);
        cotisation.getSelectionModel().select(null);
        dateOperation.setValue(null);
        montantAttendu.setText("");
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
        if (dateOperation.getValue() == null) {
            errorMessage += "Date Inscription invalide!\n";
        }

        if (montantAttendu.getText() == null || montantAttendu.getText().length() == 0 || montantAttendu.getText().length() > 9) {
            errorMessage += "Montant invalide!\n";
        } else {
            // try to parse the telephone number into an int.
            try {
                Double.parseDouble(montantAttendu.getText());
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
        return pretsEtRembPanelController;
    }

   

    public void setPretsEtRembPanelController(PretsEtRembPanelController pretsEtRembPanelController) {
        this.pretsEtRembPanelController = pretsEtRembPanelController;
    }


    public InscriptionCotisation getInscriptionCotisation() {
        return inscriptionCotisation;
    }

    public void setEffectuerCotisation(InscriptionCotisationFx inscriptionCotisationFx) {
        initialize();
        valider.setText("Modifier");
//        nomMembre.getSelectionModel().select(inscriptionCotisationFx.getMembreFx());
        cotisation.getSelectionModel().select(inscriptionCotisationFx.getCotisationFx());
        dateOperation.setValue(inscriptionCotisationFx.getDateInscrptionProperty().getValue());
        montantAttendu.setText("" + inscriptionCotisationFx.getNumeroTirage());
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
