package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;

public class EpargnePanelController {

    private MainAppGF mainAppGF;
    // pour effectuer cotisation
    private ObservableList<InscriptionCotisationFx> listeInscritsCotisation = FXCollections.observableArrayList();
    private ObservableList<InscriptionCotisationFx> listeEpargnants = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeEpargnes = FXCollections.observableArrayList();

    @FXML
    private TableView<InscriptionCotisationFx> inscritsCotisationTable;
    @FXML
    private TableColumn<InscriptionCotisationFx, Long> idCol1;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> nomCol1;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> prenomCol1;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> cotisationCol1;
    @FXML
    private TableColumn<InscriptionCotisationFx, String> anneeCol1;

    // pour Etat Epargne Individuelle

    private ObservableList<TransactionFx> listeEpargneIndividuelle = FXCollections.observableArrayList();

    @FXML
    private TableView<TransactionFx> etatEpargneIndividuelle;
    @FXML
    private TableColumn<TransactionFx, Long> idCol;
    @FXML
    private TableColumn<TransactionFx, String> nomCol;
    @FXML
    private TableColumn<TransactionFx, String> prenomCol;
    @FXML
    private TableColumn<TransactionFx, LocalDate> dateCol;
    @FXML
    private TableColumn<TransactionFx, String> cotisationCol;
    @FXML
    private TableColumn<TransactionFx, String> anneeCotisationEtatIndividuel;
    @FXML
    private TableColumn<TransactionFx, Double> montantCol;
    @FXML
    private TableColumn<TransactionFx, Integer> dureeCol;
    @FXML
    private TableColumn<TransactionFx, Integer> nombreCol;


    // pour Etat Epargne General

    private ObservableList<TransactionFx> listeEpargneGenerale = FXCollections.observableArrayList();

    @FXML
    private TableView<TransactionFx> etatEpargneGenerale;

    @FXML
    private TableColumn<TransactionFx, String> nomMembre;
    @FXML
    private TableColumn<TransactionFx, String> prenomMembre;
    @FXML
    private TableColumn<TransactionFx, String> anneeEtat;
    @FXML
    private TableColumn<TransactionFx, String> nomCotisation;
    @FXML
    private TableColumn<TransactionFx, Double> totalEpargne;
    @FXML
    private TableColumn<TransactionFx, Double> totalNombre;


    // pour Suivi des Epargnes
    private ObservableList<TransactionFx> listeEpargneSuivi = FXCollections.observableArrayList();

    @FXML
    private TableView<TransactionFx> suiviEpargne;
    @FXML
    private TableColumn<TransactionFx, LocalDate> dateSuivi;
    @FXML
    private TableColumn<TransactionFx, String> cotisationSuivi;
    @FXML
    private TableColumn<TransactionFx, String> anneeSuivi;
    @FXML
    private TableColumn<TransactionFx, Double> EpargneJour;
    @FXML
    private TableColumn<TransactionFx, Integer> dureeCol2;
    @FXML
    private TableColumn<TransactionFx, Integer> nombreCol2;


    @FXML
    private ComboBox<CotisationFx> comboCotisationEpargner;

    @FXML
    private ComboBox<InscriptionCotisationFx> comboMembreEtatIndividuel;

    @FXML
    private ComboBox<CotisationFx> comboCotisationEtatIndividuel;

    @FXML
    private ComboBox<CotisationFx> comboCotisationEtatGeneral;

    @FXML
    private ComboBox<CotisationFx> comboCotisationSuivi;


    @FXML
    private Button validerEpargne;

    @FXML
    private Button validerEtatIndividuel;

    @FXML
    private Button validerEtatGeneral;

    @FXML
    private Button validerSuivi;

    private Cotisation mCotisation;
    private Membre mMembre;

    private long dateRequest;


    public EpargnePanelController() {

        Response<Cotisation[]> response = BackendInterface.getCotisationsByType(TypeCotisation.EPARGNE);
        if (response.getBody() != null) {
            for (Cotisation cotisation : response.getBody()) {
                listeEpargnes.add(new CotisationFx(cotisation));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Tontine");
        }


    }


    @FXML
    private void initialize() {
        if (comboCotisationEpargner != null) {
            comboCotisationEpargner.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mCotisation = new Cotisation(item);
                        setText(item.getnomCotisation() + " " + item.getAnnee());
                    }
                }
            });

            comboCotisationEpargner.setCellFactory(
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

            comboCotisationEpargner.setItems(listeEpargnes);
        }

        if (comboCotisationEtatIndividuel != null) {
            comboCotisationEtatIndividuel.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mCotisation = new Cotisation(item);
                        setText(item.getnomCotisation() + " " + item.getAnnee());
                    }
                }
            });

            comboCotisationEtatIndividuel.setCellFactory(
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

            comboCotisationEtatIndividuel.setItems(listeEpargnes);
        }

        if (comboMembreEtatIndividuel != null) {
            comboMembreEtatIndividuel.setButtonCell(new ListCell<InscriptionCotisationFx>() {
                @Override
                protected void updateItem(InscriptionCotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mMembre = new Membre(item.getMembreFx());
                        setText(item.getMembreFx().getNom() + " " + item.getMembreFx().getPrenom());
                    }
                }
            });


            comboMembreEtatIndividuel.setCellFactory(
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

            comboMembreEtatIndividuel.setItems(listeEpargnants);
        }

        if (comboCotisationEtatGeneral != null) {
            comboCotisationEtatGeneral.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mCotisation = new Cotisation(item);
                        setText(item.getnomCotisation() + " " + item.getAnnee());
                    }
                }
            });

            comboCotisationEtatGeneral.setCellFactory(
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

            comboCotisationEtatGeneral.setItems(listeEpargnes);
        }

        if (comboCotisationSuivi != null) {
            comboCotisationSuivi.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mCotisation = new Cotisation(item);
                        setText(item.getnomCotisation() + " " + item.getAnnee());
                    }
                }
            });

            comboCotisationSuivi.setCellFactory(
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

            comboCotisationSuivi.setItems(listeEpargnes);


        }

        if (comboMembreEtatIndividuel != null) {
            comboMembreEtatIndividuel.setButtonCell(new ListCell<InscriptionCotisationFx>() {
                @Override
                protected void updateItem(InscriptionCotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText("");
                        mMembre = new Membre(item.getMembreFx());
                        setText(item.getMembreFx().getNom() + " " + item.getMembreFx().getPrenom());
                    }
                }
            });

            comboMembreEtatIndividuel.setCellFactory(
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

            comboMembreEtatIndividuel.setItems(listeInscritsCotisation);


        }


        // Epargner cotisation.
        idCol1.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        nomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        cotisationCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeCol1.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());

        inscritsCotisationTable.setItems(listeInscritsCotisation);

        //Etat individuel epargne
        idCol.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getIdProperty().asObject());
        nomCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        cotisationCol.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        anneeCotisationEtatIndividuel.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        montantCol.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());

        etatEpargneIndividuelle.setItems(listeEpargneIndividuelle);


        nomMembre.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomMembre.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        nomCotisation.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeEtat.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        totalEpargne.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());

        etatEpargneGenerale.setItems(listeEpargneGenerale);

        dateSuivi.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        ;
        cotisationSuivi.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getNomCotisation());
        anneeSuivi.setCellValueFactory(cellData -> cellData.getValue().getCotisationFx().getAnneeProperty());
        EpargneJour.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());

        suiviEpargne.setItems(listeEpargneSuivi);

    }

    @FXML
    private void actionOnClickValiderEpargner() {
        if (mCotisation != null) {

            EffectuerCotisationController.tmpCotisation = mCotisation;

            Response<InscriptionCotisation[]> response;

            response = BackendInterface.getInscriptionCotisation(mCotisation);
            if (response.getBody() != null) {
                if (mCotisation.getTypeCotisation() == TypeCotisation.EPARGNE) {
                    listeInscritsCotisation.clear();
                    for (InscriptionCotisation inscriptionCotisation : response.getBody()) {
                        listeInscritsCotisation.add(new InscriptionCotisationFx(inscriptionCotisation));
                    }
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }
    }


    @FXML
    private void actionOnClickValiderEtatIndividuel() {
        if (mMembre != null && mCotisation != null) {

            Response<Transaction[]> response;

            response = BackendInterface.getTransactionEpargneByCotisationAndMembre(mCotisation, mMembre);
            if (response.getBody() != null) {
                listeEpargneIndividuelle.clear();
                for (Transaction transaction : response.getBody()) {
                    listeEpargneIndividuelle.add(new TransactionFx(transaction));
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }

    }

    @FXML
    private void actionOnClickValiderEtatGeneral() {
        if (mCotisation != null) {

            Response<Transaction[]> response;

            response = BackendInterface.getTransactionEpargneByCotisation(mCotisation);
            if (response.getBody() != null) {
                listeEpargneGenerale.clear();
                for (Transaction transaction : response.getBody()) {
                    listeEpargneGenerale.add(new TransactionFx(transaction));
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }
    }


    @FXML
    private void actionOnClickValiderSuivi() {
        if (mCotisation != null) {

            Response<Transaction[]> response;

            response = BackendInterface.getSuiviTransactionEpargneByCotisation(mCotisation);
            if (response.getBody() != null) {
                listeEpargneSuivi.clear();
                for (Transaction transaction : response.getBody()) {
                    listeEpargneSuivi.add(new TransactionFx(transaction));
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }
    }

    @FXML
    private void actionOnDragDroppedEtatIndividuel() {

        System.out.println("Drop Out");

        if (comboCotisationEtatIndividuel != null) {

            mCotisation = new Cotisation(comboCotisationEtatIndividuel.getValue());

            if (mCotisation != null) {
                Response<InscriptionCotisation[]> response;

                response = BackendInterface.getInscriptionCotisation(mCotisation);
                if (response.getBody() != null) {
                    if (mCotisation.getTypeCotisation() == TypeCotisation.EPARGNE) {
                        listeInscritsCotisation.clear();
                        for (InscriptionCotisation inscriptionCotisation : response.getBody()) {
                            listeInscritsCotisation.add(new InscriptionCotisationFx(inscriptionCotisation));
                        }
                    }

                } else {
                    // Todo Display error message
                    System.out.println("An error occured - ValiderCotisation");
                }

            }


        }


    }


    @FXML
    private void actionOnclickNouvelleCotisation() {
        try {
            int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                InscriptionCotisationFx incriptCotisationFx = inscritsCotisationTable.getItems().get(selectedIndex);
                EffectuerCotisationController.tmpCotisation = new Cotisation(incriptCotisationFx.getCotisationFx());
                EffectuerCotisationController.tmpMembre = incriptCotisationFx;

            }
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/effectuerCotisation.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Effectuer Cotisation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainAppGF.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Member into the controller.
            EffectuerCotisationController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEpargnePanelController(this);

            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    @FXML
    private void actionOnclickNouveauBeneficiaire() {

    }

    @FXML
    private void actionOnclickModifierCotisation() {

        int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            InscriptionCotisationFx mbreInscritFx = inscritsCotisationTable.getItems().get(selectedIndex);
            int keyInArrayList = listeInscritsCotisation.indexOf(mbreInscritFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/inscriptionCotisation.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Inscription");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                EffectuerCotisationController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setEpargnePanelController(this);
                //  controller.setInscriptionCotisation(mbreInscritFx);
                controller.setKeyInArray(keyInArrayList);


                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();

                // return controller.isOkClicked();

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainAppGF.getPrimaryStage());
            alert.setTitle("Aucune ligne selectionnee");
            alert.setHeaderText("Aucune ligne selectionnee");
            alert.setContentText("Svp selectionnez un element dans la liste.");

            alert.showAndWait();
        }
    }


    @FXML
    private void actionOnclickSupprimerCotisation() {

        int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            inscritsCotisationTable.getItems().remove(selectedIndex);
            //BackendInterface.deleteMembre(inscritsAnnuelTable.getItems().get(selectedIndex).getId());
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainAppGF.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }


    public MainAppGF getMainAppGF() {
        return mainAppGF;
    }

    public void setMainAppGF(MainAppGF mainAppGF) {
        this.mainAppGF = mainAppGF;
    }


    public ObservableList<InscriptionCotisationFx> getListMembreInscritsCotisation() {
        return listeInscritsCotisation;
    }

}
