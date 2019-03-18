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

public class AidesPanelController {

    private MainAppGF mainAppGF;

    private ObservableList<InscriptionAnnuelleFx> listeInscritsAnnuel = FXCollections.observableArrayList();
    private ObservableList<InscriptionCotisationFx> listeInscritsCotisation = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeAnnees = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeCotisations = FXCollections.observableArrayList();
    private ObservableList<OperationFx> listeOperations = FXCollections.observableArrayList();
    private ObservableList<OperationFx> listeOperationsRemb = FXCollections.observableArrayList();
    private ObservableList<ServiceFx> listeServices = FXCollections.observableArrayList();

    @FXML
    private TableView<InscriptionAnnuelleFx> inscritsAnnuelTable;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Long> idCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> nomCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> prenomCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> adresseCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Long> cniCol;

    @FXML
    private TableView<OperationFx> listeAideTable;
    @FXML
    private TableColumn<OperationFx, Long> idAide;
    @FXML
    private TableColumn<OperationFx, String> nomMembre;
    @FXML
    private TableColumn<OperationFx, String> prenomMembre;
    @FXML
    private TableColumn<OperationFx, String> motifAide;
    @FXML
    private TableColumn<OperationFx, LocalDate> dateAide;
    @FXML
    private TableColumn<OperationFx, Double> montantAide;
    @FXML
    private TableColumn<OperationFx, LocalDate> dateRemb;

    @FXML
    private TableView<InscriptionAnnuelleFx> listeMembresTable;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Long> idCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> nomCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> prenomCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> adresseCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Long> cniCol1;

    @FXML
    private TableView<OperationFx> etatAideTable;
    @FXML
    private TableColumn<OperationFx, Long> idRemb;
    @FXML
    private TableColumn<OperationFx, String> nomCol2;
    @FXML
    private TableColumn<OperationFx, String> prenomCol2;
    @FXML
    private TableColumn<OperationFx, String> aide;
    @FXML
    private TableColumn<OperationFx, Double> montantAide2;
    @FXML
    private TableColumn<OperationFx, LocalDate> dateRemb2;
    @FXML
    private TableColumn<OperationFx, Double> montantRemb;


    @FXML
    private ComboBox<CotisationFx> comboAnneeDonner;
    @FXML
    private ComboBox<CotisationFx> comboAnneeListe;
    @FXML
    private ComboBox<CotisationFx> comboAnneeRemb;
    @FXML
    private ComboBox<OperationFx> comboAideRemb;
    @FXML
    private ComboBox<CotisationFx> comboAnneeEtat;
    @FXML
    private ComboBox<OperationFx> comboAideEtat;
    @FXML
    private Button validerDonnerAide;
    @FXML
    private Button validerListeAide;
    @FXML
    private Button validerRembourserAide;
    @FXML
    private Button validerEtatAide;

    private Cotisation mCotisation;
    private Operation mOperation;

    public AidesPanelController() {


        Response<Cotisation[]> response = BackendInterface.getCotisationsByType(TypeCotisation.ANNEE);
        if (response.getBody() != null) {
            for (Cotisation cotisation : response.getBody()) {
                listeAnnees.add(new CotisationFx(cotisation));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Annee");
        }

        Response<Service[]> response1 = BackendInterface.getServiceByType(TypeService.AIDE);
        if (response1.getBody() != null) {
            listeServices.clear();
            for (Service service : response1.getBody()) {
                listeServices.add(new ServiceFx(service));
            }
        } else {
            // Todo Display error message
            System.out.println("An error occured - ValiderService");
        }

/*
        Response<Cotisation[]> response1 = BackendInterface.getCotisationsByType(TypeCotisation.TONTINE);
        Response<Cotisation[]> response2 = BackendInterface.getCotisationsByType(TypeCotisation.EPARGNE);
        if (response1.getBody() != null || response2.getBody() != null ) {
        	
        	for (Cotisation cotisation : response2.getBody()) {
                listeCotisations.add(new CotisationFx(cotisation));
            }
            for (Cotisation cotisation : response1.getBody()) {
                listeCotisations.add(new CotisationFx(cotisation));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Cotisation");
        }
        */
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns
        if (comboAnneeDonner != null) {
            comboAnneeDonner.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getAnnee());
                        mCotisation = new Cotisation(item);
                    }
                }
            });

            comboAnneeDonner.setCellFactory(
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
            comboAnneeDonner.setItems(listeAnnees);
        }
        if (comboAnneeListe != null) {
            comboAnneeListe.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getAnnee());
                        mCotisation = new Cotisation(item);
                    }
                }
            });

            comboAnneeListe.setCellFactory(
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
            comboAnneeListe.setItems(listeAnnees);
        }

        if (comboAnneeRemb != null) {
            comboAnneeRemb.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getAnnee());
                        mCotisation = new Cotisation(item);
                    }
                }
            });

            comboAnneeRemb.setCellFactory(
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
            comboAnneeRemb.setItems(listeAnnees);
        }

        if (comboAideRemb != null) {
            comboAideRemb.setButtonCell(new ListCell<OperationFx>() {
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

            comboAideRemb.setCellFactory(
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
            comboAideRemb.setItems(listeOperations);
        }

        if (comboAnneeEtat != null) {
            comboAnneeEtat.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getAnnee());
                        mCotisation = new Cotisation(item);
                    }
                }
            });

            comboAnneeEtat.setCellFactory(
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
            comboAnneeEtat.setItems(listeAnnees);

        }

        if (comboAideEtat != null) {
            comboAideEtat.setButtonCell(new ListCell<OperationFx>() {
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

            comboAideEtat.setCellFactory(
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
            comboAideEtat.setItems(listeOperations);
        }


        idCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().idProperty().asObject());
        nomCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        adresseCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().adresseProperty());
        cniCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().cniProperty().asObject());

        idCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().idProperty().asObject());
        nomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        adresseCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().adresseProperty());
        cniCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().cniProperty().asObject());

        idAide.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomMembre.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomMembre.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        motifAide.setCellValueFactory(cellData -> cellData.getValue().getServiceFx().getMotifProperty());
        dateAide.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        montantAide.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());
        dateRemb.setCellValueFactory(cellData -> cellData.getValue().dateRemboursementProperty());


        idRemb.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomCol2.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol2.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        aide.setCellValueFactory(cellData -> cellData.getValue().getOperation());
        dateRemb2.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        montantRemb.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());
        montantAide2.setCellValueFactory(cellData -> cellData.getValue().montantAttenduProperty().asObject());


        inscritsAnnuelTable.setItems(listeInscritsAnnuel);
        listeMembresTable.setItems(listeInscritsAnnuel);
        listeAideTable.setItems(listeOperations);
        //listeAideTable.setItems();
        etatAideTable.setItems(listeOperationsRemb);


    }


    @FXML
    private void actionOnClickValiderDonnerAide() {

        if (mCotisation != null) {
            Response<InscriptionAnnuelle[]> response;

            response = BackendInterface.getInscriptionAnnuelle(mCotisation);
            if (response.getBody() != null) {
                if (mCotisation.getTypeCotisation() == TypeCotisation.ANNEE) {
                    listeInscritsAnnuel.clear();
                    for (InscriptionAnnuelle inscriptionCotisation : response.getBody()) {
                        listeInscritsAnnuel.add(new InscriptionAnnuelleFx(inscriptionCotisation));
                    }
                }


            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }


    }

    @FXML
    private void actionOnClickValiderListeAide() {

        if (mCotisation != null) {
            Response<Operation[]> response;

            response = BackendInterface.getOperationsByCotisationAndType(mCotisation, TypeOperation.AIDER);
            if (response.getBody() != null) {
                listeOperations.clear();
                for (Operation operation : response.getBody()) {
                    listeOperations.add(new OperationFx(operation));
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderListeAide");
            }
        }


    }

    @FXML
    private void actionOnClickValiderRembourserAide() {

        if (mCotisation != null) {
            Response<InscriptionAnnuelle[]> response;

            response = BackendInterface.getMembresPourRemboursementAide(mCotisation, mOperation, TypeOperation.AIDER);
            if (response.getBody() != null) {
                listeInscritsAnnuel.clear();
                for (InscriptionAnnuelle inscriptionAnnuelle : response.getBody()) {
                    listeInscritsAnnuel.add(new InscriptionAnnuelleFx(inscriptionAnnuelle));
                }
                RembourserServiceController.effectif = listeInscritsAnnuel.size();
            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }
    }

    @FXML
    private void actionOnClickValiderEtatAides() {

        if (mCotisation != null) {
            Response<Operation[]> response;

            response = BackendInterface.getMembresRembourseurtAide(mCotisation, mOperation, TypeOperation.REMBOURSER_AIDE);
            if (response.getBody() != null) {
                listeOperationsRemb.clear();
                for (Operation operation : response.getBody()) {
                    listeOperationsRemb.add(new OperationFx(operation));
                }
                RembourserServiceController.effectif = listeInscritsAnnuel.size();
            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }


    }

    @FXML
    private void actionOnclickNouvelleAide() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            int selected = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();

            if (selected >= 0) {

                InscriptionAnnuelleFx tmpSelected = inscritsAnnuelTable.getItems().get(selected);

                EffectuerServiceController.tmpMembre = tmpSelected;
                EffectuerServiceController.typeService = TypeService.AIDE;

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/effectuerService.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Effectuer une aide");
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                EffectuerServiceController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setAidePanelController(this);

                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();
            } else {
                // todo alert no element selected

            }


            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void actionOnclickModifierAide() {

        int selectedIndex = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            InscriptionAnnuelleFx mbreInscritFx = inscritsAnnuelTable.getItems().get(selectedIndex);
            int keyInArrayList = listeInscritsAnnuel.indexOf(mbreInscritFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/effectuerService.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Inscription");
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                InscriptionAnnuelleController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setInscriptionAnnuelle(mbreInscritFx);
                controller.setKeyInArray(keyInArrayList);
                //controller.setInscriptionsPanelController(this);

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
    private void actionOnclickSupprimerAide() {

       /* int selectedIndex = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //// TODO: 20/04/2017 Add alert before delete element
            BackendInterface.deleteInscriptionAnnuelle(inscritsAnnuelTable.getItems().get(selectedIndex).getId());

            actionOnClickValiderAnnuelle();
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainAppGF.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }*/
    }


    @FXML
    private void actionOnclickNouveauRemboursementAide() {

        int selected = listeMembresTable.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            try {
                InscriptionAnnuelleFx annuelle = listeMembresTable.getItems().get(selected);

                RembourserServiceController.tmpMembre = annuelle;
                RembourserServiceController.tmpOperation = mOperation;

                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/rembourserService.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Nouveau Remboursement");
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                RembourserServiceController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setAidePanelController(this);

                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();

                // return controller.isOkClicked();

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            //todo alert no element selected
        }

    }

    @FXML
    private void actionOnclickModifierRemboursementAide() {

        /*int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
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
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                InscriptionCotisationController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setInscriptionCotisation(mbreInscritFx);
                controller.setKeyInArray(keyInArrayList);
                controller.setInscriptionsPanelController(this);

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
        }*/
    }


    @FXML
    private void actionOnclickSupprimerRemboursementAide() {

       /* int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //// TODO: 20/04/2017 Add alert before delete element
            BackendInterface.deleteInscriptionCotisation(inscritsCotisationTable.getItems().get(selectedIndex).getId());
            actionOnClickValiderCotisation();
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainAppGF.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }*/
    }

    public MainAppGF getMainAppGF() {
        return mainAppGF;
    }

    public void setMainAppGF(MainAppGF mainAppGF) {
        this.mainAppGF = mainAppGF;
    }

    public ObservableList<InscriptionAnnuelleFx> getListMembreInscrits() {
        return listeInscritsAnnuel;
    }

    public ObservableList<InscriptionCotisationFx> getListMembreInscritsCotisation() {
        return listeInscritsCotisation;
    }

}
