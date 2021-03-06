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

public class InscriptionAnnuelleController {

    public static Cotisation tmpCotisation;
    private ObservableList<MembreFx> listeMembres = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeAnnees = FXCollections.observableArrayList();

    @FXML
    private ComboBox<MembreFx> nomMembre;
    @FXML
    private ComboBox<CotisationFx> cotisation;
    @FXML
    private DatePicker dateInscription;
    @FXML
    private TextField montant;
    @FXML
    private Button valider;

    private int keyInArray = 0;
    private MembrePanelController membrePanelController;
    private InscriptionsPanelController inscriptionPanelController;
    private Stage dialogStage;
    private InscriptionAnnuelle inscriptionAnnuelle;
    private InscriptionAnnuelleFx inscriptionAnnuelleFx;
    private boolean validerClicked = false;
    private Membre mMembre;
    private Cotisation mCotisation;

    @FXML
    private void initialize() {
        System.out.println("Année: "+ tmpCotisation);
        if (cotisation != null) {
            cotisation.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getAnnee());
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
                                        setText(item.getAnnee());
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
                        return item.getAnnee();
                    }
                }

                @Override
                public CotisationFx fromString(String item) {
                    return null;
                }
            });

        }
        if (nomMembre != null) {
            nomMembre.setButtonCell(new ListCell<MembreFx>() {
                @Override
                protected void updateItem(MembreFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getNom() + " " + item.getPrenom());
                        //mMembre = new Membre(item);

                    }
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
                                        setText(item.getNom() + " " + item.getPrenom());
                                    }
                                }
                            };
                            return cell;
                        }


                    });

            cotisation.setItems(listeAnnees);
            nomMembre.setItems(listeMembres);

            if (tmpCotisation != null) {
                cotisation.getSelectionModel().select(new CotisationFx(tmpCotisation));
            }
            dateInscription.setValue(LocalDate.now());

            new ComboBoxAutoComplete<MembreFx>(nomMembre);

        }


    }

    public InscriptionAnnuelleController() {

        Response<Membre[]> response = BackendInterface.getMembres();
        if (response.getBody() != null) {
            for (Membre membre : response.getBody()) {
                listeMembres.add(new MembreFx(membre));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Membres");
        }

        Response<Cotisation[]> response1 = BackendInterface.getCotisationsByType(TypeCotisation.ANNEE);
        if (response1.getBody() != null) {
            for (Cotisation cotisation : response1.getBody()) {
                listeAnnees.add(new CotisationFx(cotisation));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Annee");
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
            mMembre = new Membre(nomMembre.getSelectionModel().getSelectedItem());
            mCotisation = new Cotisation(cotisation.getSelectionModel().getSelectedItem());
            InscriptionAnnuelle ic = new InscriptionAnnuelle(mCotisation,
                    mMembre,
                    DateUtil.format(dateInscription.getValue()),
                    Integer.parseInt(montant.getText()));

            Response<InscriptionAnnuelle> response;

            if (valider.getText().equals("Valider")) {
                response = BackendInterface.createInscriptionAnnuelle(ic);
                if (response.getBody() != null) {
                    inscriptionPanelController.getListMembreInscrits().add(new InscriptionAnnuelleFx(response.getBody()));

                } else {
                    // Todo Display error message
                }
            } else {
                ic.setId(inscriptionAnnuelleFx.getId());
                ic.setMembre(new Membre(inscriptionAnnuelleFx.getMembreFx()));
                ic.setCotisation(new Cotisation(inscriptionAnnuelleFx.getAnneeFx()));
                response = BackendInterface.updateInscriptionAnnuelle(ic);
                if (response.getBody() != null) {
                    inscriptionPanelController.getListMembreInscrits().set(keyInArray, new InscriptionAnnuelleFx(response.getBody()));
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
        this.dialogStage.close();

    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (nomMembre.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Membre invalide!\n";
        }
        if (cotisation.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Annee invalide!\n";
        }
        if (dateInscription.getValue() == null) {
            errorMessage += "Date Inscription invalide!\n";
        }

        if (montant.getText() == null || montant.getText().length() == 0) {
            errorMessage += "Montant invalide!\n";
        } else {
            // try to parse the telephone number into an int.
            try {
                if (Integer.parseInt(montant.getText()) < 0) {
                    errorMessage += "Montant doit etre une valeur positive!\n";
                }
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

    public InscriptionsPanelController getInscriptionsPanelController() {
        return inscriptionPanelController;
    }

    public void setInscriptionsPanelController(InscriptionsPanelController inscriptionPanelController) {
        this.inscriptionPanelController = inscriptionPanelController;
    }

    public InscriptionAnnuelle getInscriptionAnnuelle() {
        return inscriptionAnnuelle;
    }

    public void setInscriptionAnnuelle(InscriptionAnnuelleFx inscriptionAnnuelleFx) {
        valider.setText("Modifier");
        nomMembre.getSelectionModel().select(inscriptionAnnuelleFx.getMembreFx());
        cotisation.getSelectionModel().select(inscriptionAnnuelleFx.getAnneeFx());
        dateInscription.setValue(inscriptionAnnuelleFx.getDateInscrptionProperty().getValue());
        montant.setText("" + inscriptionAnnuelleFx.getMontant());
        this.inscriptionAnnuelleFx = inscriptionAnnuelleFx;
    }

    public int getKeyInArray() {
        return keyInArray;
    }

    public void setKeyInArray(int keyInArray) {
        this.keyInArray = keyInArray;
    }
}
