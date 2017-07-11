package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
import gf.util.ComboBoxAutoComplete;
import gf.util.CoreUtil;
import gf.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class FaireEmpruntController {


    public static Cotisation tmpCotisation;
    public static InscriptionCotisationFx tmpMembre;
    private  NumberFormat numberFormat;
    private ObservableList<CotisationFx> listeCotisation = FXCollections.observableArrayList();
    private ObservableList<InscriptionCotisationFx> listeMembresInscrits = FXCollections.observableArrayList();

    @FXML
    private ComboBox<InscriptionCotisationFx> nomMembre;
    @FXML
    private ComboBox<CotisationFx> cotisation;
    @FXML
    private DatePicker dateOperation;
    @FXML
    private TextField montantEmprunt;
    @FXML
    private TextField tauxInterets;
    @FXML
    private TextField montantInterets;
    @FXML
    private DatePicker dateRemb;
    @FXML
    private ComboBox<InscriptionCotisationFx> avalyseur1;
    @FXML
    private TextField avalyseur2;
    @FXML
    private Button valider;

    private int keyInArray = 0;
    private PretsEtRembPanelController pretsEtRembPanelController;
    private Stage dialogStage;
    private InscriptionCotisation inscriptionCotisation;
    private InscriptionCotisationFx inscriptionCotisationFx;
    private boolean validerClicked = false;
    private Membre mMembre;
    private Cotisation mCotisation;
    private Membre mAvalyseur = null;

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

            numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setMaximumFractionDigits(0);
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


            dateRemb.setValue(LocalDate.now());

            //new ComboBoxAutoComplete<MembreFx>(nomMembre);
            montantInterets.setEditable(false);

            if (tauxInterets != null && montantInterets != null) {
                tauxInterets.textProperty().addListener((observable, oldValue, newValue) -> {

                    if (!newValue.isEmpty()) {
                        double montantInt = Double.valueOf(tauxInterets.getText())*Double.valueOf(montantEmprunt.getText()) / 100;
                        if (montantInt < 0) {
                            valider.setDisable(true);
                            montantInterets.setText("Error");
                        } else {
                            valider.setDisable(false);
                            montantInterets.setText(numberFormat.format(montantInt));
                        }
                    }


                });
            }

        }


        avalyseur1.setButtonCell(new ListCell<InscriptionCotisationFx>() {
            @Override
            protected void updateItem(InscriptionCotisationFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText("");
                } else {
                    setText(item.getMembreFx().getNom() + " " + item.getMembreFx().getPrenom());
                    mAvalyseur = new Membre(item.getMembreFx());
                }
            }
        });

        avalyseur1.setConverter(new StringConverter<InscriptionCotisationFx>() {
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

        avalyseur1.setCellFactory(
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
        avalyseur1.setItems(listeMembresInscrits);
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
        dateRemb.setValue(LocalDate.now());

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
            LocalDate dateR = dateRemb.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));

            long dateOp = Date.from(instant).getTime();
            instant = Instant.from(dateR.atStartOfDay(ZoneId.systemDefault()));
            long dataRemb = Date.from(instant).getTime();


            double montantOp = Double.parseDouble(montantEmprunt.getCharacters().toString().trim());
            System.out.println("Montant = " + montantOp);

            mMembre = new Membre(nomMembre.getSelectionModel().getSelectedItem().getMembreFx());
            mCotisation = new Cotisation(cotisation.getSelectionModel().getSelectedItem());

            Transaction transaction = new Transaction();
            transaction.setMembre(mMembre);
            transaction.setCotisation(mCotisation);
            transaction.setDateTransaction(dateOp);
            transaction.setMontantTransaction(montantOp);
            transaction.setType(TypeTransaction.EMPRUNTER);
            transaction.setDateRemboursement(dataRemb);
            transaction.setAvaliseur1(mAvalyseur);
            transaction.setMontantInteret(Double.valueOf(CoreUtil.removeSpace(montantInterets.getText().trim())));
            transaction.setTauxInteret(Double.valueOf(tauxInterets.getText().trim()));


            System.out.println("Transaction:" + transaction);


            Response<Transaction> response;

            if (valider.getText().equals("Valider")) {
                response = BackendInterface.createTransaction(transaction);
                if (response.getBody() != null) {
//                    tontinePanelController.getListMembreInscritsCotisation().add(new TransactionFx(response.getBody()));
                    System.out.println(mMembre.getNom() + " a emprunté!");

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.initOwner(dialogStage);
                    alert.setTitle(response.getBody().getCotisation().getTypeCotisation().name());
                    alert.setHeaderText("Prêt Effectuée !!");
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
        montantEmprunt.setText("");
        dialogStage.close();
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

        if (montantEmprunt.getText() == null || montantEmprunt.getText().length() == 0 || montantEmprunt.getText().length() > 9) {
            errorMessage += "Montant invalide!\n";
        } else {
            // try to parse the telephone number into an int.
            try {
                Double.parseDouble(montantEmprunt.getText());
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

    public void setTransactionFx(TransactionFx transactionFx) {
        initialize();
        valider.setText("Modifier");
        InscriptionCotisationFx inscrit = new InscriptionCotisationFx();
        inscrit.setMembrefx(transactionFx.getMembreFx());
        nomMembre.getSelectionModel().select(inscrit);
        cotisation.getSelectionModel().select(transactionFx.getCotisationFx());
        dateOperation.setValue(transactionFx.getDateOperation());
        montantEmprunt.setText("" + transactionFx.getMontantTransaction());

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
