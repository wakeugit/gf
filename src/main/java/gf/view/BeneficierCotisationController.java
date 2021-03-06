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

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class BeneficierCotisationController {


    private ObservableList<InscriptionCotisationFx> listeMembresInscrits = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeCotisation = FXCollections.observableArrayList();

    @FXML
    private ComboBox<InscriptionCotisationFx> nomMembre;
    @FXML
    private ComboBox<CotisationFx> cotisation;
    @FXML
    private DatePicker date;
    @FXML
    private TextField montantPlace;
    @FXML
    private TextField interet;
    @FXML
    private TextField montantRetenu;
    @FXML
    private Button valider;

    private int keyInArray = 0;
    private TontinePanelController tontinePanelController;
    private Stage dialogStage;
    private InscriptionCotisation inscriptionCotisation;
    private InscriptionCotisationFx inscriptionCotisationFx;
    private TransactionFx transactionFx;
    private boolean validerClicked = false;

    public static Cotisation tmpCotisation;
    private Membre mMembre;
    private Cotisation mCotisation;

    @FXML
    private void initialize() {
        if (nomMembre != null) {
            nomMembre.setButtonCell(new ListCell<InscriptionCotisationFx>() {
                @Override
                protected void updateItem(InscriptionCotisationFx item,
                                          boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getMembreFx().getNom() + " "
                                + item.getMembreFx().getPrenom());
                        mMembre = new Membre(item.getMembreFx());
                    }
                }
            });

            nomMembre.setCellFactory(new Callback<ListView<InscriptionCotisationFx>, ListCell<InscriptionCotisationFx>>() {

                @Override
                public ListCell<InscriptionCotisationFx> call(
                        ListView<InscriptionCotisationFx> arg0) {
                    ListCell<InscriptionCotisationFx> cell = new ListCell<InscriptionCotisationFx>() {
                        @Override
                        protected void updateItem(
                                InscriptionCotisationFx item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setText("");
                            } else {
                                setText(item.getMembreFx().getNom() + " "
                                        + item.getMembreFx().getPrenom());
                            }
                        }
                    };
                    return cell;
                }

            });

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
        }
        nomMembre.setItems(listeMembresInscrits);

        cotisation.setItems(listeCotisation);

        new ComboBoxAutoComplete<>(nomMembre);


    }


    public BeneficierCotisationController() {


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

//            cotisation.getSelectionModel().select(transactionFx.getCotisationFx());

            listeCotisation.add(new CotisationFx(tmpCotisation));
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
            LocalDate localDate = date.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));

            long dateOp = Date.from(instant).getTime();

            double montantOp = Double.parseDouble(montantPlace.getCharacters().toString());
            System.out.println("Montant = " + montantOp);

            Transaction transaction = new Transaction();


            System.out.println("Transaction:" + transaction);


            Response<Transaction> response;

            if (valider.getText().equals("Valider")) {

                transaction.setMembre(mMembre);
                transaction.setCotisation(mCotisation);
                transaction.setDateTransaction(dateOp);
                transaction.setMontantTransaction(Double.valueOf(montantPlace.getText()));
                transaction.setType(TypeTransaction.BENEFICIER);
                transaction.setMontantPlace(Double.valueOf(montantPlace.getText()));
                transaction.setMontantRetenu(Double.valueOf(montantRetenu.getText()));
                transaction.setTauxInteret(Double.valueOf(interet.getText()));
                transaction.setMontantInteret((Double.valueOf(montantPlace.getText()) * Double.valueOf(interet.getText())) / 100);
                transaction.setMontantBeneficie(Math.abs(Double.valueOf(montantPlace.getText()) - Double.valueOf(montantRetenu.getText())));

                response = BackendInterface.createTransaction(transaction);

            } else {

                transaction = new Transaction(transactionFx);
                transaction.setAvaliseur1(null);
                transaction.setMembre(mMembre);

                transaction.setDateTransaction(dateOp);
                transaction.setMontantTransaction(montantOp);
                transaction.setMontantPlace(Double.valueOf(montantPlace.getText()));
                transaction.setMontantRetenu(Double.valueOf(montantRetenu.getText()));
                transaction.setTauxInteret(Double.valueOf(interet.getText()));
                transaction.setMontantInteret((Double.valueOf(montantPlace.getText()) * Double.valueOf(interet.getText())) / 100);
                transaction.setMontantBeneficie(Math.abs(Double.valueOf(montantPlace.getText()) - Double.valueOf(montantRetenu.getText())));

                System.out.println("Transaction:" + transaction);

                response = BackendInterface.updateTransaction(transaction);

            }

            if (response.getBody() != null) {
//                    tontinePanelController.getListMembreInscritsCotisation().add(new TransactionFx(response.getBody()));
                System.out.println(mMembre.getNom() + " a beneficié!");

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.initOwner(dialogStage);
                alert.setTitle("Tontine");
                alert.setHeaderText("Effectué !!");
                alert.setContentText(mMembre.getNom() + " a beneficié!");

                alert.showAndWait();

            } else {
                // Todo Display error message
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
        montantPlace.setText("");
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
        if (date.getValue() == null) {
            errorMessage += "Date Inscription invalide!\n";
        }


        if (montantPlace.getText() == null || montantPlace.getText().length() == 0 || montantPlace.getText().length() > 9) {
            errorMessage += "Montant Place invalide!\n";
        } else {
            // try to parse the telephone number into an int.
            try {
                Double.parseDouble(montantPlace.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Montant Placé invalide (ne doit contenir que des chiffres)!\n";
            }
        }

        if (montantRetenu.getText() == null || montantRetenu.getText().length() == 0 || montantRetenu.getText().length() > 9) {
            errorMessage += "Montant Retenu invalide!\n";
        } else {
            // try to parse the telephone number into an int.
            try {
                Double.parseDouble(montantPlace.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Montant Retenu invalide (ne doit contenir que des chiffres)!\n";
            }
        }

        if (interet.getText() == null || interet.getText().length() == 0 || interet.getText().length() > 5) {
            errorMessage += "Interet invalide!\n";
        } else {
            // try to parse the telephone number into an int.
            try {
                Double.parseDouble(montantPlace.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Montant Retenu invalide (ne doit contenir que des chiffres)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("SVP corrigez les champs invalides");
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
//        nomMembre.getSelectionModel().select(inscriptionCotisationFx);
//        cotisation.getSelectionModel().select(inscriptionCotisationFx.getCotisationFx());
        date.setValue(inscriptionCotisationFx.getDateInscrptionProperty().getValue());
        montantPlace.setText("" + inscriptionCotisationFx.getNumeroTirage());


    }

    public void setCotisationFx(TransactionFx transactionFx) {
        initialize();

        if (transactionFx != null && transactionFx.getMontantBeneficie() > 0) {
            valider.setText("Modifier");

        }
        InscriptionCotisationFx inscrit = new InscriptionCotisationFx();
        inscrit.setMembrefx(transactionFx.getMembreFx());
        nomMembre.getSelectionModel().select(inscrit);
        cotisation.getSelectionModel().select(transactionFx.getCotisationFx());
        date.setValue(transactionFx.getDateOperation());
        montantPlace.setText("" + transactionFx.montantTransactionProperty().getValue());
        montantRetenu.setText("" + transactionFx.montantRetenuProperty().getValue());
        interet.setText("" + transactionFx.montantInteretsProperty().getValue());

        mCotisation = new Cotisation(transactionFx.getCotisationFx());
        mMembre = new Membre(transactionFx.getMembreFx());


        tmpCotisation = new Cotisation(transactionFx.getCotisationFx());
        this.transactionFx = transactionFx;
    }

    public int getKeyInArray() {
        return keyInArray;
    }

    public void setKeyInArray(int keyInArray) {
        this.keyInArray = keyInArray;
    }
}
